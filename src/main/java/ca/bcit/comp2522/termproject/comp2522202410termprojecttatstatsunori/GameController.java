package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;

public class GameController {
    public static double SECONDS_TO_DOUBLE_SPEED = 300.0;
    private final GameView gameView;
    private final Session session;
    private AnimationTimer gameLoop;

    public GameController(GameView gameView, Session session) {
        this.gameView = gameView;
        this.session = session;
        initializeControls();
    }

    private final void initializeControls() {
        gameView.getScene().setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress (KeyEvent event) {
        Block currentBlock = session.getCurrentBlock();
        Board board = session.getBoard();
        switch (event.getCode()) {
            case RIGHT -> board.moveBlockByOne(currentBlock, Direction.RIGHT);
            case LEFT -> board.moveBlockByOne(currentBlock, Direction.LEFT);
            case DOWN -> board.moveBlockByOne(currentBlock, Direction.DOWN);
        }
        gameView.updateBoardDisplay(board);
    }

    public void startGameLoop() {
        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1_000_000_000 / session.getGameSpeed()) {
                    Block currentBlock = session.getCurrentBlock();
                    Board board = session.getBoard();
                    if (board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.DOWN)) {
                        board.moveBlockByOne(currentBlock, Direction.DOWN);
                    } else {
                        int scoreToAdd = board.processEliminating(currentBlock.getXCoordinate()
                                , currentBlock.getYCoordinate());
                        session.addScore(scoreToAdd);
                        gameView.setScoreText(session.getScore());
                        gameView.setSpeedText(session.getGameSpeed());
                        session.createNextBlock();
                    }
                    gameView.updateBoardDisplay(board);
                    lastUpdate = now;
                    updateGame();

                    // update gameSpeed (linear increase)
                    double incrementPerSecond = (2.0 - 1.0) / SECONDS_TO_DOUBLE_SPEED;
                    double secondsPerIteration = 1.0 / session.getGameSpeed();
                    double newGameSpeed = session.getGameSpeed() + incrementPerSecond * secondsPerIteration;
                    session.setGameSpeed(newGameSpeed);
                }
            }
        };
        gameLoop.start();
    }

    private void updateGame() {
        if (session.isGameOver()) {
            gameLoop.stop();
            gameView.showGameOverMessage();
        }
    }
}
