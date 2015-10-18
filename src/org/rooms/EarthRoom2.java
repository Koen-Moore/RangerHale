package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Eagle;
import org.enemies.Voggie;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CaveDoor;
import org.walls.CaveExit;
import org.walls.RockPlatform;
import org.walls.VerticalRockWall;
import org.walls.Wall;

public class EarthRoom2 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		HEIGHT = 600;
		WIDTH = 800;
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
		// walls.add(new VerticalRockWall(-1,HEIGHT-300,300));
		walls.add(new VerticalRockWall(WIDTH - 9, 0, HEIGHT - 300 - 70));
		walls.add(new VerticalRockWall(WIDTH - 9, HEIGHT - 299, 300));

		walls.add(new VerticalRockWall(400, 0, 180));
		walls.add(new VerticalRockWall(50, 305, 100));
		walls.add(new VerticalRockWall(400, 305, 60));
		walls.add(new VerticalRockWall(310, 455, 50));
		walls.add(new VerticalRockWall(500, 350, 150));

		walls.add(new CaveDoor(0, HEIGHT - 80));
		exit = new CaveExit(WIDTH - 40, HEIGHT - 80 - 300);

		// walls.add(new RockPlatform(0,300,5));

		walls.add(new RockPlatform(200, 130, 5));
		walls.add(new RockPlatform(600, 130, 5));
		walls.add(new RockPlatform(50, 300, 37));
		walls.add(new RockPlatform(200, 450, 15));
		walls.add(new RockPlatform(0, 500, 15));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Voggie(100, 450));
		enemies.add(new Voggie(500, 450));

		enemies.add(new Eagle(100, 50));
		enemies.add(new Eagle(500, 50));
	}
}
