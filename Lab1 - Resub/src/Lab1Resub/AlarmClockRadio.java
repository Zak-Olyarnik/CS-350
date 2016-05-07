package Lab1Resub;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmClockRadio {
	private Clock clock = new Clock();
	private ArrayList<Alarm> alarms = new ArrayList<Alarm>();
	private Radio radio = new Radio();
	
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

	public void set(Scanner s){		// scanner is passed to continue collecting input
		clock.set(s);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){	// new Timer Task to check alarms
			public void run(){
				for (int i=0; i<alarms.size(); i++){
					Alarm currentAlarm = alarms.get(i);
					if (currentAlarm.getStrAlarm().equals(clock.getCurrentTime()) && currentAlarm.getIsOn()){	// checks if alarm is set to current time and turned on
						System.out.println(clock.getCurrentTime());
						currentAlarm.ring();
					}
				}
			}
		}, 0, 60*1000);
	}
	
	public ArrayList<Alarm> getAlarms(){
		return alarms;
	}
	
	public Radio getRadio(){
		return radio;
	}
	
	public Clock getClock(){
		return clock;
	}
		
}

