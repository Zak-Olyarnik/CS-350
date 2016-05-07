/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import maze.ui.MazeViewer;

/**
 * 
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class SimpleMazeGame
{
	/**
	 * Creates a small maze.
	 */
	
	private static Map<Room, String[]> roomSides = new HashMap<Room, String[]>();	// container for rooms' sides
	private static Map<String, Door> doors = new HashMap<String, Door>();			// container for doors
	private static ArrayList<Room> rooms = new ArrayList<Room>();					// container for rooms
	
	public Maze createMaze()	// creates a default maze
	{
		
		Maze maze = new Maze();
		
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
		r2.setSide(Direction.West, theDoor);

		maze.setCurrentRoom(r1);		// loads ball
		return maze;
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
				Room roomNum = new Room(s.nextInt());			// room constructor takes room number as argument
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
				Door currentDoor = new Door(rooms.get(r1), rooms.get(r2));	// door constructor takes rooms as argument.  This assumes all rooms have
				currentDoor.setOpen(s.next().equals("open"));					// already been created, which is true in the current file format
				doors.put(doorNum, currentDoor);				// links door and its identifier in doors map
			}
		}
		
		Maze maze = new Maze();		// now that all rooms and doors have been created, we can set the room's sides and add them to the maze
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
			return new Wall();
		}else if(side.substring(0, 1).equals("d")){
			return doors.get(side);
		}else{
			int roomNum = Integer.parseInt(side);
			return rooms.get(roomNum);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		SimpleMazeGame m = new SimpleMazeGame();
		Maze maze;
		
		if (args.length == 0){			// no file provided, create default maze
			maze = m.createMaze();
		}else{							// load maze from file
			maze = m.loadMaze(args[0]);
		}

	    MazeViewer viewer = new MazeViewer(maze);	// show maze to screen
	    viewer.run();
	}
}
