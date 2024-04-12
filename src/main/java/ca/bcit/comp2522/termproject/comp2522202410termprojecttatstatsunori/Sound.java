package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.File;
import java.util.Objects;

/**
 * Encapsulates the functionality of playing audio files in the game.
 *
 * @author Tatsunori Marumo, Tatsuya Yoshida
 * @version 2024
 */
public class Sound {
    private final MediaPlayer mediaPlayer;

    /**
     * Constructs a Sound object that initializes the media player with the specified music file.
     * @param musicFilePath the path to the music file to be played
     */
    Sound (String musicFilePath) {
        Media media = new Media(new File(musicFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    /**
     * Gets the MediaPlayer associated with this Sound object.
     * @return the MediaPlayer used for audio playback
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Plays the background music in a loop.
     */
    public void playBgm() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    /**
     * Plays the sound once.
     */
    public void play() {
        mediaPlayer.play();
    }

    /**
     * Stops the sound, preparing it to be played again from the start.
     */
    public void stop() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    @Override
    public String toString() {
        return "Sound{"
                + "mediaPlayer=" + mediaPlayer
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sound sound)) return false;
        return Objects.equals(getMediaPlayer(), sound.getMediaPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMediaPlayer());
    }
}
