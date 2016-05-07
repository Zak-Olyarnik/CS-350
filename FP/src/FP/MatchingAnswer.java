package FP;

import java.util.ArrayList;

public class MatchingAnswer extends Answer {

	private Matching que;												// linked Question
	private ArrayList<Character> ansList = new ArrayList<Character>();	// list of Answer letters ordered according to first column
	
	@Override
	public void choose(){		// answer Matching by entering letter choices in order of first column matches
		ansList.clear();
		for (int i=0; i<getQuestion().getNumChoices(); i++){		// asks for Answers corresponding to the first column, in order
			ui.stringOut("Enter letter matching choice " + getQuestion().getLeft(i) + ":");
			String choice;		// entered letter
			int numChoice;		// entered letter converted to number for error checking
			do{			// error checking - letter in range
				choice = ui.stringIn().toUpperCase();
				numChoice = Character.getNumericValue(choice.charAt(0)) - 10;		// shift from 'A' to '0' is 10
				if(numChoice < 0 || numChoice > getQuestion().getNumChoices()-1){
					ui.stringOut("Enter letter matching choice " + getQuestion().getLeft(i) + ":");
				}
			} while (numChoice < 0 || numChoice > getQuestion().getNumChoices()-1);
			add(choice.charAt(0));		// stores Answer
		}
	}
	
	@Override
	public void display(){
		for (int i=0; i<ansList.size(); i++){
			ui.charOut(ansList.get(i));
		}
	}
	
	@Override
	public void linkQuestion(Question q){
		que = (Matching) q;
	}
	
	@Override
	public Matching getQuestion(){
		return que;
	}
	
	@Override
	public Boolean compare(Answer myA){
		for (int i = 0; i< ansList.size(); i++){
			if (!ansList.get(i).equals(((MatchingAnswer) myA).getAnswer(i))){
				return false;
			}
		}
		return true;
	}
	
	// returns Answer at position i
	public char getAnswer(int i){
		return ansList.get(i);
	}
	
	// stores Answer
	public void add(char i){
		ansList.add(i);
	}
}
