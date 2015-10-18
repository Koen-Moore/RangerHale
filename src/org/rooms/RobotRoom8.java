package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.*;
import org.enemies.*;

public class RobotRoom8 extends Room {
	static final BufferedImage hull = ImagePack.getImage("backgrounds/shipHull.png"), hull2 = ImagePack
			.getImage("backgrounds/shipHullWindow.png"), hull3 = ImagePack.getImage("backgrounds/shipHullDark.png"), vent = ImagePack
			.getImage("backgrounds/shipVent.png");
	public void load() {
		HEIGHT = 800;
		WIDTH = 1000;
		spriteX = 10;
		spriteY = 0;
		cameraX = 100;
		cameraY = 300;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, 500), new Wall(WIDTH, 0, 0, HEIGHT)}));
		walls.add(new MachinePlatform(400, 200, 40));
		walls.add(new MachinePlatform(400, 100, 40));

		walls.add(new MachinePlatform(250, 600, 5));
		walls.add(new MachinePlatform(50, 600, 5));

		walls.add(new VerticalShipWall(0, 0, HEIGHT - 80));

		walls.add(new VerticalShipWall(400, 200, 600 - 80));

		exit = new ShipExit(400 - 3, HEIGHT - 80);
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
		for (int i = 409; i < WIDTH; i += 80) {
			for (int a = 200; a < HEIGHT; a += 80) {
				background.add(new Wall(i, a, hull3));
			}
		}
		background.addAll(walls);

		for (int i = 0; i < 5; i++) {
			enemies.add(new SmashBlock(400 + 60 * i, 150));
			enemies.add(new Tredbot(430 + 60 * i, 110));
			enemies.add(new Rocketbot(400 + 60 * i, 110));

			enemies.add(new SmashBlock(400 + 60 * i, 50));
			enemies.add(new Tredbot(430 + 60 * i, 0));
			enemies.add(new Rocketbot(400 + 60 * i, 0));
		}

	}
}
