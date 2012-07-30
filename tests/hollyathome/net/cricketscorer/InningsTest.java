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
	
	@Test
	public void testRunoutStrikerNoRunsCompleted(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		
		Game g = new Game(homeTeam, visitors, 20);
		
		Innings inns = g.startGame(visitors);
		
		assertEquals("P.Player1", inns.getFacingBatsman().getPlayersName());
		assertEquals("P.Player2", inns.getSecondBatsman().getPlayersName());
		
		Delivery delivery = new Ran(1);
		inns.addDelivery(delivery);
		
		assertEquals("P.Player2", inns.getFacingBatsman().getPlayersName());
		assertEquals("P.Player1", inns.getSecondBatsman().getPlayersName());
		
		delivery = new Ran(0);
		Wicket wicket = new RunOut(
				inns.getFieldingTeam().getPlayer("P.Player4"), 
				inns.getFacingBatsman());
		delivery.setWicket(wicket);
		inns.addDelivery(delivery);
		
		assertEquals("P.Player5", inns.getFacingBatsman().getPlayersName());
		assertEquals("P.Player1", inns.getSecondBatsman().getPlayersName());
	}
	
	@Test
	public void testRunoutNonStrikerNoRunsCompleted(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		
		Game g = new Game(homeTeam, visitors, 20);
		
		Innings inns = g.startGame(visitors);
		
		assertEquals("P.Player1", inns.getFacingBatsman().getPlayersName());
		assertEquals("P.Player2", inns.getSecondBatsman().getPlayersName());
		
		Delivery delivery = new Ran(0);
		Wicket wicket = new RunOut(
				inns.getFieldingTeam().getPlayer("P.Player4"), 
				inns.getSecondBatsman());
		delivery.setWicket(wicket);
		inns.addDelivery(delivery);
		
		assertEquals("RunOut", wicket.getBatsman().getStatus());
		assertEquals("P.Player1", inns.getFacingBatsman().getPlayersName());
		assertEquals("P.Player5", inns.getSecondBatsman().getPlayersName());
		
	}
	
	@Test
	public void testInningsCompleteAfterAllOversComplete(){
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		
		//Just one over to keep it simple
		Game g = new Game(homeTeam, visitors, 1);
		
		Innings inns = g.startGame(visitors);
		
		for(int i = 0; i < 6; i++){
			inns.addDelivery(new DotBall());
		}
		
		assertEquals(true, inns.isCompleted());
		
	}
	
	@Test
	public void testInningsNotCompleteWhenOversLeft(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		
		//Just two overs to keep it simple
		Game g = new Game(homeTeam, visitors, 2);
		
		Innings inns = g.startGame(visitors);
		
		//Complete just 1 over
		for(int i = 0; i < 6; i++){
			inns.addDelivery(new DotBall());
		}
		
		assertEquals(false, inns.isCompleted());
		
	}
	
	@Test
	public void testSwapBatsmanAferSingleBye()
	{
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		
		//Just two overs to keep it simple
		Game g = new Game(homeTeam, visitors, 2);
		
		Innings inns = g.startGame(visitors);
		
		inns.addDelivery(new Bye(1));
		
		assertEquals("P.Player2", inns.getFacingBatsman().getPlayersName());
		
		inns.addDelivery(new LegBye(1));
		
		assertEquals("P.Player1", inns.getFacingBatsman().getPlayersName());
	}
	
	@Test
	public void testSwapBatsmanFromTwoWides(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		
		//Just two overs to keep it simple
		Game g = new Game(homeTeam, visitors, 2);
		
		Innings inns = g.startGame(visitors);
		
		inns.addDelivery(new Wide(1));
		
		assertEquals("P.Player2", inns.getFacingBatsman().getPlayersName());
		
	}
	
	@Test
	public void testSwapBatsmanAfterNoBall(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		
		//Just two overs to keep it simple
		Game g = new Game(homeTeam, visitors, 2);
		
		Innings inns = g.startGame(visitors);
		
		inns.addDelivery(new NoBall(false, 1));
		
		assertEquals("P.Player2", inns.getFacingBatsman().getPlayersName());
		
		
	}
}
