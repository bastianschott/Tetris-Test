

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardPanelTestSandra {
	Tetris tetris;
	BoardPanel bp = new BoardPanel(tetris);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testIsValidAndEmpty() {
		Tetris tt = new Tetris();
		// Teil TypeJ
		TileType tile = TileType.values()[2];
		
		// Try left boundary
		assertFalse(tt.board.isValidAndEmpty(tile, -1, 11, 0));
		assertTrue(tt.board.isValidAndEmpty(tile, 0, 11, 0));
		// Try right boundary
		assertFalse(tt.board.isValidAndEmpty(tile, 8, 11, 0));
		assertTrue(tt.board.isValidAndEmpty(tile, 7, 11, 0));
		// Try too high rows
		assertFalse(tt.board.isValidAndEmpty(tile, 2, -1, 0));
		assertTrue(tt.board.isValidAndEmpty(tile, 2, 0, 0));
		// Try too low rows
		assertFalse(tt.board.isValidAndEmpty(tile, 2, 21, 0));
		assertTrue(tt.board.isValidAndEmpty(tile, 2, 20, 0));
		
		// IsEmpty
		tt.board.addPiece(tile, 5, 4, 0);
		//exactly the same position
		assertFalse(tt.board.isValidAndEmpty(tile, 5, 4, 0));
		//overlap on right side in one field
		assertFalse(tt.board.isValidAndEmpty(tile, 7, 4, 0));
		//overlap on left side in one field
		assertFalse(tt.board.isValidAndEmpty(tile, 3, 4, 0));
		//pieces do not overlap
		assertTrue(tt.board.isValidAndEmpty(tile, 1, 1, 0));
	}

	@Test public void testAddPiece() {
		Tetris tt = new Tetris();
		// Teil TypeJ
		TileType baustein = TileType.values()[2];
		tt.board.addPiece(baustein, 5, 4, 0);
		assertFalse(tt.board.isValidAndEmpty(baustein, 5, 4, 0));
		assertTrue(tt.board.isValidAndEmpty(baustein, 1, 1, 0));
	 }

	@Test
	public void testCheckLines() {
		BoardPanel bp = new BoardPanel(null);
		assertEquals(0, bp.checkLines());
	}

}