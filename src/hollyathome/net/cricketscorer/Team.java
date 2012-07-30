package hollyathome.net.cricketscorer;

import java.io.Serializable;
import java.util.*;

public class Team implements Serializable{
	
	private static int nextPlayerNumber;
	private String name;
	private TeamStatus status;
	protected List<Player> players;
	
	public enum TeamStatus {None, Batting, Fielding, AllOut};
	
	public Team(String teamName){
		this.name = teamName;
		this.players = new ArrayList<Player>();
		this.status = TeamStatus.None;
	}
	
	public void setStatus(TeamStatus status){
		this.status = status;
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
	
	private Player createNewAnonymousPlayer(){
		nextPlayerNumber += 1;
		String playerName = "Player" + nextPlayerNumber;
		Player anonPlayer = new Player(playerName, playerName);
		players.add(anonPlayer);
		return anonPlayer;
	}
	
	public Player getFirstPlayer(){
		if(players.isEmpty()){
			createNewAnonymousPlayer();
		}
		Player first = players.get(0);
		first.setInPlay();
		return players.get(0);
	}
	
	public Player getNextPlayer(String previousPlayerName){
		
		if(status == TeamStatus.Batting){
			return getNextBatsman(previousPlayerName);
		}
		
		if(status == TeamStatus.Fielding){
			return getNextBowler(previousPlayerName);
		}
		
		return null;
	}
	
	private Player getNextBowler(String previousPlayerName){
		
		Player nextPlayer = null;
		for(Player p: players){
			if(!p.getPlayersName().equals(previousPlayerName)){
				nextPlayer = p;
			}
		}
		
		if(nextPlayer == null){
			nextPlayer = createNewAnonymousPlayer();
		}
		
		return nextPlayer;
	}
	
	private Player getNextBatsman(String previousPlayerName){
		
		Player nextPlayer = null;
		for(Player p: players){
			if(!p.getPlayersName().equals(previousPlayerName)
					&& !p.isInPlay()
					&& p.getHowOut() == null){
				nextPlayer = p;
				break;
			}
		}
		
		if(nextPlayer == null){
			nextPlayer = createNewAnonymousPlayer();
		}
		
		nextPlayer.setInPlay();
		return nextPlayer;
	}
	
	public String[] getPlayerNames(String status){
		String[] names;
		int i = 0;
		if(status.equals("Any")){
			names = new String[players.size()];
			for(Player p: players){
				names[i] = p.getPlayersName();
				i++;
			}
		}else{
			List<String> subList = new ArrayList<String>();
			for(Player p: players){
				if(p.getStatus().equals(status)){
					subList.add(p.getPlayersName());
				}
			}
			names = new String[subList.size()];
			for(String s: subList){
				names[i] = s;
				i++;
			}
		}
		
		return names;
	}
	
	public int getPlayerCount(){
		return players.size();
	}
	
	public void resetPlayers(){
		for(Player p: players){
			p.reset();
		}
	}
	
}
