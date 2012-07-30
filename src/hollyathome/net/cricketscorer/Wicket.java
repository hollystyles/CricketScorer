package hollyathome.net.cricketscorer;

import java.io.Serializable;

public abstract class Wicket implements Serializable{

	protected Player bowler;
	protected Player fielder;
	protected Player batsman;
	protected String type;
	
	public String getType(){
		return type;
	}
	
	public Player getBowler(){
		return bowler;
	}
	
	public Player getFielder(){
		return fielder;
	}
	
	public Player getBatsman(){
		return batsman;
	}
	
	public void setBowler(Player bowler){
		this.bowler = bowler;
	}
	
	public void setFielder(Player fielder){
		this.fielder = fielder;
	}
	
	public void setBatsman(Player batsman){
		this.batsman = batsman;
	}
	
}
