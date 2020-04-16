import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardPanelTest {
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
		//fail("Not yet implemented");
	}

	@Test
	public void testAddPiece() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCheckLines() {
		assertEquals(0, bp.checkLines());
	}

}
