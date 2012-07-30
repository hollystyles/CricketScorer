package hollyathome.net.cricketscorer;

import java.io.Serializable;
import java.util.*;

public class Over implements Serializable{
	
	private Player bowler;
	private List<Delivery> deliveries;
	private int overNumber;
	private int numBalls;
	
	public Over(Player bowler, int overNumber){
		this.bowler = bowler;
		deliveries = new ArrayList<Delivery>();
		this.overNumber = overNumber;
		numBalls = 0;
	}
	
	public int getOverNumber(){
		return overNumber;
	}
	
	public boolean IsComplete(){
		return (numBalls == 6);
	}
	
	public void addDelivery(Delivery deliveryToAdd){
		deliveries.add(deliveryToAdd);
		if(deliveryToAdd.isComplete()){
			numBalls += 1;
		}
	}
	
	public int getRemainingBalls(){
		return 6 - numBalls;
	}
	
	public int getRuns(){
		int runs = 0;
		for(Delivery d: deliveries){
			runs += d.getRuns();
		}
		return runs;
	}
	
	public int getExtras(){
		int extras = 0;
		for(Delivery d: deliveries){
			if(d.extra){
				extras += d.getRuns();
			}
		}
		return extras;
	}
	
	public int getNumWickets(){
		int wickets = 0;
		for(Delivery d : deliveries){
			if(d.isWicket()){
				wickets += 1;
			}
		}
		return wickets;
	}
	
	public Player getBowler(){
		return bowler;
	}
}
