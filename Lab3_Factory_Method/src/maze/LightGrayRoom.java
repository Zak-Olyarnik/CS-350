package maze;

import java.awt.Color;

public class LightGrayRoom extends Room {

	
	public LightGrayRoom(int num) {
		super(num);
	}

	@Override
	public Color getColor()
	{
		return Color.LIGHT_GRAY;
	}
}
