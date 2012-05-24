package hollyathome.net.cricketscorer;

import static org.junit.Assert.*;

import org.junit.Test;

public class TeamTest {

	@Test
	public void getNameTest() {
		Team t = new Team("Essex CC");
		assertEquals("Essex CC", t.getName());
	}
	
	@Test
	public void addPlayerTest(){
		
		Team.resetPlayerNumbers();
		
		Team t = new Team("Essex CC");
		
		Player p = new Player("Joe", "Bloggs");
		
		t.addPlayer(p);
		
		assertEquals(p.getPlayersName(), t.getPlayer("J.Bloggs").getPlayersName());
		assertEquals(p.getPlayersName(), t.getFirstPlayer().getPlayersName());
	}

}
