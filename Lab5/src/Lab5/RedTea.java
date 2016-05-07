package Lab5;

public class RedTea extends TeaBeverage{

	public RedTea(String size){
		this.setSize(new TeaBased(size));
		this.description = size + " Red Tea";
	}
	
	@Override
	public double cost(){
		return getSize().cost() + 0.8;
	}
}