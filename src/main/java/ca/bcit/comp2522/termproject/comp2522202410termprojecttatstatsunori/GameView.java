package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameView {
    private Pane group;
    private Text scoreText;
    private Text timeText;
    private Scene scene;
    public static final int MAX_X = Board.WIDTH * Block.SIZE;
    public static final int MAX_Y = Board.HEIGHT * Block.SIZE;

    public GameView() {
        this.group = new Pane();
        this.scene = new Scene(group, MAX_X + 150, MAX_Y);
        initializeUIComponents();
    }

    private final void initializeUIComponents() {
        Line line = new Line(MAX_X, 0, MAX_X, MAX_Y);
        this.scoreText = new Text("Score: ");
        scoreText.setStyle("-fx-font: 20 arial;");
        scoreText.setY(50);
        scoreText.setX(MAX_X + 5);

        this.timeText = new Text("Time: 0");
        timeText.setStyle("-fx-font: 20 arial;");
        timeText.setY(80);
        timeText.setX(MAX_X + 5);

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
        group.getChildren().clear();

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
