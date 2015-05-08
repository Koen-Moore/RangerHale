package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Shark;
import org.enemies.Crab;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.WaterExit;
import org.walls.WaterPlatform;
import org.walls.Wall;
import org.enemies.Clam;

public class WaterRoom4 extends Room{
	static final BufferedImage gay=ImagePack.getImage("backgrounds/easy.png");
	public void load() {
		
		HEIGHT=500;WIDTH=800;
		spriteX=0;spriteY=0;
		exit=new Wall(799,0,1,500);
		cameraX=100;cameraY=100;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
				new Wall(0,0,0,HEIGHT),
		    	new Wall(0,0,WIDTH,0),
		    	new Wall(0,HEIGHT,WIDTH,500),
		    	new Wall(WIDTH,0,0,HEIGHT),
		    	new WaterPlatform(0,150,15),
		    	new WaterPlatform(300,450,80),
		    	new WaterPlatform(0,500,800),
		}));

		exit=new WaterExit(760,420);
	background = new Background();
	background.tessellate(gay);
	background.addAll(walls);
	enemies.add(new Clam(200,300));
	enemies.add(new Shark(300,400));
	enemies.add(new Clam(400,300));
	enemies.add(new Clam(400,300));
	enemies.add(new Crab(400,485));
	enemies.add(new Crab(300,485));
	}
	
}