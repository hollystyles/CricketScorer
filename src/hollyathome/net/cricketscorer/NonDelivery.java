package hollyathome.net.cricketscorer;

public class NonDelivery extends Delivery {

	public NonDelivery(){
		this.type = "NonDelivery";
		this.complete = false;
		this.extra = false;
		this.boundary = false;
		this.conceded = this.ran = this.runs = 0;
	}
}
