package maze;

import java.util.Stack;

public class MazeMover implements MazeMoveListener {

	Stack<Command> moves = new Stack<Command>();
	
	@Override
	public void moveEast(Maze maze) {
		MazeMoveCommand moveEast = new MazeMoveCommand(maze, Direction.East);
		moveEast.execute();
		if(moveEast.getMoved()){	// getMoved() says whether the position in maze actually changed, or if the ball just
			moves.push(moveEast);		// ran into a wall (which should not be undone and is not pushed on the stack)
		}
	}

	@Override
	public void moveNorth(Maze maze) {
		MazeMoveCommand moveNorth = new MazeMoveCommand(maze, Direction.North);
		moveNorth.execute();
		if(moveNorth.getMoved()){
			moves.push(moveNorth);
		}
	}

	@Override
	public void moveSouth(Maze maze) {
		MazeMoveCommand moveSouth = new MazeMoveCommand(maze, Direction.South);
		moveSouth.execute();	
		if(moveSouth.getMoved()){
			moves.push(moveSouth);
		}
	}

	@Override
	public void moveWest(Maze maze) {
		MazeMoveCommand moveWest = new MazeMoveCommand(maze, Direction.West);
		moveWest.execute();	
		if(moveWest.getMoved()){
			moves.push(moveWest);
		}
	}
	
	@Override
	public void undoMove() {
		if (!moves.isEmpty()){		// checks if there are Commands available to undo
			Command last = moves.pop();
			if(last instanceof UndoableCommand){	// checks if those Commands are Undoable
				((UndoableCommand) last).undo();		// casts the Command and undoes it
			}
		}
	}
}
