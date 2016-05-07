import java.util.ArrayList;
import java.util.Scanner;


public class Ranking extends Question{

	private int numChoices;
	private ArrayList<String> choices = new ArrayList<String>();		// list of choices
	
	public void create(Scanner s){
		System.out.println("Enter a prompt for your ranking question:");
		setPrompt(s.nextLine());
		System.out.println("Enter the number of choices to rank:");
		while (!s.hasNextInt()){
			System.out.println("Enter the number of choices to rank:");
			s.next();
		}
		int nChoices = s.nextInt();
		s.nextLine();		// clears scanner
		setNumChoices(nChoices);
		char alphabet = 'A';
		for (int i=0; i<nChoices; i++){
			System.out.println("Enter answer choice #" + (i+1) + ":");
			addChoice(alphabet + ") " + s.nextLine());		// concatenates the choices with letters
			alphabet++;
		}
	}
	
	public void display(){		// overridden from Question class
		System.out.println(getPrompt());	// method of Question class
		for (int i=0; i<numChoices; i++){
			System.out.print(choices.get(i) + "   ");
		}
		System.out.println();
	}
	
	public void selectAnswer(AnswerSheet a,  Scanner s){	// overridden from Question class
		RankingAnswer ra = new RankingAnswer();
		ra.linkQuestion(this);
		ra.choose(s);
		a.addAnswer(ra);
	}
	
	public void addChoice(String s){
		choices.add(s);
	}
	
	public void setNumChoices(int i){
		numChoices = i;
	}
	
	public int getNumChoices(){
		return numChoices;
	}
}
