package ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import java.util.Random;

public class Block {
    public static final int MAX_VALUE = 9;
    private int value;
    private Color color;
    private boolean isAlive;
    private boolean isMoving;

    public Block(int value) {
        if (0 <= value && value <= MAX_VALUE) {
            this.value = value;
        }
        assignColor(this.value);
        this.isAlive = true;
        this.isMoving = false;
    }

    public int getValue() {
        return this.value;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public boolean getIsMoving() {
        return this.isMoving;
    }

    public final void setValue(int value) {
        if (0 <= value && value <= MAX_VALUE) {
            this.value = value;
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void setMoving(boolean isMoving) {
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

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Block block = new Block(new Random().nextInt(1, 10));
            int value = block.getValue();
            Color color = block.getColor();
            System.out.printf("Value %s: %s\n", value, color);
        }
    }
}
