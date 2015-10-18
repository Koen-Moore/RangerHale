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

public class AirRoom11 extends Room {
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

		walls.add(new CloudPlatform(170, 125, 13));
		walls.add(new CloudPlatform(0, 210, 8));
		walls.add(new CloudPlatform(490, 210, 8));
		walls.add(new CloudPlatform(100, 398, 20));
		walls.add(new CloudPlatform(0, 840, 13));
		walls.add(new CloudPlatform(330, 840, 13));
		walls.add(new CloudPlatform(150, 890, 15));

		foreground.add(new FlyingPlatform(300, 300));

		foreground.add(new FlyingPlatform(170, 480));
		foreground.add(new FlyingPlatform(120, 600));
		foreground.add(new FlyingPlatform(170, 730));

		foreground.add(new FlyingPlatform(340, 480));
		foreground.add(new FlyingPlatform(440, 600));
		foreground.add(new FlyingPlatform(340, 730));

		foreground.add(new FlyingPlatform(75, 1000));
		foreground.add(new FlyingPlatform(500, 1000));
		foreground.add(new FlyingPlatform(140, 1080));
		foreground.add(new FlyingPlatform(430, 1080));

		walls.add(new VerticalCloudWall(100, 400, 375));
		walls.add(new VerticalCloudWall(500, 400, 375));
		walls.add(new VerticalCloudWall(270, 480, 360));
		walls.add(new VerticalCloudWall(330, 480, 360));

		background = new Background();
		background.fillBack(new Color(75, 252, 255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);

		// enemies.add(new PurpleBug(500,20));
		enemies.add(new PurpleBug(300, 190));

		enemies.add(new PurpleFly(60, 300));
		enemies.add(new PurpleFly(500, 300));
		enemies.add(new PurpleFly(25, 670));
		enemies.add(new PurpleFly(510, 670));

		enemies.add(new Beehive(120, 420));
		enemies.add(new Beehive(450, 420));
		enemies.add(new Beehive(120, 500));
		enemies.add(new Beehive(450, 500));

	}
}
