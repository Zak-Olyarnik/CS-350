package Lab1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmClock extends MyClock{

	private ArrayList<Alarm> alarms = new ArrayList<Alarm>();
	
	public void addAlarm(Scanner s){
		Alarm al = new Alarm();
		al.set(s);
		al.toggleOnOff();
		alarms.add(al);
		}
	
	public void resetAlarm(Scanner s){	// resets an already-set alarm
		String input = s.nextLine();
		int alarmNum = Integer.parseInt(input)-1;
		Alarm al = new Alarm();
		al.set(s);
		al.toggleOnOff();
		alarms.set(alarmNum, al);
	}
		
	public ArrayList<Alarm> getAlarms(){
		return alarms;
	}
	
	public void set(Scanner s){		// overridden to allow for checking of alarms
		System.out.println("Please Set Clock HH:MM: ");
		String input = s.nextLine();
		setCurrentTime(input);
		String time[] = input.split(":");
		setHour(Integer.parseInt(time[0]));
		setMinute(Integer.parseInt(time[1]));
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
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
				ArrayList<Alarm> alarmList = getAlarms();
				for (int i=0; i<alarmList.size(); i++){
					Alarm currentAlarm = alarmList.get(i);
					if (currentAlarm.getStrAlarm().equals(getCurrentTime()) && currentAlarm.getIsOn()){	// checks if alarm is set to current time and turned on
						System.out.println(getCurrentTime());
						currentAlarm.ring();
					}
				}
			}
			
		}, 60*1000, 60*1000);
		System.out.print("Clock set as ");
		display();
	}
		
}
