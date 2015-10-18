package org.rooms;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.*;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CloudDoor;
import org.walls.CloudPlatform;
import org.walls.FlyingPlatform;
import org.walls.VerticalCloudWall;
import org.walls.Wall;

public class AirRoom6 extends Room {
	public static final BufferedImage cloud = ImagePack.getImage("cloud.png");
	public void load() {
		HEIGHT = 1200;
		WIDTH = 600;
		spriteX = 300;
		spriteY = 0;
		cameraX = 250;
		cameraY = 250;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				// new Wall(0,HEIGHT,WIDTH,500),
				new Wall(WIDTH, 0, 0, HEIGHT),

				new CloudPlatform(0, HEIGHT - 50, 12), new CloudPlatform(375, HEIGHT - 50, 20),}));
		foreground.add(new CloudDoor(255, HEIGHT - 50, 5));
		exit = new Wall(0, HEIGHT - 1, WIDTH, 1);

		walls.add(new CloudPlatform(0, 100, 21));
		walls.add(new CloudPlatform(510, 100, 10));
		foreground.add(new FlyingPlatform(420, 160));
		walls.add(new CloudPlatform(300, 250, 10));
		foreground.add(new FlyingPlatform(170, 320));

		walls.add(new CloudPlatform(0, 420, 8));
		foreground.add(new FlyingPlatform(150, 520));
		walls.add(new CloudPlatform(270, 600, 10));
		foreground.add(new FlyingPlatform(460, 700));
		walls.add(new CloudPlatform(360, 780, 8));
		foreground.add(new FlyingPlatform(250, 890));
		walls.add(new CloudPlatform(130, 980, 10));
		walls.add(new CloudPlatform(0, 1100, 25));

		// walls.add(new VerticalCloudWall(300,171,670));

		background = new Background();
		background.fillBack(new Color(75, 252, 255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);

		enemies.add(new PurpleBug(500, 20));
		enemies.add(new PurpleBug(400, 950));

		enemies.add(new PurpleFly(20, 360));
		enemies.add(new PurpleFly(500, 620));

		enemies.add(new Beehive(340, 260));
		enemies.add(new Beehive(330, 595));
		enemies.add(new Beehive(320, 900));

	}
}
