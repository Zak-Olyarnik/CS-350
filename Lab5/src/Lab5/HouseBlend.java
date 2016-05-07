package Lab5;

public class HouseBlend extends CoffeeBeverage{

	public HouseBlend(String size){
		this.setSize(new CoffeeBased(size));
		this.description = size + " House Blend";
	}
	
	@Override
	public double cost(){
		return getSize().cost() + 0.8;
	}
}
