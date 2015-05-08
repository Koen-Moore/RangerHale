package org.walls;

import java.awt.image.BufferedImage;
import org.resources.*;
import org.rooms.Room;

public class WaterDoor extends Wall implements Runnable{
	BufferedImage[]ani=WaterExit.ani;
	public WaterDoor(int x,int y){
		super(x,y,40,10);
		image=ani[0];
		Room.walls.add(new Wall(x,y,40,10,ani[0]));
		Room.foreground.add(new Wall(x,y,image=ani[1]));
		
	}

}
