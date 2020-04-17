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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		tetris = new Tetris();
		type = tetris.getPieceType();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsValidAndEmpty() {
		assertFalse("Column fail", bp.isValidAndEmpty(type, -1, 5, 0));
		assertFalse("Row fail", bp.isValidAndEmpty(type, 4, -20, 0));
		assertTrue("Overall success", bp.isValidAndEmpty(type, 5, 5, 0));
	}

	@Test
	public void testAddPiece() {
		assertTrue("Type check success", type.isTile(4, 4, 0));
		assertTrue("Type check fail", type.isTile(-5, -5, 0));
	}

	@Test
	public void testCheckLines() {
		assertEquals(0, bp.checkLines());
	}

}
