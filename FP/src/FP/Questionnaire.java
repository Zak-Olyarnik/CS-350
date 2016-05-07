package FP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Questionnaire implements Serializable{

	private String title;													// identifying name
	private Boolean isGradeable;											// distinguishes between Survey and Test
	private int numResponses;												// number of times Questionnaire has been taken 
	private ArrayList<Question> questionsList = new ArrayList<Question>();	// actual list of Questions
	private static ConsoleUI ui = new ConsoleUI();							// handles all I/O
	
	// prints Questionnaire to screen
	public void display(){
		ui.stringOut(getTitle());
		ui.stringOut("");
		for (int i=0; i<questionsList.size(); i++){		// prints all Questions, enumerated
			ui.stringOut(i+1 + ") " );
			this.questionsList.get(i).display();
			ui.stringOut("");
			}
	}
	
	// serializes to file.  All Surveys collected in a Survey folder with .Survey extension and Tests in Test folder with .Test extension
	public void save(){
		String folder;
		if(getGradeable()){
			folder = "Test";
			
		}else{
			folder = "Survey";
		} 
		try
	      {
			new File(folder).mkdir();	// creates directory if does not exist
	        FileOutputStream outfile = new FileOutputStream(folder + "\\" + getTitle() + "." + folder);
	        ObjectOutputStream out = new ObjectOutputStream(outfile);
	        out.writeObject(this);
	        out.close();
	        outfile.close();
	        ui.stringOut(getTitle() + " saved successfully\n");
	      }catch(IOException i){
	          i.printStackTrace();
	      }
	}
	
	// deserializes from file
	public Questionnaire load(Boolean isGradeable){
		String folder;
		Questionnaire loaded = null;
		if(isGradeable){
			folder = "Test";	
		}else{
			folder = "Survey";
		}
		File directory = new File(folder);
		if (!directory.exists() || directory.listFiles().length == 0){	// folder does not exist or is empty
			ui.stringOut("No " + folder + "s found\n");
		}else{
			ui.stringOut("Which " + folder + " would you like to load?");
			int i = 1;
			for (File file : directory.listFiles()) {
				ui.stringOut(i + " - " + file.getName());
				i++;
			}
			int input = ui.inputCheck(i-1, folder, "load") - 1;		// error checking
			String filename = folder + "\\" + directory.listFiles()[input].getName();	// concatenates file name to load
			try
			{
				FileInputStream infile = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(infile);
				loaded = (Questionnaire) in.readObject();
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
		return loaded;
		}
	
	// prints list of Questions for modification
	public int modify(){
		ui.stringOut("Which question would you like to modify?");
		for (int i=0; i<questionsList.size(); i++){			// prints enumerated list of Questions
			ui.stringOut(i+1 + ") " );
			this.questionsList.get(i).display();
			ui.stringOut("");
		}
		int input = ui.inputCheck(questionsList.size(), "question", "modify") - 1;	// error checking
		return input;
	}
	
	// creates Questionnaire by determining Survey or Test and setting identifying name
	public void create(Boolean grad){
		String surveyOrTest;
		if(grad){
			surveyOrTest = "test";
		}else{
			surveyOrTest = "survey";
		}
		ui.stringOut("Enter a name for your " + surveyOrTest);
		setTitle(ui.stringIn());
		setGradeable(grad);
		numResponses = 0;
	}
	
	// adds Question to list
	public void addQuestion(Question q){
		questionsList.add(q);
	}
	
	// sets identifying title
	public void setTitle(String s){
		title = s;
	}
	
	// sets true if Test, false if Survey
	public void setGradeable(Boolean b){
		isGradeable = b;
	}
	
	// indicates a Questionnaire has been taken
	public void addResponse(){
		numResponses = numResponses + 1;
	}
	
	// returns identifying title
	public String getTitle(){
		return title;
	}
	
	// returns true if Test, false if Survey
	public Boolean getGradeable(){
		return isGradeable;
	}
	
	// returns number of Questions
	public int getSize(){
		return questionsList.size();
	}
	
	//returns number of responses
	public int getNumResponses(){
		return numResponses;
	}
	
	// returns Question at position i
	public Question getQuestion(int i){
		return questionsList.get(i);
	}
}
