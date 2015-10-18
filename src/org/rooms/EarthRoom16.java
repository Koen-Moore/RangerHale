package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom16 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		HEIGHT = 900;
		WIDTH = 1200;
		spriteX = 10;
		spriteY = HEIGHT - 38;
		cameraX = 250;
		cameraY = 250;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),}));
		walls.add(new VerticalRockWall(-1, 0, HEIGHT - 70));
		walls.add(new VerticalRockWall(-1, HEIGHT, HEIGHT));
		walls.add(new VerticalRockWall(WIDTH - 9, 0, 100 - 70));
		walls.add(new VerticalRockWall(WIDTH - 9, 100, HEIGHT));

		walls.add(new VerticalRockWall(215, 800, 200));
		walls.add(new VerticalRockWall(280, 740, 200));
		walls.add(new VerticalRockWall(340, 650, 300));
		walls.add(new VerticalRockWall(610, 300, 600));
		walls.add(new VerticalRockWall(930, 200, 600));
		walls.add(new VerticalRockWall(1030, 200, 600));
		walls.add(new VerticalRockWall(300, 70, 30));

		walls.add(new CaveDoor(0, HEIGHT - 80));
		exit = new CaveExit(WIDTH - 40, 100 - 80);

		// walls.add(new RockPlatform(0,100,2));
		// walls.add(new RockPlatform(WIDTH-140,240,10));

		walls.add(new RockPlatform(200, 100, 55));
		walls.add(new RockPlatform(0, 200, 46));
		walls.add(new RockPlatform(1030, 200, 10));
		walls.add(new RockPlatform(550, 300, 5));
		walls.add(new RockPlatform(0, 370, 20));
		walls.add(new RockPlatform(430, 500, 10));
		walls.add(new RockPlatform(700, 430, 5));
		walls.add(new RockPlatform(700, 600, 5));
		walls.add(new RockPlatform(700, 770, 5));
		walls.add(new RockPlatform(350, 750, 2));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Slurg(534, 100 - 28));
		enemies.add(new Slurg(334, 100 - 28));

		enemies.add(new Eagle(120, 230));
		enemies.add(new Eagle(200, 230));

		enemies.add(new Hoopla(100, 800));
		enemies.add(new Hoopla(950, 400));

		enemies.add(new Voggie(220, 110));
		enemies.add(new Voggie(380, 110));
		enemies.add(new Voggie(500, 110));

	}
}
