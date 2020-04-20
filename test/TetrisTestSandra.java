

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TetrisTestSandra {


	/*
	 * public Tetris tetris;
	 * 
	 * @Before public void setUp() throws Exception { tetris = new Tetris(); }
	 * 
	 * @After public void tearDown() throws Exception { tetris = null; }
	 */

	@Test
	public void testUpdateGame() throws AWTException {

		Tetris tt = new Tetris();
		Robot r = new Robot();
		
		tt.startGameInitialization();
		r.keyPress(KeyEvent.VK_ENTER);
		
		//test of: see if the piece's position can move down to the next row //if(...)
		for(int i=1; i<10; i++) {
			tt.startGameWhile();
			tt.updateGame();
			
			if (tt.currentType.name() == "TypeJ" || tt.currentType.name() == "TypeL" || tt.currentType.name() == "TypeO"
					|| tt.currentType.name() == "TypeS" || tt.currentType.name() == "TypeT"
					|| tt.currentType.name() == "TypeZ") {
				assertEquals(i, tt.getCurrentRow());
			}
			if (tt.currentType.name() == "TypeI") {
				assertEquals(i + 1, tt.getCurrentRow());
			}
		}
		//test of else-part
		float gameSpeed = tt.gameSpeed;
		int score = 0;
		//int cleared = 2;
		TileType baustein = TileType.values()[2];
	//	tt.board.addPiece(baustein, 4, 17, 0);
		
		for(int k=0; k<100; k++) {
			tt.startGameWhile();
			int cleared = tt.board.checkLines();
			tt.updateGame();
			if(tt.board.isValidAndEmpty(tt.currentType, tt.currentCol, tt.currentRow + 1, tt.currentRotation)==false) {
				tt.updateGame();
				
				if(cleared>0) {
					assertEquals(score+=50, tt.score);
				}
				//increasing gameSpeed
				assertEquals(gameSpeed += 0.035f, tt.gameSpeed, 0.0001);
				//reset logicTimer
				assertEquals(0, tt.logicTimer.elapsedCycles);
				//dropcooldown is 25
				assertEquals(25, tt.dropCooldown);
				//spawn new piece
			}
		}



		
		/*
		 * for (int i = 0; i < 50; i++) {
		 * 
		 * tt.startGameWhile();
		 * 
		 * // ohne das if wird null pointer geworfen, mit ist der Test immer erfolgreich
		 * if (tt.logicTimer.hasElapsedCycle()) { if
		 * (tt.board.isValidAndEmpty(tt.currentType, tt.currentCol, tt.currentRow,
		 * tt.currentRotation)) { tt.updateGame(); assertEquals(currentRow + 1,
		 * tt.getCurrentRow()); } else { tt.updateGame(); assertEquals(currentRow,
		 * tt.getCurrentRow()); assertEquals(gameSpeed + 0.035f, tt.gameSpeed, 0.00001);
		 * assertEquals(score + 50, tt.score); } } }
		 */

		// Test if(board.isValidAndEmpty...)
		//assertEquals(0, tt.getCurrentRow());

		// tt.updateGame();
		// tt.updateGame();
		// assertEquals(1,tt.getCurrentRow());

		/*
		 * // Give the timer time to count up, so there is at least one new game cycle.
		 * sleep(); tetris.logicTimer.update();
		 * 
		 * if (tetris.logicTimer.hasElapsedCycle()) { tetris.updateGame(); }
		 * assertEquals(1, tetris.getCurrentRow());
		 * 
		 * //players score does not increase, if the new piece does not result in any
		 * cleared lines assertEquals(0,tetris.getScore());
		 */

	}

	private void asserEquals(int i, int score) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * public void startGameInitialization() { tt.random = new Random();
	 * tt.isNewGame = true; tt.gameSpeed = 1.0f;
	 * 
	 * 
	 * Setup the timer to keep the game from running before the user presses enter
	 * to start it.
	 * 
	 * tt.logicTimer = new Clock(tt.gameSpeed); tt.logicTimer.setPaused(true);
	 * 
	 * }
	 * 
	 * public void startGameWhile() {
	 * 
	 * //Get the time that the frame started. long start = System.nanoTime();
	 * 
	 * //Update the logic timer. tt.logicTimer.update();
	 * 
	 * 
	 * If a cycle has elapsed on the timer, we can update the game and move our
	 * current piece down.
	 * 
	 * if(tt.logicTimer.hasElapsedCycle()) { tt.updateGame(); }
	 * 
	 * //Decrement the drop cool down if necessary. if(tt.dropCooldown > 0) {
	 * tt.dropCooldown--; }
	 * 
	 * //Display the window to the user. tt.renderGame();
	 * 
	 * 
	 * Sleep to cap the framerate.
	 * 
	 * long delta = (System.nanoTime() - start) / 1000000L; if(delta <
	 * tt.FRAME_TIME) { try { Thread.sleep(tt.FRAME_TIME - delta); } catch(Exception
	 * e) { e.printStackTrace(); } } }
	 */
}