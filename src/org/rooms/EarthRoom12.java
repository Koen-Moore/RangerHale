package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom12 extends Room {
	public static final BufferedImage cave1=ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2=ImagePack.getImage("backgrounds/caveback2.png");
	public void load(){
		HEIGHT=1100;WIDTH=600;
		spriteX=10;spriteY=100-38;
		cameraX=250;cameraY=250;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
	    	new Wall(0,0,0,HEIGHT),
	    	new Wall(0,0,WIDTH,0),
	    	new Wall(0,HEIGHT,WIDTH,HEIGHT),
	    	new Wall(WIDTH,0,0,HEIGHT),
		}));
		walls.add(new VerticalRockWall(-1,0,100-70));
		walls.add(new VerticalRockWall(-1,100,HEIGHT));
		walls.add(new VerticalRockWall(WIDTH-9,0,HEIGHT-70));
		//walls.add(new VerticalRockWall(WIDTH-9,240,HEIGHT));
		

		//walls.add(new VerticalRockWall(200,420,300));
		
		walls.add(new CaveDoor(0,100-80));
		exit=new CaveExit(WIDTH-40,HEIGHT-80);

		walls.add(new RockPlatform(0,100,2));
		//walls.add(new RockPlatform(WIDTH-140,240,10));

		
		walls.add(new RockPlatform(0,180,20));
		walls.add(new RockPlatform(230,300,45));
		walls.add(new RockPlatform(120,420,10));
		
		walls.add(new RockPlatform(280,580,7));
		
		walls.add(new RockPlatform(180,680,5));
		walls.add(new RockPlatform(400,680,20));
		
		walls.add(new RockPlatform(100,800,5));
		walls.add(new RockPlatform(300,800,5));
		
		walls.add(new RockPlatform(0,900,5));
		walls.add(new RockPlatform(200,900,10));
		walls.add(new RockPlatform(500,900,10));
		
		walls.add(new RockPlatform(0,1100,13));
		walls.add(new RockPlatform(400,1100,20));

		
		background=new Background();
		background.fillBack(new Color(68,37,18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		//enemies.add(new Slurg(34,340-28));

		enemies.add(new Glorble(300,700));
		enemies.add(new Glorble(350,700));
		enemies.add(new Glorble(400,700));
		enemies.add(new Glorble(450,700));
		enemies.add(new Glorble(400,0));
		enemies.add(new Glorble(450,0));
		enemies.add(new Glorble(500,0));
		
		enemies.add(new Glorble(400,200));
		enemies.add(new Glorble(200,200));
		enemies.add(new Glorble(450,600));
		
		enemies.add(new Glorble(150,600));
		enemies.add(new Glorble(230,320));
		enemies.add(new Glorble(340,120));
		
		
	}
}
