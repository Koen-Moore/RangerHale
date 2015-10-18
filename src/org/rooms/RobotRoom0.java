package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.FadingBlack;
import org.HUDSystem.FadingHudTitle;
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

public class RobotRoom0 extends Room {
	static final BufferedImage hull = ImagePack.getImage("backgrounds/shipHull.png"), hull2 = ImagePack
			.getImage("backgrounds/shipHullWindow.png"), vent = ImagePack.getImage("backgrounds/shipVent.png");
	public void load() {
		RoomRunner.saveGame();
		HEIGHT = 560;
		WIDTH = 800;
		spriteX = 30;
		spriteY = 0;
		cameraX = 100;
		cameraY = 50;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, 500), new Wall(WIDTH, 0, 0, HEIGHT)}));
		// walls.add(new MachinePlatform(200,480,10));
		// walls.add(new WaterPlatform(500,480,30));
		background = new Background();
		// background.fillBack(new Color(200,200,200));
		background.tessellate(hull);

		walls.add(new VerticalShipWall(0, 0, HEIGHT));
		walls.add(new VerticalShipWall(WIDTH - 9, 0, HEIGHT - 80));
		exit = new ShipExit(WIDTH - 12, HEIGHT - 80);
		// backdrop.add(new ShipDoor(0,HEIGHT-80));
		backdrop.add(exit);

		for (int i = 0; i < 800; i += 160) {
			background.add(new Wall(i, 400, hull2));
		}
		for (int i = 105; i < 800; i += 160) {
			background.add(vent, i, 194);
		}
		background.addAll(walls);

		hud.add(new FadingHudTitle("X225 MUST BE HIDING ON THE BRIDGE!", 100, 200, Color.white, 300, 10));
		hud.add(new FadingBlack(100, 60));
		hud.add(new FadingHudTitle("THE ICARUS:", 150, 200, Color.white, 100, 60));
		hud.add(new FadingHudTitle("by Brian Richer", 180, 230, Color.white, 100, 60));
	}
}
