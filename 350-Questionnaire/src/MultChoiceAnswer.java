import java.util.ArrayList;
import java.util.Scanner;


public class MultChoiceAnswer extends Answer {
	
	private MultChoice que;													// linked question
	private ArrayList<Character> ansList = new  ArrayList<Character>();		// list of answer letters (one or more)
	
	public void choose(Scanner s){		// answer MultChoice by entering letter of correct answer (possibly multiple)
		String choice = s.nextLine();
		// add error checking
		add(choice.charAt(0));
		if (getQuestion().getNumSelectableChoices() > 1){
			for (int i=1; i<getQuestion().getNumSelectableChoices(); i++){
				System.out.println("Question accepts multiple answers...Enter another choice:");
				choice = s.nextLine();
				add(choice.charAt(0));
			}
		}
	}
	
	public void display(){
		for (int i=0; i<ansList.size(); i++){
			System.out.println(ansList.get(i));
		}
	}
	
	public void add(char s){
		ansList.add(s);
	}
	
	public void linkQuestion(MultChoice q){
		que = q;
	}
	
	public MultChoice getQuestion(){
		return que;
	}
}
