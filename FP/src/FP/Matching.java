package FP;

import java.util.ArrayList;

public class Matching extends Question{

	private int numChoices;												// number of choices
	private ArrayList<String> leftColumn = new ArrayList<String>();		// left column choices
	private ArrayList<String> rightColumn = new ArrayList<String>();	// right column choices
	
	@Override
	public void create(){
		ui.stringOut("Enter a prompt for your matching question:");
		setPrompt(ui.stringIn());
		ui.stringOut("Enter the number of choices per column:");
		int input;
		do{		// error checking - integer > 0
			while (!ui.checkForInt()){
				ui.stringOut("Enter the number of choices per column:");
				ui.in();
			}
			input = ui.intIn();
			if(input < 1){
				ui.stringOut("Enter the number of choices per column:");
			}
		} while (input < 1);
		ui.stringIn();		// clears scanner
		setNumChoices(input);
		// asks for choices
		for (int i=0; i<input; i++){
			ui.stringOut("Enter left column choice #" + (i+1) + ":");
			addLeft(ui.stringIn());
		}
		for (int i=0; i<input; i++){
			ui.stringOut("Enter right column choice #" + (i+1) + ":");
			addRight(ui.stringIn());
		}
	}
	
	@Override
	public void display(){
		System.out.println(getPrompt());
		char alphabet = 'A';
		for (int i=0; i<numChoices; i++){
			ui.stringOut(leftColumn.get(i) + "				" + alphabet + ") " + rightColumn.get(i));		// concatenates the choices with letters
			alphabet++;	
		}
	}
	
	@Override
	public void modify(){
		display();
		// modify prompt
		ui.stringOut("Would you like to modify the prompt? (y/n)");
		if(ui.stringIn().toUpperCase().equals("Y")){
			ui.stringOut("Enter a new prompt:");
			setPrompt(ui.stringIn());
		}
		// modify number of choices
		ui.stringOut("Would you like to modify the number of choices? (y/n)");
		if(ui.stringIn().toUpperCase().equals("Y")){
			ui.stringOut("Enter the number of choices:");
			int input;
			do{		// error checking - integer > 0
				while (!ui.checkForInt()){
					ui.stringOut("Enter the number of choices:");
					ui.in();
				}
				input = ui.intIn();
				if(input < 1){
					ui.stringOut("Enter the number of choices:");
				}
			} while (input < 1);
			ui.stringIn();		// clears scanner
			while (input < getNumChoices()){	// deletes choices if new number < current number.  Choices are deleted from both columns to maintain matching
				ui.stringOut("Enter letter of left column choice to delete:");
				listChoices("left");
				int pos;
				do{		// error checking - letter in range
					pos = Character.getNumericValue(ui.stringIn().charAt(0)) - 10;		// shift from 'A' to '0' is 10
					if(pos < 0 || pos > getNumChoices()-1){
						ui.stringOut("Enter letter of left column choice to delete:");
					}
				} while (pos < 0 || pos > getNumChoices()-1);
				leftDeleteChoice(pos);				// deletes choice
				ui.stringOut("Enter letter of right column choice to delete:");
				listChoices("right");
				do{		// error checking - letter in range
					pos = Character.getNumericValue(ui.stringIn().charAt(0)) - 10;		// shift from 'A' to '0' is 10
					if(pos < 0 || pos > getNumChoices()-1){		// must re-print prompt
						ui.stringOut("Enter letter of right column choice to delete:");
					}
				} while (pos < 0 || pos > getNumChoices()-1);
				rightDeleteChoice(pos);					// deletes choice
				setNumChoices(getNumChoices() - 1);		// decrements total number of choices
			}
			while(input > getNumChoices()){		// adds choices if new number > current number.  Choices are added to both columns to maintain matching
				ui.stringOut("Enter new left column choice #" + (getNumChoices() + 1) + ":");
				addLeft(ui.stringIn());					// adds choice
				ui.stringOut("Enter new right column choice #" + (getNumChoices() + 1) + ":");
				addRight(ui.stringIn());				// adds choice
				setNumChoices(getNumChoices() + 1);		// increments total number of choices
			}
		}
		// modify text of left column choices
		ui.stringOut("Would you like to modify any left column choices? (y/n)");
		if(ui.stringIn().toUpperCase().equals("Y")){
			do{
				ui.stringOut("Which choice would you like to modify?");
				listChoices("left");
				int pos;
				do{		// error checking - letter in range
					pos = Character.getNumericValue(ui.stringIn().charAt(0)) - 10;		// shift from 'A' to '0' is 10
					if(pos < 0 || pos > getNumChoices()-1){
						ui.stringOut("Which choice would you like to modify?");
					}
				} while (pos < 0 || pos > getNumChoices()-1);
				ui.stringOut("Enter new choice:");
				leftColumn.set(pos, ui.stringIn());
				ui.stringOut("Would you like to modify any left column choices? (y/n)");
			}while(ui.stringIn().toUpperCase().equals("Y"));
		}
		// modify text of right column choices
		ui.stringOut("Would you like to modify any right column choices? (y/n)");
		if(ui.stringIn().toUpperCase().equals("Y")){
			do{
				ui.stringOut("Which choice would you like to modify?");
				listChoices("right");
				int pos;
				do{		// error checking - letter in range
					pos = Character.getNumericValue(ui.stringIn().charAt(0)) - 10;		// shift from 'A' to '0' is 10
					if(pos < 0 || pos > getNumChoices()-1){
						ui.stringOut("Which choice would you like to modify?");
					}
				} while (pos < 0 || pos > getNumChoices()-1);
				ui.stringOut("Enter new choice:");
				rightColumn.set(pos, ui.stringIn());
				ui.stringOut("Would you like to modify any right column choices? (y/n)");
			}while(ui.stringIn().toUpperCase().equals("Y"));
		}
	}
	
	@Override
	public MatchingAnswer selectAnswer(){
		MatchingAnswer ma = new MatchingAnswer();		// instantiates a new Answer
		ma.linkQuestion(this);							// links to this Question
		ma.choose();									// selects and stores Answer
		return ma;
	}
	
	// prints all choices to screen, concatenated with letters
	public void listChoices(String col){
		char alphabet = 'A';
		for (int i=0; i<numChoices; i++){
			if (col.equals("left")){
				ui.stringOutRep(alphabet + ") " + leftColumn.get(i) + "   ");
			}else if (col.equals("right")){
				ui.stringOutRep(alphabet + ") " + rightColumn.get(i) + "   ");
			}
			alphabet++;	
		}
		ui.stringOut("");
	}
	
	// adds a choice to the left column
	public void addLeft(String s){
		leftColumn.add(s);
	}
	
	// adds a choice to the right column
	public void addRight(String s){
		rightColumn.add(s);
	}
	
	// deletes the choice at position i of left column
	public void leftDeleteChoice(int i){
		leftColumn.remove(i);
	}
	
	// deletes the choice at position i of right column
	public void rightDeleteChoice(int i){
		rightColumn.remove(i);
	}
	
	// sets the number of choices
	public void setNumChoices(int i){
		numChoices = i;
	}
	
	// returns the number of choices
	public int getNumChoices(){
		return numChoices;
	}
	
	// returns the choice at position i of left column
	public String getLeft(int i){
		return leftColumn.get(i);
	}
}
