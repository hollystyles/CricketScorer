package hollyathome.net.cricketscorer;

public class LegBye extends Bye {
	
	public LegBye(int runs){
		super(runs);
		initialize();
	}
	
	public LegBye(boolean wasBoundary, int runs){
		super(wasBoundary, runs);
		initialize();
	}
	
	private void initialize(){
		this.type = "LegBye";		
	}
	
}
