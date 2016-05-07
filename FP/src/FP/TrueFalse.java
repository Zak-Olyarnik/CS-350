package FP;

import java.util.ArrayList;

public class TrueFalse extends Question{
	
	private ArrayList<String> choices = new ArrayList<String>();	// list of choices
	
	@Override
	public void create(){
		ui.stringOut("Enter a prompt for your true or false question:");
		setPrompt(ui.stringIn());
		addChoice("T) True");
		addChoice("F) False");
	}
	
	@Override
	public void display(){
		ui.stringOut(getPrompt());
		for (int i=0; i<2; i++){			// T/F will always only have 2 choices
			ui.stringOutRep(choices.get(i) + "   ");
		}
		ui.stringOut("");
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
	}
	
	@Override
	public TrueFalseAnswer selectAnswer(){
		TrueFalseAnswer tfa = new TrueFalseAnswer();	// instantiates a new Answer
		tfa.linkQuestion(this);							// links to this Question
		tfa.choose();									// selects and stores Answer
		return tfa;
	}
	
	// adds possible choices to list
	public void addChoice(String s){
		choices.add(s);
	}
}
