package org.rooms;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.JoeMamma;
import org.enemies.Rocketbot;
import org.enemies.Tredbot;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.MachinePlatform;
import org.walls.ShipDoor;
import org.walls.ShipExit;
import org.walls.VerticalShipWall;
import org.walls.Wall;

public class RobotRoom6 extends Room{
	static final BufferedImage hull=ImagePack.getImage("backgrounds/shipHull.png"),
		hull2=ImagePack.getImage("backgrounds/shipHullWindow.png"),
		vent=ImagePack.getImage("backgrounds/shipVent.png");
	public void load(){
	    HEIGHT = 800; WIDTH = 1000;
	    spriteX = 10; spriteY = 0;
	    cameraX = 200; cameraY = 300;
	    visibleHEIGHT = 500; visibleWIDTH = 500;
	    startLeft = false;
	    walls = new ThreadList<Wall>(Arrays.asList(new Wall[] { 
	    	new Wall(0, 0, 0, HEIGHT), 
	    	new Wall(0, 0, WIDTH, 0), 
	    	new Wall(0, HEIGHT, WIDTH, 500), 
	    	new Wall(WIDTH, 0, 0, HEIGHT) 
	    }));
	    walls.add(new MachinePlatform(9,160,8));
	    walls.add(new MachinePlatform(9,300,40));
	    walls.add(new MachinePlatform(1000-732-9,600,40));
	    

	    
	    walls.add(new VerticalShipWall(0,0,80));
	    walls.add(new VerticalShipWall(0,160,HEIGHT-160));

	    walls.add(new VerticalShipWall(WIDTH-9,0,HEIGHT-80));
	    
		exit = new ShipExit(WIDTH-12,HEIGHT-80);
	    backdrop.add(new ShipDoor(0,80));
	    
	    backdrop.add(exit);
	    for(int i=0;i<4;i++){
	    	//walls.add(new FirePlatform(100+105*i,380,i));
	    }
	    background = new Background();
	    //background.fillBack(new Color(200,200,200));
	    background.tessellate(hull);
	    for(int i=0;i<WIDTH;i+=160){
	    	background.add(new Wall(i,400,hull2));
	    }

	    for(int i=95;i<WIDTH;i+=160){
	    	background.add(vent,i,153);
	    }
	    background.addAll(walls);
	    
	    
	    

	    enemies.add(new Tredbot(700,100));
	    enemies.add(new Tredbot(800,100));
	    enemies.add(new Tredbot(900,100));

	    enemies.add(new Tredbot(700,700));
	    enemies.add(new Tredbot(800,700));
	    enemies.add(new Tredbot(900,700));
	    

	    enemies.add(new Rocketbot(400,500));
	    enemies.add(new Rocketbot(500,500));
	    enemies.add(new Rocketbot(550,500));
	    

   		enemies.add(new JoeMamma(600,600,-3,-3));
   		enemies.add(new JoeMamma(700,600,3,-3));
   		enemies.add(new JoeMamma(800,600,3,-3));
   		enemies.add(new JoeMamma(900,600,-3,3));
   		enemies.add(new JoeMamma(600,700,-3,-3));
   		enemies.add(new JoeMamma(700,700,3,-3));
   		enemies.add(new JoeMamma(800,700,3,-3));
   		enemies.add(new JoeMamma(900,700,-3,3));
   		
	}
}
