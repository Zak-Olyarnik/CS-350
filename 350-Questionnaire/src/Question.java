import java.io.Serializable;
import java.util.Scanner;


public class Question implements Serializable{	// parent class of all specific questions, serializable

	private String prompt;
	private int number;
	
	public void create(){}		// overridden by all subclasses
	
	public void modify(){}
	
	public void delete(){}
	
	public void display(){}		// overridden by all subclasses
	
	public void selectAnswer(AnswerSheet a, Scanner s){}	// overridden by all subclasses
	
	public void setPrompt(String s){
		prompt = s;
	}
	
	public String getPrompt(){
		return prompt;
	}
}
