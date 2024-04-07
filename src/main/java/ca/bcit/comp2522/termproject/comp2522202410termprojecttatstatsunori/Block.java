package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.scene.paint.Color;

import java.util.Objects;
import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * Represents a block in a game or application with a value, color, and coordinates.
 * The block has the ability to move and can be alive or dead.
 *
 * @author Tatsunori Marumo, Tatsuya Yoshida
 * @version 2024
 */
public class Block {

    /** Size of the block */
    public static int SIZE = 55;

    /** Text size within the block */
    public static int TEXT_SIZE = 20;

    /** Maximum value that a block can have */
    public static final int MAX_VALUE = 9;

    private final Rectangle rectangle;
    private int value;
    private int xCoordinate;
    private int yCoordinate;
    private Color color;
    private boolean isAlive;
    private boolean isMoving;
    private Text text;

    /**
     * Constructs a Block with a specified value, initializing its appearance and state.
     *
     * @param value The integer value of the block, must be between 0 and {@link #MAX_VALUE}.
     */
    public Block(int value) {
        if (0 <= value && value <= MAX_VALUE) {
            this.value = value;
        }
        this.rectangle = new Rectangle(SIZE - 1, SIZE - 1);
        this.text = new Text(String.valueOf(value));
        text.setFont(Font.font("Arial", FontWeight.BOLD, TEXT_SIZE));
        this.text.setX(this.rectangle.getX() + this.rectangle.getWidth() / 2 - this.text.getBoundsInLocal().getWidth() / 2);
        this.text.setY(this.rectangle.getY() + this.rectangle.getHeight() / 2 + this.text.getBoundsInLocal().getHeight() / 4);
        assignColor(this.value);
        fillColor();
        this.isAlive = true;
        this.isMoving = true;
    }

    /** Returns the value of the block. */
    public int getValue() {
        return this.value;
    }

    /** Returns the X coordinate of the block. */
    public int getXCoordinate() {return this.xCoordinate;}

    /** Returns the Y coordinate of the block. */
    public int getYCoordinate(){return this.yCoordinate;}

    /** Returns the color of the block. */
    public Color getColor() {
        return this.color;
    }

    /** Returns whether the block is alive. */
    public boolean getIsAlive() {
        return this.isAlive;
    }

    /** Returns whether the block is moving. */
    public boolean getIsMoving() {
        return this.isMoving;
    }

    /** Returns the rectangle shape of the block. */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /** Returns the text displayed on the block. */
    public Text getText() {
        return text;
    }

    /** Sets the value of the block. */
    public final void setValue(int value) {
        if (0 <= value && value <= MAX_VALUE) {
            this.value = value;
        }
    }

    /** Sets the X coordinate of the block. */
    public final void setXCoordinate(final int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /** Sets the Y coordinate of the block. */
    public final void setYCoordinate(final int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /** Sets the color of the block. */
    public void setColor(Color color) {
        this.color = color;
    }

    /** Sets the moving state of the block. */
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    /** Sets the moving state of the block. */
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    /**
     * Assigns a color to the block based on its value.
     *
     * @param value The value of the block to determine its color.
     */
    public final void assignColor(int value) {
        switch (value) {
            case 1:
                setColor(Color.LIGHTBLUE);
                break;
            case 2:
                setColor(Color.LIGHTCORAL);
                break;
            case 3:
                setColor(Color.LIGHTYELLOW);
                break;
            case 4:
                setColor(Color.LIGHTPINK);
                break;
            case 5:
                setColor(Color.LIGHTGRAY);
                break;
            case 6:
                setColor(Color.LIGHTSKYBLUE);
                break;
            case 7:
                setColor(Color.LIGHTGREEN);
                break;
            case 8:
                setColor(Color.LIGHTSALMON);
                break;
            case 9:
                setColor(Color.LIGHTSEAGREEN);
                break;
            default:
                setColor(Color.WHITE); // colour for value 0
                break;
        }
    }

    /**
     * Creates a new block with a random value between 1 and {@link #MAX_VALUE}.
     *
     * @return A new Block instance with a random value.
     */
    public static Block createBlock() {
        return new Block(new Random().nextInt(1, 10));
    }

    /** Fills the rectangle of the block with its assigned color. */
    public final void fillColor() {
        this.rectangle.setFill(this.color);
    }

    /** Updates the position of the text within the block based on the block's size and position. */
    public void updateTextPosition() {
        this.text.setX(this.rectangle.getX() + this.rectangle.getWidth() / 2 - this.text.getBoundsInLocal().getWidth() / 2);
        this.text.setY(this.rectangle.getY() + this.rectangle.getHeight() / 2 + this.text.getBoundsInLocal().getHeight() / 4);
    }

    /**
     * Compares this block to another object to determine equality.
     *
     * @param o The object to compare with this block.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block block)) return false;
        return getValue() == block.getValue() && xCoordinate == block.xCoordinate && yCoordinate == block.yCoordinate
                && isAlive == block.isAlive && isMoving == block.isMoving && Objects.equals(getRectangle(),
                block.getRectangle()) && Objects.equals(getColor(), block.getColor()) && Objects.equals(getText(),
                block.getText());
    }

    /**
     * Generates a hash code for the block.
     *
     * @return A hash code value for the block.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getRectangle(), getValue(), xCoordinate, yCoordinate, getColor(), isAlive, isMoving,
                getText());
    }
}
