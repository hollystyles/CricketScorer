package hollyathome.net.cricketscorer;

import java.util.*;

public class Team {
	private static int nextPlayerNumber;
	private String name;
	protected List<Player> players; 
	
	public Team(String teamName){
		this.name = teamName;
		players = new ArrayList<Player>();
	}
	
	public static void resetPlayerNumbers(){
		nextPlayerNumber = 0;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void addPlayer(Player playerToAdd){
		this.players.add(playerToAdd);
	}
	
	public Player getPlayer(String playerName){
		for	(Player p : players){
			if(p.getPlayersName().equals(playerName)){
				return p;
			}
		}
		return null;
	}
	
	public Player getFirstPlayer(){
		if(players.isEmpty()){
			nextPlayerNumber += 1;
			String playerName = "Player" + nextPlayerNumber;
			players.add(new Player(playerName, playerName));
		}
		return players.get(0);
	}
	
	public Player getNextPlayer(String previousPlayerName){
		int nextPlayerIndex = players.indexOf(getPlayer(previousPlayerName)) + 1;
		if(players.size() <= nextPlayerIndex){
			nextPlayerNumber += 1;
			String playerName = "Player" + nextPlayerNumber;
			players.add(new Player(playerName, playerName));
		}
		return players.get(nextPlayerIndex); 
	}
	
}
