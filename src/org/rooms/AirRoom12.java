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
import org.walls.FlyingPlatform;
import org.walls.VerticalCloudWall;
import org.walls.Wall;

public class AirRoom12 extends Room {
	public static final BufferedImage cloud=ImagePack.getImage("cloud.png");
	public void load() {
		HEIGHT=1100;WIDTH=600;
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
		

		walls.add(new CloudPlatform(220,50,8));
		walls.add(new CloudPlatform(0,120,8));
		walls.add(new CloudPlatform(120,200,8));
		walls.add(new CloudPlatform(250,270,8));
		walls.add(new CloudPlatform(400,375,10));
		walls.add(new CloudPlatform(210,470,8));
		walls.add(new CloudPlatform(120,560,8));
		walls.add(new CloudPlatform(0,650,8));
		walls.add(new CloudPlatform(120,710,8));
		walls.add(new CloudPlatform(220,820,8));
		walls.add(new CloudPlatform(0,900,5));
		walls.add(new CloudPlatform(220,900,30));
		

		//foreground.add(new FlyingPlatform(300,300));
		
		//walls.add(new VerticalCloudWall(100,400,375));
		
		background=new Background();
		background.fillBack(new Color(75,252,255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);
		
		//enemies.add(new PurpleBug(500,20));

		enemies.add(new PurpleFly(50,50));
		enemies.add(new PurpleFly(420,320));
		enemies.add(new PurpleFly(140,600));
		enemies.add(new PurpleFly(20,850));
		enemies.add(new PurpleFly(300,840));

		enemies.add(new Beehive(200,230));
		enemies.add(new Beehive(180,580));
		enemies.add(new Beehive(200,760));
		
	}	
}

