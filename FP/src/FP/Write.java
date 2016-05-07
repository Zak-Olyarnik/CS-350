package FP;

public class Write extends Question{

	private int maxLength;	// differentiates short answer (=30) and essay (=1500)
	
	@Override
	public void create(){
		String essayOrShort;
		if(getLength() == 30){
			essayOrShort = "short answer";
		}else{
			essayOrShort = "essay";
		}
		ui.stringOut("Enter a prompt for your " + essayOrShort + " question:");
		setPrompt(ui.stringIn());		// stores prompt
	}
	
	@Override
	public void display(){
		ui.stringOut(getPrompt());
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
	public WriteAnswer selectAnswer(){
		WriteAnswer wa = new WriteAnswer();		// instantiates a new Answer
		wa.linkQuestion(this);					// links to this Question
		wa.choose();							// selects and stores Answer
		return wa;
	}
	
	// sets maximum answer field length
	public void setLength(int i){
		maxLength = i;
	}
	
	// returns maximum answer field length
	public int getLength(){
		return maxLength;
	}
}
