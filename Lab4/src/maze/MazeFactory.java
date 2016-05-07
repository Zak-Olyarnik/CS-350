package maze;

public class MazeFactory {

	public Maze makeMaze(){
		return new Maze();
	}
	
	public Room makeRoom(int rn){
		return new Room(rn);
	}
	
	public Door makeDoor(Room r1, Room r2){
		return new Door(r1, r2);
	}
	
	public Wall makeWall(){
		return new Wall();
	}
}
