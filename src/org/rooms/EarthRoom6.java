package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom6 extends Room {
	public static final BufferedImage cave1=ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2=ImagePack.getImage("backgrounds/caveback2.png");
	public void load(){
		HEIGHT=1200;WIDTH=800;
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
		//walls.add(new VerticalRockWall(-1,HEIGHT,HEIGHT));
		walls.add(new VerticalRockWall(WIDTH-9,0,150-70));
		walls.add(new VerticalRockWall(WIDTH-9,150,HEIGHT));
		
		
		walls.add(new CaveDoor(0,HEIGHT-80));
		exit=new CaveExit(WIDTH-40,150-80);

		//walls.add(new RockPlatform(0,600,5));
		walls.add(new RockPlatform(WIDTH-200,150,10));

		walls.add(new RockPlatform(160,150,5));
		walls.add(new RockPlatform(380,150,5));
		walls.add(new RockPlatform(0,250,8));
		walls.add(new RockPlatform(250,300,5));
		walls.add(new RockPlatform(450,350,5));

		walls.add(new RockPlatform(650,450,10));
		walls.add(new RockPlatform(480,550,5));
		walls.add(new RockPlatform(330,630,5));
		walls.add(new RockPlatform(150,700,5));
		walls.add(new RockPlatform(0,800,8));

		walls.add(new RockPlatform(150,870,5));
		walls.add(new RockPlatform(350,950,5));
		walls.add(new RockPlatform(450,1000,5));
		walls.add(new RockPlatform(600,1100,5));

		background=new Background();
		background.fillBack(new Color(68,37,18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Eagle(300,0));
		enemies.add(new Eagle(150,400));
		enemies.add(new Eagle(560,780));
		
		
	}
}
