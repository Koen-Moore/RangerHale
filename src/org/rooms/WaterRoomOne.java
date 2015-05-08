package org.rooms;

import java.util.Arrays;

import org.resources.ThreadList;
import org.walls.Wall;

public class WaterRoomOne extends Room{
	public void load() {
		exit=new Wall(799,0,1,500);
		HEIGHT=500;WIDTH=800;
		spriteX=0;spriteY=0;
		cameraX=100;cameraY=100;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
				new Wall(0,0,0,HEIGHT),
		    	new Wall(0,0,WIDTH,0),
		    	new Wall(0,HEIGHT,WIDTH,500),
		    	new Wall(WIDTH,0,0,HEIGHT)
				}));
		}
}