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

public class AirRoom13 extends Room {
	public static final BufferedImage cloud=ImagePack.getImage("cloud.png");
	public void load() {
		HEIGHT=1200;WIDTH=600;
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
		

		walls.add(new CloudPlatform(0,100,25));
		walls.add(new CloudPlatform(120,240,14));
		walls.add(new CloudPlatform(0,360,14));
		walls.add(new CloudPlatform(120,500,14));
		walls.add(new CloudPlatform(0,660,14));
		walls.add(new CloudPlatform(120,800,14));
		walls.add(new CloudPlatform(0,950,14));
		walls.add(new CloudPlatform(140,1075,50));
		

		//foreground.add(new FlyingPlatform(300,300));
		
		walls.add(new VerticalCloudWall(400,240,840));
		walls.add(new VerticalCloudWall(500,102,830));
		
		background=new Background();
		background.fillBack(new Color(75,252,255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);
		
		enemies.add(new PurpleBug(400,20));
		enemies.add(new PurpleBug(300,130));

		//enemies.add(new PurpleFly(50,50));

		enemies.add(new Beehive(20,120));
		enemies.add(new Beehive(320,260));
		enemies.add(new Beehive(20,400));
		enemies.add(new Beehive(340,560));
		enemies.add(new Beehive(20,700));
		enemies.add(new Beehive(300,850));
		
	}	
}

