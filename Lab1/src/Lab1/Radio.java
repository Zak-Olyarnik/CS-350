package Lab1;

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
		System.out.println("Volume is " + Integer.toString(volume));
	}
	
	public float getStation(){
		return station;
	}
	
	public int getVolume(){
		return volume;
	}
	
	public void display(){
		System.out.println("Selected station is " + Float.toString(station));
	}
	
	public void toggleOnOff(){
		isOn = !(isOn);
		System.out.print("Radio is ");
		if (isOn){
			System.out.println("on");
		}else{
				System.out.println("off");		
		}
	}

}
