package FP;

import java.util.ArrayList;

public class Driver {
	
	private static ArrayList<Questionnaire> localSurveys = new ArrayList<Questionnaire>();	// container for all Surveys
	private static ArrayList<Questionnaire> localTests = new ArrayList<Questionnaire>();	// container for all Tests
	private static ConsoleUI ui = new ConsoleUI();											// handles all I/O
	
	// instantiates the UI_Driver object
	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.mainMenu();			// loads main menu
	}

	// prints main menu to screen and processes user input
	public void mainMenu(){
		String menu = "1  - Create a new Survey\n"
				 	+ "2  - Create a new Test\n"
				 	+ "3  - Display a Survey\n"
				 	+ "4  - Display a Test\n"
				 	+ "5  - Save a Survey\n"
				 	+ "6  - Save a Test\n"
				 	+ "7  - Modify an existing Survey\n"
				 	+ "8  - Modify an existing Test\n"
				 	+ "9  - Load a Survey\n"
				 	+ "10 - Load a Test\n"
				 	+ "11 - Take a Survey\n"
				 	+ "12 - Take a Test\n"
				 	+ "13 - Grade a Test\n"
				 	+ "14 - Tabulate a Survey\n"
				 	+ "15 - Tabulate a Test\n"
				 	+ "16 - Exit\n";
		ui.stringOut(menu);
		String choice = ui.stringIn();
		while (! choice.equals("16")){		// loops to take in new commands
			switch (choice){
			case "1":	// create Survey
				Questionnaire q = new Questionnaire();
				q.create(false);			// differentiates as Survey
				addMenu(q);				// adds Questions
				localSurveys.add(q);		// adds to list of Surveys
				q.save();					// auto-saves
				break;
			case "2":	// create Test
				q = new Questionnaire();
				q.create(true);			// differentiates as Test
				addMenu(q);				// adds Questions and creates AnswerSheet
				localTests.add(q);			// adds to list of Tests
				q.save();					// auto-saves
				break;
			case "3":	// display Survey
				q = localCheck(localSurveys, "Survey", "display");
				if (!(q == null)){
					q.display();		// displays the chosen Survey
				}
				break;
			case "4":	// display Test
				q = localCheck(localTests, "Test", "display");
				if (!(q == null)){
					q.display();						// displays the chosen Test
					AnswerSheet a = new AnswerSheet();
					a = a.load("TestAnswer\\" + q.getTitle() + ".Key.Answer");	// loads the AnswerSheet key corresponding with the chosen Test
					a.linkQuestionnaire(q);				// links AnswerSheet to Questionnaire
					a.display();						// displays the AnswerSheet
				}
				break;
			case "5":	// save Survey from the list of localSurveys
				q = localCheck(localSurveys, "Survey", "save");
				if (!(q == null)){
					q.save();				// saves the chosen Survey
				}
				break;
			case "6":	// save Test from the list of localTests
				q = localCheck(localTests, "Test", "save");
				if (!(q == null)){
					q.save();				// saves the chosen Test
				}
				break;
			case "7":	// modify Survey
				q = localCheck(localSurveys, "Survey", "modify");
				if (!(q == null)){
					do{
						int modQuest = q.modify();			// asks which Question to modify
						q.getQuestion(modQuest).modify();	// modifies that Question
						q.getQuestion(modQuest).display();	// displays the modified Question for confirmation 
						ui.stringOut("Modify another question? (y/n)");
					} while(ui.stringIn().toUpperCase().equals("Y"));		// loops to allow more modification
					q.save();
				}
				break;
			case "8":	// modify Test
				q = localCheck(localTests, "Test", "modify");
				if (!(q == null)){
					AnswerSheet a = new AnswerSheet();
					a = a.load("TestAnswer\\" + q.getTitle() + ".Key.Answer");	// loads the AnswerSheet key corresponding with the chosen Test
					a.linkQuestionnaire(q);				// links AnswerSheet and Questionnaire
					do{
						int modQuest = q.modify();			// asks which Question to modify
						q.getQuestion(modQuest).modify();	// modifies that Question
						q.getQuestion(modQuest).display();	// displays the modified Question for confirmation
						a.getAnswer(modQuest).linkQuestion(q.getQuestion(modQuest));	// links the modified Question to its Answer
						a.getAnswer(modQuest).modify();		// modifies that Answer in case the Question changed
						q.getQuestion(modQuest).display();	// displays the modified Question and Answer for confirmation
						a.getAnswer(modQuest).display();
						ui.stringOut("Modify another question? (y/n)");
					} while(ui.stringIn().toUpperCase().equals("Y"));	// loops to allow more modification
					a.save(q.getTitle(), "Test");			// auto-saves
					q.save();
				}
				break;
			case "9":	// load Survey from file
				q = new Questionnaire();
				q = q.load(false);
				if (q != null){
					localSurveys.add(q);
					ui.stringOut(q.getTitle() + " loaded locally\n");
				}
				break;
			case "10":	// load Test from file
				q = new Questionnaire();
				q = q.load(true);
				if (q != null){
					localTests.add(q);
					ui.stringOut(q.getTitle() + " loaded locally\n");
				}
				break;
			case "11":	// take survey
				q = localCheck(localSurveys, "Survey", "take");
				if (!(q == null)){
					AnswerSheet a = new AnswerSheet();
					a.linkQuestionnaire(q);
					a.takeQuestionnaire();
					a.setName(Integer.toString(q.getNumResponses()));	// names Survey AnswerSheet anonymously based on number of times Survey has been
					a.save(q.getTitle(), "Survey");												// filled out
					q.save();
					ui.stringOut("");
				}
				break;
			case "12":	// take test
				q = localCheck(localTests, "Test", "take");
				if (!(q == null)){
					AnswerSheet a = new AnswerSheet();
					ui.stringOut("Enter your name:");
					a.setName(ui.stringIn());				// names Test AnswerSheet based on student name
					a.linkQuestionnaire(q);
					a.takeQuestionnaire();
					a.save(q.getTitle(), "Test");
					q.save();
					ui.stringOut("");
				}
				break;
			case "13":	// grade test
				q = localCheck(localTests, "Test", "grade");
				if (!(q == null)){
					Grader g = new Grader();
					g.load(q);			// loads the Key and student response AnswerSheets based on selected Test
					g.grade();			// grades
				}
				break;
			case "14":	// tabulate survey
				q = localCheck(localSurveys, "Survey", "tabulate");
				if (!(q == null)){
					Grader g = new Grader();
					g.load(q);			// loads the student response AnswerSheets based on selected Survey
					g.tabulate();		// tabulates
				}
				break;
			case "15":	// tabulate test
				q = localCheck(localTests, "Test", "tabulate");
				if (!(q == null)){
					Grader g = new Grader();
					g.load(q);			// loads the student response AnswerSheets based on selected Test
					g.tabulate();		// tabulates
				}
				break;
			default:	// bad input
				ui.stringOut("Please enter the number of your choice.\n");
				break;
			}
			ui.stringOut(menu);	// resets menu
			choice = ui.stringIn();
		}
		System.exit(0);		// quits
	}
	
	// adds Questions.  If more question types are added, changes should be localized here
	public void addMenu(Questionnaire q){
		String addMenu = "\n1 - Add a new True or False Question\n"
						 + "2 - Add a new Multiple Choice Question\n"
						 + "3 - Add a new Short Answer Question\n"
						 + "4 - Add a new Essay Question\n"
						 + "5 - Add a new Matching Question\n"
						 + "6 - Add a new Ranking Question\n"
						 + "7 - Finish and Save\n";
		AnswerSheet key = new AnswerSheet();		// AnswerSheets for Tests are created at the same time as Test
		ui.stringOut(addMenu);
		String choice = ui.stringIn();
		while (! choice.equals("7")){	// loops to take in new commands
			Question newQ = null;
			Boolean badInput = false;
			switch (choice){
			case "1":	// true or false
				newQ = new TrueFalse();
				break;
			case "2":	// multiple choice
				newQ = new MultChoice();
				break;
			case "3":	// short answer
				Write sa = new Write();
				sa.setLength(30);			// difference between short answer and essay is character limit
				newQ = sa;
				break;
			case "4":	// essay
				Write es = new Write();
				es.setLength(1500);
				newQ = es;
				break;
			case "5":	// matching
				newQ = new Matching();
				break;
			case "6":	// ranking
				newQ = new Ranking();
				break;
			default:	// bad input
				ui.stringOut("Please enter the number of your choice.");
				badInput = true;
				break;
			}
			if(!badInput){
				newQ.create();				// actually creates the Question
				newQ.display();
				q.addQuestion(newQ);		// adds Question to Questionnaire
				if (q.getGradeable()){		// adds Answers if Questionnaire is a Test
					if (newQ instanceof Write && ((Write) newQ).getLength() == 1500){	// essay Questions do not have correct Answers so they must be handled
							WriteAnswer wa = new WriteAnswer();								// specially.  I know this is bad, but I don't want to modify the 
							wa.linkQuestion(newQ);											// Write class because the normal selectAnswer() is still needed for 
							wa.setAns("ESSAY");												// students taking the Questionnaire
							key.addAnswer(wa);
					}else{
						ui.stringOut("Enter correct answer:");
						Answer newA = newQ.selectAnswer();				// overridden method creates the Answer of the correct subclass matching the Question
						key.addAnswer(newA);							// adds the Answer to the AnswerSheet
					}
				}
			}
				ui.stringOut(addMenu);	// resets menu
				choice = ui.stringIn();
		}
		if (q.getGradeable()){		// if it's a Test, do cleanup with the AnswerSheet
			key.setName("Key");			// designates the AnswerSheet as a Key
			key.linkQuestionnaire(q);	// links AnswerSheet to Test
			key.save(q.getTitle(), "Test");					// saves AnswerSheet
		}
	}
	
	// checks contents of lists of local Questionnaires
	public Questionnaire localCheck(ArrayList<Questionnaire> l, String surveyOrTest, String action){
		Questionnaire q = null;
		if (l.size() == 0){		// Questionnaires must be loaded locally before doing anything else with them
			ui.stringOut("No " + surveyOrTest + "s loaded locally\n");
		}else{
			ui.stringOut("Which " + surveyOrTest + " would you like to " + action + "?");
			int i = 1;
			for(Questionnaire quest : l){
				ui.stringOut(i + " - " + quest.getTitle());		// prints the list of local Questionnaires
				i++;
			}
			q = l.get(ui.inputCheck(i-1, surveyOrTest, action) - 1);	// error checking
		}
		return q;
	}
}
