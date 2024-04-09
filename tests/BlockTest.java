import main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori.Block;

import javafx.scene.paint.Color;

import java.util.Objects;
import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BlockTest {
    private Block blockOne;
    private Block blockTwo;
    private Block blockThree;
    private Block blockFour;
    private Block blockFive;
    private Block blockSix;
    private Block blockSeven;
    private Block blockEight;
    private Block blockNine;

    @BeforeEach
    void setUp() {
        blockOne = new Block(1);
        blockTwo = new Block(2);
        blockThree = new Block(3);
        blockFour = new Block(4);
        blockFive = new Block(5);
        blockSix = new Block(6);
        blockSeven = new Block(7);
        blockEight = new Block(8);
        blockNine = new Block(9);
    }

    @Test
    void testBlockValueOne() {
        assertEquals(1, blockOne.getValue());
    }

    @Test
    void testBlockValueTwo() {
        assertEquals(2, blockTwo.getValue());
    }

    @Test
    void testBlockValueThree() {
        assertEquals(3, blockThree.getValue());
    }

    @Test
    void testBlockValueFour() {
        assertEquals(4, blockFour.getValue());
    }

    @Test
    void testBlockValueFive() {
        assertEquals(5, blockFive.getValue());
    }

    @Test
    void testBlockValueSix() {
        assertEquals(6, blockSix.getValue());
    }

    @Test
    void testBlockValueSeven() {
        assertEquals(7, blockSeven.getValue());
    }

    @Test
    void testBlockValueEight() {
        assertEquals(8, blockEight.getValue());
    }

    @Test
    void testBlockValueNine() {
        assertEquals(9, blockNine.getValue());
    }

    @Test
    void testBlockColorValueOne() {
        assertEquals(Color.LIGHTBLUE, blockOne.getColor());
    }

    @Test
    void testBlockColorValueTwo() {
        assertEquals(Color.LIGHTCORAL, blockTwo.getColor());
    }

    @Test
    void testBlockColorValueThree() {
        assertEquals(Color.LIGHTYELLOW, blockThree.getColor());
    }

    @Test
    void testBlockColorValueFour() {
        assertEquals(Color.LIGHTPINK, blockFour.getColor());
    }

    @Test
    void testBlockColorValueFive() {
        assertEquals(Color.LIGHTGRAY, blockFive.getColor());
    }

    @Test
    void testBlockColorValueSix() {
        assertEquals(Color.LIGHTSKYBLUE, blockSix.getColor());
    }

    @Test
    void testBlockColorValueSeven() {
        assertEquals(Color.LIGHTGREEN, blockSeven.getColor());
    }

    @Test
    void testBlockColorValueEight() {
        assertEquals(Color.LIGHTSALMON, blockEight.getColor());
    }

    @Test
    void testBlockColorValueNine() {
        assertEquals(Color.LIGHTSEAGREEN, blockNine.getColor());
    }

    @Test
    void testGetIsMoving() {
        Block block = Block.createBlock();
        assertTrue(block.getIsMoving());
    }

    @Test
    void testSetISMoving() {
        Block block = Block.createBlock();
        block.setIsMoving(false);
        assertFalse(block.getIsMoving());
    }

    @Test
    void testGetIsAlive() {
        Block block = Block.createBlock();
        assertTrue(block.getIsAlive());
    }

    @Test
    void testSetIsAlive() {
        Block block = Block.createBlock();
        block.setIsAlive(false);
        assertFalse(block.getIsAlive());
    }
}



