import main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori.Block;
import main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori.Board;
import main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testPlaceBlockWithinBounds() {
        Block block = new Block(5);
        board.placeBlock(block, 2, 3);
        Assertions.assertSame(block, board.getBlocks()[2][3]);
        Assertions.assertEquals(2, block.getXCoordinate());
        Assertions.assertEquals(3, block.getYCoordinate());
    }

    @Test
    void testPlaceBlockOutOfBounds() {
        Block block = new Block(5);
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.placeBlock(block, -1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.placeBlock(block, 0, 11));
    }

    @Test
    void testRemoveExistingBlock() {
        Block block = new Block(5);
        board.placeBlock(block, 2, 3);
        Assertions.assertEquals(1, board.removeBlock(block));
        Assertions.assertNull(board.getBlocks()[2][3]);
    }

    @Test
    void testRemoveNonExistentBlock() {
        Block block = new Block(5);
        Assertions.assertEquals(0, board.removeBlock(block));
    }

    @Test
    void testValidateMoveWithinBounds() {
        Block block = new Block(5);
        board.placeBlock(block, 2, 3);
        Assertions.assertTrue(board.validateMove(2, 3, Direction.LEFT));
        Assertions.assertTrue(board.validateMove(2, 3, Direction.RIGHT));
        Assertions.assertTrue(board.validateMove(2, 3, Direction.UP));
        Assertions.assertTrue(board.validateMove(2, 3, Direction.DOWN));
    }

    @Test
    void testValidateMoveOutOfBounds() {
        Assertions.assertFalse(board.validateMove(0, 3, Direction.LEFT));
        Assertions.assertFalse(board.validateMove(4, 3, Direction.RIGHT));
        Assertions.assertFalse(board.validateMove(2, 0, Direction.UP));
        Assertions.assertFalse(board.validateMove(2, 10, Direction.DOWN));
    }

    @Test
    void testValidateMoveWithInvalidCoordinates() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.validateMove(-1, 3, Direction.LEFT));
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.validateMove(5, 3, Direction.RIGHT));
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.validateMove(2, -1, Direction.UP));
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.validateMove(2, 11, Direction.DOWN));
    }

    @Test
    void testMoveBlockByOneWithinBounds() {
        Block block = new Block(5);
        board.placeBlock(block, 2, 3);
        Assertions.assertEquals(1, board.moveBlockByOne(block, Direction.LEFT));
        Assertions.assertSame(block, board.getBlocks()[1][3]);
        Assertions.assertEquals(1, block.getXCoordinate());
        Assertions.assertEquals(3, block.getYCoordinate());
    }

    @Test
    void testMoveBlockByOneOutOfBounds() {
        Block block = new Block(5);
        board.placeBlock(block, 4, 10);
        Assertions.assertEquals(0, board.moveBlockByOne(block, Direction.DOWN));
        Assertions.assertSame(block, board.getBlocks()[4][10]);
    }

    @Test
    void testMoveBlocksDownward() {
        Board board = new Board();
        Block block1 = new Block(5);
        Block block2 = new Block(3);
        Block block3 = new Block(2);
        board.placeBlock(block1, 0, 0);
        board.placeBlock(block2, 1, 0);
        board.placeBlock(block3, 2, 0);

        Assertions.assertEquals(3, board.moveBlocks(Direction.DOWN));
        Assertions.assertSame(block1, board.getBlocks()[0][1]);
        Assertions.assertSame(block2, board.getBlocks()[1][1]);
        Assertions.assertSame(block3, board.getBlocks()[2][1]);
    }

    @Test
    void testMoveBlocksHorizontally() {
        Block block1 = new Block(5);
        Block block2 = new Block(3);
        Block block3 = new Block(2);
        board.placeBlock(block1, 0, 0);
        board.placeBlock(block2, 0, 1);
        board.placeBlock(block3, 0, 2);

        Assertions.assertEquals(0, board.moveBlocks(Direction.LEFT));
        Assertions.assertEquals(0, board.moveBlocks(Direction.RIGHT));
    }

    @Test
    void testProcessEliminatingWithinBounds() {
        Block block1 = new Block(5);
        Block block2 = new Block(3);
        Block block3 = new Block(2);
        board.placeBlock(block1, 0, 0);
        board.placeBlock(block2, 0, 1);
        board.placeBlock(block3, 0, 2);

        Assertions.assertEquals(3, board.processEliminating(0, 0));
        Assertions.assertNull(board.getBlocks()[0][0]);
        Assertions.assertNull(board.getBlocks()[0][1]);
        Assertions.assertNull(board.getBlocks()[0][2]);
    }

    @Test
    void testProcessEliminatingOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.processEliminating(-1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.processEliminating(0, 11));
    }

    @Test
    void testProcessEliminatingWithNoBlock() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.processEliminating(0, 0));
    }
}