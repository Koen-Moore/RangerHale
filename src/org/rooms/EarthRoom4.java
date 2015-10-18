package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom4 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		HEIGHT = 750;
		WIDTH = 1000;
		spriteX = 10;
		spriteY = 550;
		cameraX = 225;
		cameraY = 225;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),}));
		walls.add(new VerticalRockWall(-1, 0, 600 - 70));
		walls.add(new VerticalRockWall(-1, 601, HEIGHT));
		walls.add(new VerticalRockWall(WIDTH - 9, 0, 100 - 70));
		walls.add(new VerticalRockWall(WIDTH - 9, 104, HEIGHT));

		walls.add(new VerticalRockWall(170, 170, 60));
		walls.add(new VerticalRockWall(250, 230, 60));

		walls.add(new VerticalRockWall(70, 335, 60));
		walls.add(new VerticalRockWall(150, 405, 60));
		walls.add(new VerticalRockWall(840, 480, 60));
		walls.add(new VerticalRockWall(910, 550, 140));

		walls.add(new CaveDoor(0, 600 - 80));
		exit = new CaveExit(WIDTH - 40, 100 - 80);

		walls.add(new RockPlatform(0, 600, 5));
		walls.add(new RockPlatform(700, 180, 5));

		walls.add(new RockPlatform(770, 100, 11));
		walls.add(new RockPlatform(100, 160, 4));
		walls.add(new RockPlatform(170, 220, 4));
		walls.add(new RockPlatform(240, 280, 38));

		walls.add(new RockPlatform(0, 330, 4));
		walls.add(new RockPlatform(70, 400, 4));
		walls.add(new RockPlatform(140, 470, 35));
		walls.add(new RockPlatform(840, 540, 4));

		walls.add(new RockPlatform(200, 600, 5));
		walls.add(new RockPlatform(480, 600, 5));
		walls.add(new RockPlatform(100, 670, 5));
		walls.add(new RockPlatform(350, 670, 5));
		walls.add(new RockPlatform(600, 670, 5));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Glorble(100, 100));
		enemies.add(new Glorble(340, 100));
		enemies.add(new Glorble(40, 100));

		enemies.add(new Glorble(120, 700));
		enemies.add(new Glorble(500, 700));
		enemies.add(new Glorble(340, 700));

	}
}
