package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.FireSkull;
import org.enemies.Fireman;
import org.enemies.Lava;
import org.enemies.Snail;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.FireDoor;
import org.walls.FireExit;
import org.walls.FirePlatform;
import org.walls.Wall;

public class FireRoom2 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		exit = new Wall(499, 0, 500, 500);
		HEIGHT = 500;
		WIDTH = 500;
		spriteX = 0;
		spriteY = 460;
		cameraX = 100;
		cameraY = 100;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),
				// new MachinePlatform(100,300,4),
				new FirePlatform(0, 310, 2), new FirePlatform(175, 340, 2), new FirePlatform(225, 200, 1), new FirePlatform(410, 150, 2),
		// new FirePlatform(550,390,3),
		// new FirePlatform(600,200,1),
				}));

		walls.add(new FireDoor(0, 420));
		exit = new FireExit(460, 70);
		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);
		foreground.add(new Lava(0, 450, 800, 50));
		for (int i = 0; i < 120; i += 40) {
			enemies.add(new Fireman(365 + i, 360 + i));
		}
		enemies.add(new FireSkull(360, 350));

		enemies.add(new Snail(410, 150));
	}
}
