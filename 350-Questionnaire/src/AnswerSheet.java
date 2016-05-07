import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class AnswerSheet implements Serializable{

	private ArrayList<Answer> answersList = new ArrayList<Answer>();		// actual list of all answers
	private Questionnaire quest;		// linked Questionnaire
	
	public void display(){		// loads AnswerSheet from file and displays to console
		AnswerSheet disp = load();
		for(int i=0; i<disp.answersList.size(); i++){
			System.out.print(i+1 + ") " );
			disp.answersList.get(i).display();
			System.out.println();
		}
	}
	
	public AnswerSheet load(){		// deserializes AnswerSheet from file
		AnswerSheet f = null;
		String filename = "Answer\\" + getQuestionnaire().getTitle() + ".Answer";	// AnswerSheet named based on Test
			
		try
		{
			FileInputStream infile = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(infile);
			f = (AnswerSheet) in.readObject();
			in.close();
			infile.close();
		}catch(IOException e){
			e.printStackTrace();
			return f;
		}catch(ClassNotFoundException c){
			System.out.println("Class not found");
			c.printStackTrace();
			return f;
		}
		return f;
	}

	public void save(){		// serializes to file.  All AnswerSheets saved in Answer folder with .Answer extension
		try
	      {
			new File("Answer").mkdir();		// creates Answer directory if does not exist
	        FileOutputStream outfile = new FileOutputStream("Answer\\" + getQuestionnaire().getTitle() + ".Answer");
	        ObjectOutputStream out = new ObjectOutputStream(outfile);
	        out.writeObject(this);
	        out.close();
	        outfile.close();
	        System.out.println(getQuestionnaire().getTitle() + " answers saved successfully\n");
	      }catch(IOException i){
	          i.printStackTrace();
	      }
	}
	
	public void addAnswer(Answer a){
		answersList.add(a);
	}
	
	public void linkQuestionnaire(Questionnaire q){
		quest = q;
	}
	
	public Questionnaire getQuestionnaire(){
		return quest;
	}
	
}
