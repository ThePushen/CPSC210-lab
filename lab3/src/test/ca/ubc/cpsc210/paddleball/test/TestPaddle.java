package ca.ubc.cpsc210.paddleball.test;

import ca.ubc.cpsc210.paddleball.model.PaddleBallGame;
import ca.ubc.cpsc210.paddleball.model.Paddle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the Paddle class.
 */
class TestPaddle {
	private static final int XLOC = PaddleBallGame.DIMENSION1 / 2;
	private Paddle p;
	
	@BeforeEach
	void runBefore() {
		p = new Paddle(XLOC);
	}
	
	@Test
	void testGetX() {
		assertEquals(XLOC, p.getX());
	}
	
	@Test
	void testUpdate() {
		final int NUM_UPDATES = 8;
		
		p.moveDX();
		assertEquals(XLOC + Paddle.DX, p.getX());
		
		for(int count = 1; count < NUM_UPDATES; count++) {
			p.moveDX();
		}
		
		assertEquals(XLOC + NUM_UPDATES * Paddle.DX, p.getX());
	}
	
	@Test
	void testFlipDirection() {
		p.moveDX();
		assertEquals(XLOC + Paddle.DX, p.getX());
		p.paddleMoveLeft();
		p.moveDX();
		assertEquals(XLOC, p.getX());
		p.paddleMoveRight();
		p.moveDX();
		assertEquals(XLOC + Paddle.DX, p.getX());
	}
	
	@Test 
	void testLeftBoundary() {
		p.paddleMoveLeft();
		for(int count = 0; count < (PaddleBallGame.DIMENSION1 / 2 - Paddle.DIMENSION1 / 2) / Paddle.DX + 1; count++)
			p.moveDX();
		assertEquals(Paddle.DIMENSION1 / 2, p.getX());
		p.moveDX();
		assertEquals(Paddle.DIMENSION1 / 2, p.getX());
	}
	
	@Test
	void testRightBoundary() {
		p.paddleMoveRight();
		for(int count = 0; count < (PaddleBallGame.DIMENSION1 / 2 - Paddle.DIMENSION1 / 2) / Paddle.DX + 1; count++)
			p.moveDX();
		assertEquals(PaddleBallGame.DIMENSION1 - Paddle.DIMENSION1 / 2, p.getX());
		p.moveDX();
		assertEquals(PaddleBallGame.DIMENSION1 - Paddle.DIMENSION1 / 2, p.getX());
	}
}
