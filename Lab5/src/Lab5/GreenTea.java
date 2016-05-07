package Lab5;

public class GreenTea extends TeaBeverage{

	public GreenTea(String size){
		this.setSize(new TeaBased(size));
		this.description = size + " Green Tea";
	}
	
	@Override
	public double cost(){
		return getSize().cost() + 1.0;
	}
}