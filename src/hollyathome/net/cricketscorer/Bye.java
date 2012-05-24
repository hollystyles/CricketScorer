package hollyathome.net.cricketscorer;

public class Bye extends Delivery {

	public Bye(int runs){
		this.boundary = false;
		initialize(runs);
	}
	
	public Bye(boolean wasBoundary, int runs){
		this.boundary = wasBoundary;
		initialize(runs);
	}
	
	private void initialize(int runs){
		this.type = "Bye";
		this.complete = true;
		this.extra = true;
		this.ran = this.conceded = 0;
		this.runs = runs;
	}
	
}
