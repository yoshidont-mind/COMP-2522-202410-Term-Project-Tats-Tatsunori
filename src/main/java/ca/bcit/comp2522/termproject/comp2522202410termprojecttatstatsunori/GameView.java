package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the graphics of the game.
 * It handles the rendering of game components like the grid, score, time, and speed,
 * as well as updating the display during gameplay.
 *
 * @author Tatsunori Marumo, Tatsuya Yoshida
 * @version 2024
 */
public class GameView {
    public static final int TEXT_POSITION_X = 30;
    public static final int TEXT_SIZE = 20;
    public static final int TEXT_FIELD_WIDTH = 200;
    public static final int NEXT_BLOCK_X = 50;
    public static final int NEXT_BLOCK_Y = 70;
    public static final int NEXT_BLOCK_TEXT_Y = 50;
    public static final int BEST_SCORE_TEXT_Y = 200;
    public static final int SCORE_TEXT_Y = 250;
    public static final int GAME_SPEED_Y = 300;
    public static final int GAME_OVER_MESSAGE_X = 100;
    public static final int GAME_OVER_MESSAGE_Y = 150;
    private final List<Node> permanentUIComponents;
    private final Pane group;
    private Text nextBlockText;
    private Text scoreText;
    private Text bestScoreText;
    private Text speedText;
    private final Scene scene;
    public static final int MAX_X = Board.WIDTH * Block.SIZE;
    public static final int MAX_Y = Board.HEIGHT * Block.SIZE;

    /**
     * Constructs a GameView object and initializes the UI components.
     */
    public GameView() {
        this.group = new Pane();
        Pane whiteBackGround = new Pane();
        this.permanentUIComponents = new ArrayList<>();
        group.setStyle("-fx-background-color: black");
        whiteBackGround.setStyle("-fx-background-color: white");
        whiteBackGround.setPrefSize(TEXT_FIELD_WIDTH, MAX_Y);
        whiteBackGround.setTranslateX(MAX_X);
        whiteBackGround.setTranslateY(0);
        group.getChildren().add(whiteBackGround);
        permanentUIComponents.add(whiteBackGround);
        this.scene = new Scene(group, MAX_X + TEXT_FIELD_WIDTH, MAX_Y);
        initializeUIComponents();
    }

    private void initializeUIComponents() {

        // set next block text
        this.nextBlockText = new Text("Next Block");
        nextBlockText.setFont(Font.font("arial", TEXT_SIZE));
        nextBlockText.setY(NEXT_BLOCK_TEXT_Y);
        nextBlockText.setX(MAX_X + TEXT_POSITION_X);
        nextBlockText.setFill(Color.BLACK);

        // set best score text
        this.bestScoreText = new Text("Best Score: ");
        bestScoreText.setFont(Font.font("arial", TEXT_SIZE));
        bestScoreText.setY(BEST_SCORE_TEXT_Y);
        bestScoreText.setX(MAX_X + TEXT_POSITION_X);
        bestScoreText.setFill(Color.BLACK);

        // set score text
        this.scoreText = new Text("Score: 0");
        scoreText.setFont(Font.font("arial", TEXT_SIZE));
        scoreText.setY(SCORE_TEXT_Y);
        scoreText.setX(MAX_X + TEXT_POSITION_X);
        scoreText.setFill(Color.BLACK);

        // set speed text
        this.speedText = new Text("Speed: x1.00");
        speedText.setFont(Font.font("arial", TEXT_SIZE));
        speedText.setY(GAME_SPEED_Y);
        speedText.setX(MAX_X + TEXT_POSITION_X);
        speedText.setFill(Color.BLACK);

        group.getChildren().addAll(nextBlockText, bestScoreText, scoreText, speedText);
        drawGrid();
    }

    private void drawGrid() {
        for (int x = 0; x <= Board.WIDTH; x++) {
            Line verticalLine = new Line(x * Block.SIZE, 0, x * Block.SIZE, MAX_Y);
            verticalLine.setStroke(Color.GRAY);
            permanentUIComponents.add(verticalLine);
            group.getChildren().add(verticalLine);
        }
        for (int y = 0; y <= Board.HEIGHT; y++) {
            Line horizontalLine = new Line(0, y * Block.SIZE, MAX_X, y * Block.SIZE);
            horizontalLine.setStroke(Color.GRAY);
            permanentUIComponents.add(horizontalLine);
            group.getChildren().add(horizontalLine);
        }
    }

    /**
     * Gets the Scene object of this game view.
     * @return the scene of this game view
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets the Group object that contains all UI elements of this game view.
     * @return the group containing UI elements
     */
    public Pane getGroup() {
        return group;
    }

    public void setBestScoreText(int bestScore) {
        bestScoreText.setText(String.format("Best Score: %s", bestScore));
    }

    /**
     * Sets the score text in the game view.
     * @param score the score to display
     */
    public void setScoreText(int score) {
        scoreText.setText(String.format("Score: %s", score));
    }

    /**
     * Sets the speed text in the game view.
     * @param speed the speed factor to display
     */
    public void setSpeedText(double speed) {
        speedText.setText(String.format("Speed: x%.2f", speed));
    }

    /**
     * Initializes the stage and shows the game view.
     * @param stage the primary stage of the application
     */
    public void show(Stage stage) {
        stage.setScene(scene);
        stage.setTitle("Numbered Tetris");
        stage.show();
    }

    public void drawNextBlock(Block nextBlock) {
        Rectangle rectangle = nextBlock.getRectangle();
        rectangle.setX(MAX_X + NEXT_BLOCK_X);
        rectangle.setY(NEXT_BLOCK_Y);
        Text text = nextBlock.getText();
        nextBlock.updateTextPosition();
        group.getChildren().addAll(rectangle, text);
    }

    /**
     * Updates the display of the board within the game view.
     * @param board the current game board to display
     */
    public void updateBoardDisplay(Board board) {
        group.getChildren().retainAll(permanentUIComponents);
        group.getChildren().addAll(nextBlockText, bestScoreText, scoreText, speedText);

        for (int x = 0; x < Board.WIDTH; x++) {
            for (int y = 0; y < Board.HEIGHT; y++) {
                Block block = board.getBlocks()[x][y];
                if (block != null && block.getIsAlive()) {
                    Rectangle rectangle = block.getRectangle();
                    rectangle.setX(x * Block.SIZE);
                    rectangle.setY(y * Block.SIZE);
                    Text text = block.getText();
                    block.updateTextPosition();
                    group.getChildren().addAll(rectangle, text);
                }
            }
        }
    }

    /**
     * Displays the game over message.
     */
    public void showGameOverMessage() {
        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, TEXT_SIZE * 2));
        gameOverText.setFill(Color.RED);
        gameOverText.setX(GAME_OVER_MESSAGE_X);
        gameOverText.setY(GAME_OVER_MESSAGE_Y);
        group.getChildren().add(gameOverText);
    }
}
