package ca.ubc.cpsc210.paddleball.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

import ca.ubc.cpsc210.paddleball.model.PaddleBallGame;
import ca.ubc.cpsc210.paddleball.model.Ball;
import ca.ubc.cpsc210.paddleball.model.Paddle;

/*
 * The panel in which the game is rendered.
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
    private static final String OVER = "Game Over!";
    private static final String REPLAY = "R to replay";
    private PaddleBallGame game;

    // Constructs a game panel
    // EFFECTS:  sets size and background colour of panel,
    //  updates this with the game to be displayed
    GamePanel(PaddleBallGame g) {
        setPreferredSize(new Dimension(PaddleBallGame.DIMENSION1, PaddleBallGame.DIMENSION2));
        setBackground(Color.GRAY);
        this.game = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);

        if (game.isGameOver()) {
            gameOver(g);
        }
    }

    // Draws the game
    // MODIFIES: g
    // EFFECTS:  draws the game onto g
    private void drawGame(Graphics g) {
        drawPaddle(g);
        drawBall(g);
    }

    // Draw the tank
    // MODIFIES: g
    // EFFECTS:  draws the tank onto g
    private void drawPaddle(Graphics g) {
        Paddle t = game.getPaddle();
        Color savedCol = g.getColor();
        g.setColor(Paddle.COLOR);
        g.fillRect(t.getX()
                - Paddle.DIMENSION1 / 2, Paddle.Y_POS - Paddle.DIMENSION2 / 2, Paddle.DIMENSION1, Paddle.DIMENSION2);
        g.setColor(savedCol);
    }

    // Draw the ball
    // MODIFIES: g
    // EFFECTS:  draws the ball onto g
    private void drawBall(Graphics g) {
        Ball b = game.getBall();
        Color savedCol = g.getColor();
        g.setColor(Ball.COLOR);
        g.fillOval(b.getX() - Ball.SIZE / 2, b.getY() - Ball.SIZE / 2, Ball.SIZE, Ball.SIZE);
        g.setColor(savedCol);
    }

    // Draws the "game over" message and replay instructions
    // MODIFIES: g
    // EFFECTS:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, PaddleBallGame.DIMENSION2 / 2);
        centreString(REPLAY, g, fm, PaddleBallGame.DIMENSION2 / 2 + 50);
        g.setColor(saved);
    }

    // Centres a string on the screen
    // MODIFIES: g
    // EFFECTS:  centres the string str horizontally onto g at vertical position y
    private void centreString(String str, Graphics g, FontMetrics fm, int y) {
        int width = fm.stringWidth(str);
        g.drawString(str, (PaddleBallGame.DIMENSION1 - width) / 2, y);
    }
}
