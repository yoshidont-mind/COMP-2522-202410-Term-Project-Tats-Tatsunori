package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import java.time.LocalDateTime;

public class Session {
    /* instance variables */
    private LocalDateTime startTime;
    private int score;
    private boolean isFinished;
    private double gameSpeed;
    private Board board;

    /* constructors */

    /**
     * Constructs an instance of Session without accepting parameters.
     */
    public Session() {
        this.startTime = LocalDateTime.now();
        this.score = 0;
        this.isFinished = false;
        this.gameSpeed = 1.0;
        this.board = new Board();
    }
}
