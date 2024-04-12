import main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori.FileCreator;
import main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;

class FileCreatorTest {

    @TempDir
    Path tempDir;

    @Test
    void createDirectory_DirectoryDoesNotExist_DirectoryIsCreated() {
        String directoryName = tempDir.resolve("newDirectory").toString();
        FileCreator.createDirectory(directoryName);
        Assertions.assertTrue(Files.exists(Path.of(directoryName)));
    }

    @Test
    void createDirectory_DirectoryAlreadyExists_NoActionTaken() {
        String directoryName = tempDir.toString();
        FileCreator.createDirectory(directoryName);
        Assertions.assertTrue(Files.exists(Path.of(directoryName)));
    }

    @Test
    void serializePlayer_FilePathIsValid_PlayerIsSerialized() {
        String filePath = tempDir.resolve("player.ser").toString();
        Player player = new Player(100);
        FileCreator.serializePlayer(filePath, player);
        Assertions.assertTrue(Files.exists(Path.of(filePath)));
    }

    @Test
    void serializePlayer_FilePathIsInvalid_NoErrorThrown() {
        String filePath = tempDir.resolve("non-existent-directory/player.ser").toString();
        Player player = new Player(100);
        FileCreator.serializePlayer(filePath, player);
        Assertions.assertFalse(Files.exists(Path.of(filePath)));
    }

    @Test
    void deserializePlayer_FileExists_PlayerIsDeserialized() {
        String filePath = tempDir.resolve("player.ser").toString();
        Player player = new Player(100);
        FileCreator.serializePlayer(filePath, player);
        Player deserializedPlayer = FileCreator.deserializePlayer(filePath);
        Assertions.assertNotNull(deserializedPlayer);
        Assertions.assertEquals(player.getBestScore(), deserializedPlayer.getBestScore());
    }

    @Test
    void deserializePlayer_FileDoesNotExist_ReturnNull() {
        String filePath = tempDir.resolve("non-existent-file.ser").toString();
        Player deserializedPlayer = FileCreator.deserializePlayer(filePath);
        Assertions.assertNull(deserializedPlayer);
    }
}