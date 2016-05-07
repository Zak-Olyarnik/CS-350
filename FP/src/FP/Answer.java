package FP;

import java.io.Serializable;

public class Answer implements Serializable{	// parent class of all specific Answers, serializable
	
	private Question que;									// the Question to which this Answer is linked
	protected static ConsoleUI ui = new ConsoleUI();		// handles all I/O

	// actually chooses Answer to store
	public void choose(){}		// overridden by subclasses
	
	// prints Answer to screen
	public void display(){}				// overridden by subclasses
	
	// modifies correct Answer
	public void modify(){ 		// same for all subclasses (except WriteAnswer).  The correct subclass choose() will be called
		System.out.println("Enter correct answer:");
		choose();		// subclass method
	}
	
	// links the Answer to its Question
	public void linkQuestion(Question q){	// overridden by subclasses
		que = q;
	}
	
	// returns the linked Question
	public Question getQuestion(){		// overridden by subclasses
		return que;
	}
	
	// compares two Answers
	public Boolean compare(Answer myA){	// overridden by subclasses
		return false;
	}
}