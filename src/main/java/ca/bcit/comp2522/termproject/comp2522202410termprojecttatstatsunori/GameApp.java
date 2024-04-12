package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main application class for the game, responsible for initializing and starting the game.
 * This class sets up the game environment, including the background music, game view, session, and player data management.
 * It uses JavaFX for the graphical user interface, making it crucial for launching and displaying the game.
 *
 * @author The application developer
 */
public class GameApp extends Application {

    public static final String BGM_PATH = "sound/tetris-theme-korobeiniki-rearranged-arr-for-music-box-184978.mp3";
    public static final String DIRECTORY_NAME = "Player";
    public static final String FILE_PATH = "Player/player";

    @Override
    public void start(Stage primaryStage) {
        Sound bgm = new Sound(BGM_PATH);
        bgm.playBgm();
        GameView gameView = new GameView();
        Session session = new Session();
        FileCreator.createDirectory(DIRECTORY_NAME);
        Player bestPlayer = FileCreator.deserializePlayer(FILE_PATH);
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
