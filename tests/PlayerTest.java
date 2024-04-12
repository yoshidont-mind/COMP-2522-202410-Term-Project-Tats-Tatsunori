import main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(100); // Initialize with a test best score of 100
    }

    @Test
    void getBestScore_initialScoreSetInConstructor_returnsCorrectValue() {
        assertEquals(100, player.getBestScore(), "The getBestScore method should return the initial score set in the constructor.");
    }

    @Test
    void setBestScore_updateScore_scoreUpdatedCorrectly() {
        player.setBestScore(200); // Update the score
        assertEquals(200, player.getBestScore(), "The setBestScore method should update the score correctly.");
    }

    @Test
    void testToString() {
        String expected = "Player{bestScore=100}";
        assertEquals(expected, player.toString(), "The toString method should return the correctly formatted string.");
    }

    @Test
    void testEquals_sameObject_returnsTrue() {
        assertEquals(player, player, "The equals method should return true when the same object is passed.");
    }

    @Test
    void testEquals_differentObjectSameScore_returnsTrue() {
        Player anotherPlayer = new Player(100);
        assertEquals(player, anotherPlayer, "The equals method should return true when another player object has the same score.");
    }

    @Test
    void testEquals_differentObjectDifferentScore_returnsFalse() {
        Player anotherPlayer = new Player(200);
        assertNotEquals(player, anotherPlayer, "The equals method should return false when another player object has a different score.");
    }

    @Test
    void testHashCode_consistency_returnsSameHashCode() {
        int initialHashCode = player.hashCode();
        assertEquals(initialHashCode, player.hashCode(), "The hashCode should be consistent and return the same value on multiple calls.");
    }

    @Test
    void testHashCode_sameScoreDifferentObjects_returnsSameHashCode() {
        Player anotherPlayer = new Player(100);
        assertEquals(player.hashCode(), anotherPlayer.hashCode(), "The hashCode method should return the same hash code for two objects with the same score.");
    }
}
