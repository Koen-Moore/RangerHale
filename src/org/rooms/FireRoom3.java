package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Barrel;
import org.enemies.FireSkull;
import org.enemies.Fireman;
import org.enemies.Lava;
import org.enemies.Lavaspot;
import org.enemies.Snail;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CaveDoor;
import org.walls.CloudPlatform;
import org.walls.FireDoor;
import org.walls.FireExit;
import org.walls.FirePlatform;
import org.walls.MachinePlatform;
import org.walls.Wall;

public class FireRoom3 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		exit = new Wall(499, 0, 500, 500);
		HEIGHT = 500;
		WIDTH = 500;
		spriteX = 0;
		spriteY = 462;
		cameraX = 100;
		cameraY = 100;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),
				// new MachinePlatform(100,300,4),
				new FirePlatform(50, 280, 2), new FirePlatform(200, 140, 2), new FirePlatform(375, 280, 1), new FirePlatform(200, 400, 2),
		// new FirePlatform(550,390,3),
		// new FirePlatform(600,200,1),
				}));
		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		exit = new FireExit(460, 420);
		walls.add(new FireDoor(0, 420));
		background.tessellateOnTop(cave2);
		background.addAll(walls);
		// foreground.add(new Lava(0,450,800,50));
		// for(int i =0;i<120;i+=10){
		// enemies.add(new Fireman(365+i,360+i));
		// }

		for (int x = 0; x < 10; x += 10) {
			enemies.add(new FireSkull(50 + x, 280));
		}
		for (int x = 0; x < 10; x += 10) {
			enemies.add(new Snail(450 + x, 450));
		}
		for (int x = 0; x < 50; x += 10) {
			enemies.add(new Lavaspot(225 + x, 120 + x));
		}
		// for(int y=0;y<15;y+=5){
		// enemies.add(new Snail(410+y,150));
		// }
	}
}