package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Sets up and starts the JavaFX application.
 * It initializes the game environment, manages background music, and handles player data persistence.
 *
 * @author Tatsuya Yoshida, Tatsunori Marumo
 * @version 2024
 */
public class GameApp extends Application {

    /**
     * Path to the background music file.
     */
    public static final String BGM_PATH = "sound/tetris-theme-korobeiniki-rearranged-arr-for-music-box-184978.mp3";

    /**
     * Name of the directory where player data is stored.
     */
    public static final String DIRECTORY_NAME = "Player";

    /**
     * Path to the serialized player data file.
     */
    public static final String FILE_PATH = "Player/player";

    /**
     * Starts the primary stage of the application, setting up the game view, loading player data,
     * and initiating the game controller.
     *
     * @param primaryStage The primary window for this application.
     */
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

    /**
     * The main method to launch the application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
