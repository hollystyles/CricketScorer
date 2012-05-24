package hollyathome.net.cricketscorer;

public class Game {
	private Team homeTeam;
	private Team visitingTeam;
	private int numOvers;	
	private Innings currentInnings;
	
	public Game(Team home, Team visitors, int numOvers){
		homeTeam = home;
		visitingTeam = visitors;
		this.numOvers = numOvers;
	}
	
	public Innings startGame(Team battingTeam){
		Team.resetPlayerNumbers();
		if(homeTeam.getName().equals(battingTeam.getName())){
			currentInnings = new Innings(homeTeam, visitingTeam);
		}
		else{
			currentInnings = new Innings(visitingTeam, homeTeam);
		}
		return currentInnings;
	}
	
	public int getOversRemaining(){
		return (numOvers - currentInnings.getNumCompletedOvers());
	}
	
	public int getTotalBallsRemaining(){
		return ((numOvers * 6) - (currentInnings.getNumCompletedOvers() * 6));
	}
}
