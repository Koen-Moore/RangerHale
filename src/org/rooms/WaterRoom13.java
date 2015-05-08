package org.rooms;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Clam;
import org.enemies.Crab;
import org.enemies.TentacleMonster;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.Wall;
import org.walls.WaterPlatform;

public class WaterRoom13 extends Room{
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
		    	new WaterPlatform(200,300,200),
		    	new WaterPlatform(400,140,80),
		    	new WaterPlatform(0,400,30),
		}));
	
	background = new Background();
	background.tessellate(gay);
	background.addAll(walls);
	enemies.add(new Clam(500,90));
	enemies.add(new Clam(500,150));
	enemies.add(new Clam(500,230));
	enemies.add(new Crab(200,485));
	enemies.add(new Crab(250,485));
	enemies.add(new Crab(450,485));
	enemies.add(new Crab(300,485));
	enemies.add(new Crab(400,485));
	enemies.add(new Crab(350,485));
	enemies.add(new TentacleMonster(40,420));
	}
	
}