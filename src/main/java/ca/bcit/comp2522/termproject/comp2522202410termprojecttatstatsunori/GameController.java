package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;

public class GameController {
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
            case UP -> board.moveBlockByOne(currentBlock, Direction.UP);
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
                    if (board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate() + 1, Direction.DOWN)) {
                        board.moveBlockByOne(currentBlock, Direction.DOWN);
                    } else {
                        session.nextBlock();
                    }
                    gameView.updateBoardDisplay(board);
                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();
    }
}
