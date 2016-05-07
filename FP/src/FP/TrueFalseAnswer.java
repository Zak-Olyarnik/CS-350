package FP;

public class TrueFalseAnswer extends Answer {

	private TrueFalse que;		// linked Question
	private char ans;			// Answer letter
	
	@Override
	public void choose(){		// answer T/F by entering letter of correct Answer or whole Answer
		String choice;
		do{		// error checking - first character is either "T" or "F"
			choice = ui.stringIn().toUpperCase();
			if (!(choice.charAt(0) == 'T' || choice.charAt(0) == 'F')){
				ui.stringOut("Enter answer:");
			}
		}while(!(choice.charAt(0) == 'T' || choice.charAt(0) == 'F'));
		setAns(choice.charAt(0));	// stores Answer
	}
	
	@Override
	public void display(){
		ui.charOut(ans);
	}
	
	@Override
	public void linkQuestion(Question q){
		que = (TrueFalse) q;
	}
	
	@Override
	public TrueFalse getQuestion(){
		return que;
	}
	
	@Override
	public Boolean compare(Answer myA){
		return ans == ((TrueFalseAnswer) myA).getAns();
	}
	
	// stores Answer
	public void setAns(char c){
		ans = c;
	}
	
	// returns Answer
	public char getAns(){
		return ans;
	}
}
