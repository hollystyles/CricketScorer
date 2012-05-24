/**
 * 
 */
package hollyathome.net.cricketscorer;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author paul
 *
 */
public class PlayerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void playerTest() {
		
		Player p = new Player("Joe", "Bloggs");
		assertEquals("J.Bloggs", p.getPlayersName());
	}
	
	@Test
	public void testBatsmanScore(){
		
		Team.resetPlayerNumbers();
		
		Team home = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		Player p = new Player("Joe", "Bloggs");
		home.addPlayer(p);
		Game g = new Game(home, visitors, 20);
		Innings inns = g.startGame(home);
		
		inns.addDelivery(new Ran(false, 4));
		inns.addDelivery(new Bye(false, 1));
		
		assertEquals(4, p.getBattingScore());
		
	}
	
	@Test
	public void testBowlerRunsConceded(){
		
		Team.resetPlayerNumbers();
		
		Team home = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		Player p = new Player("Joe", "Bloggs");
		home.addPlayer(p);
		Game g = new Game(home, visitors, 20);
		Innings inns = g.startGame(visitors);
		
		//Byes do not count against the bowler
		inns.addDelivery(new Bye(1));
		inns.addDelivery(new LegBye(1));
		inns.addDelivery(new DotBall());
		
		//Wides/No balls and runs count against the bowler
		inns.addDelivery(new NoBall(false, 0));
		inns.addDelivery(new Wide(1));
		inns.addDelivery(new Ran(2));
		
		inns.addDelivery(new DotBall());
		inns.addDelivery(new DotBall());
				
		assertEquals(5, p.getRunsConceded());
	}

}
