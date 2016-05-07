package FP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class AnswerSheet implements Serializable{

	private ArrayList<Answer> answersList = new ArrayList<Answer>();		// actual list of all answers
	private Questionnaire quest;											// linked Questionnaire
	private String name;													// student's name, Survey iterator, or "Key"
	private static ConsoleUI ui = new ConsoleUI();							// handles all I/O
	
	// prints AnswerSheet to screen
	public void display(){
		ui.stringOut("Answers:");
		for(int i=0; i<answersList.size(); i++){	// prints all Answers, enumerated
			ui.stringOutRep("\n" + (i+1) + ") " );
			if (answersList.get(i) instanceof WriteAnswer && ((Write) answersList.get(i).getQuestion()).getLength() == 1500){
				ui.stringOut("No answer available");
			}else{
				answersList.get(i).display();
				ui.stringOut("");
			}
		}
		ui.stringOut("\n");
	}

	// serializes to file.  All AnswerSheets saved in Answer folder with .Answer extension
	public void save(String title, String surveyOrTest){
		try
	      {
			new File(surveyOrTest + "Answer").mkdir();		// creates Answer directory if does not exist
	        FileOutputStream outfile = new FileOutputStream(surveyOrTest + "Answer\\" + title + "." + getName() + ".Answer");
	        ObjectOutputStream out = new ObjectOutputStream(outfile);
	        out.writeObject(this);
	        out.close();
	        outfile.close();
	        ui.stringOut(getQuestionnaire().getTitle() + " answers saved successfully");
	      }catch(IOException i){
	          i.printStackTrace();
	      }
	}
	
	// deserializes AnswerSheet from file
	public AnswerSheet load(String filename){
		AnswerSheet loaded = null;
		try
		{
			FileInputStream infile = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(infile);
			loaded = (AnswerSheet) in.readObject();
			in.close();
			infile.close();
		}catch(IOException e){
			e.printStackTrace();
			return loaded;
		}catch(ClassNotFoundException c){
			ui.stringOut("Class not found");
			c.printStackTrace();
			return loaded;
		}
		return loaded;
	}
	
	// answers the Questions on a Questionnaire
	public void takeQuestionnaire(){
		for (int i=0; i<getQuestionnaire().getSize(); i++){
			ui.stringOut(i+1 + ") " );
			getQuestionnaire().getQuestion(i).display();
			Answer newA = getQuestionnaire().getQuestion(i).selectAnswer();
			this.addAnswer(newA);
			ui.stringOut("");
		}
		getQuestionnaire().addResponse();	// indicates that an AnswerSheet has been filled out
	}
	
	// adds Answer to list
	public void addAnswer(Answer a){
		answersList.add(a);
	}
	
	// links AnswerSheet and Questionnaire
	public void linkQuestionnaire(Questionnaire q){
		quest = q;
	}
	
	// returns linked Questionnaire
	public Questionnaire getQuestionnaire(){
		return quest;
	}
	
	// returns Answer at position i
	public Answer getAnswer(int i){
		return answersList.get(i);
	}
	
	// returns number of Answers
	public int getSize(){
		return answersList.size();
	}
	
	// sets student's name, Survey iterator, or "Key"
	public void setName(String s){
		name = s;
	}
	
	// returns name
	public String getName(){
		return name;
	}
}
