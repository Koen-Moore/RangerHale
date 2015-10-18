package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.*;
import org.enemies.*;

public class RobotRoom5 extends Room {
	static final BufferedImage hull = ImagePack.getImage("backgrounds/shipHull.png"), hull2 = ImagePack
			.getImage("backgrounds/shipHullWindow.png"), vent = ImagePack.getImage("backgrounds/shipVent.png");
	public void load() {
		HEIGHT = 800;
		WIDTH = 800;
		spriteX = 10;
		spriteY = 0;
		cameraX = 100;
		cameraY = 300;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, 500), new Wall(WIDTH, 0, 0, HEIGHT)}));
		walls.add(new MachinePlatform(500, 160, 2));
		walls.add(new MachinePlatform(500, 240, 4));
		walls.add(new MachinePlatform(500, 320, 6));
		walls.add(new MachinePlatform(500, 400, 8));
		walls.add(new MachinePlatform(500, 480, 10));
		walls.add(new MachinePlatform(500, 560, 12));

		walls.add(new MachinePlatform(9, 600, 10));
		// walls.add(new VerticalShipWall(100,0,800));

		walls.add(new MachinePlatform(WIDTH - 102 - 9, 160, 5));

		walls.add(new VerticalShipWall(0, 0, 80));
		walls.add(new VerticalShipWall(0, 160, HEIGHT - 160));

		walls.add(new VerticalShipWall(WIDTH - 9, 0, 80));
		walls.add(new VerticalShipWall(WIDTH - 9, 80 * 2, HEIGHT - 80 * 2));

		walls.add(new VerticalShipWall(500 - 9, 0, 600));

		exit = new ShipExit(WIDTH - 12, 160 - 80);
		backdrop.add(new ShipDoor(0, 80));

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

		enemies.add(new Tredbot(400, 700));
		enemies.add(new Tredbot(500, 700));
		enemies.add(new Tredbot(200, 700));
		enemies.add(new Tredbot(300, 700));

		for (int i = 0; i < 5; i++) {
			enemies.add(new SmashBlock(550 + 20 * i, 20));
		}

		enemies.add(new JoeMamma(100, 100, -3, -3));
		enemies.add(new JoeMamma(200, 200, 3, -3));
		enemies.add(new JoeMamma(300, 300, -3, 3));

	}
}
