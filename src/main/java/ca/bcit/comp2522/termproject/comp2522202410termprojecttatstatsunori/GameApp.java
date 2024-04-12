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
    public static final String FILE_PATH = "Player/player.ser";

    /**
     * Starts the primary stage of the application, setting up the game view, loading player data,
     * and initiating the game controller.
     *
     * @param primaryStage The primary window for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Load and play background music
        Sound bgm = new Sound(BGM_PATH);
        bgm.playBgm();

        // Initialize the game view and session components
        GameView gameView = new GameView();
        Session session = new Session();

        // Ensure player data directory exists; create if not
        FileCreator.createDirectory(DIRECTORY_NAME);

        // Deserialize the player's best score from file
        Player bestPlayer = FileCreator.deserializePlayer(FILE_PATH);
        int bestScore = bestPlayer != null ? bestPlayer.getBestScore() : 0;

        // Initialize player with the best score
        Player player = new Player(bestScore);

        // Set up game controller to manage game logic and interactions
        GameController gameController = new GameController(gameView, session, player);
        gameController.startGameLoop();

        // Display the primary game window
        gameView.show(primaryStage);

        // Handle application close event to stop music
        primaryStage.setOnCloseRequest(event -> bgm.stop());
    }

    /**
     * Provides a string representation of the GameApp instance, useful for debugging and logging.
     *
     * @return A string representation of the GameApp instance.
     */
    @Override
    public String toString() {
        return "GameApp{}";
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
