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

public class AirRoom14 extends Room {
	public static final BufferedImage cloud=ImagePack.getImage("cloud.png");
	public void load() {
		HEIGHT=1100;WIDTH=800;
		spriteX=420;spriteY=0;
		cameraX=250;cameraY=250;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
				new Wall(0,0,0,HEIGHT),
		    	new Wall(0,0,WIDTH,0),
		    	//new Wall(0,HEIGHT,WIDTH,500),
		    	new Wall(WIDTH,0,0,HEIGHT),
		    	
		    	new CloudPlatform(0,HEIGHT-50,12),
		    	new CloudPlatform(375,HEIGHT-50,30),
		}));
		foreground.add(new CloudDoor(255,HEIGHT-50,5));
		exit=new Wall(0,HEIGHT-1,WIDTH,1);
		

		walls.add(new CloudPlatform(100,195,10));
		walls.add(new CloudPlatform(500,195,10));
		walls.add(new CloudPlatform(0,800,10));
		walls.add(new CloudPlatform(300,800,10));
		walls.add(new CloudPlatform(600,800,10));
		walls.add(new CloudPlatform(120,920,15));
		walls.add(new CloudPlatform(500,920,15));
		walls.add(new CloudPlatform(300,1000,10));
		

		//foreground.add(new FlyingPlatform(300,300));
		
		walls.add(new VerticalCloudWall(100,200,500));
		walls.add(new VerticalCloudWall(200,300,500));
		walls.add(new VerticalCloudWall(300,200,600));
		walls.add(new VerticalCloudWall(400,0,650));
		walls.add(new VerticalCloudWall(500,200,600));
		walls.add(new VerticalCloudWall(600,300,500));
		walls.add(new VerticalCloudWall(700,200,500));
		
		background=new Background();
		background.fillBack(new Color(75,252,255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);
		
		enemies.add(new PurpleBug(120,720));
		enemies.add(new PurpleBug(620,720));
		enemies.add(new PurpleBug(540,820));

		enemies.add(new PurpleFly(140,80));
		enemies.add(new PurpleFly(600,80));
		enemies.add(new PurpleFly(170,820));

		enemies.add(new Beehive(340,300));
		enemies.add(new Beehive(340,400));
		enemies.add(new Beehive(340,810));
		enemies.add(new Beehive(440,810));
		enemies.add(new Beehive(440,400));
		enemies.add(new Beehive(440,300));
		
	}	
}

