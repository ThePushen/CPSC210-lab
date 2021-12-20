package ca.ubc.cpsc210.paddleball.model;

import java.awt.Color;

/**
 * Represents a paddle
 */
public class Paddle {
    public static final int DIMENSION1 = 26;  // must be even integer
    public static final int DIMENSION2 = 10;
    public static final int DX = 3;
    public static final int Y_POS = PaddleBallGame.DIMENSION2 - 40;
    public static final Color COLOR = new Color(250, 222, 54);

    private int whichWay;
    private int coordX;

    // Construct a paddle.
    // EFFECTS: places paddle at position (x, Y_POS) moving right.
    public Paddle(int xc) {
        this.coordX = xc;
        whichWay = 1;
    }

    public int getX() {
        return coordX;
    }

    // Paddle moves to right
    // MODIFIES: this
    // EFFECTS: paddle is moving right
    public void paddleMoveRight() {
        whichWay = 1;
    }

    // Paddle moves to left
    // MODIFIES: this
    // EFFECTS: paddle is moving left
    public void paddleMoveLeft() {
        whichWay = -1;
    }

    // Updates the paddle on clock tick
    // MODIFIES: this
    // EFFECTS:  paddle is moved DX units in whatever direction it is facing and is
    //           constrained to remain within boundaries of game
    public void moveDX() {
        coordX = coordX + whichWay * DX;

        constrainPaddle();
    }

    // Constrains paddle so that it doesn't travel off sides of screen
    // MODIFIES: this
    // EFFECTS: paddle is constrained to remain within vertical boundaries of game
    private void constrainPaddle() {
        if (coordX - DIMENSION1 / 2 < 0) {
            coordX = DIMENSION1 / 2;
        } else if (coordX + DIMENSION1 / 2 > PaddleBallGame.DIMENSION1) {
            coordX = PaddleBallGame.DIMENSION1 - DIMENSION1 / 2;
        }
    }
}
