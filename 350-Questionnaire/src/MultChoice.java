import java.util.ArrayList;
import java.util.Scanner;


public class MultChoice extends Question{

	private int numChoices;
	private int numSelectableChoices;
	private ArrayList<String> choices = new ArrayList<String>();	// list of choices
	
	public void create(Scanner s){					// creates multiple choice
		System.out.println("Enter a prompt for your multiple choice question:");
		setPrompt(s.nextLine());
		System.out.println("Enter the number of choices for your multiple choice question:");
		while (!s.hasNextInt()){
			System.out.println("Enter the number of choices for your multiple choice question:");
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
		
		System.out.println("Enter the number of selectable choices for your multiple choice question:");
		int nSelChoices=99;		// must initialize for error checking
		do{
			while (!s.hasNextInt()){
			System.out.println("Enter the number of selectable choices for your multiple choice question:");
			s.next();
			}
			nSelChoices = s.nextInt();
			if(nSelChoices > nChoices){
				System.out.println("Selectable choices must not be greater than total choices");
			}
		} while (nSelChoices > nChoices);
		s.nextLine();		// clears scanner
		setNumSelectableChoices(nSelChoices);
	}
	
	public void create(Scanner s, int choices){		// creates true or false
		System.out.println("Enter a prompt for your true or false question:");
		setPrompt(s.nextLine());
		setNumChoices(choices);			// this will always be 2
		setNumSelectableChoices(1);		// this will always be 1
		addChoice("A) True");
		addChoice("B) False");
	}
	
	public void display(){		// overridden from Question class
		System.out.println(getPrompt());	// method of Question class
		for (int i=0; i<numChoices; i++){
			System.out.print(choices.get(i) + "   ");
		}
		System.out.println();
	}
	
	public void selectAnswer(AnswerSheet a,  Scanner s){	// overridden from Question class
		MultChoiceAnswer mca = new MultChoiceAnswer();
		mca.linkQuestion(this);
		mca.choose(s);
		a.addAnswer(mca);
	}
	
	public void addChoice(String s){
		choices.add(s);
	}
	
	public void setNumChoices(int i){
		numChoices = i;
	}
	
	public void setNumSelectableChoices(int i){
		numSelectableChoices = i;
	}
	
	public int getNumChoices(){
		return numChoices;
	}
	
	public int getNumSelectableChoices(){
		return numSelectableChoices;
	}
}
