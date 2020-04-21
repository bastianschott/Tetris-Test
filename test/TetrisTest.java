import static org.junit.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TetrisTest {

	private Tetris tetris;
	Robot robot;
	long start;

	@Before
	public void setUp() throws Exception {
		tetris = new Tetris();
		robot = new Robot();

		// initialize game
		mockStartGame();
	}

	@After
	public void tearDown() throws Exception {
		tetris.setVisible(false);
		tetris.dispose();
		tetris = null;
	}

	@Test
	public void testUpdateGame() {

		// press enter to start the game
		robot.keyPress(KeyEvent.VK_ENTER);

		// let the game run until a new block falls down to actualize the board
		startGameLoopBegin();
		startGameLoopEnd();
		startGameLoopBegin();
		startGameLoopEnd();

		robot.keyRelease(KeyEvent.VK_ENTER);

		// add square blocks to the bottom 2 rows
		for (int j = 0; j < 10; j += 2) {
			startGameLoopBegin();
			tetris.getBoardPanel().addPiece(TileType.values()[3], j, 20, 0);
			if (tetris.getLogicTimer().hasElapsedCycle()) {
				tetris.updateGame();
			}
			startGameLoopEnd();
		}

		// let the game run 200 cycles and check every time if the cycle count is
		// resetted to 0. Also checks the current row when a new block is spawned
		for (int i = 0; i < 200; i++) {
			startGameLoopBegin();

			// press key S constantly to fast forward the test
			robot.keyPress(KeyEvent.VK_S);

			if (tetris.getLogicTimer().hasElapsedCycle()) {

				tetris.updateGame();

				if (tetris.getBoardPanel().isValidAndEmpty(tetris.getCurrentType(), tetris.getCurrentCol(), tetris.getCurrentRow() + 1,
						tetris.getCurrentRotation())) {

					int tilesize = tetris.getCurrentType().name() == "TypeI" ? tetris.getCurrentRow()
							: tetris.getCurrentRow();
					assertEquals(tilesize, tetris.getCurrentRow());

				} else {
					tetris.updateGame();
					assertEquals(0, tetris.getLogicTimer().elapsedCycles);
				}
			}

			startGameLoopEnd();
		}

		assertEquals(200, tetris.getScore());
	}

	/**
	 * Method to initialize the game. Copied from Tetris.startGame()
	 */
	private void mockStartGame() {
		/*
		 * Initialize our random number generator, logic timer, and new game variables.
		 */
		tetris.setRandom(new Random());
		tetris.setIsNewGame(true);
		tetris.setGameSpeed(1.0f);

		/*
		 * Setup the timer to keep the game from running before the user presses enter
		 * to start it.
		 */
		tetris.setLogicTimer(new Clock(tetris.getGameSpeed()));
		tetris.getLogicTimer().setPaused(true);
	}

	/**
	 * Begin of the loop in Tetris.startGame()
	 */
	private void startGameLoopBegin() {
		// Get the time that the frame started.
		start = System.nanoTime();

		// Update the logic timer.
		tetris.getLogicTimer().update();
	}

	/**
	 * End of the loop in Tetris.startGame()
	 */
	private void startGameLoopEnd() {
		// Decrement the drop cool down if necessary.
		if (tetris.getDropCooldown() > 0) {
			tetris.setDropCooldown(tetris.getDropCooldown() - 1);
		}

		// Display the window to the user.
		tetris.renderGame();

		/*
		 * Sleep to cap the framerate.
		 */
		long delta = (System.nanoTime() - start) / 1000000L;
		if (delta < Tetris.getFrameTime()) {
			try {
				Thread.sleep(Tetris.getFrameTime() - delta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
