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
    void testBlockValue() {
        assertEquals(1, blockOne.getValue());
        assertEquals(2, blockTwo.getValue());
        assertEquals(3, blockThree.getValue());
        assertEquals(4, blockFour.getValue());
        assertEquals(5, blockFive.getValue());
        assertEquals(6, blockSix.getValue());
        assertEquals(7, blockSeven.getValue());
        assertEquals(8, blockEight.getValue());
        assertEquals(9, blockNine.getValue());
    }

    @Test
    void testBlockColor() {
        assertEquals(Color.LIGHTBLUE, blockOne.getColor());
        assertEquals(Color.LIGHTCORAL, blockTwo.getColor());
        assertEquals(Color.LIGHTYELLOW, blockThree.getColor());
        assertEquals(Color.LIGHTPINK, blockFour.getColor());
        assertEquals(Color.LIGHTGRAY, blockFive.getColor());
        assertEquals(Color.LIGHTSKYBLUE, blockSix.getColor());
        assertEquals(Color.LIGHTGREEN, blockSeven.getColor());
        assertEquals(Color.LIGHTSALMON, blockEight.getColor());
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



