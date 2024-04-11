package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameApp extends Application {

    public static String bgmPath = "sound/tetris-theme-korobeiniki-rearranged-arr-for-music-box-184978.mp3";
    public static String directoryName = "Player";
    public static String filePath = "Player/player";

    @Override
    public void start(Stage primaryStage) {
        Sound bgm = new Sound(bgmPath);
        bgm.playBgm();
        GameView gameView = new GameView();
        Session session = new Session();
        FileCreator.createDirectory(directoryName);
        Player bestPlayer = FileCreator.deserializeObject(filePath);
        int bestScore = bestPlayer != null ? bestPlayer.getBestScore() : 0;
        Player player = new Player(bestScore);
        GameController gameController = new GameController(gameView, session, player);
        gameController.startGameLoop();
        gameView.show(primaryStage);
        primaryStage.setOnCloseRequest(event -> bgm.stop());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
