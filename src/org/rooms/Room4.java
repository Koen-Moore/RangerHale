package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.items.FireTree;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.*;

public class Room4 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		exit = new Wall(799, 0, 1, 500);
		HEIGHT = 500;
		WIDTH = 800;
		spriteX = 0;
		spriteY = 0;
		cameraX = 100;
		cameraY = 100;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT), new MachinePlatform(100, 300, 4),
				new CloudPlatform(0, 360, 5), new CloudPlatform(200, 360, 6), new CloudPlatform(400, 360, 5),
				new CloudPlatform(600, 360, 6),}));
		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);
		for (int i = 50; i < 600; i += 100) {
			enemies.add(new Slurg(i, 320));
		}

		for (int x = 100; x <= 700; x += 100) {
			backdrop.add(new FireTree(x, 450 - 56));
		}
		enemies.add(new Shark(200, 200));
		enemies.add(new Lava(0, 450, 800, 50));
	}
}
