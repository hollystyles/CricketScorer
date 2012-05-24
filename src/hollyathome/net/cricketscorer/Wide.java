package hollyathome.net.cricketscorer;

public class Wide extends Delivery {
	
	public Wide(int additionalRuns){
		this.boundary = false;
		initialize(additionalRuns);
	}
	
	public Wide(boolean wasBoundary, int additionalRuns){
		this.boundary = wasBoundary;
		initialize(additionalRuns);
	}
	
	private void initialize(int additionalRuns){
		this.type = "Wide";
		this.complete = false;
		this.extra = true;
		this.ran = additionalRuns;
		this.conceded = this.runs = 1 + additionalRuns;
	}
}
