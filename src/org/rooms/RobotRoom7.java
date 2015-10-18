package org.rooms;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Rocketbot;
import org.enemies.SmashBlock;
import org.enemies.Tredbot;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.MachinePlatform;
import org.walls.ShipDoor;
import org.walls.ShipExit;
import org.walls.VerticalShipWall;
import org.walls.Wall;

public class RobotRoom7 extends Room {
	static final BufferedImage hull = ImagePack.getImage("backgrounds/shipHull.png"), hull2 = ImagePack
			.getImage("backgrounds/shipHullWindow.png"), vent = ImagePack.getImage("backgrounds/shipVent.png");
	public void load() {
		HEIGHT = 1000;
		WIDTH = 1650;
		spriteX = 10;
		spriteY = HEIGHT - 38;
		cameraX = 100;
		cameraY = 300;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, 500), new Wall(WIDTH, 0, 0, HEIGHT)}));

		walls.add(new MachinePlatform(9, 100, 4));
		walls.add(new MachinePlatform(9, 300, 4));
		walls.add(new MachinePlatform(9, 500, 4));
		enemies.add(new Rocketbot(9, 9));
		enemies.add(new Rocketbot(9, 200));
		enemies.add(new Rocketbot(9, 400));
		for (int i = 0; i < 3; i++) {
			enemies.add(new SmashBlock(320 + 30 * i, 100));
		}

		walls.add(new MachinePlatform(509, 100, 4));
		walls.add(new MachinePlatform(509, 300, 4));
		walls.add(new MachinePlatform(509, 500, 4));
		enemies.add(new Tredbot(509, 9));
		enemies.add(new Tredbot(509, 200));
		enemies.add(new Tredbot(509, 400));
		for (int i = 0; i < 3; i++) {
			enemies.add(new SmashBlock(820 + 30 * i, 100));
		}

		walls.add(new MachinePlatform(1009, 100, 4));
		walls.add(new MachinePlatform(1009, 300, 4));
		walls.add(new MachinePlatform(1009, 500, 4));
		enemies.add(new Rocketbot(1009, 9));
		enemies.add(new Rocketbot(1009, 200));
		enemies.add(new Rocketbot(1009, 400));
		for (int i = 0; i < 3; i++) {
			enemies.add(new SmashBlock(1320 + 30 * i, 100));
		}

		walls.add(new VerticalShipWall(0, 0, HEIGHT - 80));
		walls.add(new VerticalShipWall(300, 0, 600));
		walls.add(new VerticalShipWall(500, 0, 600));
		walls.add(new VerticalShipWall(800, 0, 600));
		walls.add(new VerticalShipWall(1000, 0, 600));
		walls.add(new VerticalShipWall(1300, 0, 600));
		walls.add(new VerticalShipWall(1500, 0, 900));

		walls.add(new VerticalShipWall(WIDTH - 9, 0, 80));
		walls.add(new VerticalShipWall(WIDTH - 9, 160, HEIGHT - 80 * 2));

		exit = new ShipExit(WIDTH - 12, 80);
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

		/*
		 * enemies.add(new Tredbot(700,100)); enemies.add(new Tredbot(800,100));
		 * enemies.add(new Tredbot(900,100));
		 * 
		 * enemies.add(new Tredbot(700,700)); enemies.add(new Tredbot(800,700));
		 * enemies.add(new Tredbot(900,700));
		 * 
		 * 
		 * enemies.add(new Rocketbot(400,500)); enemies.add(new
		 * Rocketbot(500,500)); enemies.add(new Rocketbot(550,500));
		 */

	}
}
