package Lab5;

public class Decaf extends CoffeeBeverage{

	public Decaf(String size){
		this.setSize(new CoffeeBased(size));
		this.description = size + " Decaf";
	}
	
	@Override
	public double cost(){
		return getSize().cost() + 0.5;
	}
}