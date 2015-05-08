package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CloudPlatform;
import org.walls.FireDoor;
import org.walls.FireExit;
import org.walls.FirePlatform;
import org.walls.MachinePlatform;
import org.walls.Wall;
import org.walls.WaterDoor;
import org.walls.WaterExit;

public class FireRoom14 extends Room {
	public static final BufferedImage cave1=ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2=ImagePack.getImage("backgrounds/caveback2.png");
	public void load(){
		HEIGHT=500;WIDTH=800;
		spriteX=0;spriteY=500-38;
		cameraX=100;cameraY=100;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
	    	new Wall(0,0,0,HEIGHT),
	    	new Wall(0,0,WIDTH,0),
	    	new Wall(0,HEIGHT,WIDTH,HEIGHT),
	    	new Wall(WIDTH,0,0,HEIGHT),
	    	//new MachinePlatform(100,300,4),
	    	//new FirePlatform(100,200,2),
	    	new FirePlatform(400,400,1),
	    	new FirePlatform(500,350,2),
	    	new FirePlatform(600,250,1),
	    	new FirePlatform(730,200,3),

		}));
		walls.add(new FireDoor(0,500-80));
		exit=new FireExit(800-40,200-80);
		background=new Background();
		background.fillBack(new Color(68,37,18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);
		//foreground.add(new Lava(0,450,800,50));

		enemies.add(new Snail(345,40));
		enemies.add(new Snail(445,40));
		enemies.add(new Snail(545,40));
		


		for(int i=260;i<321;i+=20){
			enemies.add(new Barrel(i,480));
		}
		for(int i=560;i<621;i+=20){
			enemies.add(new Barrel(i,480));
		}
		
		enemies.add(new Fireman(345,20));
		enemies.add(new Fireman(345,40));
		enemies.add(new Fireman(385,40));
		enemies.add(new Fireman(525,40));
		
		//foreground.add(new Lava(0,450,600,50));
	}
}
