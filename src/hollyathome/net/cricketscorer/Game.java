package hollyathome.net.cricketscorer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable{
	private Team homeTeam;
	private Team visitingTeam;
	private int numOvers;	
	private Innings currentInnings;
	private List<Innings> innings;
	
	public Game(Team home, Team visitors, int numOvers){
		this.homeTeam = home;
		this.visitingTeam = visitors;
		this.numOvers = numOvers;
		this.innings = new ArrayList<Innings>();
		this.currentInnings = null;
	}
	
	public Innings startGame(Team battingTeam){
		
		if(homeTeam.getName().equals(battingTeam.getName())){
			currentInnings = new Innings(homeTeam, visitingTeam, numOvers);
		}
		else{
			currentInnings = new Innings(visitingTeam, homeTeam, numOvers);
		}
		
		innings.add(currentInnings);
		
		return currentInnings;
	}
	
	public Innings startNextInnings(){
		
		if(homeTeam.getName() == currentInnings.getBattingTeam().getName()){
			currentInnings = new Innings(visitingTeam, homeTeam, numOvers);
		}else{
			currentInnings = new Innings(homeTeam, visitingTeam, numOvers);
		}
		
		innings.add(currentInnings);
		
		return currentInnings;
	}
	
	public Team getHomeTeam(){
		return homeTeam;
	}
	
	public Team getVistingTeam(){
		return visitingTeam;
	}
	
	public int getOversRemaining(){
		return (numOvers - currentInnings.getNumCompletedOvers());
	}
	
	public int getTotalBallsRemaining(){
		return ((numOvers * 6) - (currentInnings.getNumCompletedOvers() * 6));
	}
	
	public Innings getInnings(int index){
		return innings.get(index);
	}
	
	public Innings getCurrentInnings(){
		return currentInnings;
	}
	
	public boolean isCompleted(){
		return (innings.size() == 2 && currentInnings.isCompleted());
	}
}
