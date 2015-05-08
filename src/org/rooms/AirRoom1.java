package org.rooms;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Beehive;
import org.enemies.Eagle;
import org.enemies.Glorble;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CloudDoor;
import org.walls.CloudPlatform;
import org.walls.Wall;

public class AirRoom1 extends Room {
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
		

		walls.add(new CloudPlatform(140,600,8));
		walls.add(new CloudPlatform(420,785,20));
		walls.add(new CloudPlatform(250,900,8));
		walls.add(new CloudPlatform(150,1050,8));
		
		//walls.add(new VerticalCloudWall(1,1,1));
		
		background=new Background();
		background.fillBack(new Color(75,252,255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);
		
		enemies.add(new Beehive(300,770));
		
	}	
}
