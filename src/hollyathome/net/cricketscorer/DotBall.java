package hollyathome.net.cricketscorer;

public class DotBall extends Delivery {
	
	public DotBall(){
		this.type = "DotBall";
		this.complete = true;
		this.extra = false;
		this.boundary = false;
		this.runs = this.ran = this.conceded = 0;
		
	}
}
