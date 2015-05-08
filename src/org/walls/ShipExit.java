package org.walls;

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import org.resources.*;
import org.rooms.Room;

public class ShipExit extends Wall implements Runnable{
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("backgrounds/shipDoor.png"),
		ImagePack.getImage("backgrounds/shipDoor2.png"),
		ImagePack.getImage("backgrounds/shipDoorR.png"),
		ImagePack.getImage("backgrounds/shipDoor2R.png"),
	};
	boolean played=false;
	public static final Clip[]shipClips=new Clip[]{
			AudioPack.newClip("music/doorOpen.wav",1),
			AudioPack.newClip("music/doorClose.wav",1),
		};
	
	public ShipExit(int x,int y){
		super(x,y,12,80);
		Room.walls.add(new Wall(x+3,y,12,80));
		image=ani[3];
		
	}
	public void run(){
		if(Room.curRoom.readyToEnd()&&played==false){
			AudioPack.playClip(shipClips[0]);
			played=true;
			image=ani[2];
		}
	}

}
