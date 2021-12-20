package ca.ubc.cpsc210.paddleball.test;

import ca.ubc.cpsc210.paddleball.model.PaddleBallGame;
import ca.ubc.cpsc210.paddleball.model.Paddle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the Game class.
 */
class TestGame {
	private PaddleBallGame g;
	
	@BeforeEach
	void runBefore() {
		g = new PaddleBallGame();
	}
	
	@Test
	void testInit() {
		Paddle t = g.getPaddle();
		assertEquals(PaddleBallGame.DIMENSION1 / 2, t.getX());
	}
	
	@Test
	void testUpdate() {
		Paddle t = g.getPaddle();
		assertEquals(PaddleBallGame.DIMENSION1 / 2, t.getX());
		g.update();
		assertEquals(PaddleBallGame.DIMENSION1 / 2 + Paddle.DX, t.getX());
		g.update();
		assertEquals(PaddleBallGame.DIMENSION1 / 2 + 2 * Paddle.DX, t.getX());
	}
	
	@Test
	void testNonKeyPadKeyEvent() {
		Paddle t = g.getPaddle();
		g.keyPressed(KeyEvent.VK_LEFT);
		g.update();
		assertEquals(PaddleBallGame.DIMENSION1 / 2 - Paddle.DX, t.getX());
		g.update();
		assertEquals(PaddleBallGame.DIMENSION1 / 2 - 2 * Paddle.DX, t.getX());
		g.keyPressed(KeyEvent.VK_RIGHT);
		g.update();
		assertEquals(PaddleBallGame.DIMENSION1 / 2 - Paddle.DX, t.getX());
		g.update();
		assertEquals(PaddleBallGame.DIMENSION1 / 2, t.getX());
	}
	
	@Test
	void testKeyPadKeyEvent() {
		Paddle t = g.getPaddle();
		g.keyPressed(KeyEvent.VK_KP_LEFT);
		g.update();
		assertEquals(PaddleBallGame.DIMENSION1 / 2 - Paddle.DX, t.getX());
		g.update();
		assertEquals(PaddleBallGame.DIMENSION1 / 2 - 2 * Paddle.DX, t.getX());
		g.keyPressed(KeyEvent.VK_KP_RIGHT);
		g.update();
		assertEquals(PaddleBallGame.DIMENSION1 / 2 - Paddle.DX, t.getX());
		g.update();
		assertEquals(PaddleBallGame.DIMENSION1 / 2, t.getX());
	}
}
