package hollyathome.net.cricketscorer;

import hollyathome.net.cricketscorer.Team.TeamStatus;

import java.io.Serializable;
import java.util.*;

public class Innings implements Serializable {
	
	private Team battingTeam;
	private Team fieldingTeam;
	private Player facingBatsman;
	private Player secondBatsman;
	private Player currentBowler;
	private Player nextBowler;
	private List<Over> overs;
	private Over currentOver;
	private int numOvers;
	
	public Innings(Team battingTeam, Team fieldingTeam, int numOvers){
		this.battingTeam = battingTeam;
		this.battingTeam.setStatus(TeamStatus.Batting);
		this.fieldingTeam = fieldingTeam;
		this.fieldingTeam.setStatus(TeamStatus.Fielding);
		this.numOvers = numOvers;
		this.facingBatsman = battingTeam.getFirstPlayer();
		this.secondBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
		this.currentBowler = fieldingTeam.getFirstPlayer();
		this.nextBowler = fieldingTeam.getNextPlayer(currentBowler.getPlayersName());
		this.overs = new ArrayList<Over>();
		this.currentOver = new Over(currentBowler, 1);
		this.overs.add(currentOver);
	}
	
	public Team getFieldingTeam(){
		return fieldingTeam;
	}
	
	public Team getBattingTeam(){
		return battingTeam;
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
	
	public void setSecondBatsman(String playersName){
		Player p = battingTeam.getPlayer(playersName);
		if(p == null){
			String first = playersName.split("\\.")[0];
			String last = playersName.split("\\.")[1];
			p = new Player(first, last);
			battingTeam.addPlayer(p);
		}
		secondBatsman = p;
	}
	
	public void setBowler(String playersName){
		Player p = fieldingTeam.getPlayer(playersName);
		if(p == null){
			String first = playersName.split("\\.")[0];
			String last = playersName.split("\\.")[1];
			p = new Player(first, last);
			fieldingTeam.addPlayer(p);
		}
		currentBowler = p;
	}
	
	public void addDelivery(Delivery delivery){
		
		delivery.setOver(currentOver);
		this.currentOver.addDelivery(delivery);
		facingBatsman.addBallFaced(delivery);
		currentBowler.addBallBowled(delivery);
		
		//Was it a wicket?
		if(delivery.isWicket()){
			processWicket(delivery.getWicket());
		}
		
		//If 6 balls have been bowled swap bowlers;
		if(currentOver.IsComplete()){
			
			if(!this.isCompleted()){
				
				swapBowler();
				
				if(!delivery.isWicket() && (delivery.getRan() % 2) == 0){
					
					swapBatsman();
					
				}
				
				currentOver = new Over(currentBowler, currentOver.getOverNumber() + 1);
				overs.add(currentOver);
			}
			
		}
		// else if an odd number of runs swap facing batsman
		else if(!delivery.isWicket()){
			
			int runs = delivery.getRan();
			
			if(delivery.getType().equals("Bye") || delivery.getType().equals("LegBye")){
				runs = delivery.getRuns();
			}else if(delivery.getType().equals("Wide") || delivery.getType().equals("NoBall")){
				runs = delivery.getRuns() - 1;
			}			
			
			if((runs % 2) != 0){
				swapBatsman();
			}
			
		}
	}
	
	private void processWicket(Wicket wicket){
		if(wicket.getType() == "Caught"
			|| wicket.getType() == "Bowled"
			|| wicket.getType() == "LBW"
			|| wicket.getType() == "Stumped"){
			facingBatsman.setHowOut(wicket);
			facingBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
		}else if(wicket.getType() == "RunOut"){
			if(wicket.getBatsman() == facingBatsman){
				facingBatsman.setHowOut(wicket);
				facingBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
			}else{
				secondBatsman.setHowOut(wicket);
				secondBatsman = battingTeam.getNextPlayer(secondBatsman.getPlayersName());
			}			
		}else if(wicket.getType() == "CaughtAndBowled"){
			facingBatsman.setHowOut(wicket);
			facingBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
		}else if(wicket.getType() == "HitWicket"){
			facingBatsman.setHowOut(wicket);
			facingBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
		}else if(wicket.getType() == "RetiredOut"){
			if(wicket.getBatsman() == facingBatsman){
				facingBatsman.setHowOut(wicket);
				facingBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
			}else{
				secondBatsman.setHowOut(wicket);
				secondBatsman = battingTeam.getNextPlayer(secondBatsman.getPlayersName());
			}
		}else if(wicket.getType() == "HandledBall"){
			facingBatsman.setHowOut(wicket);
			facingBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
		}else if(wicket.getType() == "TimedOut"){
			facingBatsman.setHowOut(wicket);
			facingBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
		}else if(wicket.getType() == "HitTwice"){
			facingBatsman.setHowOut(wicket);
			facingBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
		}else if(wicket.getType() == "ObstructingField"){
			if(wicket.getBatsman() == facingBatsman){
				facingBatsman.setHowOut(wicket);
				facingBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
			}else{
				secondBatsman.setHowOut(wicket);
				secondBatsman = battingTeam.getNextPlayer(secondBatsman.getPlayersName());
			}
		}
	}
	
	public void retireBatsman(String playersName){
		if(facingBatsman.getPlayersName() == playersName){
			facingBatsman.setStatus("Retired Not-Out");
			facingBatsman = battingTeam.getNextPlayer(facingBatsman.getPlayersName());
		}else{
			secondBatsman.setStatus("Retired Not-Out");
			secondBatsman = battingTeam.getNextPlayer(secondBatsman.getPlayersName());
		}
	}
	
	private void swapBowler(){
		Player temp = currentBowler;
		currentBowler = nextBowler;
		nextBowler = temp;
	}
	
	private void swapBatsman(){
		Player temp = facingBatsman;
		facingBatsman = secondBatsman;
		secondBatsman = temp;
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
		if(!this.isCompleted()){
			return currentOver.getOverNumber() - 1;
		}else{
			return currentOver.getOverNumber();
		}
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
	
	public boolean isCompleted(){
		return (overs.size() == numOvers && currentOver.IsComplete()) || getNumWickets() == 10;
	}
}
