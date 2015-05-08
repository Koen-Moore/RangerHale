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

public class AirRoom3 extends Room {
	public static final BufferedImage cloud=ImagePack.getImage("cloud.png");
	public void load() {
		HEIGHT=900;WIDTH=600;
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
		

		walls.add(new CloudPlatform(170,170,14));
		walls.add(new CloudPlatform(0,260,8));
		walls.add(new CloudPlatform(420,260,9));
		walls.add(new CloudPlatform(170,400,14));
		walls.add(new CloudPlatform(0,500,8));
		walls.add(new CloudPlatform(420,500,9));
		walls.add(new CloudPlatform(170,650,14));
		walls.add(new CloudPlatform(0,780,8));
		walls.add(new CloudPlatform(420,780,9));
		
		walls.add(new VerticalCloudWall(300,171,670));
		
		background=new Background();
		background.fillBack(new Color(75,252,255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);
		
		enemies.add(new PurpleBug(200,790));
		enemies.add(new PurpleBug(410,790));
		
	}	
}
