package org.walls;

import org.resources.AudioPack;
import org.rooms.Room;

public class CloudDoor extends CloudPlatform implements Runnable{
	public Wall collisions;
	public CloudDoor(int a,int b,int s){
		super(a,b,s);
		Room.walls.add(collisions=new Wall(x,y,w,h));
	}
	boolean played=false;
	public void run(){
		if(Room.curRoom.readyToEnd()&&played==false){
			played=true;
			Room.walls.remove(collisions);
			dead=true;
		}
	}
}
