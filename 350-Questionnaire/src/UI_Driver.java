import java.util.Scanner;


public class UI_Driver {

	public static void main(String[] args) {
		UI_Driver driver = new UI_Driver();
		driver.mainMenu();
	}

	
	public void mainMenu(){
		String menu = "1  - Create a new Survey\n"
				 	+ "2  - Create a new Test\n"
				 	+ "3  - Display a Survey\n"
				 	+ "4  - Display a Test\n"
				 	+ "5  - Save a Survey\n"
				 	+ "6  - Save a Test\n"
				 	+ "7  - Exit\n";
				 	//+ "7  - Modify an existing Survey - COMING SOON\n"
				 	//+ "8  - Modify an existing Test - COMING SOON\n"
				 	//+ "9  - Load a Survey - COMING SOON\n"
				 	//+ "10 - Load a Test - COMING SOON\n"
				 	//+ "11 - Take a Survey - COMING SOON\n"
				 	//+ "12 - Take a Test - COMING SOON\n"
				 	//+ "13 - Grade a Test - COMING SOON\n"
				 	//+ "14 - Tabulate a Survey - COMING SOON\n"
				 	//+ "15 - Tabulate a Test - COMING SOON\n"

		
		System.out.println(menu);
		
		Scanner s = new Scanner(System.in);
		String choice = s.nextLine();		// reads in choice
		
		while (! choice.equals("7")){		// loops to take in new commands
			Questionnaire q;
			AnswerSheet a;
			switch (choice){
			case "1":	// create Survey
				q = new Questionnaire();
				q.create(s, false);
				q.save();
				break;
			case "2":	// create Test
				q = new Questionnaire();
				a = new AnswerSheet();
				q.create(s, true);
				System.out.println("Enter the correct answers:");
				q.take(a, s);
				q.save();
				a.save();
				break;
			case "3":	// display Survey
				q = new Questionnaire();
				q.setGradeable(false);
				q.display(s);
				break;
			case "4":	// display Test
				q = new Questionnaire();
				q.setGradeable(true);
				q.display(s);
				break;
			case "5":	// save Survey
				System.out.println("Survey will be saved automatically after Create or Modify\n");
				break;
			case "6":	// save Test
				System.out.println("Test will be saved automatically after Create or Modify\n");
				break;
			case "7":	// modify survey
				break;
			/*case "8":	// modify test
				break;
			case "9":	// load survey
				break;
			case "10":	// load test
				break;
			case "11":	// take survey
				break;
			case "12":	// take test
				break;
			case "13":	// grade test
				break;
			case "14":	// tabulate survey
				break;
			case "15":	// tabulate test
				break;*/
			default:	// bad input
				System.out.println("Please enter the number of your choice.");
				break;
			}
			System.out.println(menu);	// resets menu
			choice = s.nextLine();
		}
		s.close();
		System.exit(0);
	}
}
