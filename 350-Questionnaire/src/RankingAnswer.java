import java.util.ArrayList;
import java.util.Scanner;


public class RankingAnswer extends Answer {

	private Ranking que;												// linked question
	private ArrayList<Character> ansList = new ArrayList<Character>();	// list of answer letters in order
	
	public void choose(Scanner s){		// answer Ranking by entering letters for choices in numerical order
		for (int i=0; i<getQuestion().getNumChoices(); i++){
			System.out.println("Enter letter for choice #" + (i+1) + ":");
			String choice = s.nextLine();
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
	
	public void linkQuestion(Ranking q){
		que = q;
	}
	
	public Ranking getQuestion(){
		return que;
	}
}
