package Lab5;

public class Espresso extends CoffeeBeverage{

	public Espresso(String size){
		this.setSize(new CoffeeBased(size));
		this.description = size + " Espresso";
	}
	
	@Override
	public double cost(){
		return getSize().cost() + 1.0;
	}
}