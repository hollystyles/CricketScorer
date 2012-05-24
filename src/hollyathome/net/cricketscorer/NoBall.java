package hollyathome.net.cricketscorer;

public class NoBall extends Delivery {
	
	public NoBall(boolean struck, int additionalRuns){
		this.boundary = false;
		initialize(struck, additionalRuns);
	}

	public NoBall(boolean struck, boolean wasBoundary, int additionalRuns){
		this.boundary = wasBoundary;
		initialize(struck, additionalRuns);		
	}
	
	private void initialize(boolean struck, int additionalRuns){
		this.type = "NoBall";
		this.complete = false;
		this.extra = true;
		this.conceded = this.runs = 1 + additionalRuns;
		if(struck){
			this.ran = additionalRuns;
		}
	}
	
}
