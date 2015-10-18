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

public class AirRoom10 extends Room {
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

		walls.add(new CloudPlatform(70, 100, 23));
		walls.add(new CloudPlatform(190, 155, 11));
		walls.add(new CloudPlatform(85, 230, 5));
		walls.add(new CloudPlatform(425, 230, 5));
		walls.add(new CloudPlatform(0, 330, 15));
		walls.add(new CloudPlatform(400, 330, 15));
		walls.add(new CloudPlatform(260, 420, 30));

		walls.add(new CloudPlatform(0, 520, 27));
		walls.add(new CloudPlatform(0, 600, 11));
		walls.add(new CloudPlatform(350, 600, 20));
		walls.add(new CloudPlatform(100, 690, 10));
		walls.add(new CloudPlatform(220, 760, 15));
		walls.add(new CloudPlatform(0, 1100, 27));

		foreground.add(new FlyingPlatform(20, 160));
		foreground.add(new FlyingPlatform(510, 160));
		foreground.add(new FlyingPlatform(20, 760));
		foreground.add(new FlyingPlatform(100, 850));
		foreground.add(new FlyingPlatform(470, 850));
		foreground.add(new FlyingPlatform(20, 930));
		foreground.add(new FlyingPlatform(350, 930));
		foreground.add(new FlyingPlatform(100, 1020));
		foreground.add(new FlyingPlatform(260, 1020));

		walls.add(new VerticalCloudWall(190, 160, 80));
		walls.add(new VerticalCloudWall(410, 160, 80));
		walls.add(new VerticalCloudWall(220, 600, 420));

		background = new Background();
		background.fillBack(new Color(75, 252, 255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);

		// enemies.add(new PurpleBug(500,20));
		enemies.add(new PurpleBug(300, 820));

		enemies.add(new PurpleFly(370, 820));
		enemies.add(new PurpleFly(300, 340));
		enemies.add(new PurpleFly(350, 530));

		enemies.add(new Beehive(250, 130));
		enemies.add(new Beehive(350, 130));

		enemies.add(new Beehive(150, 620));
		enemies.add(new Beehive(150, 730));
		enemies.add(new Beehive(30, 800));
		enemies.add(new Beehive(160, 880));
		enemies.add(new Beehive(90, 930));

	}
}
