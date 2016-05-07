package Lab5;

public class Milk extends BeverageWithIngredient{

	private Beverage b;
	 
	public Milk(Beverage bev) {
		b = bev;
		description = b.description + ", Milk";
	}
	
	@Override
	public double cost(){
		return b.cost() + 0.3;
	}
}
