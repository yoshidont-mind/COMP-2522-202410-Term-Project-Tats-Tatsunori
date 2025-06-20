package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a game board for a block-based puzzle game.
 *
 * @author Tatsuya Yoshida, Tatsunori Marumo
 * @version 2024
 */
public class Board {
    /* constants */
    /**
     * The height of boards
     */
    public static final int HEIGHT = 10;

    /**
     * The width of boards
     */
    public static final int WIDTH = 5;

    /**
     * The value for the condition used to remove blocks
     */
    public static final int OBJECTIVE_SUM = 10;

    /* instance variables */
    private final Block[][] blocks;

    /* constructors */
    /**
     * Constructs an instance of Board without accepting parameters.
     */
    public Board() {this.blocks = new Block[WIDTH][HEIGHT];}

    /* getters */
    /**
     * Returns blocks of this Board.
     *
     * @return a two-dimensional array of Block
     */
    public Block[][] getBlocks() {
        return this.blocks;
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
            case LEFT -> coordinatesToMoveBy[0] = -1;
            case RIGHT -> coordinatesToMoveBy[0] = 1;
            case UP -> coordinatesToMoveBy[1] = -1;
            case DOWN -> coordinatesToMoveBy[1] = 1;
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
            if (newXCoordinate < 0 || newXCoordinate >= WIDTH || newYCoordinate < 0 || newYCoordinate >= HEIGHT) {
                return false;
            }
            return (this.blocks[newXCoordinate][newYCoordinate] == null);
        }
    }

    /**
     * Moves given Block to given Direction, and returns 1 if it moved, else 0.
     *
     * @param block an instance of Block
     * @param direction a Direction
     * @return 1 if block moved, else 0
     */
    public int moveBlockByOne(final Block block, final Direction direction) {
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
            return 1;
        }
        return 0;
    }

    /**
     * Moves all blocks on board to given direction, and returns number of blocks moved.
     *
     * @param direction a Direction
     * @return number of blocks moved
     */
    public int moveBlocks(final Direction direction) {
        int movedBlocks = 0;
        ArrayList<Block> alreadyMoved = new ArrayList<>();

        for (int yCoordinate = 0; yCoordinate < HEIGHT; yCoordinate++) {
            for (int xCoordinate = 0; xCoordinate < WIDTH; xCoordinate++) {
                if (this.blocks[xCoordinate][yCoordinate] != null) {
                    Block block = this.blocks[xCoordinate][yCoordinate];
                    if (!alreadyMoved.contains(block)) {
                        movedBlocks += moveBlockByOne(block, direction);
                        alreadyMoved.add(block);
                    }
                }
            }
        }
        return movedBlocks;
    }

    /* Checks one direction from given coordinates, and kill blocks if the condition is met. */
    private void seeOneDirection(final int xCoordinate, int yCoordinate, final Direction direction) {
        if (xCoordinate < 0 || xCoordinate >= WIDTH || yCoordinate < 0 || yCoordinate >= HEIGHT) {
            throw new IllegalArgumentException("coordinates must exist on this board");
        } else if (this.blocks[xCoordinate][yCoordinate] == null) {
            throw new IllegalArgumentException("There must exist a block at given coordinates");
        } else {
            // initialize local variables
            int sum = 0;
            int currentX = xCoordinate;
            int currentY = yCoordinate;
            ArrayList<Block> checkedBlocks = new ArrayList<>();

            // add value of next block one by one until sum becoming OBJECTIVE_SUM, reaching no-block-coordinates, or reaching wall
            while (0 <= currentX && currentX < WIDTH && 0 <= currentY && currentY < HEIGHT
                    && this.blocks[currentX][currentY] != null) {
                Block currentBlock = this.blocks[currentX][currentY];
                sum += currentBlock.getValue();
                checkedBlocks.add(currentBlock);

                // if sum is the objective number, kill all checked blocks and return total number of killed blocks
                if (sum == OBJECTIVE_SUM) {
                    for (Block block: checkedBlocks) {
                        block.setIsAlive(false);
                    }
                }

                // modify current coordinates to check next block
                currentX += this.coordinatesToMoveBy(direction)[0];
                currentY += this.coordinatesToMoveBy(direction)[1];
            }
        }
    }

    /* Removes dead blocks. */
    private int removeDeadBlocks() {
        int removedBlocks = 0;
        for (int xCoordinate = 0; xCoordinate < WIDTH; xCoordinate++) {
            for (int yCoordinate = 0; yCoordinate < HEIGHT; yCoordinate++) {
                Block currentBlock = this.blocks[xCoordinate][yCoordinate];
                if (currentBlock != null && !currentBlock.getIsAlive()) {
                    removeBlock(currentBlock);
                    removedBlocks++;
                }
            }
        }
        return removedBlocks;
    }

    /**
     * Processes elimination starting from given coordinates.
     *
     * @param xCoordinate an int between 0 and WIDTH - 1
     * @param yCoordinate an int between 0 and HEIGHT - 1
     * @throws IllegalArgumentException when given coordinates does not exist on this board
     * @throws IllegalArgumentException when a block does not exist at given coordinates
     * @return an int that represents number of removed blocks
     */
    public int processEliminating(final int xCoordinate, int yCoordinate) {
        if (xCoordinate < 0 || xCoordinate >= WIDTH || yCoordinate < 0 || yCoordinate >= HEIGHT) {
            throw new IllegalArgumentException("coordinates must exist on this board");
        } else if (this.blocks[xCoordinate][yCoordinate] == null) {
            throw new IllegalArgumentException("There must exist a block at given coordinates");
        } else {
            // see directions one by one to change isAlive of blocks
            this.seeOneDirection(xCoordinate, yCoordinate, Direction.LEFT);
            this.seeOneDirection(xCoordinate, yCoordinate, Direction.RIGHT);
            this.seeOneDirection(xCoordinate, yCoordinate, Direction.DOWN);

            int numOfRemovedBlock =  removeDeadBlocks();

            // move all blocks downwards repeatedly until no blocks move anymore
            int numOfMovedBlocks;
            do {
                numOfMovedBlocks = this.moveBlocks(Direction.DOWN);
            }
            while (numOfMovedBlocks > 0);

            return numOfRemovedBlock;
        }
    }

    /**
     * Returns a string representation of the board.
     *
     * @return A string that represents the current state of the board.
     */
    @Override
    public String toString() {
        return "Board{"
                + "blocks=" + Arrays.toString(blocks)
                + '}';
    }

    /**
     * Compares this board to another object for equality.
     *
     * @param o The object to compare this Board against.
     * @return true if the given object represents a Board equivalent to this board, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board board)) return false;
        return Arrays.deepEquals(getBlocks(), board.getBlocks());
    }

    /**
     * Returns a hash code value for the board.
     *
     * @return A hash code value for this board.
     */
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(getBlocks());
    }
}
