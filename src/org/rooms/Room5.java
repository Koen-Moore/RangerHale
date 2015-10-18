package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.*;
import org.enemies.*;

public class Room5 extends Room {
	static final BufferedImage hull = ImagePack.getImage("backgrounds/shipHull.png"), hull2 = ImagePack
			.getImage("backgrounds/shipHullWindow.png"), vent = ImagePack.getImage("backgrounds/shipVent.png");
	public void load() {
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
		walls.add(new WaterPlatform(500, 480, 30));
		walls.add(new VerticalShipWall(0, 0, HEIGHT - 80));
		walls.add(new VerticalShipWall(WIDTH - 9, 0, HEIGHT - 80));
		exit = new ShipExit(WIDTH - 12, HEIGHT - 80);
		backdrop.add(new ShipDoor(0, HEIGHT - 80));
		backdrop.add(exit);
		for (int i = 0; i < 4; i++) {
			// walls.add(new FirePlatform(100+105*i,380,i));
		}
		background = new Background();
		// background.fillBack(new Color(200,200,200));
		background.tessellate(hull);
		for (int i = 0; i < 800; i += 160) {
			background.add(new Wall(i, 400, hull2));
		}

		for (int i = 95; i < 800; i += 160) {
			background.add(vent, i, 153);
		}
		background.addAll(walls);
		for (int i = 100; i < 400; i += 100) {
			// enemies.add(new Rocketbot(i+50,500));
			// enemies.add(new Tredbot(i,537));
			// enemies.add(new SmashBlock(i+150,70));

		}
		enemies.add(new JoeMamma(300, 470, 3, -3));
		enemies.add(new JoeMamma(30, 470, 3, 3));
		enemies.add(new JoeMamma(300, 40, -3, 3));
		enemies.add(new JoeMamma(300, 400, -3, -3));
		enemies.add(new JoeMamma(350, 400, 3, 3));
		enemies.add(new JoeMamma(500, 10, 3, 3));
	}
}
