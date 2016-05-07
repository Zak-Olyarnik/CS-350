package Lab1Resub;

public class Radio {
	
	private float station;
	private int volume;
	private Boolean isOn = false;
	
	public void setStation(float stat){
		station = stat;
		display();
	}
	
	public void setVolume(int vol){
		volume = vol;
		display();
	}
	
	public float getStation(){
		return station;
	}
	
	public int getVolume(){
		return volume;
	}
	
	public void toggleOnOff(){
		isOn = !(isOn);
		display();
	}
	
	public void display(){		// called any time there is a change in status
		System.out.println("Selected station is " + Float.toString(station));
		System.out.println("Volume is " + Integer.toString(volume));
		System.out.print("Radio is ");
		if (isOn){
			System.out.println("on");
		}else{
			System.out.println("off");		
		}
	}
}
