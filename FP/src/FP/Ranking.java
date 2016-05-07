package FP;

import java.util.ArrayList;

public class Ranking extends Question{

	private int numChoices;												// number of choices
	private ArrayList<String> choices = new ArrayList<String>();		// list of choices
	
	@Override
	public void create(){
		ui.stringOut("Enter a prompt for your ranking question:");
		setPrompt(ui.stringIn());
		ui.stringOut("Enter the number of choices to rank:");
		int input;
		do{		// error checking - integer > 0
			while (!ui.checkForInt()){
				ui.stringOut("Enter the number of choices to rank:");
				ui.in();
			}
			input = ui.intIn();
			if(input < 1){
				ui.stringOut("Enter the number of choices to rank:");
			}
		} while (input < 1);
		ui.stringIn();		// clears scanner
		setNumChoices(input);
		for (int i=0; i<input; i++){		// asks for choices
			ui.stringOut("Enter answer choice #" + (i+1) + ":");
			addChoice(ui.stringIn());	// adds the choice
		}
	}
	
	@Override
	public void display(){
		ui.stringOut(getPrompt());
		listChoices();
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
			ui.stringOut("Enter the number of choices to rank:");
			int input;
			do{		// error checking - integer > 0
				while (!ui.checkForInt()){
					ui.stringOut("Enter the number of choices to rank:");
					ui.in();
				}
				input = ui.intIn();
				if(input < 1){
					ui.stringOut("Enter the number of choices to rank:");
				}
			} while (input < 1);
			ui.stringIn();		// clears scanner
			while (input < getNumChoices()){	// deletes choices if new number < current number
				ui.stringOut("Enter letter of choice to delete:");
				listChoices();
				int pos;
				do{		// error checking - letter in range
					pos = Character.getNumericValue(ui.stringIn().charAt(0)) - 10;		// shift from 'A' to '0' is 10
					if(pos < 0 || pos > getNumChoices()-1){
						ui.stringOut("Enter letter of choice to delete:");
					}
				} while (pos < 0 || pos > getNumChoices()-1);
				deleteChoice(pos);						// deletes selected choice
				setNumChoices(getNumChoices() - 1);		// decrements total number of choices
			}
			while(input > getNumChoices()){		// adds choices if new number > current number
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
	}
	
	@Override
	public RankingAnswer selectAnswer(){
		RankingAnswer ra = new RankingAnswer();		// instantiates a new Answer
		ra.linkQuestion(this);						// links to this Question
		ra.choose();								// selects and stores Answer
		return ra;
	}
	
	// adds choice to list
	public void addChoice(String s){
		choices.add(s);
	}
	
	// removes choice from  list
	public void deleteChoice(int i){
		choices.remove(i);
	}
	
	// prints all choices to screen, concatenated with letters
	public void listChoices(){
		char alphabet = 'A';
		for (int i=0; i<numChoices; i++){
			ui.stringOutRep(alphabet + ") " + choices.get(i) + "   ");
			alphabet++;	
		}
		System.out.println();
	}
	
	// sets number of choices
	public void setNumChoices(int i){
		numChoices = i;
	}
	
	// returns number of choices
	public int getNumChoices(){
		return numChoices;
	}
}
