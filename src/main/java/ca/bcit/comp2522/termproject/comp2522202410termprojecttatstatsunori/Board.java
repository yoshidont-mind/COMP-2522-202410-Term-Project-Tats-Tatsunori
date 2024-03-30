package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

public class Board {
    /* constants */
    public static final int HEIGHT = 11;
    public static final int WIDTH = 5;

    /* instance variables */
    private Block[][] blocks;

    /* constructors */
    /**
     * Constructs an instance of Board without accepting parameters.
     */
    public Board() {};

    /**
     * Constructs an instance of Board with accepting parameters.
     */
    public Board(final Block[][] blocks) {
        this.blocks = blocks;
    }

    /* getters */
    /**
     * Returns blocks of this Board.
     *
     * @return a two-dimensional array of Block
     */
    public Block[][] getBlocks() {
        return this.blocks;
    }

    /* setters */
    /**
     * Set blocks of this object to the given blocks.
     *
     * @param blocks a two-dimensional array of Block
     */
    public void setBlocks(final Block[][] blocks) {
        this.blocks = blocks;
    }

    /* general purpose methods */
    /**
     * Place given block to given coordinates.
     *
     * @param block an instance of Block
     * @param xCoordinate an int between 0 and WIDTH - 1
     * @param yCoordinate an int between 0 and HEIGHT - 1
     * @throws IllegalArgumentException when given coordinates does not exist on this board
     */
    public void placeBlock(final Block block, final int xCoordinate, final int yCoordinate) {
        if (xCoordinate < 0 || xCoordinate >= WIDTH || yCoordinate < 0 || yCoordinate >= HEIGHT) {
            throw new IllegalArgumentException("coordinates must exist on this board");
        } else {
            this.blocks[xCoordinate][yCoordinate] = block;
            block.setXCoordinate(xCoordinate);
            block.setYCoordinate(yCoordinate);
        }
    }


    /**
     * Remove specified Block if it exists on this board, and return the number of removed Blocks.
     *
     * @param blockToRemove an instance of Block
     * @return an int that represents the number of removed Blocks
     */
    public int removeBlock(final Block blockToRemove) {
        int xCoordinate = blockToRemove.getXCoordinate();
        int yCoordinate = blockToRemove.getYCoordinate();
        if (this.blocks[xCoordinate][yCoordinate] == blockToRemove) {
            this.blocks[xCoordinate][yCoordinate] = null;
            return 1;
        }
        return 0;
    }

    /* Returns coordinates to move by as an array of int */
    private int[] coordinatesToMoveBy (final Direction direction) {
        int[] coordinatesToMoveBy = {0, 0};
        switch (direction) {
            case LEFT:
                coordinatesToMoveBy[0] = -1;
                break;
            case RIGHT:
                coordinatesToMoveBy[0] = 1;
                break;
            case UP:
                coordinatesToMoveBy[1] = 1;
                break;
            case DOWN:
                coordinatesToMoveBy[1] = -1;
                break;
        }
        return coordinatesToMoveBy;
    }

    /**
     * Return if the block at given coordinates can move to given direction.
     *
     * @param xCoordinate an int between 0 and WIDTH - 1
     * @param yCoordinate an int between 0 and HEIGHT - 1
     * @param direction an instance of Direction
     * @throws IllegalArgumentException when given coordinates does not exist on this board
     * @return a boolean that represents if the block at given coordinates can move to given direction
     */
    public boolean validateMove (final int xCoordinate, int yCoordinate, final Direction direction) {
        if (xCoordinate < 0 || xCoordinate >= WIDTH || yCoordinate < 0 || yCoordinate >= HEIGHT) {
            throw new IllegalArgumentException("coordinates must exist on this board");
        } else {
            int[] coordinatesToMoveBy = this.coordinatesToMoveBy(direction);
            int newXCoordinate = xCoordinate + coordinatesToMoveBy[0];
            int newYCoordinate = yCoordinate + coordinatesToMoveBy[1];
            return  (0 <= newXCoordinate && newXCoordinate < WIDTH && 0 <= newYCoordinate && newYCoordinate < HEIGHT
                    || this.blocks[newXCoordinate][newYCoordinate] == null);
        }
    }

    /**
     * Moves given Block to given Direction
     *
     * @param block an instance of Block
     * @param direction a Direction
     */
    public void moveBlockByOne(final Block block, final Direction direction) {
        int xCoordinate = block.getXCoordinate();
        int yCoordinate = block.getYCoordinate();
        if (validateMove(xCoordinate, yCoordinate, direction)) {
            int[] coordinatesToMoveBy = this.coordinatesToMoveBy(direction);
            int newXCoordinate = xCoordinate + coordinatesToMoveBy[0];
            int newYCoordinate = yCoordinate + coordinatesToMoveBy[1];

            // move block
            this.blocks[xCoordinate][yCoordinate] = null;
            this.blocks[newXCoordinate][newYCoordinate] = block;

            // update coordinates of block
            block.setXCoordinate(newXCoordinate);
            block.setYCoordinate(newYCoordinate);
        }
    }

    public int moveBlocks(final Direction direction) {
    }

    private int seeOneDirection(final int xCoordinate, int yCoordinate, final Direction direction) {
    }

    private int removeDeadBlocks(final int xCoordinate, int yCoordinate) {

    }

    public int processEliminating() {

    }
}
