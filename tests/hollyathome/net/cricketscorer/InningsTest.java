package hollyathome.net.cricketscorer;

import static org.junit.Assert.*;

import org.junit.Test;

public class InningsTest {

	@Test
	public void testBowled() {
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		
		Game g = new Game(homeTeam, visitors, 20);
		
		Innings inns = g.startGame(visitors);
		
		Player player = inns.getFacingBatsman();
		
		Delivery delivery = new Ran(false, 0);
		delivery.setWicket(new Bowled());
		
		inns.addDelivery(delivery);
		
		assertEquals(1, inns.getNumWickets());
		//P.Player1 should be out
		assertEquals("Bowled", player.getStatus());
		
	}

	@Test
	public void testBoundaryFourOffNoBall(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		
		Player striker = new Player("Joe", "Bloggs");
		visitors.addPlayer(striker);
		
		Game g = new Game(homeTeam, visitors, 20);
		
		Innings inns = g.startGame(visitors);
		
		Delivery delivery = new NoBall(true, 4);
		inns.addDelivery(delivery);
		
		assertEquals(6, inns.getCurrentOverRemainingBalls());
		assertEquals(5, inns.getScore());
		assertEquals(4, striker.getBattingScore());
		assertEquals(1, striker.getNumBallsFaced());
	}
}
