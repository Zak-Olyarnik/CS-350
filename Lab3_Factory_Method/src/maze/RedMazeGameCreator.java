package maze;

public class RedMazeGameCreator extends MazeGameCreator{
	
	public Room makeRoom(int rn){
		return new PinkRoom(rn);
	}
	
	public Door makeDoor(Room r1, Room r2){
		return new GreenDoor(r1, r2);
	}
	
	public Wall makeWall(){
		return new RedWall();
	}
}
