package FP;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Grader {

	private AnswerSheet key = new AnswerSheet();									// AnswerSheet with correct Answers, filled out at time of Test creation
	private ArrayList<AnswerSheet> responses = new ArrayList<AnswerSheet>();		// container for student AnswerSheets
	private static ConsoleUI ui = new ConsoleUI();									// handles all I/O
	
	// grades by comparing student response AnswerSheets to Key AnswerSheet
	public void grade(){
		Boolean essayFlag = false;		// Test contains Essay Questions
		for(AnswerSheet a : responses){
			int corr = 0;		// number of correct student Answers
			int total = 0;		// number of total non-Essay Answers
			for (int i=0; i<key.getSize(); i++){
				if (key.getAnswer(i) instanceof WriteAnswer && ((WriteAnswer)key.getAnswer(i)).getAnswer().equals("ESSAY")){	// essay questions
					essayFlag = true;			// do not have correct Answers so they cannot be graded
				}else{
					if(key.getAnswer(i).compare(a.getAnswer(i))){	// does Answer comparison
						corr = corr+1;
					}
					total = total+1;
				}
			}
			ui.stringOut(a.getName() + ":");		// prints student name, grade, and percentage
			ui.stringOut(corr + " answers correct out of " + total);
			ui.stringOut((int) 100*corr/total + "%");
			if (essayFlag){			// notes Essay Questions
				ui.stringOut("Essays must be graded seperately");
			}
			ui.stringOut("");
		}
	}
	
	// collects all the student Answers
	public void tabulate(){
		ArrayList<Map<Answer, Integer>> tab = new ArrayList<Map<Answer, Integer>>();	// each ArrayList position corresponds to an Answered Question
		AnswerSheet a = responses.get(0);
		Map<Answer, Integer> m = null;		// each Map in the ArrayList holds the Question choices mapped to the number of students who chose them
		for (int i=0; i<a.getSize(); i++){		// set up the ArrayList initially by using the first response
			m = new HashMap<Answer, Integer>();
			m.put(a.getAnswer(i), 1);		// add the selected Answer as a key in the Map for that Question
			tab.add(m);						// add the Map to the ArrayList
		}
		for(int i=1; i<responses.size(); i++){	// loop for each of the rest of the responses
			AnswerSheet as = responses.get(i);
			for (int j=0; j<a.getSize(); j++){	// loop for each of the Questions
				m = tab.get(j);
				Boolean foundFlag = false;			// flag for matching Answer
				Answer foundAns = null;				// matching Answer
				Set<Answer> mySet = m.keySet();		// keySet() aggregates the keys of a map
				for(Answer ans : mySet){			// loop for choices
					if (ans.compare(as.getAnswer(j))){	// compare new choice to choices already in map
						foundFlag = true;
						foundAns = ans;
					}
				}
				if (foundFlag){				// if Answer choice already exists, increment it
					m.put(foundAns, m.get(foundAns)+1);
				}else{						// else add it as a new choice
					m.put(as.getAnswer(j), 1);
				}
			}
		}
		int i=0;
		for (Map<Answer, Integer> mapp : tab){			// iterate through Maps (Questions)
			Set<Answer> mySet = mapp.keySet();			// retrieve the Map's keys (Answers)
			ui.stringOut("\n" + (i+1) + ") " );		// print the keys (Answers), enumerated	
			for(Answer ans : mySet){					// iterate through the keys to print them
				ans.display();
				ui.stringOut("  -  " + mapp.get(ans));	// print the number of students who chose that Answer
			}
			i++;
		}
		ui.stringOut("\n");
	}
	
	// loads the Key and student AnswerSheets based on the selected Test
	public void load(Questionnaire q){
		String title = q.getTitle();
		String surveyOrTest;
		if (q.getGradeable()){
			surveyOrTest = "Test";
		}else{
			surveyOrTest = "Survey";
		}
		File directory = new File(surveyOrTest + "Answer");
		for (File file : directory.listFiles()) {
			String fName = file.getName();
			if (fName.startsWith(title + ".Key")){
				key = key.load("TestAnswer\\" + title + ".Key.Answer");
			}else if (fName.startsWith(title)){
				AnswerSheet a = new AnswerSheet();
				a = a.load(surveyOrTest + "Answer\\" + fName);
				responses.add(a);
			}
		}
	}
}
