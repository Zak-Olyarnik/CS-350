package Lab5;

public class Ginger extends BeverageWithIngredient{

	private Beverage b;
	 
	public Ginger(Beverage bev) {
		b = bev;
		description = b.description + ", Ginger";
	}
	
	@Override
	public double cost(){
		return b.cost() + 0.6;
	}
}
