package Lab1;

import java.util.Scanner;

public class Alarm {

	private int hour;
	private int minute;
	private String strAlarm;
	private Boolean isOn = false;
	
	public int getHour(){
		return hour;
	}
	
	public int getMinute(){
		return minute;
	}
	
	public String getStrAlarm(){
		return strAlarm;
	}
	
	public Boolean getIsOn(){
		return isOn;
	}
	
	public void set(Scanner s){	// scanner is passed to continue collecting input
		System.out.println("Please Set Alarm HH:MM: ");
		String input = s.nextLine();
		strAlarm = input;
		String time[] = input.split(":");
		hour = Integer.parseInt(time[0]);
		minute = Integer.parseInt(time[1]);
		System.out.print("Alarm set for ");
		display();
	}
	
	public void toggleOnOff(){
		isOn = !(isOn);
		System.out.print("Alarm is ");
		if (isOn){
			System.out.println("on");
		}else{
			System.out.println("off");		
		}
	}
	
	public void snooze(){	// adds 9min snooze
		for (int i=0; i<9; i++){
			int m = getMinute();
			int h = getHour();
			if (m == 59){
				minute = 0;
				if (h == 12){
					hour = 1;
				}else{
					hour = h+1;
				}
			}else{
				minute = m+1;
			}
		}
		strAlarm = Integer.toString(hour) + ":" + String.format("%02d",minute);
		System.out.println("Alarm set for " + strAlarm);
	}
	
	public void ring(){
		System.out.println("Buzz Buzz Buzz");
	}
	
	public void display(){
		System.out.println(getStrAlarm());
	}
}
