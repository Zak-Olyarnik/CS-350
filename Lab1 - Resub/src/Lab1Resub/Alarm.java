package Lab1Resub;

import java.util.Scanner;

public class Alarm {

	private int hour;
	private int minute;
	private String strAlarm;
	private Boolean isOn = false;
	
	public void set(Scanner s){	// scanner is passed to continue collecting input
		System.out.println("Please Set Alarm HH:MM: ");
		String input = s.nextLine();
		strAlarm = input;
		String time[] = input.split(":");
		hour = Integer.parseInt(time[0]);
		minute = Integer.parseInt(time[1]);
	}
	
	public void toggleOnOff(){
		isOn = !(isOn);
		display();
	}
	
	public String getStrAlarm(){
		return strAlarm;
	}
	
	public Boolean getIsOn(){
		return isOn;
	}
	
	public void snooze(){	// adds 9min snooze
		for (int i=0; i<9; i++){
			if (minute == 59){
				minute = 0;
				if (hour == 12){
					hour = 1;
				}else{
					hour = hour+1;
				}
			}else{
				minute = minute+1;
			}
		}
		strAlarm = Integer.toString(hour) + ":" + String.format("%02d",minute);
		display();
	}
	
	public void ring(){
		System.out.println("Buzz Buzz Buzz");
	}
	
	public void display(){		// called any time there is a change in status
		System.out.print("Alarm set for " + strAlarm + " is ");
		if (isOn){
			System.out.println("on");
		}else{
			System.out.println("off");		
		}
	}
}
