package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import java.io.Serializable;

public class Player implements Serializable {
    private int bestScore;

    public Player(int bestScore) {
        this.bestScore = bestScore;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
