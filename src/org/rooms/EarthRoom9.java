package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom9 extends Room {
	public static final BufferedImage cave1=ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2=ImagePack.getImage("backgrounds/caveback2.png");
	public void load(){
		HEIGHT=600;WIDTH=800;
		spriteX=10;spriteY=HEIGHT-38;
		cameraX=250;cameraY=250;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
	    	new Wall(0,0,0,HEIGHT),
	    	new Wall(0,0,WIDTH,0),
	    	new Wall(0,HEIGHT,WIDTH,HEIGHT),
	    	new Wall(WIDTH,0,0,HEIGHT),
		}));
		walls.add(new VerticalRockWall(-1,0,HEIGHT-70));
		//walls.add(new VerticalRockWall(-1,100,HEIGHT));
		walls.add(new VerticalRockWall(WIDTH-9,0,240-70));
		walls.add(new VerticalRockWall(WIDTH-9,240,HEIGHT));
		

		walls.add(new VerticalRockWall(180,100,140));
		walls.add(new VerticalRockWall(660,455,30));
		
		walls.add(new CaveDoor(0,HEIGHT-80));
		exit=new CaveExit(WIDTH-40,240-80);

		//walls.add(new RockPlatform(0,100,2));
		walls.add(new RockPlatform(WIDTH-140,240,10));

		
		walls.add(new RockPlatform(110,100,4));
		walls.add(new RockPlatform(300,120,8));
		walls.add(new RockPlatform(530,120,8));
		walls.add(new RockPlatform(0,170,3));
		walls.add(new RockPlatform(100,240,35));
		
		walls.add(new RockPlatform(0,330,18));
		walls.add(new RockPlatform(680,330,8));
		walls.add(new RockPlatform(150,400,35));
		walls.add(new RockPlatform(0,480,35));

		
		background=new Background();
		background.fillBack(new Color(68,37,18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Slurg(200,240-28));
		enemies.add(new Slurg(300,240-28));
		enemies.add(new Slurg(400,240-28));

		enemies.add(new Slurg(200,480-28));
		enemies.add(new Slurg(300,480-28));
		enemies.add(new Slurg(400,480-28));
		
		enemies.add(new Hoopla(17,250));
		enemies.add(new Hoopla(300,250));
		
		enemies.add(new Eagle(300,10));
		enemies.add(new Eagle(500,10));
		
		
	}
}
