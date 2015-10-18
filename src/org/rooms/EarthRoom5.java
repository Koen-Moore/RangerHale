package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom5 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		HEIGHT = 800;
		WIDTH = 1000;
		spriteX = 10;
		spriteY = HEIGHT - 38;
		cameraX = 225;
		cameraY = 225;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),}));
		walls.add(new VerticalRockWall(-1, 0, HEIGHT - 70));
		// walls.add(new VerticalRockWall(-1,HEIGHT,HEIGHT));
		walls.add(new VerticalRockWall(WIDTH - 9, 0, 160 - 70));
		walls.add(new VerticalRockWall(WIDTH - 9, 160, HEIGHT));

		walls.add(new VerticalRockWall(240, 240, 60));
		walls.add(new VerticalRockWall(700, 240, 60));
		walls.add(new VerticalRockWall(400, 530, 100));
		walls.add(new VerticalRockWall(580, 700, 100));

		walls.add(new CaveDoor(0, HEIGHT - 80));
		exit = new CaveExit(WIDTH - 40, 160 - 80);

		// walls.add(new RockPlatform(0,600,5));
		walls.add(new RockPlatform(800, 160, 10));

		walls.add(new RockPlatform(100, 300, 30));
		walls.add(new RockPlatform(700, 240, 15));

		walls.add(new RockPlatform(0, 440, 3));
		walls.add(new RockPlatform(0, 530, 6));
		walls.add(new RockPlatform(400, 580, 20));
		walls.add(new RockPlatform(0, 630, 20));
		walls.add(new RockPlatform(860, 700, 4));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Hoopla(300, 200));
		enemies.add(new Hoopla(700, 700));
		enemies.add(new Hoopla(250, 550));

	}
}
