import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardPanelTest {
	private Tetris tetris;
	BoardPanel bp = new BoardPanel(tetris);
	TileType type;

	@Before
	public void setUp() throws Exception {
		tetris = new Tetris();

		// type is always a square
		type = TileType.values()[3];
	}

	/**
	 * Check the bounds of the board.
	 */
	@Test
	public void testIsValidAndEmpty() {
		// check the left border
		assertFalse(bp.isValidAndEmpty(type, -1, 0, 0));
		assertTrue(bp.isValidAndEmpty(type, 0, 0, 0));

		// check the right border
		assertFalse(bp.isValidAndEmpty(type, 9, 0, 0));
		assertTrue(bp.isValidAndEmpty(type, 8, 0, 0));

		// check the upper border
		assertFalse(bp.isValidAndEmpty(type, 0, -1, 0));
		assertTrue(bp.isValidAndEmpty(type, 0, 0, 0));

		// check the bottom border
		assertFalse(bp.isValidAndEmpty(type, 0, 21, 0));
		assertTrue(bp.isValidAndEmpty(type, 0, 20, 0));
	}

	/**
	 * Adds a piece and then checks if the piece occupies some cells correctly
	 */
	@Test
	public void testAddPiece() {
		tetris.getBoardPanel().addPiece(type, 5, 5, 0);

		// check 1,1 empty
		assertTrue(tetris.getBoardPanel().isValidAndEmpty(type, 1, 1, 0));

		// check 5,5 occupied
		assertFalse(tetris.getBoardPanel().isValidAndEmpty(type, 5, 5, 0));

		// check 5,6 occupied
		assertFalse(tetris.getBoardPanel().isValidAndEmpty(type, 5, 6, 1));

		// check 6,6 occupied
		assertFalse(tetris.getBoardPanel().isValidAndEmpty(type, 6, 6, 2));

		// check 6,5 occupied
		assertFalse(tetris.getBoardPanel().isValidAndEmpty(type, 6, 5, 3));
	}

	@Test
	public void testCheckLines() {
		assertEquals(0, bp.checkLines());
	}

}
