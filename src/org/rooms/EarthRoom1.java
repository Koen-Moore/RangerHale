package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom1 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		HEIGHT = 600;
		WIDTH = 800;
		spriteX = 10;
		spriteY = 300 - 38;
		cameraX = 225;
		cameraY = 225;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),}));
		walls.add(new VerticalRockWall(-1, 0, HEIGHT - 300 - 70));
		walls.add(new VerticalRockWall(-1, HEIGHT - 300, 300));
		walls.add(new VerticalRockWall(WIDTH - 9, 0, HEIGHT - 300 - 70));
		walls.add(new VerticalRockWall(WIDTH - 9, HEIGHT - 299, 300));

		walls.add(new VerticalRockWall(300, 200, 400));

		walls.add(new CaveDoor(0, 300 - 80));
		exit = new CaveExit(WIDTH - 40, HEIGHT - 80 - 300);

		walls.add(new RockPlatform(0, 300, 5));
		walls.add(new RockPlatform(WIDTH - 110, 300, 5));

		walls.add(new RockPlatform(80, 250, 5));
		walls.add(new RockPlatform(160, 200, 5));
		for (int i = 0; i < 4; i++) {
			walls.add(new RockPlatform(310 - 50 - 20 * i, 300 + 75 * i, 4 + 2 * i));
		}
		walls.add(new RockPlatform(490, 450, 5));
		walls.add(new RockPlatform(590, 375, 5));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Voggie(100, 500));
		enemies.add(new Voggie(150, 500));

		enemies.add(new Slurg(400, 600 - 30));
		enemies.add(new Eagle(600, 50));
	}
}
