package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom11 extends Room {
	public static final BufferedImage cave1=ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2=ImagePack.getImage("backgrounds/caveback2.png");
	public void load(){
		HEIGHT=800;WIDTH=1000;
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
		

		walls.add(new VerticalRockWall(200,420,290));
		walls.add(new VerticalRockWall(320,200,220));
		walls.add(new VerticalRockWall(575,100,475));
		walls.add(new VerticalRockWall(790,190,510));
		walls.add(new VerticalRockWall(890,100,475));
		
		walls.add(new CaveDoor(0,100-80));
		exit=new CaveExit(WIDTH-40,HEIGHT-80);

		//walls.add(new RockPlatform(0,450,2));
		//walls.add(new RockPlatform(WIDTH-140,240,10));

		
		walls.add(new RockPlatform(0,100,45));
		walls.add(new RockPlatform(320,200,5));
		walls.add(new RockPlatform(720,200,3));
		walls.add(new RockPlatform(420,300,13));
		walls.add(new RockPlatform(200,420,10));
		walls.add(new RockPlatform(720,420,3));
		walls.add(new RockPlatform(340,580,15));
		walls.add(new RockPlatform(100,700,45));

		
		background=new Background();
		background.fillBack(new Color(68,37,18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		//enemies.add(new Slurg(34,340-28));

		enemies.add(new Voggie(300,700));
		enemies.add(new Voggie(350,700));
		enemies.add(new Voggie(400,700));
		enemies.add(new Voggie(450,700));
		enemies.add(new Voggie(400,0));
		enemies.add(new Voggie(450,0));
		enemies.add(new Voggie(500,0));
		
		enemies.add(new Hoopla(400,200));
		enemies.add(new Hoopla(600,200));
		enemies.add(new Hoopla(450,600));
		
		enemies.add(new Glorble(150,600));
		enemies.add(new Glorble(240,330));
		enemies.add(new Glorble(350,110));
		
		
	}
}
