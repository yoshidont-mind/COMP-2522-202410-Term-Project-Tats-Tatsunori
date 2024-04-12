package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

/**
 * Represents a game session, managing the state and progression of gameplay, including score, game speed, blocks,
 * and the game board.
 *
 * @author Tatsunori Marumo, Tatsuya Yoshida
 * @version 2024
 */
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

    /**
     * Retrieves the current block in play.
     *
     * @return The current Block object.
     */
    public Block getCurrentBlock() {
        return this.currentBlock;
    }

    /**
     * Retrieves the next block to be played.
     *
     * @return The next Block object.
     */
    public Block getNextBlock() {
        return this.nextBlock;
    }

    /**
     * Checks if the session is paused.
     *
     * @return True if the session is paused, false otherwise.
     */
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

    /**
     * Updates the current block to the next block and generates a new next block.
     */
    public void setCurrentBlock() {
        this.currentBlock = nextBlock;
    }

    /**
     * Sets the next block to the specified block.
     *
     * @param block The new next block as a Block object.
     */
    public void setNextBlock(Block block) {
        this.nextBlock = block;
    }

    /* general purpose methods */
    public void setPaused() {
        this.isPaused = !isPaused;
    }

    /**
     * Adds the specified score to the current session score.
     *
     * @param scoreToAdd The score to add as an integer.
     */
    public void addScore(int scoreToAdd) {
        this.score += scoreToAdd;
    }

    /**
     * Handles the creation and placement of the next block, updating the current and next blocks.
     */
    public void createNextBlock() {
        setCurrentBlock();
        setNextBlock(Block.createBlock());
        Random random = new Random();
        board.placeBlock(currentBlock, random.nextInt(0, Board.WIDTH), 0);
    }

    /**
     * Determines if the game is over based on the ability to move the current block.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean getIsGameOver() {
        if (!board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.DOWN)
                && !board.validateMove(currentBlock.getXCoordinate(), currentBlock.getYCoordinate(), Direction.UP)) {
            setFinished(true);
        }
        return getFinished();
    }

    @Override
    public String toString() {
        return "Session{"
                + "startTime=" + startTime
                + ", score=" + score
                + ", isFinished=" + isFinished
                + ", gameSpeed=" + gameSpeed
                + ", board=" + board
                + ", currentBlock=" + currentBlock
                + ", nextBlock=" + nextBlock
                + ", isPaused=" + isPaused
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session session)) return false;
        return getScore() == session.getScore() && getFinished() == session.getFinished() && Double.compare(getGameSpeed(), session.getGameSpeed()) == 0 && isPaused == session.isPaused && Objects.equals(getStartTime(), session.getStartTime()) && Objects.equals(getBoard(), session.getBoard()) && Objects.equals(getCurrentBlock(), session.getCurrentBlock()) && Objects.equals(getNextBlock(), session.getNextBlock());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartTime(), getScore(), getFinished(), getGameSpeed(), getBoard(), getCurrentBlock(), getNextBlock(), isPaused);
    }
}
