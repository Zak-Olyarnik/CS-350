import java.util.Scanner;


public class Write extends Question{

	private int maxLength;	// differentiates short answer (=30) and essay (=1500)
	
	public void create(Scanner s, int length){
		String essayOrShort;
		if(length == 30){
			essayOrShort = "short answer";
		}else{
			essayOrShort = "essay";
		}
		System.out.println("Enter a prompt for your " + essayOrShort + " question:");
		setPrompt(s.nextLine());
		setLength(length);
	}
	
	public void display(){		// overridden from Question class
		System.out.println(getPrompt());	// method of Question class
	}
	
	public void selectAnswer(AnswerSheet a,  Scanner s){	// overridden from Question class
		WriteAnswer wa = new WriteAnswer();
		wa.linkQuestion(this);
		wa.choose(s);
		a.addAnswer(wa);
	}
	
	public void setLength(int i){
		maxLength = i;
	}
	
	public int getLength(){
		return maxLength;
	}
}
