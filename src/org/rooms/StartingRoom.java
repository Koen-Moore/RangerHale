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

public class StartingRoom extends Room{
	static final BufferedImage hull=ImagePack.getImage("backgrounds/shipHull.png"),
		hull2=ImagePack.getImage("backgrounds/shipHullWindow.png"),
		vent=ImagePack.getImage("backgrounds/shipVent.png");
	Enemy []a=new Enemy[]{
		new ShipButton(350,0),
		new ShipButton(400,0),
	};
	public void load(){
		a=new Enemy[]{
			new ShipButton(350,0),
			new ShipButton(400,0),
		};
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
	    walls.add(new VerticalShipWall(0,0,HEIGHT));
	    walls.add(new VerticalShipWall(WIDTH-9,0,HEIGHT-80));
		exit = new ShipExit(WIDTH-12,HEIGHT-80);
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
	    enemies.add(a[0]);
	    enemies.add(a[1]);

	    background.addAll(walls);

	    backdrop.add(new HudTitle("BUTTONS -A- AND -D- TO WALK",100,200,white));
	    backdrop.add(new HudTitle("ARROW KEYS TO SHOOT",100,230,white));
	    backdrop.add(new HudTitle("-W- TO JUMP",100,260,white));
	    backdrop.add(new HudTitle("SHOOT THESE TARGETS",200,35,white));
	    backdrop.add(new HudTitle("TO ESCAPE THIS ROOM",200,65,white));
	}
	public boolean readyToEnd(){
		for(Enemy e:a){
			if(e.life>=0)return false;
		}
		return true;
	}
}
