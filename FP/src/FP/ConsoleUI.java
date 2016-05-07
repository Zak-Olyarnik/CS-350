package FP;

import java.util.Scanner;

public class ConsoleUI implements UI{
	
	private Scanner s = new Scanner(System.in);
	
	public String stringIn(){
		String in = s.nextLine();
		return in;
	}
	
	public int intIn(){
		int in = s.nextInt();
		return in;
	}
	
	public String in(){
		String n = s.next();
		return n;
	}
	
	public Boolean checkForInt(){
		Boolean flag = s.hasNextInt();
		return flag;
	}
	
	public void stringOut(String s){
		System.out.println(s);
	}
	
	public void stringOutRep(String s){
		System.out.print(s);
	}
	
	public void charOut(char c){
		System.out.print(c);
	}
	
	public int inputCheck(int bound, String item, String action){
		int input;
		do{		// error checking - integer > 0 and < total number of Questionnaires available
			while (!checkForInt()){
				stringOut("Which " + item + " would you like to " + action + "?");
				in();
			}
			input = intIn();
			if(input < 1 || input > bound){
				stringOut("Which " + item + " would you like to " + action + "?");
			}
		} while (input < 1 || input > bound);
		stringIn();		// clears scanner
		return input;
	}
}
