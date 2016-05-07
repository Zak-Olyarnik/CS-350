package Lab1Resub;

import java.util.ArrayList;		// used for listing alarms
import java.util.Scanner;		// used for console output


public class Lab1Driver {
	public static void main(String[] args) {
		
		String menu = "\n0 - Exit Clock\n"
					  + "1  - Set Current Time\n"
					  + "2  - Display Current Time\n"
					  + "3  - Set New Alarm\n"
					  + "4  - Change Alarm Time\n"
					  + "5  - Show Alarm Times\n"
					  + "6  - Toggle Alarm On/Off\n"
					  + "7  - Snooze\n"
					  + "8  - Set Radio Station\n"
					  + "9  - Display Radio\n"
					  + "10 - Set Radio Volume\n"
					  + "11 - Toggle Radio On/Off\n";
			
		AlarmClockRadio theClock = new AlarmClockRadio();
		Scanner scanIn = new Scanner(System.in);
		theClock.set(scanIn);				// initial clock set
		System.out.println(menu);			// displays menu
		String choice = scanIn.nextLine();	// reads in choice
			
		while (! choice.equals("0")){		// loops to take in new commands
			ArrayList<Alarm> alarmList;
			Radio radio;
			String input;		// used for additional console input
			switch (choice){
				case "1":	// set time
					theClock.set(scanIn);
					break;
				case "2":	// display time
					theClock.getClock().display();
					break;
				case "3":	// set new alarm
					theClock.addAlarm(scanIn);
					break;
				case "4":	// change alarm
					alarmList = theClock.getAlarms();
					if (alarmList.size() == 0){
						System.out.println("No alarms set");
					}else{
						System.out.println("Select alarm to change:");
						for (int i=0; i<alarmList.size(); i++){
							System.out.print(Integer.toString(i+1) + " - ");
							alarmList.get(i).display();
						}
						theClock.resetAlarm(scanIn);
					}
					break;
				case "5":	// show all alarms
					alarmList = theClock.getAlarms();
					if (alarmList.size() == 0){
						System.out.println("No alarms set");
					}
					for (int i=0; i<alarmList.size(); i++){
						System.out.print("Alarm " + Integer.toString(i+1) + " - ");
						alarmList.get(i).display();
					}
					break;
				case "6":	// toggle alarm on/off
					alarmList = theClock.getAlarms();
					if (alarmList.size() == 0){
						System.out.println("No alarms set");
					}else{
						System.out.println("Select alarm to change:");
						for (int i=0; i<alarmList.size(); i++){
							System.out.print(Integer.toString(i+1) + " - ");
							alarmList.get(i).display();
						}
						input = scanIn.nextLine();
						int alarmNum = Integer.parseInt(input)-1;
						Alarm currentAlarm = alarmList.get(alarmNum);
						currentAlarm.toggleOnOff();
					}
					break;
				case "7":	// trigger 9min snooze
					alarmList = theClock.getAlarms();
					if (alarmList.size() == 0){
						System.out.println("No alarms set");
					}else{
						System.out.println("Select alarm to snooze:");
						for (int i=0; i<alarmList.size(); i++){
							System.out.print(Integer.toString(i+1) + " - ");
							alarmList.get(i).display();
						}
						input = scanIn.nextLine();
						int alarmNum = Integer.parseInt(input)-1;
						Alarm currentAlarm = alarmList.get(alarmNum);
						currentAlarm.snooze();
					}
					break;
				case "8":	// set radio station
					System.out.println("Enter desired station number:");
					input = scanIn.nextLine();
					float station = Float.parseFloat(input);
					radio = theClock.getRadio();
					radio.setStation(station);
					break;
				case "9":	// display radio station
					radio = theClock.getRadio();
					radio.display();
					break;
				case "10":	// set radio volume
					System.out.println("Enter desired volume 1-10:");
					input = scanIn.nextLine();
					int volume = Integer.parseInt(input);
					radio = theClock.getRadio();
					radio.setVolume(volume);
					break;
				case "11":	// toggle radio on/off
					radio = theClock.getRadio();
					radio.toggleOnOff();
					break;
				default:
					System.out.println("Please enter the number of your choice.");
					break;
			}
			System.out.println(menu);	// resets menu
			choice = scanIn.nextLine();
		}
		scanIn.close();
		System.exit(0);
	}
}
