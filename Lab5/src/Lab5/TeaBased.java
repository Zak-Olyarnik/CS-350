package Lab5;

public class TeaBased implements SizeFactor {

	private String size;
	
	public TeaBased(String s){		// constructor stores size to calculate price later
		size = s;
	};
	
	public double cost(){
		double c = 0.0;
		switch(size.toUpperCase()){
		case "SMALL":
			c = 0.2;
			break;
		case "MEDIUM":
			c = 0.5;
			break;
		case "LARGE":
			c = 0.7;
			break;
		default:		// bad input
			System.out.println("Please enter a size from the menu");
		}
		return c;
	}
}