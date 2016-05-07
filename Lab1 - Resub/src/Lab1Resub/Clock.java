package Lab1Resub;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
	private int hour;
	private int minute;
	private String strCurrentTime;
	
	public int getHour(){
		return hour;
	}
	
	public int getMinute(){
		return minute;
	}
	
	public String getCurrentTime(){
		return strCurrentTime;
	}
	
	public void setHour(int h){
		hour = h;
		strCurrentTime = Integer.toString(h) + ":" + String.format("%02d",minute);	// padding
	}
	
	public void setMinute(int m){
		minute = m;
		strCurrentTime = Integer.toString(hour) + ":" + String.format("%02d",m);	// padding
	}
	
	public void setCurrentTime(String s){
		strCurrentTime = s;
	}
	
	public void display(){
		System.out.println(getCurrentTime());
	}
	
	public void set(Scanner s){		// scanner is passed to continue collecting input
		System.out.println("Please Set Clock HH:MM: ");
		String input = s.nextLine();
		setCurrentTime(input);
		String time[] = input.split(":");
		setHour(Integer.parseInt(time[0]));
		setMinute(Integer.parseInt(time[1]));
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){		// actual clock code runs here
			public void run(){
				int m = getMinute();
				int h = getHour();
				if (m == 59){
					setMinute(0);
					if (h == 12){
						setHour(1);
					}else{
						setHour(h+1);
					}
				}else{
					setMinute(m+1);
				}
			}
			
		}, 60*1000, 60*1000);	// delay first execution 60sec and run every 60sec after
		System.out.print("Clock set as ");
		display();
	}
}
