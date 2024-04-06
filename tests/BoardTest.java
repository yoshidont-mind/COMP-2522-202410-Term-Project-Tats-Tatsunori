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
}