package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Shark;
import org.enemies.Crab;
import org.enemies.Water;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.Log;
import org.walls.WaterPlatform;
import org.walls.Wall;
import org.enemies.Clam;
import org.enemies.TentacleMonster;

public class WaterRoom15 extends Room{
	static final BufferedImage gay=ImagePack.getImage("backgrounds/hard.png");
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
		    	new WaterPlatform(200,400,30),
		    	new WaterPlatform(600,400,30),
		    	new WaterPlatform(600,80,100),
		    	new WaterPlatform(400,190,50),
		    	new WaterPlatform(0,80,100),
		}));
	
	background = new Background();
	background.tessellate(gay);
	background.addAll(walls);
	enemies.add(new Crab(10,485));
	enemies.add(new Crab(20,485));
	enemies.add(new Crab(30,485));
	enemies.add(new Crab(40,485));
	enemies.add(new TentacleMonster(40,420));
	enemies.add(new TentacleMonster(400,420));
	enemies.add(new TentacleMonster(760,420));
	enemies.add(new Clam(20,300));
	enemies.add(new Clam(20,400));
	}
	
}