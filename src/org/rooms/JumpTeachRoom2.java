package org.rooms;

import static java.awt.Color.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.HudTitle;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.*;
import org.enemies.*;

public class JumpTeachRoom2 extends Room{
	static final BufferedImage hull=ImagePack.getImage("backgrounds/shipHull.png"),
		hull2=ImagePack.getImage("backgrounds/shipHullWindow.png"),
		vent=ImagePack.getImage("backgrounds/shipVent.png");
	
	public void load(){
	    HEIGHT = 560; WIDTH = 560;
	    spriteX = 10; spriteY = HEIGHT-38;
	    cameraX = 100; cameraY = 50;
	    visibleHEIGHT = 500; visibleWIDTH = 500;
	    startLeft = false;
	    walls = new ThreadList<Wall>(Arrays.asList(new Wall[] { 
	    	new Wall(0, 0, 0, HEIGHT), 
	    	new Wall(0, 0, WIDTH, 0), 
	    	new Wall(0, HEIGHT, WIDTH, 500), 
	    	new Wall(WIDTH, 0, 0, HEIGHT) 
	    }));
	    walls.add(new VerticalShipWall(0,0,HEIGHT-80));
	    walls.add(new VerticalShipWall(WIDTH-9,0,320-80));
	    walls.add(new VerticalShipWall(WIDTH-9,320,HEIGHT-160));
	    
	    walls.add(new VerticalShipWall(80,80,HEIGHT));
	    walls.add(new VerticalShipWall(400,320,HEIGHT));
	    
		exit = new ShipExit(WIDTH-12,320-80);
		backdrop.add(new ShipDoor(0,HEIGHT-80));
		walls.add(new MachinePlatform(400,320,24));
	    backdrop.add(exit);

	    background = new Background();
	    //background.fillBack(new Color(200,200,200));
	    background.tessellate(hull);
	    for(int i=0;i<800;i+=160){
	    	background.add(new Wall(i,400,hull2));
	    }

	    for(int i=95;i<800;i+=160){
	    	background.add(vent,i,153);
	    }
	    background.addAll(walls);
	    for(int i=100;i<400;i+=100){
	    //	enemies.add(new Rocketbot(i+50,500));
	    //	enemies.add(new Tredbot(i,537));
	    //	enemies.add(new SmashBlock(i+150,70));
	    	
	    }

	    backdrop.add(new HudTitle("GRAB ONTO THE LEDGE BY HOLDING",280,250,white));
	    backdrop.add(new HudTitle("-W- AS YOU JUMP TOWARDS THE PLATFORM",280,280,white));
	    
	    backdrop.add(new HudTitle("CLIMB OUT OF THE TUNNEL BY",100,400,white));
	    backdrop.add(new HudTitle("REPEATEDLY WALL JUMPING",100,430,white));
	    backdrop.add(new HudTitle("UP THE SIDES OF THE TUNNEL",100,460,white));
	}
}
