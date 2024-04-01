package main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import javafx.scene.paint.Color;
import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Block {

    public static int SIZE = 25;
    public static final Rectangle rectangle = new Rectangle(SIZE - 1, SIZE - 1);
    public static final int MAX_VALUE = 9;
    private int value;
    private int xCoordinate;
    private int yCoordinate;
    private Color color;
    private boolean isAlive;
    private boolean isMoving;
    private Text text;

    public Block(int value) {
        if (0 <= value && value <= MAX_VALUE) {
            this.value = value;
        }
        this.text = new Text(String.valueOf(value));
        this.text.setX(this.rectangle.getX() + this.rectangle.getWidth() / 2 - this.text.getBoundsInLocal().getWidth() / 2);
        this.text.setY(this.rectangle.getY() + this.rectangle.getHeight() / 2 + this.text.getBoundsInLocal().getHeight() / 4);
        assignColor(this.value);
        fillColor();
        this.isAlive = true;
        this.isMoving = false;
    }

    public int getValue() {
        return this.value;
    }
    public int getXCoordinate() {return this.xCoordinate;}
    public int getYCoordinate(){return this.yCoordinate;}

    public Color getColor() {
        return this.color;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public boolean getIsMoving() {
        return this.isMoving;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public Text getText() {
        return text;
    }

    public final void setValue(int value) {
        if (0 <= value && value <= MAX_VALUE) {
            this.value = value;
        }
    }

    public final void setXCoordinate(final int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public final void setYCoordinate(final int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public final void assignColor(int value) {
        switch (value) {
            case 1:
                setColor(Color.BLUE);
                break;
            case 2:
                setColor(Color.RED);
                break;
            case 3:
                setColor(Color.YELLOW);
                break;
            case 4:
                setColor(Color.GREEN);
                break;
            case 5:
                setColor(Color.ORANGE);
                break;
            case 6:
                setColor(Color.PURPLE);
                break;
            case 7:
                setColor(Color.PINK);
                break;
            case 8:
                setColor(Color.GRAY);
                break;
            case 9:
                setColor(Color.BROWN);
                break;
            default:
                setColor(Color.WHITE); // colour for value 0
                break;
        }
    }

    public static Block createBlock() {
        return new Block(new Random().nextInt(1, 10));
    }

    public final void fillColor() {
        this.rectangle.setFill(this.color);
    }

    public void updateTextPosition() {
        // テキストの位置を更新
        this.text.setX(this.rectangle.getX() + this.rectangle.getWidth() / 2 - this.text.getBoundsInLocal().getWidth() / 2);
        this.text.setY(this.rectangle.getY() + this.rectangle.getHeight() / 2 + this.text.getBoundsInLocal().getHeight() / 4);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Block block = createBlock();
            int value = block.getValue();
            Color color = block.getColor();
            System.out.printf("Value %s: %s\n", value, color);
        }
    }
}
