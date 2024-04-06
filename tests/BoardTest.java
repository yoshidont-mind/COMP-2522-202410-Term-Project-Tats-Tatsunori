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
}