package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class BackgroundMusic {
    private MediaPlayer mediaPlayer;

    public BackgroundMusic(String musicFilePath) {
        Media media = new Media(new File(musicFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void play() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.stop();
    }
}
