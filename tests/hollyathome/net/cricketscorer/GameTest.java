package hollyathome.net.cricketscorer;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void testNewGameWithNoTeamSetup() {
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		Game g = new Game(homeTeam, visitors, 20);
		
		Innings i = g.startGame(visitors);
				
		assertEquals("P.Player1", i.getFacingBatsman().getPlayersName());
		assertEquals("P.Player3", i.getCurrentBowler().getPlayersName());
	}
	
	@Test
	public void testDotBallDelivery(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		Game g = new Game(homeTeam, visitors, 20);
		
		Innings i = g.startGame(visitors);
		
		i.addDelivery(new DotBall());
		assertEquals(0, i.getScore());
	}
	
	@Test
	public void testWideDelivery(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		Game g = new Game(homeTeam, visitors, 20);
		
		Innings i = g.startGame(visitors);
		String facingBatsmanName = i.getFacingBatsman().getPlayersName();
		
		i.addDelivery(new Ran(false, 2));
		i.addDelivery(new Wide(false, 0));
		
		//Should be all runs + extras
		assertEquals(3, i.getScore());
		
		//Wide ball are extras
		assertEquals(1, i.getExtras());
		
		//batsman ran two runs, so should still be facing the bowler
		assertEquals(facingBatsmanName, i.getFacingBatsman().getPlayersName());
	}
	
	@Test
	public void testSwapBatsmanOddRuns(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		Game g = new Game(homeTeam, visitors, 20);
		
		Innings i = g.startGame(visitors);
		String secondBatsmanName = i.getSecondBatsman().getPlayersName();
		
		i.addDelivery(new Ran(1));
		
		assertEquals(secondBatsmanName, i.getFacingBatsman().getPlayersName());
	}
	
	@Test
	public void testChangeEndsEndOfOver(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		Game g = new Game(homeTeam, visitors, 20);
		
		Innings inns = g.startGame(visitors);
		Player nextBowler = inns.getNextBowler();
		
		inns.addDelivery(new Wide(false, 0));
		for(int i = 0; i < 6; i++){
			inns.addDelivery(new Ran(false, 1));
		}
		
		assertEquals(7, inns.getScore());
		assertEquals(2, inns.getCurrentOverNumber());
		assertEquals(6, inns.getCurrentOverRemainingBalls());
		assertEquals(19, g.getOversRemaining());
		assertEquals(114, g.getTotalBallsRemaining());
		assertEquals(nextBowler.getPlayersName(), inns.getCurrentBowler().getPlayersName());
	}
	
	@Test
	public void testGameCompletedAfterTwoInnings(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		
		//Just one over to keep it simple
		Game g = new Game(homeTeam, visitors, 1);
		
		Innings inns = g.startGame(visitors);
		
		for(int i = 0; i < 6; i++){
			inns.addDelivery(new DotBall());
		}
		
		inns = g.startNextInnings();
		
		for(int i = 0; i < 6; i++){
			inns.addDelivery(new DotBall());
		}
		
		assertEquals(true, g.isCompleted());
		
	}
	
	@Test
	public void testGameNotCompleteAfterOneInnings(){
		
		Team.resetPlayerNumbers();
		
		Team homeTeam = new Team("Essex CC");
		Team visitors = new Team("Surrey CC");
		
		//Just one over to keep it simple
		Game g = new Game(homeTeam, visitors, 1);
		
		Innings inns = g.startGame(visitors);
		
		for(int i = 0; i < 6; i++){
			inns.addDelivery(new DotBall());
		}
		
		inns = g.startNextInnings();
		
		for(int i = 0; i < 5; i++){
			inns.addDelivery(new DotBall());
		}
		
		assertEquals(false, g.isCompleted());
		
	}

}
