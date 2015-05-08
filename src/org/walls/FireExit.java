package org.walls;

import java.awt.image.BufferedImage;
import org.resources.*;
import org.rooms.Room;

public class FireExit extends Wall implements Runnable{
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("firelevel/fireDoor.png"),
		ImagePack.getImage("firelevel/fireDoor2.png"),
		ImagePack.getImage("firelevel/fireDoorR.png"),
		ImagePack.getImage("firelevel/fireDoor2R.png"),
	};
	public FireExit(int x,int y){
		super(x+35,y,5,80);
		Room.walls.add(new Wall(x,y,40,10,ani[2]));
		Room.foreground.add(new Wall(x,y,image=ani[3]));
	}

}
