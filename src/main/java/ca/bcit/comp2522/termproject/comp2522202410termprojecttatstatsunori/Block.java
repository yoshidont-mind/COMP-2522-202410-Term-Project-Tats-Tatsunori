package ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;

import java.util.Random;

public class Block {
    private int value;
    private Color color;

    public Block() {
        this.value = new Random().nextInt(1, 10);
        assignColor(this.value);
    }

    public int getValue() {
        return this.value;
    }

    public Color getColor() {
        return this.color;
    }

    public final void setValue(int value) {
        this.value = value;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private final void assignColor(int value) {
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
                setColor(Color.WHITE);
                break;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Block block = new Block();
            int value = block.getValue();
            Color color = block.getColor();
            System.out.printf("Value %s: %s\n", value, color);
        }
    }

}
