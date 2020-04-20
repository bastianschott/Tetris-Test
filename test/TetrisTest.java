import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TetrisTest {

	private Tetris tetris;
	Robot robot;
	long start;

	@Before
	public void setUp() throws Exception {
		tetris = new Tetris();
		robot = new Robot();
	}

	@After
	public void tearDown() throws Exception {
		tetris = null;
	}

	@Test
	public void testUpdateGame() {
		mockStartGame();


		robot.keyPress(KeyEvent.VK_ENTER);

		float gameSpeed = tetris.gameSpeed;
		int score = 0;

		for (int i = 0; i < 200; i++) {
			startGameLoopBegin();

			robot.keyPress(KeyEvent.VK_S);

			if (tetris.logicTimer.hasElapsedCycle()) {
				switch (tetris.board.checkLines()) {
				case 0: break;
				case 1: score += 100; break;
				case 2: score += 200; break;
				case 3: score += 400; break;
				case 4: score += 800; break;
				default: fail("wrong range of cleared lines");
			}
				tetris.updateGame();
				
				// =====================================================================

				if (tetris.board.isValidAndEmpty(tetris.currentType, tetris.currentCol, tetris.currentRow + 1,
						tetris.currentRotation)) {

					int tilesize = tetris.currentType.name() == "TypeI" ? tetris.getCurrentRow()
							: tetris.getCurrentRow();
					assertEquals(tilesize, tetris.getCurrentRow());

				} else {
					tetris.updateGame();
					assertEquals(0, tetris.logicTimer.elapsedCycles);
				}
			}


			assertEquals(score, tetris.score);

			// =====================================================================
			startGameLoopEnd();
		}
	}

	void mockStartGame() {
		/*
		 * Initialize our random number generator, logic timer, and new game variables.
		 */
		tetris.random = new Random();
		tetris.isNewGame = true;
		tetris.gameSpeed = 1.0f;

		/*
		 * Setup the timer to keep the game from running before the user presses enter
		 * to start it.
		 */
		tetris.logicTimer = new Clock(tetris.gameSpeed);
		tetris.logicTimer.setPaused(true);
	}

	private void startGameLoopBegin() {
		// Get the time that the frame started.
		start = System.nanoTime();

		// Update the logic timer.
		tetris.logicTimer.update();

	}

	private void startGameLoopEnd() {
		// Decrement the drop cool down if necessary.
		if (tetris.dropCooldown > 0) {
			tetris.dropCooldown--;
		}

		// Display the window to the user.
		tetris.renderGame();

		/*
		 * Sleep to cap the framerate.
		 */
		long delta = (System.nanoTime() - start) / 1000000L;
		if (delta < Tetris.FRAME_TIME) {
			try {
				Thread.sleep(Tetris.FRAME_TIME - delta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
