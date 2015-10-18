package org.rooms;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.items.HealthPack;
import org.main.RoomRunner;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.MachinePlatform;
import org.walls.ShipDoor;
import org.walls.ShipExit;
import org.walls.VerticalShipWall;
import org.walls.Wall;
import org.walls.WaterPlatform;

public class SupplyRoom extends Room {
	static final BufferedImage hull = ImagePack.getImage("backgrounds/shipHull.png"), hull2 = ImagePack
			.getImage("backgrounds/shipHullWindow.png"), vent = ImagePack.getImage("backgrounds/shipVent.png");
	public void load() {
		RoomRunner.saveGame();
		HEIGHT = 560;
		WIDTH = 800;
		spriteX = 10;
		spriteY = HEIGHT - 38;
		cameraX = 100;
		cameraY = 50;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, 500), new Wall(WIDTH, 0, 0, HEIGHT)}));
		walls.add(new MachinePlatform(200, 480, 10));
		walls.add(new MachinePlatform(500, 400, 5));
		walls.add(new MachinePlatform(250, 300, 6));
		background = new Background();
		// background.fillBack(new Color(200,200,200));
		background.tessellate(hull);

		walls.add(new VerticalShipWall(0, 0, HEIGHT - 80));
		walls.add(new VerticalShipWall(WIDTH - 9, 0, HEIGHT - 80));
		exit = new ShipExit(WIDTH - 12, HEIGHT - 80);
		backdrop.add(new ShipDoor(0, HEIGHT - 80));
		backdrop.add(exit);

		for (int i = 0; i < 800; i += 160) {
			background.add(new Wall(i, 400, hull2));
		}
		for (int i = 105; i < 800; i += 160) {
			background.add(vent, i, 194);
		}
		background.addAll(walls);
		items.add(new HealthPack(100, 545, true));
		items.add(new HealthPack(120, 545, true));
		items.add(new HealthPack(140, 545, false));
		items.add(new HealthPack(160, 545, false));
		items.add(new HealthPack(180, 545, false));
	}
}
