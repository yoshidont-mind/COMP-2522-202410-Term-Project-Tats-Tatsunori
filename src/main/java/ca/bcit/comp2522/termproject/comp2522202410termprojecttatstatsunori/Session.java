package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Session extends Application {
    /* instance variables */

    public static final int SIZE = 25;
    public static final int MAX_X = Board.WIDTH * SIZE;

    public static final int MAX_Y = Board.HEIGHT * SIZE;
    private final LocalDateTime startTime;
    private int score;

    private Text scoreText;
    private Text timeText;
    private boolean isFinished;
    private double gameSpeed;
    private final Board board;
    private Scene scene;
    private Pane group;
    private Timer fallTimer;

    /* constructors */
    /**
     * Constructs an instance of Session without accepting parameters.
     */
    public Session() {
        this.startTime = LocalDateTime.now();
        this.score = 0;
        this.isFinished = false;
        this.gameSpeed = 1.0;
        this.board = new Board();
        this.group = new Pane();
        this.scene = new Scene(group, MAX_X + 150, MAX_Y);
    }

    /* getters */

    /**
     * Returns startTime of this Session.
     *
     * @return a LocalDateTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns score of this Session
     *
     * @return an int
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns whether this Session has finished
     *
     * @return a boolean
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Returns gameSpeed of this Session.
     *
     * @return a double
     */
    public double getGameSpeed() {
        return gameSpeed;
    }

    /**
     * Returns board of this Session.
     *
     * @return a Board
     */
    public Board getBoard() {
        return board;
    }

    /* setters */

    /**
     * Sets score of this Session to given value.
     *
     * @param score an int
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets isFinished of this Session to given value.
     *
     * @param isFinished a boolean
     */
    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * Sets gameSpeed of this Session to given value.
     *
     * @param gameSpeed a double
     */
    public void setGameSpeed(double gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    /* general purpose methods */
    public void updateDisplay() {
        scoreText.setText("Score: " + score);
        Duration duration = Duration.between(startTime, LocalDateTime.now());
       // timeText.setText("Time: " + formatDuration(duration));
    }

    private void initializeUIComponents(Pane group) {
        Line line = new Line(MAX_X, 0, MAX_X, MAX_Y);
        this.scoreText = new Text("Score: ");
        scoreText.setStyle("-fx-font: 20 arials;");
        scoreText.setY(50);
        scoreText.setX(MAX_X + 5);

        timeText = new Text("Time: 0");
        timeText.setStyle("-fx-font: 20 arial;");
        timeText.setY(80);
        timeText.setX(MAX_X + 5);

        group.getChildren().addAll(scoreText, line, timeText);
    }


    private void moveOnKeyPress (Block block) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case RIGHT -> board.moveBlockByOne(block, Direction.RIGHT);
                    case LEFT -> board.moveBlockByOne(block, Direction.LEFT);
                    case UP -> board.moveBlockByOne(block, Direction.UP);
                    case DOWN -> board.moveBlockByOne(block, Direction.DOWN);
                }
            }
        });
    }

    public void start(Stage stage) {
        group = new Pane();
        Scene scene = new Scene(group, MAX_X + 150, MAX_Y);

        Block currentBlock = Block.createBlock();
        board.placeBlock(currentBlock, Board.WIDTH / 2, 0);
        board.drawBoard(group);

        stage.setScene(scene);
        stage.setTitle("Numbered Tetris");
        stage.show();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (!isFinished) {
                    gameLoop(currentBlock);
                } else {
                    this.stop();
                }
            }
        }.start();
    }

    private void gameLoop(Block currentBlock) {
        if (!board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.DOWN)) {
            currentBlock = Block.createBlock();
            board.placeBlock(currentBlock, Board.WIDTH / 2, 0);
        } else {
            moveOnKeyPress(currentBlock);
        }

        if (!board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.DOWN)
                && !board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.UP)) {
            isFinished = true;
        }

        board.drawBoard(group);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
