import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;


public class ControllerTest {

	private HangmanGame hm;
	private JFrame frame;
	
	@Before
	public void setUp() throws Exception {
		hm = new EvilHangMan(6, 8);
		frame = new JFrame("Evil Hangman");
	}
	
	@Test
	public void controllerTestWin() {
		
		// correctly guess the word and see if the game ends
				hm = new EvilHangMan(4, 16);
				//hm.makeGuess('A');
				String[] labels = hm.controller('A', frame);
				assertEquals("Nope!", labels[3]);
				assertFalse(hm.game.gameOver());
				hm.controller('E', frame);
				hm.controller('I', frame);
				hm.controller('O', frame);
				hm.controller('U', frame);
				hm.controller('R', frame);
				hm.controller('S', frame);
				hm.controller('T', frame);
				hm.controller('L', frame);
				assertTrue(hm.isEvil);
				hm.controller('N', frame);
				//assertTrue(hm.IsEvil);
				// at this point, the correct word is WYCH
				
				labels = hm.controller('W', frame);
				assertTrue(hm.game instanceof NormalHangMan); 
				assertEquals("Yes!", labels[3]);
				assertFalse(hm.isEvil);
				hm.controller('Y', frame);
				hm.controller('C', frame);
				labels = hm.controller('H', frame);
				assertTrue(hm.game.gameOver());
				assertTrue(hm.game.isWin());
	}
	
	@Test
	public void controllerTestLose() {
		// incorrectly guess the word and see if the game ends
		hm = new EvilHangMan(4, 4);
		hm.controller('A', frame);
		hm.controller('E', frame);
		hm.controller('I', frame);
		hm.controller('O', frame);
		assertTrue(hm.game.gameOver());
		assertFalse(hm.game.isWin());
	
	}

}
