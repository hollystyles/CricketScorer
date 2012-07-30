package hollyathome.net.cricketscorer;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable{
	private String firstName;
	private String lastName;
	private List<Delivery> ballsFaced;
	private List<Delivery> ballsBowled;
	private String status = "Not Out";
	private Wicket howOut;
	private boolean inPlay = false;
	
	public Player(String playerFirstName, String playerLastName){
		this.firstName = playerFirstName;
		this.lastName = playerLastName;
		this.ballsFaced = new ArrayList<Delivery>();
		this.ballsBowled = new ArrayList<Delivery>();
	}
	
	public boolean isInPlay(){
		return this.inPlay;
	}
	
	public void setInPlay(){
		this.inPlay = true;
	}
	
	public String getPlayersName(){
		return this.firstName.substring(0,1) + "." + this.lastName;
	}

	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void setPlayersName(String first, String last){
		this.firstName = first;
		this.lastName = last;
	}
	
	public void addBallFaced(Delivery deliveryToAdd){
		ballsFaced.add(deliveryToAdd);
	}
	
	public void setHowOut(Wicket wicket){
		this.status = wicket.getType();
		this.howOut = wicket;
		this.inPlay = false;
	}
	
	public Wicket getHowOut(){
		return this.howOut;
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
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public void reset(){
		ballsBowled.clear();
		ballsFaced.clear();
		howOut = null;
		status = "Not Out";
		inPlay = false;
	}
	
	public int getNumOversBowled(){
		int validDeliveries = 0;
		for(Delivery d: ballsBowled){
			if(d.isComplete()){
				validDeliveries += 1;
			}
		}
		
		return validDeliveries / 6;
	}
}
