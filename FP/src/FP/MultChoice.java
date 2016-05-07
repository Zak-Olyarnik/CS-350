package FP;

import java.util.ArrayList;

public class MultChoice extends Question{

	private int numChoices;											// number of choices
	private int numSelectableChoices;								// number of selectable choices
	private ArrayList<String> choices = new ArrayList<String>();	// list of choices
	
	@Override
	public void create(){
		ui.stringOut("Enter a prompt for your multiple choice question:");
		setPrompt(ui.stringIn());
		ui.stringOut("Enter the number of choices for your multiple choice question:");
		int input;
		do{		// error checking - integer > 0
			while (!ui.checkForInt()){
				ui.stringOut("Enter the number of choices for your multiple choice question:");
				ui.in();
			}
			input = ui.intIn();
			if(input < 1){
				ui.stringOut("Enter the number of choices for your multiple choice question:");
			}
		} while (input < 1);
		ui.stringIn();		// clears scanner
		setNumChoices(input);
		for (int i=0; i<input; i++){
			ui.stringOut("Enter answer choice #" + (i+1) + ":");
			addChoice(ui.stringIn());
		}
		ui.stringOut("Enter the number of selectable choices for your multiple choice question:");
		do{		// error checking - integer > 0 and <= total number of choices
			while (!ui.checkForInt()){
				ui.stringOut("Enter the number of selectable choices for your multiple choice question:");
				ui.in();
			}
			input = ui.intIn();
			if(input < 1){
				ui.stringOut("Enter the number of selectable choices for your multiple choice question:");
			}else if (input > getNumChoices()){
				ui.stringOut("Selectable choices must not be greater than total choices.  Enter the number of selectable choices for your multiple choice question:");
			}
		} while (input < 1 || input > getNumChoices());
		ui.stringIn();		// clears scanner
		setNumSelectableChoices(input);
	}
	
	@Override
	public void display(){
		ui.stringOut(getPrompt());
		listChoices();
	}
	
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
			while (input < getNumChoices()){		// deletes choices if new number < current number
				ui.stringOut("Enter letter of choice to delete:");
				listChoices();
				int pos;
				do{		// error checking - letter in range
					pos = Character.getNumericValue(ui.stringIn().charAt(0)) - 10;		// shift from 'A' to '0' is 10
					if(pos < 0 || pos > getNumChoices()-1){
						ui.stringOut("Enter letter of choice to delete:");
					}
				} while (pos < 0 || pos > getNumChoices()-1);
				deleteChoice(pos);						// deletes choice
				setNumChoices(getNumChoices() - 1);		// decrements total number of choices
			}
			while(input > getNumChoices()){			// adds choices if new number > current number
				ui.stringOut("Enter new answer choice #" + (getNumChoices() + 1) + ":");
				addChoice(ui.stringIn());				// adds choice
				setNumChoices(getNumChoices() + 1);		// increments total number of choices
			}
		}
		// modify text of choices
		ui.stringOut("Would you like to modify any of the choices? (y/n)");
		if(ui.stringIn().toUpperCase().equals("Y")){
			do{
				ui.stringOut("Which choice would you like to modify?");
				listChoices();				
				int pos;
				do{		// error checking - letter in range
					pos = Character.getNumericValue(ui.stringIn().charAt(0)) - 10;		// shift from 'A' to '0' is 10
					if(pos < 0 || pos > getNumChoices()-1){
						ui.stringOut("Which choice would you like to modify?");
					}
				} while (pos < 0 || pos > getNumChoices()-1);
				ui.stringOut("Enter new choice:");
				choices.set(pos, ui.stringIn());
				ui.stringOut("Would you like to modify any of the choices? (y/n)");
			}while(ui.stringIn().toUpperCase().equals("Y"));
		}
		// modify number of selectable choices
		ui.stringOut("Would you like to modify the number of selectable choices? (y/n)");
		if(ui.stringIn().toUpperCase().equals("Y")){
			int input;
			ui.stringOut("Enter the number of selectable choices for your multiple choice question:");
			do{		// error checking - integer > 0 and <= total number of choices
				while (!ui.checkForInt()){
					ui.stringOut("Enter the number of selectable choices for your multiple choice question:");
					ui.in();
				}
				input = ui.intIn();
				if(input < 1){
					ui.stringOut("Enter the number of selectable choices for your multiple choice question:");
				}else if (input > getNumChoices()){
					ui.stringOut("Selectable choices must not be greater than total choices.  Enter the number of selectable choices for your multiple choice question:");
				}
			} while (input < 1 || input > getNumChoices());
			ui.stringIn();		// clears scanner
			setNumSelectableChoices(input);
		}
	}
	
	@Override
	public MultChoiceAnswer selectAnswer(){
		MultChoiceAnswer mca = new MultChoiceAnswer();		// instantiates a new Answer
		mca.linkQuestion(this);								// links to this Question
		mca.choose();										// selects and stores Answer
		return mca;
	}
	
	// prints all choices to screen, concatenated with letters
	public void listChoices(){
		char alphabet = 'A';
		for (int i=0; i<numChoices; i++){
			ui.stringOutRep(alphabet + ") " + choices.get(i) + "   ");
			alphabet++;	
		}
		ui.stringOut("");
	}
	
	// adds a choice
	public void addChoice(String s){
		choices.add(s);
	}
	
	// deletes a choice
	public void deleteChoice(int i){
		choices.remove(i);
	}
	
	// sets number of choices
	public void setNumChoices(int i){
		numChoices = i;
	}
	
	// sets number of selectable choices
	public void setNumSelectableChoices(int i){
		numSelectableChoices = i;
	}
	
	// returns number of choices
	public int getNumChoices(){
		return numChoices;
	}
	
	// returns number of selectable choices
	public int getNumSelectableChoices(){
		return numSelectableChoices;
	}
}
