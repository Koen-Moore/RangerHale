package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom8 extends Room {
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
		walls.add(new VerticalRockWall(WIDTH-9,0,150-70));
		walls.add(new VerticalRockWall(WIDTH-9,150,HEIGHT));
		

		walls.add(new VerticalRockWall(240,150,450));
		walls.add(new VerticalRockWall(530,0,470));
		
		walls.add(new CaveDoor(0,HEIGHT-80));
		exit=new CaveExit(WIDTH-40,150-80);

		//walls.add(new RockPlatform(0,100,2));
		walls.add(new RockPlatform(WIDTH-140,150,10));

		walls.add(new RockPlatform(0,130,5));
		walls.add(new RockPlatform(137,230,5));
		walls.add(new RockPlatform(0,350,5));
		walls.add(new RockPlatform(137,470,5));
		
		walls.add(new RockPlatform(440,230,13));
		walls.add(new RockPlatform(300,420,10));
		walls.add(new RockPlatform(660,350,20));
		walls.add(new RockPlatform(530,460,5));

		background=new Background();
		background.fillBack(new Color(68,37,18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Slurg(300,600-28));
		enemies.add(new Slurg(350,600-28));
		enemies.add(new Hoopla(17,80));
		enemies.add(new Hoopla(700,250));
		enemies.add(new Glorble(650,500));
		
		
	}
}
