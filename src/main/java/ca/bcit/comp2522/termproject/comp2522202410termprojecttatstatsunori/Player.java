package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a player in the game, storing and managing player-specific data such as the best score.
 *
 * @author Tatsunori Marumo, Tatsuya Yoshida
 * @version 2024
 */
public class Player implements Serializable {
    private int bestScore;

    /**
     * Constructs a new Player with the specified initial best score.
     *
     * @param bestScore The initial best score of the player, typically retrieved from a saved state.
     */
    public Player(int bestScore) {
        this.bestScore = bestScore;
    }

    /**
     * Retrieves the best score achieved by the player.
     *
     * @return The best score as an integer.
     */
    public int getBestScore() {
        return bestScore;
    }

    /**
     * Updates the best score of the player.
     *
     * @param bestScore The new best score, which should be the highest score the player has achieved.
     */
    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    @Override
    public String toString() {
        return "Player{"
                + "bestScore=" + bestScore
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return getBestScore() == player.getBestScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBestScore());
    }
}
