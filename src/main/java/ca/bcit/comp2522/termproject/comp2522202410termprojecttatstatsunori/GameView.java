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

public class GameView {
    public static int TEXT_POSITION_X = 20;
    public static int TEXT_SIZE = 25;
    public static int SCORE_TEXT_POSITION_Y = 100;
    public static int GAME_SPEED_TEXT_POSITION_Y = 150;
    public static int TEXT_FIELD_WIDTH = 150;
    private List<Node> permanentUIComponents;
    private Pane group;
    private Pane whiteBackGround;
    private Text scoreText;
    private Text gameSpeedText;
    private Scene scene;
    public static final int MAX_X = Board.WIDTH * Block.SIZE;
    public static final int MAX_Y = Board.HEIGHT * Block.SIZE;

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
        this.scoreText = new Text("Score: 0");
        scoreText.setFont(Font.font("arial", TEXT_SIZE));
        scoreText.setY(SCORE_TEXT_POSITION_Y);
        scoreText.setX(MAX_X + TEXT_POSITION_X);
        scoreText.setFill(Color.BLACK);

        this.gameSpeedText = new Text("Game speed: 1.0");
        gameSpeedText.setFont(Font.font("arial", TEXT_SIZE));
        gameSpeedText.setY(GAME_SPEED_TEXT_POSITION_Y);
        gameSpeedText.setX(MAX_X + TEXT_POSITION_X);
        gameSpeedText.setFill(Color.BLACK);

        group.getChildren().addAll(scoreText, gameSpeedText);
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

    public Scene getScene() {
        return scene;
    }

    public Pane getGroup() {
        return group;
    }

    public void setScoreText(int score) {
        scoreText.setText(String.format("Score: %s", score));
        scoreText.setFont(Font.font("arial", TEXT_SIZE));
        scoreText.setY(SCORE_TEXT_POSITION_Y);
        scoreText.setX(MAX_X + TEXT_POSITION_X);
        scoreText.setFill(Color.BLACK);
    }

    public void setTimeText(double gameSpeed) {
        gameSpeedText.setText(String.format("Game speed: %s", gameSpeed));
        gameSpeedText.setFont(Font.font("arial", TEXT_SIZE));
        gameSpeedText.setY(GAME_SPEED_TEXT_POSITION_Y);
        gameSpeedText.setX(MAX_X + TEXT_POSITION_X);
        gameSpeedText.setFill(Color.BLACK);
    }

    public void show(Stage stage) {
        stage.setScene(scene);
        stage.setTitle("Numbered Tetris");
        stage.show();
    }

    public void updateBoardDisplay(Board board) {
        group.getChildren().retainAll(permanentUIComponents);
        group.getChildren().addAll(scoreText, gameSpeedText);

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

    public void showGameOverMessage() {
        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, TEXT_SIZE));
        gameOverText.setFill(Color.RED);
        gameOverText.setX(50);
        gameOverText.setY(100);
        group.getChildren().add(gameOverText);
    }
}
