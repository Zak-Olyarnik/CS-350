package Lab5;

public class BeverageWithIngredient extends Beverage{

	/* all subclasses have this form:
	 * 
	 * private Beverage b;			// the Beverage being wrapped by this decorator
	 * 
	 * public BeverageWithIngredient(Beverage bev) {
	 * 		b = bev;
	 * 		description = b.description + ", <BeverageWithIngredient>";			// updates description
	 * }
	 * 
	 * @Override
	 * public double cost(){		// adds the ingredient price to the base cost of the Beverage being wrapped
	 * 		return b.cost() + <cost of BeverageWithIncredient>;
	 * }
	 * 
	 */
	
	private Beverage b;
	
	// methods here are unnecessary in this system (they will already be inherited from superclass Beverage), but present on the UML
	@Override
	public String getDescription(){
		return description;
	}
	
	@Override
	public double cost(){
		return 0.0;
	}
}
