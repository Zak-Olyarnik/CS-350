package Lab5;

public class Chocolate extends BeverageWithIngredient{

	private Beverage b;
	 
	public Chocolate(Beverage bev) {
		b = bev;
		description = b.description + ", Chocolate";
	}

	@Override
	public double cost(){
		return b.cost() + 0.3;
	}
}
