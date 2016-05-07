package Lab5;

public class Driver {

	public static void main(String[] args) {
		
		if (args.length < 2){		// must always provide at least base Beverage and size
			System.out.println("Please enter a beverage and size from the menu");
		}else{		
			
			Beverage b = null;
			switch (args[0].toUpperCase()){		// different base drinks on the menu, some automatically add ingredients
			case "ESPRESSO":
				b = new Espresso(args[1]);
				break;
			case "DECAF":
				b = new Decaf(args[1]);
				break;
			case "HOUSEBLEND":			// multi-word arguments must either be entered as one word or enclosed in quotation 
			case "HOUSE BLEND":				// marks in the run configuration
				b = new HouseBlend(args[1]);
				break;
			case "MOCHA":
				b = new Espresso(args[1]);
				b = new Chocolate(b);
				break;
			case "LATTE":
				b = new Espresso(args[1]);
				b = new Milk(b);			
				break;
			case "CAPPUCCINO":
				b = new Espresso(args[1]);
				b = new WhipCream(b);
				break;
			case "DECAFMOCHA":
			case "DECAF MOCHA":
				b = new Decaf(args[1]);
				b = new Chocolate(b);
				break;
			case "DECAFLATTE":
			case "DECAF LATTE":
				b = new Decaf(args[1]);
				b = new Milk(b);
				break;
			case "DECAFCAPPUCCINO":
			case "DECAF CAPPUCCINO":
				b = new Decaf(args[1]);
				b = new WhipCream(b);
				break;
			case "GREENTEA":
			case "GREEN TEA":
				b = new GreenTea(args[1]);
				break;
			case "REDTEA":
			case "RED TEA":
				b = new RedTea(args[1]);
				break;
			case "WHITETEA":
			case "WHITE TEA":
				b = new WhiteTea(args[1]);
				break;
			case "FLOWERTEA":
			case "FLOWER TEA":
				b = new GreenTea(args[1]);
				b = new Jasmine(b);
				break;
			case "GINGERTEA":
			case "GINGER TEA":
				b = new GreenTea(args[1]);
				b = new Ginger(b);
				break;
			case "TEALATTE":
			case "TEA LATTE":
				b = new RedTea(args[1]);
				b = new Milk(b);
				break;
			default:
				System.out.println("Please enter a beverage from the menu");
			}
			
			if (args.length > 2){		// extra ingredients added
				for (int i = 2; i < args.length; i++)
				{
					switch(args[i].toUpperCase()){
					case "GINGER":
						b = new Ginger(b);
						break;
					case "MILK":
						b = new Milk(b);
						break;
					case "JASMINE":
						b = new Jasmine(b);
						break;
					case "WHIPCREAM":
					case "WHIP CREAM":
						b = new WhipCream(b);
						break;
					case "CHOCOLATE":
						b = new Chocolate(b);
						break;
					default:
						System.out.println("Please enter toppings from the menu");
					}
				}
			}
			
			System.out.printf("The total cost of your order is: $%.2f", b.cost());		// prints final cost
			//System.out.println("\n" + b.getDescription());							// prints description (not asked for)
		}
	}
}
