package hollyathome.net.cricketscorer;

import java.io.Serializable;

public abstract class Delivery implements Serializable {
	protected int runs;
	protected int ran;
	protected int conceded;
	protected boolean extra;
	protected boolean complete;
	protected boolean boundary;
	protected Wicket wicket;
	protected String type;
	protected Over over;
	
	public String getType(){
		return type;
	}
	
	public int getRuns(){
		return runs;
	}
	
	public int getRan(){
		return this.ran;
	}
	
	public int getConceded(){
		return conceded;
	}
	
	public boolean isBoundary(){
		return boundary;
	}
	
	public boolean isExtra(){
		return extra;
	}
	
	public boolean isComplete(){
		return complete;
	}	
	
	public boolean isWicket(){
		return (wicket != null);
	}
	
	public Wicket getWicket(){
		return wicket;
	}
	
	public void setWicket(Wicket wicket){
		this.wicket = wicket;
	}
	
	public void setOver(Over currentOver){
		this.over = currentOver;
	}
}

