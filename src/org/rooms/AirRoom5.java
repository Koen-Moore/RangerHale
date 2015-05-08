package org.rooms;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CloudDoor;
import org.walls.CloudPlatform;
import org.walls.VerticalCloudWall;
import org.walls.Wall;

public class AirRoom5 extends Room {
	public static final BufferedImage cloud=ImagePack.getImage("cloud.png");
	public void load() {
		HEIGHT=1000;WIDTH=600;
		spriteX=300;spriteY=0;
		cameraX=250;cameraY=250;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
				new Wall(0,0,0,HEIGHT),
		    	new Wall(0,0,WIDTH,0),
		    	//new Wall(0,HEIGHT,WIDTH,500),
		    	new Wall(WIDTH,0,0,HEIGHT),
		    	
		    	new CloudPlatform(0,HEIGHT-50,12),
		    	new CloudPlatform(375,HEIGHT-50,20),
		}));
		foreground.add(new CloudDoor(255,HEIGHT-50,5));
		exit=new Wall(0,HEIGHT-1,WIDTH,1);
		

		walls.add(new CloudPlatform(0,130,26));
		walls.add(new CloudPlatform(0,260,10));
		walls.add(new CloudPlatform(350,260,20));
		walls.add(new CloudPlatform(180,320,10));
		walls.add(new CloudPlatform(120,420,6));
		walls.add(new CloudPlatform(350,420,6));
		walls.add(new CloudPlatform(0,500,10));
		walls.add(new CloudPlatform(500,500,10));
		walls.add(new CloudPlatform(150,620,5));
		walls.add(new CloudPlatform(0,730,15));
		walls.add(new CloudPlatform(430,730,15));
		walls.add(new CloudPlatform(189,880,17));
		
		//walls.add(new VerticalCloudWall(300,171,670));
		
		background=new Background();
		background.fillBack(new Color(75,252,255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);
		
		enemies.add(new PurpleBug(200,170));
		enemies.add(new PurpleBug(100,170));

		enemies.add(new PurpleFly(430,180));
		enemies.add(new PurpleFly(480,300));
		
		enemies.add(new Beehive(80,740));
		enemies.add(new Beehive(500,740));
		
	}	
}
