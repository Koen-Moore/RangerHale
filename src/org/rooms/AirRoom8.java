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

public class AirRoom8 extends Room {
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

		walls.add(new CloudPlatform(130, 850, 17));
		walls.add(new CloudPlatform(0, 950, 15));
		walls.add(new CloudPlatform(350, 950, 20));
		walls.add(new CloudPlatform(160, 1100, 20));
		foreground.add(new FlyingPlatform(180, 270));
		foreground.add(new FlyingPlatform(330, 400));
		foreground.add(new FlyingPlatform(160, 540));
		foreground.add(new FlyingPlatform(320, 700));

		walls.add(new VerticalCloudWall(130, 210, 655));
		walls.add(new VerticalCloudWall(470, 210, 655));

		background = new Background();
		background.fillBack(new Color(75, 252, 255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);

		// enemies.add(new PurpleBug(500,20));
		// enemies.add(new PurpleBug(400,950));

		enemies.add(new PurpleFly(180, 600));
		enemies.add(new PurpleFly(180, 800));
		enemies.add(new PurpleFly(400, 550));

		enemies.add(new Beehive(170, 860));
		enemies.add(new Beehive(300, 860));
		enemies.add(new Beehive(400, 860));

	}
}
