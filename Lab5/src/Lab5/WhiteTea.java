package Lab5;

public class WhiteTea extends TeaBeverage{

	public WhiteTea(String size){
		this.setSize(new TeaBased(size));
		this.description = size + " White Tea";
	}
	
	@Override
	public double cost(){
		return getSize().cost() + 1.0;
	}
}