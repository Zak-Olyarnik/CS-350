package Lab5;

public class Jasmine extends BeverageWithIngredient{

	private Beverage b;
	 
	public Jasmine(Beverage bev) {
		b = bev;
		description = b.description + ", Jasmine";
	}
	
	@Override
	public double cost(){
		return b.cost() + 0.5;
	}
}
