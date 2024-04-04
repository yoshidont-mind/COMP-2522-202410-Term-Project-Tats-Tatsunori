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
    private List<Node> permanentUIComponents;
    private Pane group;
    private Pane whiteBackGround;
    private Text scoreText;
    private Text timeText;
    private Scene scene;
    private Line line;
    public static final int MAX_X = Board.WIDTH * Block.SIZE;
    public static final int MAX_Y = Board.HEIGHT * Block.SIZE;

    public GameView() {
        this.group = new Pane();
        this.whiteBackGround = new Pane();
        this.permanentUIComponents = new ArrayList<>();
        group.setStyle("-fx-background-color: black");
        whiteBackGround.setStyle("-fx-background-color: white");
        whiteBackGround.setPrefSize(150, MAX_Y);
        whiteBackGround.setTranslateX(MAX_X);
        whiteBackGround.setTranslateY(0);
        group.getChildren().add(whiteBackGround);
        permanentUIComponents.add(whiteBackGround);
        this.scene = new Scene(group, MAX_X + 150, MAX_Y);
        initializeUIComponents();
    }

    private final void initializeUIComponents() {
        line = new Line(MAX_X, 0, MAX_X, MAX_Y);
        this.scoreText = new Text("Score: 0");
        scoreText.setStyle("-fx-font: 20 arial;");
        scoreText.setY(50);
        scoreText.setX(MAX_X + 10);
        scoreText.setFill(Color.BLACK);

        this.timeText = new Text("Time: 0");
        timeText.setStyle("-fx-font: 20 arial;");
        timeText.setY(80);
        timeText.setX(MAX_X + 10);
        timeText.setFill(Color.BLACK);

        group.getChildren().addAll(scoreText, line, timeText);
    }

    public Scene getScene() {
        return scene;
    }

    public Pane getGroup() {
        return group;
    }

    public void setScoreText(int score) {
        scoreText.setText(String.format("Score: %s", score));
        scoreText.setStyle("-fx-font: 20 arial;");
        scoreText.setY(50);
        scoreText.setX(MAX_X + 10);
        scoreText.setFill(Color.BLACK);
    }

    public void setTimeText(String time) {
        timeText.setText(String.format("Time: %s", time));
    }

    public void show(Stage stage) {
        stage.setScene(scene);
        stage.setTitle("Numbered Tetris");
        stage.show();
    }

    public void updateBoardDisplay(Board board) {
        group.getChildren().retainAll(permanentUIComponents);
        group.getChildren().addAll(scoreText);

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
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gameOverText.setFill(Color.RED);
        gameOverText.setX(50);
        gameOverText.setY(100);
        group.getChildren().add(gameOverText);
    }
}
