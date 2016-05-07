import java.util.ArrayList;
import java.util.Scanner;


public class MatchingAnswer extends Answer {

	private Matching que;												// linked question
	private ArrayList<Character> ansList = new ArrayList<Character>();	// list of answer letters ordered according to first column
	
	public void choose(Scanner s){		// answer Matching by entering letter choices in order of first column matches
		for (int i=0; i<getQuestion().getNumChoices(); i++){
			System.out.println("Enter letter matching choice " + getQuestion().getLeft(i) + ":");
			String choice = s.nextLine();
			//add error checking
			add(choice.charAt(0));
		}
	}
	
	public void display(){
		for (int i=0; i<ansList.size(); i++){
			System.out.println(ansList.get(i));
		}
	}
	
	public void add(char i){
		ansList.add(i);
	}
	
	public void linkQuestion(Matching q){
		que = q;
	}
	
	public Matching getQuestion(){
		return que;
	}
}
