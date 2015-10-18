package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom3 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		HEIGHT = 600;
		WIDTH = 800;
		spriteX = 10;
		spriteY = 130;
		cameraX = 225;
		cameraY = 225;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),}));
		walls.add(new VerticalRockWall(-1, 0, 180 - 70));
		walls.add(new VerticalRockWall(-1, 180, HEIGHT));
		walls.add(new VerticalRockWall(WIDTH - 9, 0, 180 - 70));
		walls.add(new VerticalRockWall(WIDTH - 9, 181, HEIGHT));

		walls.add(new VerticalRockWall(70, 40, 35));
		walls.add(new VerticalRockWall(270, 70, 500 - 70));
		walls.add(new VerticalRockWall(480, 400, 100));
		walls.add(new VerticalRockWall(620, 400, 100));

		walls.add(new CaveDoor(0, 180 - 80));
		exit = new CaveExit(WIDTH - 40, 180 - 80);

		walls.add(new RockPlatform(0, 180, 5));
		walls.add(new RockPlatform(700, 180, 5));

		walls.add(new RockPlatform(0, 70, 13));
		walls.add(new RockPlatform(380, 70, 5));
		walls.add(new RockPlatform(530, 70, 5));
		walls.add(new RockPlatform(700, 70, 5));
		walls.add(new RockPlatform(430, 250, 18));
		walls.add(new RockPlatform(280, 380, 5));
		walls.add(new RockPlatform(480, 380, 6));
		walls.add(new RockPlatform(280, 500, 9));
		walls.add(new RockPlatform(620, 500, 5));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Voggie(100, 450));
		enemies.add(new Voggie(340, 400));

		enemies.add(new Eagle(120, 120));
		enemies.add(new Eagle(500, 150));
	}
}
