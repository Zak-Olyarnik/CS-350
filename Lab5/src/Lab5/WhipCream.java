package Lab5;

public class WhipCream extends BeverageWithIngredient{

	private Beverage b;
	 
	public WhipCream(Beverage bev) {
		b = bev;
		description = b.description + ", Whip Cream";
	}
	
	@Override
	public double cost(){
		return b.cost() + 0.3;
	}
}
