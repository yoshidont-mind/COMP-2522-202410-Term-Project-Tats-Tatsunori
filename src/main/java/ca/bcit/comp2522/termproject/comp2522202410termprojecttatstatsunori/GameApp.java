package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameApp extends Application {

    public static String bgmPath = "sound/tetris-theme-korobeiniki-rearranged-arr-for-music-box-184978.mp3";

    @Override
    public void start(Stage primaryStage) {
        BackgroundMusic bgm = new BackgroundMusic(bgmPath);
        bgm.play();
        GameView gameView = new GameView();
        Session session = new Session();
        GameController gameController = new GameController(gameView, session);
        gameController.startGameLoop();
        gameView.show(primaryStage);
        primaryStage.setOnCloseRequest(event -> bgm.stop());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
