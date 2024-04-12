package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Provides utility methods for managing file directories, serializing and deserializing objects,
 * and other operations related to fish object management.
 *
 * @author Tatsuya Yoshida, Tatsunori Marumo
 * @version 2024
 */
public final class FileCreator {
    private FileCreator() {
    }

    /**
     * Creates a directory if it does not already exist.
     *
     * @param directoryName The path of the directory to be created
     */
    public static void createDirectory(final String directoryName) {
        Path path = Path.of(directoryName);
        if (Files.exists(path)) {
            return;
        }
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            System.err.printf("An error occurred during the creation of a directory: %s\n", e.getMessage());
        }
    }

    /**
     * Serializes an instance of Player and writes it to a file at the specified file path.
     *
     * @param filePath The file path where the object will be serialized and saved
     * @param player The object to be serialized
     */
    public static void serializePlayer(final String filePath, final Player player) {
        Path path = Path.of(filePath);
        if (!Files.exists(path.getParent())) {
            System.out.printf("Could not find the file in: %s\n", filePath);
            return;
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(player);
        } catch (IOException e) {
            System.err.printf("An error occurred during serialization: %s\n", e.getMessage());
        }
    }

    /**
     * Deserializes an instance of Player from a file at the specified file path.
     *
     * @param filePath The file path from which the object will be deserialized
     * @return The deserialized Player, or null if an error occurs during deserialization
     */
    public static Player deserializePlayer(final String filePath) {
        if (!Files.exists(Path.of(filePath))) {
            System.out.printf("Could not find the file in: %s\n", filePath);
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return  (Player) in.readObject();
        } catch (IOException e) {
            System.out.println("Start new game");
        } catch (ClassNotFoundException e) {
            System.err.printf("An error occurred while loading data\n: %s", e.getMessage());
        }
        return null;
    }
}
