package maze;

// code significantly improved from Lab2, following the conventions discussed in class.
	// I updated my data structures and implemented better file parsing, all according to the model we were shown

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import maze.ui.MazeViewer;

public class MazeGameCreator {
	private static Map<Room, String[]> roomSides = new HashMap<Room, String[]>();	// container for rooms' sides
	private static Map<String, Door> doors = new HashMap<String, Door>();			// container for doors
	private static ArrayList<Room> rooms = new ArrayList<Room>();					// container for rooms
	
	public Maze createMaze()	// creates a default maze
	{
		
		/*Maze maze = new Maze();
		
		// Naive Version - code from in-class notes
		Room r1 = new Room(0);
		Room r2 = new Room(1);
		Door theDoor = new Door(r1, r2); 	
		maze.addRoom(r1);
		maze.addRoom(r2);
		r1.setSide(Direction.North, new Wall());
		r1.setSide(Direction.South, new Wall());
		r1.setSide(Direction.East, theDoor);
		r1.setSide(Direction.West, new Wall());
		r2.setSide(Direction.North, new Wall());
		r2.setSide(Direction.South, new Wall());
		r2.setSide(Direction.East, new Wall());
		r2.setSide(Direction.West, theDoor);*/

		
		Maze maze = makeMaze();
		Room r1 = makeRoom(0);
		Room r2 = makeRoom(1);
		Door theDoor = makeDoor(r1, r2); 	
		maze.addRoom(r1);
		maze.addRoom(r2);
		r1.setSide(Direction.North, makeWall());
		r1.setSide(Direction.East, theDoor);
		r1.setSide(Direction.South, makeWall());
		r1.setSide(Direction.West, makeWall());
		r2.setSide(Direction.North, makeWall());
		r2.setSide(Direction.East, makeWall());
		r2.setSide(Direction.South, makeWall());
		r2.setSide(Direction.West, theDoor); 
		
		maze.setCurrentRoom(r1);		// loads ball
		return maze;
	}
	
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
	
	public Maze loadMaze(final String path) throws IOException
	{		
		File file = new File(path);
		Scanner s = new Scanner(file);
		
		// expected file format: room roomnum <North wall> <South wall> <East wall> <West wall>
		//						 door dx r1 r2 open/closed
		
		Direction[] directions = Direction.values();		// array containing the enum directions
		while (s.hasNext()){
			String roomOrDoor = s.next();
			
			if (roomOrDoor.equals("room")){
				Room roomNum = makeRoom(s.nextInt());			// room constructor takes room number as argument
				rooms.add(roomNum);								// adds to room array
				String[] sides = new String[directions.length];		// array to hold sides of room
				for (int i=0; i<directions.length; i++){
					sides[i] = s.next();
				}
				roomSides.put(roomNum, sides);		// links room and its sides in roomSides map
			
			}else if(roomOrDoor.equals("door")){
				String doorNum = s.next();
				int r1 = s.nextInt();
				int r2 = s.nextInt();
				Door currentDoor = makeDoor(rooms.get(r1), rooms.get(r2));	// door constructor takes rooms as argument.  This assumes all rooms have
				currentDoor.setOpen(s.next().equals("open"));					// already been created, which is true in the current file format
				doors.put(doorNum, currentDoor);				// links door and its identifier in doors map
			}
		}
		s.close();
		
		Maze maze = makeMaze();		// now that all rooms and doors have been created, we can set the room's sides and add them to the maze
		for(Room r : rooms){
			String[] sides = roomSides.get(r);
			for(Direction dir : directions){
				MapSite side = parseSide(sides[dir.ordinal()]);
				r.setSide(dir, side);
			}
			maze.addRoom(r);
		}
		
		maze.setCurrentRoom(rooms.get(0));		// loads ball
		return maze;
	}

	private MapSite parseSide(String side){		// helper function.  If text file changes, code changes will be localized here
		if (side.equals("wall")){
			return makeWall();
		}else if(side.substring(0, 1).equals("d")){
			return doors.get(side);
		}else{
			int roomNum = Integer.parseInt(side);
			return rooms.get(roomNum);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		// argument format should be as follows:
			// no arguments produces default, two-room maze
			// one argument file name (small.maze or large.maze) produces default maze from that file
			// one argument color (red or blue) produces two-room maze of that color
			// two arguments, file followed by color, creates a maze of that size in that color
		
		Maze maze = null;
		
		if (args.length == 0){			// no file or color provided, create default maze
			MazeGameCreator m = new MazeGameCreator();
			maze = m.createMaze();
		}else if (args.length == 1){	// load maze from file OR with specific color
			if (args[0].equals("red")){
				RedMazeGameCreator m = new RedMazeGameCreator();
				maze = m.createMaze();
			}else if(args[0].equals("blue")){
				BlueMazeGameCreator m = new BlueMazeGameCreator();
				maze = m.createMaze();
			}else{
				MazeGameCreator m = new MazeGameCreator();
				maze = m.loadMaze(args[0]);
			}
		}else if (args.length == 2){	// load maze from file AND with specific color
			if (args[1].equals("red")){
				RedMazeGameCreator m = new RedMazeGameCreator();
				maze = m.loadMaze(args[0]);
			}else if(args[1].equals("blue")){
				BlueMazeGameCreator m = new BlueMazeGameCreator();
				maze = m.loadMaze(args[0]);
			}
		}
	
	    MazeViewer viewer = new MazeViewer(maze);	// show maze to screen
	    viewer.run();
	}
}