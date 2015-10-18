package org.rooms;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Rocketbot;
import org.enemies.SmashBlock;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.MachinePlatform;
import org.walls.ShipDoor;
import org.walls.ShipExit;
import org.walls.VerticalShipWall;
import org.walls.Wall;

public class RobotRoom2 extends Room {
	static final BufferedImage hull = ImagePack.getImage("backgrounds/shipHull.png"), hull2 = ImagePack
			.getImage("backgrounds/shipHullWindow.png"), vent = ImagePack.getImage("backgrounds/shipVent.png");
	public void load() {
		HEIGHT = 800;
		WIDTH = 800;
		spriteX = 10;
		spriteY = HEIGHT - 38;
		cameraX = 100;
		cameraY = 300;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, 500), new Wall(WIDTH, 0, 0, HEIGHT)}));
		walls.add(new MachinePlatform(0, 300, 10));
		walls.add(new MachinePlatform(0, 450, 13));
		walls.add(new MachinePlatform(0, 600, 16));
		walls.add(new MachinePlatform(409, 200, 6));
		walls.add(new MachinePlatform(550, 400, 7));

		walls.add(new VerticalShipWall(0, 0, HEIGHT - 80));
		walls.add(new VerticalShipWall(WIDTH - 9, 0, HEIGHT - 80));
		walls.add(new VerticalShipWall(400, 200, 600));

		exit = new ShipExit(WIDTH - 12, HEIGHT - 80);
		backdrop.add(new ShipDoor(0, HEIGHT - 80));

		backdrop.add(exit);
		for (int i = 0; i < 4; i++) {
			// walls.add(new FirePlatform(100+105*i,380,i));
		}
		background = new Background();
		// background.fillBack(new Color(200,200,200));
		background.tessellate(hull);
		for (int i = 0; i < WIDTH; i += 160) {
			background.add(new Wall(i, 400, hull2));
		}

		for (int i = 95; i < WIDTH; i += 160) {
			background.add(vent, i, 153);
		}
		background.addAll(walls);
		enemies.add(new Rocketbot(500, 740));
		enemies.add(new Rocketbot(600, 740));

		for (int i = 0; i < 5; i++) {
			enemies.add(new SmashBlock(50 + 50 * i, 200));
		}
	}
}
