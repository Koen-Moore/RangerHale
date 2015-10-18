package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom7 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		HEIGHT = 600;
		WIDTH = 800;
		spriteX = 10;
		spriteY = 100 - 38;
		cameraX = 250;
		cameraY = 250;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),}));
		walls.add(new VerticalRockWall(-1, 0, 100 - 70));
		walls.add(new VerticalRockWall(-1, 100, HEIGHT));
		walls.add(new VerticalRockWall(WIDTH - 9, 0, 150 - 70));
		walls.add(new VerticalRockWall(WIDTH - 9, 150, HEIGHT));

		walls.add(new VerticalRockWall(100, 0, 500));
		walls.add(new VerticalRockWall(200, 100, 500));
		walls.add(new VerticalRockWall(300, 0, 500));
		walls.add(new VerticalRockWall(580, 215, 25));

		walls.add(new CaveDoor(0, 100 - 80));
		exit = new CaveExit(WIDTH - 40, 150 - 80);

		walls.add(new RockPlatform(0, 100, 2));
		// walls.add(new RockPlatform(WIDTH-200,150,10));

		walls.add(new RockPlatform(400, 150, 20));
		walls.add(new RockPlatform(300, 240, 20));
		walls.add(new RockPlatform(650, 350, 8));
		walls.add(new RockPlatform(400, 500, 20));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Eagle(500, 20));
		enemies.add(new Slurg(350, 240 - 28));
		enemies.add(new Voggie(300, 550));
		enemies.add(new Voggie(400, 550));

	}
}
