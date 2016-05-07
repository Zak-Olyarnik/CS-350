import java.util.ArrayList;
import java.util.Scanner;


public class Matching extends Question{

	private int numChoices;
	private ArrayList<String> leftColumn = new ArrayList<String>();		// left column choices
	private ArrayList<String> rightColumn = new ArrayList<String>();	// right column choices
	
	public void create(Scanner s){
		System.out.println("Enter a prompt for your matching question:");
		setPrompt(s.nextLine());
		System.out.println("Enter the number of choices per column:");
		while (!s.hasNextInt()){
			System.out.println("Enter the number of choices per column:");
			s.next();
		}
		int nChoices = s.nextInt();
		s.nextLine();		// clears scanner
		setNumChoices(nChoices);
		for (int i=0; i<nChoices; i++){
			System.out.println("Enter left column choice #" + (i+1) + ":");
			addLeft(s.nextLine());
		}
		char alphabet = 'A';
		for (int i=0; i<nChoices; i++){
			System.out.println("Enter right column choice #" + (i+1) + ":");
			addRight(alphabet + ") " + s.nextLine());		// concatenates the choices with letters
			alphabet++;
		}
	}
	
	public void display(){		// overridden from Question class
		System.out.println(getPrompt());	// method of Question class
		for (int i=0; i<numChoices; i++){
			System.out.println(leftColumn.get(i) + "				" + rightColumn.get(i));
		}
	}
	
	public void selectAnswer(AnswerSheet a,  Scanner s){	// overridden from Question class
		MatchingAnswer ma = new MatchingAnswer();
		ma.linkQuestion(this);
		ma.choose(s);
		a.addAnswer(ma);
	}
	
	public void addLeft(String s){
		leftColumn.add(s);
	}
	
	public void addRight(String s){
		rightColumn.add(s);
	}
	
	public void setNumChoices(int i){
		numChoices = i;
	}
	
	public int getNumChoices(){
		return numChoices;
	}
	
	public String getLeft(int i){
		return leftColumn.get(i);
	}
}
