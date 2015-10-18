package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom14 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		HEIGHT = 1000;
		WIDTH = 1000;
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
		walls.add(new VerticalRockWall(WIDTH - 9, 0, 100 - 70));
		walls.add(new VerticalRockWall(WIDTH - 9, 100, HEIGHT));

		// walls.add(new VerticalRockWall(230,0,200));

		walls.add(new CaveDoor(0, 100 - 80));
		exit = new CaveExit(WIDTH - 40, 100 - 80);

		// walls.add(new RockPlatform(0,100,2));
		// walls.add(new RockPlatform(WIDTH-140,240,10));

		walls.add(new RockPlatform(0, 100, 20));
		walls.add(new RockPlatform(600, 100, 30));
		walls.add(new RockPlatform(370, 200, 15));
		walls.add(new RockPlatform(300, 250, 5));
		walls.add(new RockPlatform(610, 250, 5));
		walls.add(new RockPlatform(250, 340, 8));
		walls.add(new RockPlatform(580, 340, 8));

		walls.add(new RockPlatform(190, 470, 5));
		walls.add(new RockPlatform(690, 470, 5));
		walls.add(new RockPlatform(500, 540, 4));
		walls.add(new RockPlatform(0, 600, 8));
		walls.add(new RockPlatform(420, 600, 12));
		walls.add(new RockPlatform(800, 600, 10));
		walls.add(new RockPlatform(350, 690, 20));
		walls.add(new RockPlatform(120, 770, 4));
		walls.add(new RockPlatform(190, 900, 4));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		// enemies.add(new Slurg(34,340-28));
		for (int i = 0; i < 20; i++) {
			enemies.add(new Glorble(100 + 20 * i, 900));
		}
		// enemies.add(new Eagle(900,0));
		// enemies.add(new Eagle(850,40));

		enemies.add(new Hoopla(220, 50));
		enemies.add(new Hoopla(700, 50));

	}
}
