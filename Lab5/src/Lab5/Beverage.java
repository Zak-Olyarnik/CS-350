package Lab5;

public class Beverage {

	protected String description;		// text description, protected so that all subclasses can access and update as ingredients are added
	private SizeFactor size;			// size of Beverage, used to calculate price
	
	public String getDescription(){
		return description;
	}
	
	public void setSize(SizeFactor s){
		size = s;
	}
	
	public SizeFactor getSize(){
		return size;
	}
	
	public double cost(){
		return size.cost() + 0.0;		// subclasses return getSize.cost() + <price of coffee or tea>
	}
}
