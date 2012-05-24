package hollyathome.net.cricketscorer;

import java.util.*;

public class Innings {
	
	private Team battingTeam;
	private Team fieldingTeam;
	private Player facingBatsman;
	private Player secondBatsman;
	private Player currentBowler;
	private Player nextBowler;
	private List<Over> overs;
	private Over currentOver;
	
	public Innings(Team battingTeam, Team fieldingTeam){
		this.battingTeam = battingTeam;
		this.fieldingTeam = fieldingTeam;
		this.facingBatsman = battingTeam.getFirstPlayer();
		this.secondBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
		this.currentBowler = fieldingTeam.getFirstPlayer();
		this.nextBowler = fieldingTeam.getNextPlayer(currentBowler.getPlayersName());
		this.overs = new ArrayList<Over>();
		this.currentOver = new Over(currentBowler, 1);
		this.overs.add(currentOver);
	}
	
	public Player getFacingBatsman(){
		return this.facingBatsman;
	}
	
	public Player getSecondBatsman(){
		return this.secondBatsman;
	}

	public Player getCurrentBowler(){
		return this.currentBowler;
	}
	
	public Player getNextBowler(){
		return nextBowler;
	}
	
	public void setFacingBatsman(String playersName){
		Player p = battingTeam.getPlayer(playersName);
		if(p == null){
			String first = playersName.split("\\.")[0];
			String last = playersName.split("\\.")[1];
			p = new Player(first, last);
			battingTeam.addPlayer(p);
		}
		facingBatsman = p;
	}
	
	public void addDelivery(Delivery delivery){
		delivery.setOver(currentOver);
		this.currentOver.addDelivery(delivery);
		facingBatsman.addBallFaced(delivery);
		currentBowler.addBallBowled(delivery);
		//If 6 balls have been bowled swap bowlers;
		if(currentOver.IsComplete()){
			Player temp = currentBowler;
			currentBowler = nextBowler;
			nextBowler = temp;
			currentOver = new Over(currentBowler, currentOver.getOverNumber() + 1);
			overs.add(currentOver);
		}
		// else if an odd number of runs swap facing batsman
		else if((delivery.getRan() % 2) != 0){
			Player temp = facingBatsman;
			facingBatsman = secondBatsman;
			secondBatsman = temp;
		}
	}
	
	public int getScore(){
		int runs = 0;
		for(Over o: overs){
			runs += o.getRuns();
		}
		return runs;
	}
	
	public int getExtras(){
		int extras = 0;
		for(Over o: overs){
			extras += o.getExtras();
		}
		return extras;
	}
	
	public Over getCurrentOver(){
		return currentOver;
	}
	
	public int getCurrentOverNumber(){
		return currentOver.getOverNumber();
	}
	
	public int getNumCompletedOvers(){
		return currentOver.getOverNumber() - 1;
	}
	
	public int getCurrentOverRemainingBalls(){
		return currentOver.getRemainingBalls();
	}
	
	public int getNumWickets(){
		int wickets = 0;
		for(Over o: overs){
			wickets += o.getNumWickets();
		}
		return wickets;
	}
}
