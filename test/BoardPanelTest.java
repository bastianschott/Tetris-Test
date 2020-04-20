import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardPanelTest {
	private Tetris tetris;
	BoardPanel bp = new BoardPanel(tetris);
	TileType type;

	@Before
	public void setUp() throws Exception {
		tetris = new Tetris();
		type = TileType.values()[3];
	}

	@Test
	public void testIsValidAndEmpty() {
		assertFalse(bp.isValidAndEmpty(type, -1, 0, 0));
		assertFalse(bp.isValidAndEmpty(type, 11, 0, 0));
		assertTrue(bp.isValidAndEmpty(type, 0, 0, 0));
		assertTrue(bp.isValidAndEmpty(type, 8, 0, 0));
		
		assertFalse(bp.isValidAndEmpty(type, 0, -1, 0));
		assertFalse(bp.isValidAndEmpty(type, 0, 21, 0));
		assertTrue(bp.isValidAndEmpty(type, 0, 0, 0));
		assertTrue(bp.isValidAndEmpty(type, 0, 1, 0));
		
		testAddPiece();
	}

	@Test
	public void testAddPiece() {
		tetris.board.addPiece(type, 5, 5, 0);
		assertTrue(tetris.board.isValidAndEmpty(type, 1, 1, 0));
		assertFalse(tetris.board.isValidAndEmpty(type, 5, 5, 0));
		assertFalse(tetris.board.isValidAndEmpty(type, 5, 6, 1));
		assertFalse(tetris.board.isValidAndEmpty(type, 6, 6, 2));
		assertFalse(tetris.board.isValidAndEmpty(type, 6, 5, 3));
	}

	@Test
	public void testCheckLines() {
		
		assertEquals(0, bp.checkLines());
	}

}
