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
import java.util.ArrayList;
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
	
	
	public Maze loadMaze(final String path)
	{
		Maze maze;
		File file = new File(path);
		ArrayList <String> line = new ArrayList<String>();
		
		try {
			Scanner s = new Scanner(file);
			while (s.hasNextLine()){		// read file line-by-line into array list
				line.add(s.nextLine());
			}
			s.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		maze = createFromFile(line);		// method which handles specific text file format
		return maze;
	}

	public static void main(String[] args)
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
	
	public Maze createFromFile(ArrayList<String> line){
		// method that deals with specific text file format.  If text file changes in the future, all changes to code
		// will take place here.
		// expected format: room int <North wall> <South wall> <East wall> <West wall>
		//					door dx r1 r2 open/closed
		
		String fields[][] = new String[line.size()][6];		// create 2D array to hold parsed file.  Max number of fields will be 6
		for (int i=0; i<line.size(); i++) {					// parse each line by space delimiter
	        fields[i] = line.get(i).split(" ");
	    }
		
		Maze maze = new Maze();
		ArrayList<Room> rooms = new ArrayList<Room>();		// array list holding all the room objects
		ArrayList<Door> doors = new ArrayList<Door>();		// array list holding all the door objects
		
		for (int i=0; i<line.size(); i++){
			if (fields[i][0].equals("room")){				// create rooms
				rooms.add(i, new Room(Integer.parseInt(fields[i][1])));
			}else if (fields[i][0].equals("door")){			// create doors
				Room r1 = rooms.get(Integer.parseInt(fields[i][2]));
				Room r2 = rooms.get(Integer.parseInt(fields[i][3]));
				doors.add(new Door(r1, r2));
				if (fields[i][4].equals("open")){
					doors.get(doors.size()-1).setOpen(true);
				}
			}
		}
		
		Room currentRoom;
		for (int i=0; i<rooms.size(); i++){
			currentRoom = rooms.get(i);
			maze.addRoom(currentRoom);		// adds room to maze
			for (int j=2; j<6; j++){		// positions of room's sides in text file
				switch (fields[i][j]){
				case "wall":				// adds a wall as a room's wall
					currentRoom.setSide(Direction.values()[j-2], new Wall());	// addresses enum by its value rather than name and adds new wall
					break;
				default:
					if (fields[i][j].charAt(0) == 'd'){					// adds a door as a room's wall. (Wildcards don't work in switch statements)
						String[] doorSplit = fields[i][j].split("d");	// split at "d" to get door number
						currentRoom.setSide(Direction.values()[j-2], doors.get(Integer.parseInt(doorSplit[1])));	// addresses enum by its value rather than name and gets door from door array list
					}else{												// adds another room as a room's wall
						currentRoom.setSide(Direction.values()[j-2], rooms.get(Integer.parseInt(fields[i][j])));	// addresses enum by its value rather than name and gets room from room array list
					}
					break;
				}
			}
		}
		maze.setCurrentRoom(rooms.get(0));		// loads ball
		return maze;
	}
}
