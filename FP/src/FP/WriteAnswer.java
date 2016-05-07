package FP;

public class WriteAnswer extends Answer {
	
	private Write que;									// linked question
	private String ansField;							// text field for answer
		
	@Override
	public void choose(){		// answer Write by typing text answer
		String ans;
		do{		// error checking - correct input length
			ans = ui.stringIn().toUpperCase();
			if (getQuestion().getLength() == 30 && ans.length() > 30){
				ui.stringOut("Answer must not exceed 30 characters.  Enter answer:");
			}
		}while(getQuestion().getLength() == 30 && ans.length() > 30);
		setAns(ans);	// stores answer
	}
	
	@Override
	public void display(){
		ui.stringOutRep(ansField);
	}
	
	@Override
	public void modify(){						// this method is overridden because we only want to call choose() to change the answer if it's a 
		if (getQuestion().getLength() == 30){				// short answer, not an essay
			ui.stringOut("Enter correct answer:");
			choose();
		}
	}
	
	@Override
	public void linkQuestion(Question q){
		que = (Write) q;
	}
	
	@Override
	public Write getQuestion(){
		return que;
	}
	
	@Override
	public Boolean compare(Answer myA){
			return ansField.equals(((WriteAnswer) myA).getAnswer());
	}
	
	// returns Answer
	public String getAnswer(){
		return ansField;
	}
	
	// stores Answer
	public void setAns(String s){
		ansField = s;
	}
}
