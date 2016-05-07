package Lab5;

public class CoffeeBased implements SizeFactor {

	private String size;
	
	public CoffeeBased(String s){	// constructor stores size to calculate price later
		size = s;
	};
	
	public double cost(){
		double c = 0.0;
		switch(size.toUpperCase()){
		case "SMALL":
			c = 0.4;
			break;
		case "MEDIUM":
			c = 0.7;
			break;
		case "LARGE":
			c = 1.0;
			break;
		default:		// bad input
			System.out.println("Please enter a size from the menu");
		}
		return c;
	}
}