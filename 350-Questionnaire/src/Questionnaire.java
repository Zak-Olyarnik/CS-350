import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Questionnaire implements Serializable{

	private String title;			// identifying name
	private String subject;
	private Boolean isGradeable;	// distinguishes between Survey and Test
	private ArrayList<Question> questionsList = new ArrayList<Question>();	// actual list of questions
	
	public void display(Scanner s){		// displays Questionnaire from file
		Questionnaire disp = load(s);
		if (disp != null){
			System.out.println(disp.getTitle());
			System.out.println(disp.getSubject());
			System.out.println();
			for (int i=0; i<disp.questionsList.size(); i++){
				System.out.print(i+1 + ") " );
				disp.questionsList.get(i).display();
				System.out.println();
			}
			if(getGradeable()){			// if Questionnaire is a Test, displays answers afterwards
				AnswerSheet a = new AnswerSheet();
				a.linkQuestionnaire(disp);
				a.display();
			}	
		}
	}
	
	public Questionnaire load(Scanner s){	// deserializes from file
		String folder;
		Questionnaire f = null;
		if(getGradeable()){
			folder = "Test";	
		}else{
			folder = "Survey";
		}
		
		File directory = new File(folder);
		if (!directory.exists() || directory.listFiles().length == 0){
			System.out.println("No " + folder + "s to display\n");
		}else{
			System.out.println("Which " + folder + " would you like to load?");
			int i = 1;
			for (File file : directory.listFiles()) {
				System.out.println(i + " - " + file.getName());
				i++;
			}
		
			String filename = s.nextLine();
			filename = folder + "\\" + directory.listFiles()[Integer.parseInt(filename) - 1].getName();
			
			try
			{
				FileInputStream infile = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(infile);
				f = (Questionnaire) in.readObject();
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
		return f;
		}
	
	public void save(){		// serializes to file.  All Surveys collected in a Survey folder with .Survey extension
		String folder;			// and Tests in Test folder with .Test extension
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
	        System.out.println(getTitle() + " saved successfully\n");
	      }catch(IOException i){
	          i.printStackTrace();
	      }
	}
	
	public void take(AnswerSheet a, Scanner s){		// take test, currently easiest way to add correct answers
		a.linkQuestionnaire(this);
		for (int i=0; i<this.questionsList.size(); i++){
			System.out.print(i+1 + ") " );
			this.questionsList.get(i).display();
			this.questionsList.get(i).selectAnswer(a, s);
			System.out.println();
		}
	}
	
	public void modify(){
		
	}
	
	public void create(Scanner s, Boolean grad){	// creates Questionnaire and adds questions, does all the heavy lifting
		String surveyOrTest;
		String addMenu = "\n1 - Add a new True or False Question\n"
	 		   	 + "2 - Add a new Multiple Choice Question\n"
	 		   	 + "3 - Add a new Short Answer Question\n"
	 		   	 + "4 - Add a new Essay Question\n"
	 		   	 + "5 - Add a new Matching Question\n"
	 		   	 + "6 - Add a new Ranking Question\n";
		if(grad){
			surveyOrTest = "test";
			addMenu = addMenu + "7 - Finish and Select Answers\n";
		}else{
			surveyOrTest = "survey";
			addMenu = addMenu + "7 - Finish and Save\n";
		}
		System.out.println("Enter a name for your " + surveyOrTest);
		setTitle(s.nextLine());
		System.out.println("Enter the subject for your " + surveyOrTest);
		setSubject(s.nextLine());
		setGradeable(grad);
		
		System.out.println(addMenu);
		String choice = s.nextLine();
		
		while (! choice.equals("7")){	// loop to take in new commands
			switch (choice){
			case "1":	// true or false
				MultChoice tf = new MultChoice();
				tf.create(s, 2);	// true or false always has 2 choices so method is overloaded to distinguish this 
				addQuestion(tf);
				tf.display();
				break;
			case "2":	// multiple choice
				MultChoice mc = new MultChoice();
				mc.create(s);
				addQuestion(mc);
				mc.display();
				break;
			case "3":	// short answer
				Write sa = new Write();
				sa.create(s, 30);		// short answer has max length of 30
				addQuestion(sa);
				sa.display();
				break;
			case "4":	// essay
				Write es = new Write();
				es.create(s, 1500);		// essay has max length of 1500
				addQuestion(es);
				es.display();
				break;
			case "5":	// matching
				Matching ma = new Matching();
				ma.create(s);
				addQuestion(ma);
				ma.display();
				break;
			case "6":	// ranking
				Ranking ra = new Ranking();
				ra.create(s);
				addQuestion(ra);
				ra.display();
				break;
			default:	// bad input
				System.out.println("Please enter the number of your choice.");
				break;
			}
			System.out.println(addMenu);	// resets menu
			choice = s.nextLine();
		}
	}
	
	public void delete(){
		
	}
	
	public void tabulate(){
		
	}
	
	public void addQuestion(Question q){
		questionsList.add(q);
	}
	
	public void setTitle(String s){
		title = s;
	}
	
	public void setSubject(String s){
		subject = s;
	}
	
	public void setGradeable(Boolean b){
		isGradeable = b;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getSubject(){
		return subject;
	}
	
	public Boolean getGradeable(){
		return isGradeable;
	}
}
