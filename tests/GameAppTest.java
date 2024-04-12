import main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.nio.file.Files;
import java.nio.file.Path;

class GameAppTest {
    @Test
    void main_LaunchesApplication() {
        GameApp.main(new String[0]);
        Assertions.assertTrue(Files.exists(Path.of(GameApp.DIRECTORY_NAME)));
    }
}