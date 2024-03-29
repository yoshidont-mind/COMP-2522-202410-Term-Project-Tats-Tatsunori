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
     * @param coordinates an array of int which represents an existing coordinates on this board
     * @throws IllegalArgumentException when the length of coordinates is not two
     * @throws IllegalArgumentException when coordinates does not exist on this board
     */
    public void placeBlock(final Block block, final int[] coordinates) {
        if (coordinates.length != 2) {
            throw new IllegalArgumentException("the length of coordinates must be two");
        } else if (coordinates[0] < 0 || coordinates[0] >= WIDTH || coordinates[1] < 0 || coordinates[1] >= HEIGHT) {
            throw new IllegalArgumentException("coordinates must exist on this board");
        } else {
            this.blocks[coordinates[0]][coordinates[1]] = block;
        }
    }


    /**
     * Remove specified Block if it exists on this board, and return the number of removed Blocks.
     *
     * @param blockToRemove an instance of Block
     * @return an int that represents the number of removed Blocks
     */
    public int removeBlock(final Block blockToRemove) {
        int removedBlocks = 0;
        for (int x = 0; x < this.blocks.length; x++) {
            for (int y = 0; y < this.blocks[x].length; y++) {
                if (this.blocks[x][y] == blockToRemove) {
                   this.blocks[x][y] = null;
                   removedBlocks++;
                }
            }
        }
        return removedBlocks;
    }
}
