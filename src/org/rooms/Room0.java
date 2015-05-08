package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.*;

import org.BackgroundSystem.Background;
import org.HUDSystem.HUD;
import org.HUDSystem.HealthBar;
import org.enemies.*;
import org.players.Player;
import org.resources.ImagePack;
import org.resources.Position;
import org.resources.ThreadList;
import org.resources.VisibleObject;
import org.walls.Wall;

public class Room0 extends Room{
	public static final BufferedImage green = ImagePack.getImage("backgrounds/greenBack.png");
	public static final BufferedImage red= ImagePack.getImage("backgrounds/fireBack.png");
	public static final BufferedImage brown=ImagePack.getImage("backgrounds/Rockbackground.png");
	public void load() { 
		exit = new Wall(599, 0, 1, 500);
	    HEIGHT = 500; WIDTH = 600;
	    spriteX = 0; spriteY = 62;
	    cameraX = 100; cameraY = 50;
	    visibleHEIGHT = 500; visibleWIDTH = 500;
	    startLeft = false;
	    walls = new ThreadList<Wall>(Arrays.asList(new Wall[] { 
	    	new Wall(0, 0, 0, HEIGHT), 
	    	new Wall(0, 0, WIDTH, 0), 
	    	new Wall(0, HEIGHT, WIDTH, 500), 
	    	new Wall(WIDTH, 0, 0, HEIGHT) 
	    }));
	    background = new Background();
	    //background.tessellate(green);
	    background.randomTessellate(red, 8, 8);
	    //background.tessellate(red);
	    enemies.add(new Shark(200, 450));
	    enemies.add(new Shark(250, 420));
	    enemies.add(new Shark(300, 450));
	    enemies.add(new Shark(350, 420));
	    enemies.add(new Shark(400, 450));
	    enemies.add(new Shark(450, 420));
	    enemies.add(new Shark(500, 450));
	    enemies.add(new Shark(550, 420));
	   // enemies.add(new Tredbot(100,450));
	    enemies.add(new Beehive(500,450));
	    enemies.add(new Barrel(300,480));
	    enemies.add(new Barrel(350,480));
	}
}