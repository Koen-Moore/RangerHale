package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.*;
import org.enemies.*;

public class RobotRoom4 extends Room {
	static final BufferedImage hull = ImagePack.getImage("backgrounds/shipHull.png"), hull2 = ImagePack
			.getImage("backgrounds/shipHullWindow.png"), vent = ImagePack.getImage("backgrounds/shipVent.png");
	public void load() {
		HEIGHT = 800;
		WIDTH = 500;
		spriteX = 10;
		spriteY = HEIGHT - 38;
		cameraX = 100;
		cameraY = 300;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, 500), new Wall(WIDTH, 0, 0, HEIGHT)}));
		walls.add(new MachinePlatform(0, 160, 5));
		walls.add(new MachinePlatform(225 - 96, 320, 10));
		walls.add(new MachinePlatform(0, 480, 5));
		walls.add(new MachinePlatform(225 - 96, 640, 10));

		walls.add(new MachinePlatform(WIDTH - 102 - 9, 480, 5));
		walls.add(new MachinePlatform(WIDTH - 102 - 9, 160, 5));

		walls.add(new VerticalShipWall(0, 0, HEIGHT - 80));

		walls.add(new VerticalShipWall(WIDTH - 9, 0, 80));
		walls.add(new VerticalShipWall(WIDTH - 9, 80 * 2, HEIGHT - 80 * 2));

		exit = new ShipExit(WIDTH - 12, 160 - 80);
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

		enemies.add(new Rocketbot(10, 10));
		enemies.add(new Rocketbot(550, 10));

		enemies.add(new Tredbot(200, 200));
		enemies.add(new Tredbot(300, 200));

		enemies.add(new Rocketbot(10, 400));
		enemies.add(new Rocketbot(550, 400));

		enemies.add(new Tredbot(200, 600));
		enemies.add(new Tredbot(300, 600));

	}
}
