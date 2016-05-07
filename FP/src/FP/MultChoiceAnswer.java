package FP;

import java.util.ArrayList;
import java.util.Collections;

public class MultChoiceAnswer extends Answer {
	
	private MultChoice que;													// linked Question
	private ArrayList<Character> ansList = new  ArrayList<Character>();		// list of Answer letters (one or more)
	
	@Override
	public void choose(){		// answer MultChoice by entering letter of correct Answer (possibly multiple)
		ansList.clear();
		String choice;		// entered letter
		int numChoice;		// entered letter converted to number for error checking
		do{		// error checking - letter in range
			choice = ui.stringIn().toUpperCase();
			numChoice = Character.getNumericValue(choice.charAt(0)) - 10;		// shift from 'A' to '0' is 10
			if(numChoice < 0 || numChoice > getQuestion().getNumChoices()-1){
				ui.stringOut("Enter answer:");
			}
		} while (numChoice < 0 || numChoice > getQuestion().getNumChoices()-1);
		add(choice.charAt(0));
		if (getQuestion().getNumSelectableChoices() > 1){		// ask for additional Answers if Question supports them
			for (int i=1; i<getQuestion().getNumSelectableChoices(); i++){
				ui.stringOut("Question accepts multiple answers...Enter another choice:");
				do{		// error checking - letter in range
					choice = ui.stringIn().toUpperCase();
					numChoice = Character.getNumericValue(choice.charAt(0)) - 10;		// shift from 'A' to '0' is 10
					if(numChoice < 0 || numChoice > getQuestion().getNumChoices()-1){
						ui.stringOut("Enter answer:");
					}
				} while (numChoice < 0 || numChoice > getQuestion().getNumChoices()-1);
				add(choice.charAt(0));	// stores Answer
			}
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
		que = (MultChoice) q;
	}
	
	@Override
	public MultChoice getQuestion(){
		return que;
	}
	
	@Override
	public Boolean compare(Answer myA){
		ArrayList<Character> comp = new ArrayList<Character>();		// told not to write get() methods that return entire lists so
		for (int i = 0; i< ansList.size(); i++){						// we rebuild one Answer choice at a time
			comp.add(((MultChoiceAnswer) myA).getAnswer(i));			
		}
		    Collections.sort(ansList);			// sort because multiple Answer multiple choice may be in different orders but still
		    Collections.sort(comp);      			// correct
		    return ansList.equals(comp);
	}
	
	// returns Answer at position i
	public char getAnswer(int i){
		return ansList.get(i);
	}
	
	// stores Answer
	public void add(char s){
		ansList.add(s);
	}
}
