package FP;

import java.io.Serializable;

public class Question implements Serializable{	// parent class of all specific Questions, serializable

	private String prompt;								// text actually asking the Question
	protected static ConsoleUI ui = new ConsoleUI();		// handles all I/O
	
	// creates Question by filling all private data fields
	public void create(){}			// overridden by subclasses
	
	// prints Question to screen
	public void display(){}					// overridden by all subclasses
	
	// modifies Question private data: prompt, choices, selectable choices, etc.
	public void modify(){}			// overridden by subclasses
	
	// instantiates the Answer for this Question
	public Answer selectAnswer(){	// overridden by subclasses
		Answer newA = new Answer();
		return newA;
	}
	
	// sets Question text
	public void setPrompt(String s){		// same for all subclasses
		prompt = s;
	}
	
	// returns Question text
	public String getPrompt(){				// same for all subclasses
		return prompt;
	}
}
