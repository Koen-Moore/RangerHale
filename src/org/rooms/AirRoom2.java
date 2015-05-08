package org.rooms;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Beehive;
import org.enemies.Eagle;
import org.enemies.Glorble;
import org.enemies.PurpleBug;
import org.enemies.PurpleFly;
import org.enemies.Shark;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CloudDoor;
import org.walls.CloudPlatform;
import org.walls.Wall;

public class AirRoom2 extends Room {
	public static final BufferedImage cloud=ImagePack.getImage("cloud.png");
	public void load() {
		HEIGHT=800;WIDTH=500;
		spriteX=0;spriteY=0;
		exit=new Wall(0,HEIGHT,WIDTH,0);
		cameraX=250;cameraY=250;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
				new Wall(0,0,0,HEIGHT),
		    	new Wall(0,0,WIDTH,0),
		    	//new Wall(0,HEIGHT,WIDTH,500),
		    	new Wall(WIDTH,0,0,HEIGHT),
		    	new CloudPlatform(0,300,3),
		    	new CloudPlatform(180,300,4),
		    	new CloudPlatform(400,300,4),
		    	new CloudPlatform(270,450,6),
		    	new CloudPlatform(80,450,4),
		    	new CloudPlatform(0,600,3),
		    	new CloudPlatform(180,600,4),
		    	new CloudPlatform(400,600,4),
		    	//new CloudPlatform(270,750,6),
		    	//new CloudPlatform(80,750,4),
		    	new CloudPlatform(270,150,6),
		    	new CloudPlatform(80,150,4),
		    	
		    	new CloudPlatform(0,HEIGHT-50,12),
		    	new CloudPlatform(375,HEIGHT-50,20),
		}));
		foreground.add(new CloudDoor(255,HEIGHT-50,5));
		exit=new Wall(0,HEIGHT-1,WIDTH,1);
		
		background=new Background();
		background.fillBack(new Color(75,252,255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);
		enemies.add(new Beehive(130,250));
		enemies.add(new PurpleBug(130,250));
		enemies.add(new PurpleFly(250,600));
	}	
}
