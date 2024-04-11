package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.security.Key;
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
    public static double SECONDS_TO_DOUBLE_SPEED = 300.0;

    /**
     * Path to the sound file played when a block is cleared.
     */
    public static String clearBlockSoundPath = "sound/clearBlockSound.mp3";

    /**
     * Path to the sound file played when a block lands.
     */
    public static String landBlockSoundPath = "sound/landBlockSound.mp3";

    /**
     * Path to the sound file played when a block is moved.
     */
    public static String moveBlockSoundPath = "sound/moveBlockSound.mp3";

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

    private final void initializeControls() {
        gameView.getScene().setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress (KeyEvent event) {
        if (session.isGameOver()) {
            return;
        }
        Block currentBlock = session.getCurrentBlock();
        Board board = session.getBoard();
        Sound moveBlockSound = new Sound(moveBlockSoundPath);
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

            @Override
            public void handle(long now) {
                Block currentBlock = session.getCurrentBlock();
                Board board = session.getBoard();
                if (now - lastUpdate >= 1_000_000_000 / session.getGameSpeed()) {
                    if (board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.DOWN)) {
                        board.moveBlockByOne(currentBlock, Direction.DOWN);
                    } else {
                        currentBlock.setIsMoving(false);
                    }

                    // update gameSpeed (linear increase)
                    double incrementPerSecond = (2.0 - 1.0) / SECONDS_TO_DOUBLE_SPEED;
                    double secondsPerIteration = 1.0 / session.getGameSpeed();
                    double newGameSpeed = session.getGameSpeed() + incrementPerSecond * secondsPerIteration;
                    session.setGameSpeed(newGameSpeed);
                    gameView.setSpeedText(session.getGameSpeed());
                    gameView.updateBoardDisplay(board);
                    gameView.drawNextBlock(session.getNextBlock());
                    lastUpdate = now;
                    updateGame();
                }

                // update game when the bottom of current block hits another block or floor
                if (!currentBlock.getIsMoving()) {
                    new Sound(landBlockSoundPath).play();
                    int scoreToAdd = board.processEliminating(currentBlock.getXCoordinate()
                            , currentBlock.getYCoordinate());
                    if (scoreToAdd > 1) {
                        new Sound(clearBlockSoundPath).play();
                    }
                    session.addScore(scoreToAdd); // calculate score
                    gameView.setScoreText(session.getScore());
                    if (player.getBestScore() < session.getScore()) {
                        player.setBestScore(session.getScore());
                    }
                    session.createNextBlock();
                    gameView.updateBoardDisplay(board);
                    gameView.drawNextBlock(session.getNextBlock());
                    updateGame();
                }
            }
        };
        gameLoop.start();
    }

    private void updateGame() {
        if (session.isGameOver()) {
            gameLoop.stop();
            gameView.showGameOverMessage();
            if (player.getBestScore() > bestScoreBeforeGame) {
                FileCreator.serializeObject(GameApp.filePath, player);
            }
        }
    }
}
