package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Barrel;
import org.enemies.FireSkull;
import org.enemies.Fireman;
import org.enemies.Lava;
import org.enemies.Lavaspot;
import org.enemies.Snail;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CaveDoor;
import org.walls.CloudPlatform;
import org.walls.FireDoor;
import org.walls.FireExit;
import org.walls.FirePlatform;
import org.walls.MachinePlatform;
import org.walls.Wall;

public class FireRoom16 extends Room {
	public static final BufferedImage cave1=ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2=ImagePack.getImage("backgrounds/caveback2.png");
	public void load(){
		exit=new Wall(499,0,500,500);
		HEIGHT=600;WIDTH=600;
		spriteX=0;spriteY=182;
		cameraX=100;cameraY=100;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
	    	new Wall(0,0,0,HEIGHT),
	    	new Wall(0,0,WIDTH,0),
	    	new Wall(0,HEIGHT,WIDTH,HEIGHT),
	    	new Wall(WIDTH,0,0,HEIGHT),
	    	//new MachinePlatform(100,300,4),
	    	new FirePlatform(0,220,2),
	    	new FirePlatform(120,420,2),
	    	//new FirePlatform(300,250,1),
	    	new FirePlatform(220,200,2),
	    	new FirePlatform(480,360,2),
	    	new FirePlatform(370,220,2),
	    	new FirePlatform(330,475,1),
	    	new FirePlatform(530,175,2),
		}));
		background=new Background();
		background.fillBack(new Color(68,37,18));
		background.tessellateOnBottom(cave1);
		exit=new FireExit(560,95);
		walls.add(new FireDoor(0,140));
		background.tessellateOnTop(cave2);
		background.addAll(walls);
		foreground.add(new Lava(0,560,WIDTH,HEIGHT-560));
		//foreground.add(new Lava(0,450,800,50));
		//for(int i =0;i<120;i+=10){	
		//enemies.add(new Fireman(365+i,360+i));
		//}
		
		for(int x=0;x<30;x+=10){
			enemies.add(new Fireman(270+x,320));
		}
		/*for(int x=0;x<30;x+=10){
			enemies.add(new FireSkull(470+x,220));
		}
		for(int x=0;x<40;x+=10){
			enemies.add(new Lavaspot(300+x,400+x));
		}*/
		for(int x=0;x<150;x+=25){
			enemies.add(new Lavaspot(125+x,570));
		}
		/*for(int x=0;x<20;x+=10){
			enemies.add(new Snail(730,115));
		}*/
		//for(int y=0;y<15;y+=5){
		//enemies.add(new Snail(410+y,150));
		//}
	}
}