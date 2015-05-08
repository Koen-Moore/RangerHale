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

public class AirRoom15 extends Room {
	public static final BufferedImage cloud=ImagePack.getImage("cloud.png");
	public void load() {
		HEIGHT=1000;WIDTH=600;
		spriteX=270;spriteY=0;
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
		

		walls.add(new CloudPlatform(100,69,7));
		walls.add(new CloudPlatform(370,69,7));
		walls.add(new CloudPlatform(100,149,8));
		walls.add(new CloudPlatform(390,179,8));
		walls.add(new CloudPlatform(0,330,7));
		walls.add(new CloudPlatform(250,380,30));
		walls.add(new CloudPlatform(90,475,6));
		walls.add(new CloudPlatform(0,570,25));
		

		foreground.add(new FlyingPlatform(460,700));
		foreground.add(new FlyingPlatform(300,750));
		foreground.add(new FlyingPlatform(100,800));
		foreground.add(new FlyingPlatform(200,900));
		
		walls.add(new VerticalCloudWall(100,70,75));
		walls.add(new VerticalCloudWall(260,150,325));
		walls.add(new VerticalCloudWall(390,180,100));
		walls.add(new VerticalCloudWall(530,70,110));
		
		background=new Background();
		background.fillBack(new Color(75,252,255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);
		
		enemies.add(new PurpleBug(160,500));
		enemies.add(new PurpleBug(260,500));
		enemies.add(new PurpleBug(470,310));

		enemies.add(new PurpleFly(128,15));
		enemies.add(new PurpleFly(400,15));
		enemies.add(new PurpleFly(50,250));
		enemies.add(new PurpleFly(130,400));

		enemies.add(new Beehive(15,350));
		enemies.add(new Beehive(115,90));
		enemies.add(new Beehive(470,90));
		
	}	
}

