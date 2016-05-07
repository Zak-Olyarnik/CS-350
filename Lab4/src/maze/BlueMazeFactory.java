package maze;

public class BlueMazeFactory extends MazeFactory{

	public Room makeRoom(int rn){
		return new LightGrayRoom(rn);
	}
	
	public Door makeDoor(Room r1, Room r2){
		return new BrownDoor(r1, r2);
	}
	
	public Wall makeWall(){
		return new BlueWall();
	}
}
