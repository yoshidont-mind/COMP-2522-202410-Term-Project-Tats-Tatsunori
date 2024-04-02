package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
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

    public void setScore(int score) {
        scoreText.setText(String.format("Score: %s", score));
    }

    public void setTime(String time) {
        timeText.setText(String.format("Time: %s", time));
    }

    public void show(Stage stage) {
        stage.setScene(scene);
        stage.setTitle("Numbered Tetris");
        stage.show();
    }

}
