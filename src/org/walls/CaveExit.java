package org.walls;

import java.awt.image.BufferedImage;

import org.resources.ImagePack;
import org.rooms.Room;

public class CaveExit extends Wall implements Runnable{
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("earthlevel/caveDoor.png"),
		ImagePack.getImage("earthlevel/caveDoor2.png"),
		ImagePack.getImage("earthlevel/caveDoorR.png"),
		ImagePack.getImage("earthlevel/caveDoor2R.png"),
	};
	int delay;
	public CaveExit(int x,int y){
		super(x+35,y,5,80);
		Room.walls.add(new Wall(x,y,40,10,ani[2]));
		Room.foreground.add(new Wall(x,y,image=ani[3]));
	}

}
