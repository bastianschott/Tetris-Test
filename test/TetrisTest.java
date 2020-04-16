import static org.junit.Assert.assertEquals;

import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TetrisTest {

	private Tetris tetris;

	@Before
	public void setUp() throws Exception {
		tetris = new Tetris();
	}

	@After
	public void tearDown() throws Exception {
		tetris = null;
	}

	@Test
	public void testUpdateGame() {

		// Tests the first if(true) in the method
		mockStartGame();
		assertEquals(0, tetris.getCurrentRow());

		// method is called when the ENTER key is pressed.
		tetris.resetGame();
		// Give the timer time to count up, so there is at least one new game cycle.
		sleep();

		tetris.logicTimer.update();

		if (tetris.logicTimer.hasElapsedCycle()) {
			tetris.updateGame();
		}

		assertEquals(1, tetris.getCurrentRow());

		tetris.setCurrentRow(20);
		tetris.updateGame();

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
