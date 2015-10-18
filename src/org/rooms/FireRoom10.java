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
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CloudPlatform;
import org.walls.FireDoor;
import org.walls.FireExit;
import org.walls.FirePlatform;
import org.walls.MachinePlatform;
import org.walls.Wall;

public class FireRoom10 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		HEIGHT = 500;
		WIDTH = 800;
		spriteX = 0;
		spriteY = 360 - 38;
		cameraX = 100;
		cameraY = 100;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays
				.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
						new Wall(0, HEIGHT, WIDTH, HEIGHT),
						new Wall(WIDTH, 0, 0, HEIGHT),
						// new MachinePlatform(100,300,4),
						new FirePlatform(0, 360, 1), new FirePlatform(100, 390, 2), new FirePlatform(150, 200, 1),
						new FirePlatform(300, 285, 2), new FirePlatform(550, 390, 3), new FirePlatform(620, 290, 0),
						new FirePlatform(660, 250, 0), new FirePlatform(700, 200, 1),}));
		walls.add(new FireDoor(0, 360 - 80));
		exit = new FireExit(800 - 40, 200 - 80);
		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);
		foreground.add(new Lava(0, 450, 800, 50));
		for (int i = 25; i < 800; i += 100) {
			enemies.add(new Lavaspot(i, 483));
		}
		enemies.add(new Barrel(325, 265));
		enemies.add(new Fireman(345, 20));
		enemies.add(new Fireman(385, 20));
		enemies.add(new Fireman(525, 20));
		enemies.add(new FireSkull(600, 20));
	}
}
