package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class GameController {
    private final GameView gameView;
    private final Session session;

    public GameController(GameView gameView, Session session) {
        this.gameView = gameView;
        this.session = session;
    }

    private void initializeControls() {
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
    }

}
