package org.walls;

import java.awt.image.BufferedImage;
import org.resources.*;
import org.rooms.Room;

public class ShipDoor extends Wall implements Runnable{
	BufferedImage[]ani=ShipExit.ani;
	int delay;
	boolean left;
	public ShipDoor(int x,int y){
		super(x,y,9,80);
		Room.walls.add(new Wall(x,y,9,80));
		image=ani[0];
		delay=50;
	}
	public void run(){
		delay--;
		if(delay==0){
			AudioPack.playClip(ShipExit.shipClips[1]);
			image=ani[1];
		}
	}

}
