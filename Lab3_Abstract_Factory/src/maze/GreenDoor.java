package maze;

import java.awt.Color;

public class GreenDoor extends Door{

	public GreenDoor(Room r1, Room r2) {
		super(r1, r2);
	}

	@Override
	public Color getColor()
	{
		return Color.GREEN;
	}
}
