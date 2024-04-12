package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

/**
 * Handles the game logic and user interactions in the game, manages the game loop, processes key presses,
 * and updates the game state.
 *
 * @author Tatsunori Marumo, Tatsuya Yoshida
 *
 * @version 2024
 */
public class GameController {
    /**
     * The duration in seconds after which the game speed doubles.
     */
    public static final double SECONDS_TO_DOUBLE_SPEED = 300.0;

    /**
     * Path to the sound file played when a block is cleared.
     */
    public static final String CLEAR_BLOCK_SOUND_PATH = "sound/clearBlockSound.mp3";

    /**
     * Path to the sound file played when a block lands.
     */
    public static final String LAND_BLOCK_SOUND_PATH = "sound/landBlockSound.mp3";

    /**
     * Path to the sound file played when a block is moved.
     */
    public static final String MOVE_BLOCK_SOUND_PATH = "sound/moveBlockSound.mp3";

    private static final int NANOSECOND_PER_SECOND = 1_000_000_000;

    private final GameView gameView;
    private final Session session;
    private final Player player;
    private final int bestScoreBeforeGame;
    private AnimationTimer gameLoop;

    /**
     * Constructs a GameController with the specified GameView and Session.
     * @param gameView the view of the game
     * @param session the current game session
     */
    public GameController(GameView gameView, Session session, Player player) {
        this.gameView = gameView;
        this.session = session;
        this.player = player;
        this.bestScoreBeforeGame = player.getBestScore();
        initializeControls();
        gameView.setBestScoreText(player.getBestScore());
    }

    private void initializeControls() {
        gameView.getScene().setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress (KeyEvent event) {
        if (session.getIsGameOver() || session.getIsPaused()) {
            if (event.getCode() == KeyCode.SPACE) {
                session.setPaused();
            }
            return;
        }
        Block currentBlock = session.getCurrentBlock();
        Board board = session.getBoard();
        Sound moveBlockSound = new Sound(MOVE_BLOCK_SOUND_PATH);
        switch (event.getCode()) {
            case RIGHT -> board.moveBlockByOne(currentBlock, Direction.RIGHT);
            case LEFT -> board.moveBlockByOne(currentBlock, Direction.LEFT);
            case DOWN -> board.moveBlockByOne(currentBlock, Direction.DOWN);
            case UP -> {
                while (board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.DOWN)) {
                    board.moveBlockByOne(currentBlock, Direction.DOWN);
                }
            }
            case SPACE -> session.setPaused();
        }
        if (!board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.DOWN)
        && (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN)) {
            currentBlock.setIsMoving(false);
        }
        moveBlockSound.play();
        gameView.updateBoardDisplay(board);
        gameView.drawNextBlock(session.getNextBlock());
    }

    /**
     * Starts the main game loop, updating game state and rendering at a rate determined by the game speed.
     */
    public void startGameLoop() {
        gameLoop = new AnimationTimer() {

            private long lastUpdate = 0;

            /**
             * Handles the game logic at each frame of the animation timer. This method is called in each frame
             *
             * @param now The current timestamp of the frame in nanoseconds.
             */
            @Override
            public void handle(long now) {
                // do nothing while pausing
                if (session.getIsPaused()) {
                    return;
                }

                Block currentBlock = session.getCurrentBlock();
                Board board = session.getBoard();

                // Perform game updates at intervals based on the game speed
                if (now - lastUpdate >= NANOSECOND_PER_SECOND / session.getGameSpeed()) {
                    // Attempt to move the current block down; if it cannot move, the block has landed
                    if (board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.DOWN)) {
                        board.moveBlockByOne(currentBlock, Direction.DOWN);
                    } else {
                        currentBlock.setIsMoving(false);
                    }

                    // update gameSpeed (linear increase)
                    double incrementPerSecond = (double) 1 / SECONDS_TO_DOUBLE_SPEED;
                    double secondsPerIteration = (double) 1 / session.getGameSpeed();
                    double newGameSpeed = session.getGameSpeed() + incrementPerSecond * secondsPerIteration;
                    session.setGameSpeed(newGameSpeed);

                    // Update the game view to reflect the new state
                    gameView.setSpeedText(session.getGameSpeed());
                    gameView.updateBoardDisplay(board);
                    gameView.drawNextBlock(session.getNextBlock());

                    lastUpdate = now;
                }

                // update game when the bottom of current block hits another block or floor
                if (!currentBlock.getIsMoving()) {
                    new Sound(LAND_BLOCK_SOUND_PATH).play();
                    int scoreToAdd = board.processEliminating(currentBlock.getXCoordinate()
                            , currentBlock.getYCoordinate());
                    if (scoreToAdd > 1) {
                        new Sound(CLEAR_BLOCK_SOUND_PATH).play();
                    }
                    session.addScore(scoreToAdd); // calculate score
                    gameView.setScoreText(session.getScore());
                    if (player.getBestScore() < session.getScore()) {
                        player.setBestScore(session.getScore());
                    }
                    session.createNextBlock();
                    gameView.updateBoardDisplay(board);
                    gameView.drawNextBlock(session.getNextBlock());
                    endGame();
                }
            }
        };
        gameLoop.start();
    }

    private void endGame() {
        if (session.getIsGameOver()) {
            gameLoop.stop();
            gameView.showGameOverMessage();
            if (player.getBestScore() > bestScoreBeforeGame) {
                FileCreator.serializePlayer(GameApp.FILE_PATH, player);
            }
        }
    }

    @Override
    public String toString() {
        return "GameController{"
                + "gameView=" + gameView
                + ", session=" + session
                + ", player=" + player
                + ", bestScoreBeforeGame=" + bestScoreBeforeGame
                + ", gameLoop=" + gameLoop
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameController that)) return false;
        return bestScoreBeforeGame == that.bestScoreBeforeGame && Objects.equals(gameView, that.gameView) && Objects.equals(session, that.session) && Objects.equals(player, that.player) && Objects.equals(gameLoop, that.gameLoop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameView, session, player, bestScoreBeforeGame, gameLoop);
    }
}
