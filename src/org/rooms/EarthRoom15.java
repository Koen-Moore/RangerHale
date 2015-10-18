package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.*;
import org.walls.*;

public class EarthRoom15 extends Room {
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
		walls.add(new VerticalRockWall(WIDTH - 9, 0, 200 - 70));
		walls.add(new VerticalRockWall(WIDTH - 9, 200, HEIGHT));

		walls.add(new VerticalRockWall(100, 100, 720));
		walls.add(new VerticalRockWall(200, 200, 520));
		walls.add(new VerticalRockWall(470, 330, 490));
		walls.add(new VerticalRockWall(800, 200, 480));
		walls.add(new VerticalRockWall(170, 60, 45));
		walls.add(new VerticalRockWall(270, 160, 45));

		walls.add(new CaveDoor(0, 100 - 80));
		exit = new CaveExit(WIDTH - 40, 200 - 80);

		walls.add(new RockPlatform(0, 100, 2));
		// walls.add(new RockPlatform(WIDTH-140,240,10));

		walls.add(new RockPlatform(100, 100, 55));
		walls.add(new RockPlatform(200, 200, 50));
		walls.add(new RockPlatform(470, 330, 10));
		walls.add(new RockPlatform(200, 440, 7));
		walls.add(new RockPlatform(610, 440, 9));
		walls.add(new RockPlatform(490, 530, 7));
		walls.add(new RockPlatform(570, 675, 11));
		walls.add(new RockPlatform(330, 590, 6));
		walls.add(new RockPlatform(110, 820, 40));
		walls.add(new RockPlatform(840, 920, 20));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		enemies.add(new Slurg(234, 100 - 28));
		enemies.add(new Slurg(334, 200 - 28));

		enemies.add(new Eagle(880, 250));
		enemies.add(new Eagle(850, 450));

		enemies.add(new Hoopla(280, 280));
		enemies.add(new Hoopla(690, 350));

		enemies.add(new Voggie(220, 880));
		enemies.add(new Voggie(380, 880));
		enemies.add(new Voggie(500, 880));

	}
}
