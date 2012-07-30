package hollyathome.net.cricketscorer;

public class RunOut extends Wicket {

	public RunOut(Player fielder, Player batsmanOut){
		this.type = "RunOut";
		this.fielder = fielder;
		this.batsman = batsmanOut;
	}
	
}
