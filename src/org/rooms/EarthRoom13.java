package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Eagle;
import org.enemies.Glorble;
import org.enemies.Voggie;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CaveDoor;
import org.walls.CaveExit;
import org.walls.RockPlatform;
import org.walls.VerticalRockWall;
import org.walls.Wall;

public class EarthRoom13 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		HEIGHT = 800;
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
		// walls.add(new VerticalRockWall(-1,100,HEIGHT));
		walls.add(new VerticalRockWall(WIDTH - 9, 0, HEIGHT - 70));
		// walls.add(new VerticalRockWall(WIDTH-9,240,HEIGHT));

		walls.add(new VerticalRockWall(230, 0, 200));
		walls.add(new VerticalRockWall(280, 200, 320));
		walls.add(new VerticalRockWall(320, 560, 80));
		walls.add(new VerticalRockWall(360, 200, 320));
		walls.add(new VerticalRockWall(400, 150, 50));
		walls.add(new VerticalRockWall(480, 520, 120));
		walls.add(new VerticalRockWall(620, 100, 700));
		walls.add(new VerticalRockWall(170, 520, 120));

		walls.add(new CaveDoor(0, HEIGHT - 80));
		exit = new CaveExit(WIDTH - 40, HEIGHT - 80);

		// walls.add(new RockPlatform(0,100,2));
		// walls.add(new RockPlatform(WIDTH-140,240,10));

		walls.add(new RockPlatform(550, 100, 8));
		walls.add(new RockPlatform(450, 125, 5));
		walls.add(new RockPlatform(400, 150, 5));
		walls.add(new RockPlatform(230, 200, 2));
		walls.add(new RockPlatform(360, 200, 2));
		// walls.add(new RockPlatform(170,520,5));
		walls.add(new RockPlatform(370, 520, 5));
		walls.add(new RockPlatform(175, 640, 15));

		walls.add(new RockPlatform(700, 180, 10));
		walls.add(new RockPlatform(830, 270, 10));
		walls.add(new RockPlatform(690, 360, 10));
		walls.add(new RockPlatform(1000, 360, 10));
		walls.add(new RockPlatform(860, 470, 10));
		walls.add(new RockPlatform(730, 560, 10));
		walls.add(new RockPlatform(860, 660, 10));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		// enemies.add(new Slurg(34,340-28));

		enemies.add(new Glorble(700, 700));
		enemies.add(new Glorble(850, 700));
		enemies.add(new Glorble(900, 700));
		enemies.add(new Glorble(650, 700));
		enemies.add(new Glorble(700, 0));
		enemies.add(new Glorble(850, 0));
		enemies.add(new Glorble(900, 0));

		enemies.add(new Eagle(900, 0));
		enemies.add(new Eagle(850, 40));

		enemies.add(new Voggie(220, 580));
		enemies.add(new Voggie(380, 580));

	}
}
