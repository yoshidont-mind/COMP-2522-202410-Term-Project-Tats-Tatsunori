package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.File;

public class Sound {
    private final MediaPlayer mediaPlayer;

    Sound (String musicFilePath) {
        Media media = new Media(new File(musicFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void playBgm() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void play() {
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}
