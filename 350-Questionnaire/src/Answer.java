import java.io.Serializable;
import java.util.Scanner;


public class Answer implements Serializable{	// parent class of all specific answers, serializable
	
	private Question que;

	public void choose(Scanner s){}		// overridden by all subclasses
	
	public void display(){
		System.out.println("No answer available");	// for ungradeable Write questions
	}
	
	public void linkQuestion(){	}		// overridden by all subclasses
	
	public Question getQuestion(){		// overridden by all subclasses
		return que;
	}
}
