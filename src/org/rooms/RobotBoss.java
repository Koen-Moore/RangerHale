package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.HealthBar;
import org.HUDSystem.HudTitle;
import org.enemies.*;
import org.main.RoomRunner;
import org.players.Player;
import org.resources.ImagePack;
import org.resources.LivingObject;
import org.resources.Position;
import org.resources.ThreadList;
import org.walls.MachinePlatform;
import org.walls.ShipDoor;
import org.walls.ShipExit;
import org.walls.VerticalShipWall;
import org.walls.Wall;
import org.walls.WaterPlatform;

public class RobotBoss extends Room{
	static final BufferedImage hull=ImagePack.getImage("backgrounds/shipHull.png"),
			hull2=ImagePack.getImage("backgrounds/shipHullWindow.png");
	public void load(){
	    HEIGHT = 560; WIDTH = 800;
		exit = new Wall(WIDTH-1, 0, 1,HEIGHT);
	    spriteX = 10; spriteY = 62;
	    cameraX = 100; cameraY = 50;
	    visibleHEIGHT = 500; visibleWIDTH = 500;
	    startLeft = false;
	    walls = new ThreadList<Wall>(Arrays.asList(new Wall[] { 
	    	new Wall(-500, 0, 500, HEIGHT), 
	    	new Wall(0, 0, WIDTH, 0), 
	    	new Wall(0, HEIGHT, WIDTH, 500), 
	    	new Wall(WIDTH, 0, 500, HEIGHT) 
	    }));
	    walls.add(new MachinePlatform(200,480,10));
	    walls.add(new MachinePlatform(500,480,5));
	    
	    walls.add(new VerticalShipWall(0,0,HEIGHT-80));
	   // walls.add(new VerticalShipWall(WIDTH-9,0,HEIGHT-80));
		//exit = new ShipExit(WIDTH-12,HEIGHT-80);
	    backdrop.add(new ShipDoor(0,HEIGHT-80));
	    backdrop.add(exit);
	    
	    
	    background = new Background();
	    //background.fillBack(new Color(200,200,200));
	    background.tessellate(hull);
	    for(int i=0;i<800;i+=160){
	    	background.add(new Wall(i,400,hull2));
	    }
	    background.addAll(walls);
	    ArrayList<Integer>t=new ArrayList<>();
	    t.add(0);t.add(1);t.add(2);t.add(3);
	    t.remove((Integer)((Player.unlockedProjectiles[1]-1)/2));
		t.remove((Integer)((Player.unlockedProjectiles[2]-1)/2));
		if(Math.random()>.5){
			t.set(0, t.get(0)*2+1);
			t.set(1,t.get(1)*2+2);
		}
		else{
			t.set(0, t.get(0)*2+2);
			t.set(1,t.get(1)*2+1);
			
		}
	    X225 x=new X225(300,400,new int[]{9,t.get(0),t.get(1)});
	    enemies.add(x);
	    hud.add(new HealthBar(x,200,10,300,3,Color.red));
	}
	public int delay=-1;
	public boolean isDead(){
		
		boolean d=readyToEnd()||Player.player.life<=0;
		if(d&&delay==-1){
			delay=200;
			if(Player.player.life<=0)
				hud.add(new HudTitle("SO CLOSE!",100,200,Color.white));
			else{
				hud.add(new HudTitle("YOU WIN!",100,200,Color.black));
				RoomRunner.goToMainMenu=true;
			}
		}
		if(delay>0)
			delay--;
		return delay==0;
	}
}
