import java.util.Scanner;


public class WriteAnswer extends Answer {
	
	private Write que;			// linked question
	private String ansField;	// text field for answer
	
	public void choose(Scanner s){		// answer Write by typing text answer
		String ans = s.nextLine();
		// add error checking
		setAns(ans);
	}
	
	public void setAns(String s){
		ansField = s;
	}
	
	public void linkQuestion(Write q){
		que = q;
	}
	
	public Write getQuestion(){
		return que;
	}
}
