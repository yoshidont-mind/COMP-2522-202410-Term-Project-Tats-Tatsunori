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
    public static int TEXT_POSITION_X = 20;
    public static int TEXT_SIZE = 20;
    public static int TEXT_FIELD_WIDTH = 150;
    private List<Node> permanentUIComponents;
    private Pane group;
    private Pane whiteBackGround;
    private Text scoreText;
    private Text timeText;
    private Text speedText;
    private Scene scene;
    private Line line;
    public static final int MAX_X = Board.WIDTH * Block.SIZE;
    public static final int MAX_Y = Board.HEIGHT * Block.SIZE;

    /**
     * Constructs a GameView object and initializes the UI components.
     */
    public GameView() {
        this.group = new Pane();
        this.whiteBackGround = new Pane();
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

    private final void initializeUIComponents() {
        line = new Line(MAX_X, 0, MAX_X, MAX_Y);

        // set score text
        this.scoreText = new Text("Score: 0");
        scoreText.setFont(Font.font("arial", TEXT_SIZE));
        scoreText.setY(50);
        scoreText.setX(MAX_X + TEXT_POSITION_X);
        scoreText.setFill(Color.BLACK);

        // set time text
        this.timeText = new Text("Time: 0");
        timeText.setFont(Font.font("arial", TEXT_SIZE));
        timeText.setY(80);
        timeText.setX(MAX_X + TEXT_POSITION_X);
        timeText.setFill(Color.BLACK);

        // set speed text
        this.speedText = new Text("Speed: x1.00");
        speedText.setFont(Font.font("arial", TEXT_SIZE));
        speedText.setY(110);
        speedText.setX(MAX_X + TEXT_POSITION_X);
        speedText.setFill(Color.BLACK);

        group.getChildren().addAll(scoreText, line, timeText, speedText);
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

    /**
     * Sets the score text in the game view.
     * @param score the score to display
     */
    public void setScoreText(int score) {
        scoreText.setText(String.format("Score: %s", score));
    }

    /**
     * Sets the time text in the game view.
     * @param time the time to display
     */
    public void setTimeText(String time) {
        timeText.setText(String.format("Time: %s", time));
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

    /**
     * Updates the display of the board within the game view.
     * @param board the current game board to display
     */
    public void updateBoardDisplay(Board board) {
        group.getChildren().retainAll(permanentUIComponents);
        group.getChildren().addAll(scoreText);
        group.getChildren().addAll(speedText);

        for (int x = 0; x < Board.WIDTH; x++) {
            for (int y = 0; y < Board.HEIGHT; y++) {
                Block block = board.getBlocks()[x][y];
                if (block != null && block.getIsAlive()) {
                    Rectangle rectangle = block.getRectangle();
                    rectangle.setX(x * Block.SIZE);
                    rectangle.setY(y * Block.SIZE);
                    Text text = block.getText();
                    text.setX(rectangle.getX() + Block.SIZE / 2 - text.getBoundsInLocal().getWidth() / 2);
                    text.setY(rectangle.getY() + Block.SIZE / 2 + text.getBoundsInLocal().getHeight() / 4);
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
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, TEXT_SIZE));
        gameOverText.setFill(Color.RED);
        gameOverText.setX(50);
        gameOverText.setY(100);
        group.getChildren().add(gameOverText);
    }
}
