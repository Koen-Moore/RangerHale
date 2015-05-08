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

public class JumpTeachRoom extends Room{
	static final BufferedImage hull=ImagePack.getImage("backgrounds/shipHull.png"),
		hull2=ImagePack.getImage("backgrounds/shipHullWindow.png"),
		vent=ImagePack.getImage("backgrounds/shipVent.png");
	
	public void load(){
	    HEIGHT = 500; WIDTH = 560;
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
	    walls.add(new VerticalShipWall(WIDTH-9,0,80));
	    walls.add(new VerticalShipWall(WIDTH-9,160,HEIGHT-160));
		exit = new ShipExit(WIDTH-12,80);
		backdrop.add(new ShipDoor(0,HEIGHT-80));
		walls.add(new MachinePlatform(100,260,24));
	    backdrop.add(exit);
	    for(int i=0;i<4;i++){
	    	//walls.add(new FirePlatform(100+105*i,380,i));
	    }
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

	    backdrop.add(new HudTitle("YOU MUST",260,35,white));
	    backdrop.add(new HudTitle("WALL JUMP TO REACH THIS EXIT",260,65,white));
	    backdrop.add(new HudTitle("WALL JUMP BY HOLDING -W- AFTER",100,300,white));
	    backdrop.add(new HudTitle("JUMPING TOWARDS A WALL AND THEN USE",100,330,white));
	    backdrop.add(new HudTitle("-A- OR -D- TO PUSH OFF OF THE WALL",100,360,white));
	}
}
