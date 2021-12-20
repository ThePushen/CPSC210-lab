package ca.ubc.cpsc210.paddleball.model;

import java.awt.event.KeyEvent;
import java.util.Random;

/*
 * Represents a paddle ball game.
 */
public class PaddleBallGame {
    public static final int DIMENSION1 = 800;
    public static final int DIMENSION2 = 600;
    private static final Random RND = new Random();

    private Ball objectB;
    private Paddle objectP;
    private boolean stop;

    // Constructs a Paddle Ball Game
    // EFFECTS:  creates ball at random location at top of screen
    public PaddleBallGame() {
        setGame();
    }

    public Paddle getPaddle() {
        return objectP;
    }

    public Ball getBall() {
        return objectB;
    }

    // Is game over?
    // EFFECTS: returns true if game is over, false otherwise
    public boolean isGameOver() {
        return stop;
    }

    // Updates the game on clock tick
    // MODIFIES: this
    // EFFECTS:  updates ball, paddle and game over status
    public void update() {
        objectB.move();
        objectP.moveDX();

        checkCollision();
        ballHitGround();
    }

    // Responds to key press codes
    // MODIFIES: this
    // EFFECTS:  turns paddle and resets game (if game over) in response to
    //           given key pressed code
    public void keyPressed(int codeK) {
        if (codeK == KeyEvent.VK_R && stop) {
            setGame();
        } else if (codeK == KeyEvent.VK_X) {
            System.exit(0);
        } else {
            paddleControl(codeK);
        }
    }

    // Sets / resets the game
    // MODIFIES: this
    // EFFECTS:  clears list of missiles and invaders, initializes tank
    private void setGame() {
        objectB = new Ball(RND.nextInt(PaddleBallGame.DIMENSION1), Ball.SIZE / 2);
        objectP = new Paddle(DIMENSION1 / 2);
        stop = false;
    }

    // Controls the paddle
    // MODIFIES: this
    // EFFECTS: changes direction of paddle in response to key code
    private void paddleControl(int codeK) {
        if (codeK == KeyEvent.VK_KP_LEFT || codeK == KeyEvent.VK_LEFT) {
            objectP.paddleMoveLeft();
        } else if (codeK == KeyEvent.VK_KP_RIGHT || codeK == KeyEvent.VK_RIGHT) {
            objectP.paddleMoveRight();
        }
    }

    // Checks for collision between ball and paddle
    // MODIFIES: this
    // EFFECTS:  bounces ball if it collides with paddle
    private void checkCollision() {
        if (objectB.collidedWithPaddle(objectP)) {
            objectB.bounceOffPaddle();
        }
    }

    // Is game over? (Has ball hit ground?)
    // MODIFIES: this
    // EFFECTS:  if ball has hit ground, game is marked as over
    private void ballHitGround() {
        if (objectB.getY() > DIMENSION2) {
            stop = true;
        }
    }
}
