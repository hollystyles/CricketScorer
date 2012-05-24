package hollyathome.net.cricketscorer;

public class Ran extends Delivery {
	
	public Ran(int runs){
		this.boundary = false;
		initialize(runs);
	}
	
	public Ran(boolean wasBoundary, int runs){
		this.boundary = wasBoundary;
		initialize(runs);
	}
	
	private void initialize(int runs){
		this.type="Ran";
		this.complete = true;
		this.extra = false;
		this.conceded = this.ran = this.runs = runs;
	}
}
