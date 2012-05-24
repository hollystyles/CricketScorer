package hollyathome.net.cricketscorer;

import java.util.*;

public class Player {
	private String firstName;
	private String lastName;
	private List<Delivery> ballsFaced;
	private List<Delivery> ballsBowled;
	private String status = "NotOut";
	
	public Player(String playerFirstName, String playerLastName){
		this.firstName = playerFirstName;
		this.lastName = playerLastName;
		this.ballsFaced = new ArrayList<Delivery>();
		this.ballsBowled = new ArrayList<Delivery>();
	}
	
	public String getPlayersName(){
		return this.firstName.substring(0,1) + "." + this.lastName;
	}
	
	public void setPlayersName(String first, String last){
		this.firstName = first;
		this.lastName = last;
	}
	
	public void addBallFaced(Delivery deliveryToAdd){
		if(deliveryToAdd.isWicket()){
			Wicket w = deliveryToAdd.getWicket();
			status = w.getType();
			w.setBatsman(this);
			
		}
		ballsFaced.add(deliveryToAdd);
	}
	
	public void addBallBowled(Delivery deliveryToAdd){
		if(deliveryToAdd.isWicket()){
			Wicket w = deliveryToAdd.getWicket();
			w.setBowler(this);
		}
		ballsBowled.add(deliveryToAdd);	
	}
	
	public int getBattingScore(){
		int score = 0;
		for(Delivery d: ballsFaced){
			score += d.ran;
		}
		return score;
	}
	
	public int getRunsConceded(){
		int conceded = 0;
		for(Delivery d: ballsBowled){
			conceded += d.getConceded();
		}
		return conceded;
	}
	
	public int getNumBallsFaced(){
		return ballsFaced.size();
	}
	
	public String getStatus(){
		return status;
	}
}
