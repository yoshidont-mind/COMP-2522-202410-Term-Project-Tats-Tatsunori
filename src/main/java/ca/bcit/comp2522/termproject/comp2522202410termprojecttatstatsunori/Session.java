package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import java.time.LocalDateTime;
import java.util.Random;


public class Session {
    /* instance variables */
    private final LocalDateTime startTime;
    private int score;
    private boolean isFinished;
    private double gameSpeed;
    private final Board board;
    private Block currentBlock;
    private Block nextBlock;
    private boolean isPaused;

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
        this.currentBlock = Block.createBlock();
        this.nextBlock = Block.createBlock();
        this.isPaused = false;
    }

    /* getters */

    /**
     * Returns startTime of this Session.
     *
     * @return a LocalDateTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns score of this Session
     *
     * @return an int
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns whether this Session has finished
     *
     * @return a boolean
     */
    public boolean getFinished() {
        return isFinished;
    }

    /**
     * Returns gameSpeed of this Session.
     *
     * @return a double
     */
    public double getGameSpeed() {
        return gameSpeed;
    }

    /**
     * Returns board of this Session.
     *
     * @return a Board
     */
    public Board getBoard() {
        return board;
    }

    public Block getCurrentBlock() {
        return this.currentBlock;
    }

    public Block getNextBlock() {
        return this.nextBlock;
    }

    public boolean getIsPaused() {
        return isPaused;
    }

    /* setters */

    /**
     * Sets score of this Session to given value.
     *
     * @param score an int
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets isFinished of this Session to given value.
     *
     * @param isFinished a boolean
     */
    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * Sets gameSpeed of this Session to given value.
     *
     * @param gameSpeed a double
     */
    public void setGameSpeed(double gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public void setCurrentBlock() {
        this.currentBlock = nextBlock;
    }

    public void setNextBlock(Block block) {
        this.nextBlock = block;
    }

    /* general purpose methods */
    public void setPaused() {
        this.isPaused = !isPaused;
    }

    public void addScore(int scoreToAdd) {
        this.score += scoreToAdd;
    }

    public void createNextBlock() {
        setCurrentBlock();
        setNextBlock(Block.createBlock());
        Random random = new Random();
        board.placeBlock(currentBlock, random.nextInt(0, Board.WIDTH), 0);
    }

    public boolean isGameOver() {
        if (!board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.DOWN)
                && !board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.UP)) {
            setFinished(true);
        }
        return getFinished();
    }
}
