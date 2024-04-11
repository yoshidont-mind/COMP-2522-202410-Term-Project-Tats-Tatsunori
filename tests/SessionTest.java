import main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {
    private Session session;

    @BeforeEach
    void setUp() {
        session = new Session();
    }

    @Test
    void testInitialization() {
        assertNotNull(session.getStartTime());
        assertEquals(0, session.getScore());
        assertFalse(session.getFinished());
        assertEquals(1.0, session.getGameSpeed(), 0.01);
        assertNotNull(session.getBoard());
        assertNotNull(session.getCurrentBlock());
        assertFalse(session.getIsPaused());
    }

    @Test
    void testSetScore() {
        session.setScore(100);
        assertEquals(100, session.getScore());
    }

    @Test
    void testSetFinishedTrue() {
        session.setFinished(true);
        assertTrue(session.getFinished());
    }

    @Test
    void testSetFinishedFalse() {
        session.setFinished(false);
        assertFalse(session.getFinished());
    }

    @Test
    void testSetGameSpeed() {
        session.setGameSpeed(1.0);
        assertEquals(1.0, session.getGameSpeed(), 0.01);
    }

    @Test
    void testAddScore() {
        session.addScore(10);
        assertEquals(10, session.getScore());
        session.addScore(90);
        assertEquals(100, session.getScore());
    }
}
