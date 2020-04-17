import static org.junit.Assert.assertEquals;

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

	@Before
	public void setUp() throws Exception {
		tetris = new Tetris();
		robot = new Robot();
	}

	@After
	public void tearDown() throws Exception {
		tetris = null;
	}

	@Ignore
	public void newTestUpdateGame() {
		newMockStartGame();
		assertEquals(tetris.logicTimer.getElapsedCycles(), tetris.getCurrentRow());

		tetris.resetGame();

		float gameSpeed = tetris.gameSpeed;

		for (int i = 0; i < 200; i++) {
			startGameLoop();
			robot.keyPress(KeyEvent.VK_S);

			int currentRow = tetris.getCurrentRow();

			if (tetris.logicTimer.hasElapsedCycle()) {
				if (tetris.board.isValidAndEmpty(tetris.currentType, tetris.currentCol, tetris.currentRow + 1,
						tetris.currentRotation)) {
					tetris.updateGame();
					assertEquals(currentRow + 1, tetris.getCurrentRow());
				} else {
					
					tetris.updateGame();
					assertEquals(0, tetris.getCurrentRow());
					assertEquals(gameSpeed+.035f, tetris.gameSpeed, 0.0001);
					System.out.println("test");
				}

			}

		}
		System.out.println(tetris.getCurrentRow());

	}

	@Test
	public void testUpdateGame() {

		// Tests the first if(true) in the method
		mockStartGame();
		assertEquals(0, tetris.getCurrentRow());

		// method is called when the ENTER key is pressed.
		tetris.resetGame();
		// Give the timer time to count up, so there is at least one new game cycle.
		// sleep();

		tetris.logicTimer.update();

		if (tetris.logicTimer.hasElapsedCycle()) {
			tetris.updateGame();
		}

		assertEquals(0, tetris.getCurrentRow());

		tetris.updateGame();

	}

	void newMockStartGame() {
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

		for (int i = 0; i < 1; i++) {
			startGameLoop();
		}
	}

	private void startGameLoop() {
		// Get the time that the frame started.
		long start = System.nanoTime();

		// Update the logic timer.
		tetris.logicTimer.update();

		/*
		 * If a cycle has elapsed on the timer, we can update the game and move our
		 * current piece down.
		 */
//		if (tetris.logicTimer.hasElapsedCycle()) {
//			tetris.updateGame();
//		}

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

	void mockStartGame() {
		tetris.random = new Random();
		tetris.isNewGame = true;
		tetris.gameSpeed = 1.0f;

		tetris.logicTimer = new Clock(tetris.gameSpeed);
		tetris.logicTimer.setPaused(true);

		tetris.logicTimer.update();

//		if (tetris.logicTimer.hasElapsedCycle()) {
//			tetris.updateGame();
//		}

		if (tetris.dropCooldown > 0) {
			tetris.dropCooldown--;
		}

		tetris.renderGame();

	}

	private static void sleep() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}
}
