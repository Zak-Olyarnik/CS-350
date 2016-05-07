package FP;

public interface UI {

	// reads in a string
	public String stringIn();
	
	// reads in an int
	public int intIn();
	
	// reads in a string without advancing
	public String in();
	
	// checks for an int
	public Boolean checkForInt();
	
	// prints a string
	public void stringOut(String s);
	
	// prints a string without a newline
	public void stringOutRep(String s);
	
	// prints a char
	public void charOut(char c);
	
	// ensures good input
	public int inputCheck(int bound, String item, String action);
}
