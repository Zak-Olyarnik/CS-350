package maze;

import java.util.HashMap;
import java.util.Map;

public class MazeMoveCommand implements UndoableCommand{

	private Maze maze;
	private Direction dir;
	private Boolean moved;	// denotes whether the ball moved or just ran into a wall
	
	MazeMoveCommand(Maze m, Direction d){
		maze = m;
		dir = d;
		moved = true;
	}
	
	@Override
	public void execute() {
		move(dir);		
	}

	@Override
	public void undo() {
		Map<Direction, Direction> opposites = new HashMap<Direction, Direction>();		// map stores Directions and their opposites
		opposites.put(Direction.North, Direction.South);
		opposites.put(Direction.South, Direction.North);
		opposites.put(Direction.East, Direction.West);
		opposites.put(Direction.West, Direction.East);
		move(opposites.get(dir));
	}
	
	private void move(Direction dir)
	{
		Room curRoom = maze.getCurrentRoom();
		MapSite side = curRoom.getSide(dir);
		side.enter();
		if (side instanceof Room)
			maze.setCurrentRoom((Room)side);
		else if (side instanceof Door) {
			maze.setCurrentRoom(((Door)side).getOtherSide(curRoom));
			maze.getCurrentRoom().enter();
		}else{		// ran into a wall, which should not be an UndoableCommand
			moved = false;
		}
	}
	
	public Boolean getMoved(){
		return moved;
	}
}
