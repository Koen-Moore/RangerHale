package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom10 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		HEIGHT = 600;
		WIDTH = 800;
		spriteX = 10;
		spriteY = 450 - 38;
		cameraX = 250;
		cameraY = 250;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),}));
		walls.add(new VerticalRockWall(-1, 0, 450 - 70));
		walls.add(new VerticalRockWall(-1, 450, HEIGHT));
		walls.add(new VerticalRockWall(WIDTH - 9, 0, HEIGHT - 70));
		// walls.add(new VerticalRockWall(WIDTH-9,240,HEIGHT));

		walls.add(new VerticalRockWall(210, 310, 200));
		walls.add(new VerticalRockWall(350, 75, 525));
		walls.add(new VerticalRockWall(690, 390, 60));

		walls.add(new CaveDoor(0, 450 - 80));
		exit = new CaveExit(WIDTH - 40, HEIGHT - 80);

		walls.add(new RockPlatform(0, 450, 2));
		// walls.add(new RockPlatform(WIDTH-140,240,10));

		walls.add(new RockPlatform(130, 75, 12));
		walls.add(new RockPlatform(0, 120, 4));
		walls.add(new RockPlatform(66, 180, 4));
		walls.add(new RockPlatform(155, 240, 5));
		walls.add(new RockPlatform(50, 275, 4));
		walls.add(new RockPlatform(0, 340, 13));
		walls.add(new RockPlatform(210, 510, 3));
		walls.add(new RockPlatform(290, 410, 3));

		walls.add(new RockPlatform(350, 170, 15));
		walls.add(new RockPlatform(400, 300, 4));
		walls.add(new RockPlatform(600, 300, 4));
		walls.add(new RockPlatform(460, 450, 18));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Slurg(34, 340 - 28));
		enemies.add(new Slurg(220, 600 - 28));
		enemies.add(new Slurg(500, 600 - 28));

		enemies.add(new Voggie(700, 400));
		enemies.add(new Voggie(710, 400));

		enemies.add(new Hoopla(400, 100));
		enemies.add(new Hoopla(150, 100));

		enemies.add(new Eagle(500, 40));
		enemies.add(new Eagle(400, 200));
		enemies.add(new Eagle(500, 200));

	}
}
