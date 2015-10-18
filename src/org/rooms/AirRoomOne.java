package org.rooms;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Eagle;
import org.enemies.Glorble;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CloudPlatform;
import org.walls.Wall;

public class AirRoomOne extends Room {
	public static final BufferedImage cloud = ImagePack.getImage("cloud.png");
	public void load() {
		exit = new Wall(799, 0, 1, 500);
		HEIGHT = 500;
		WIDTH = 800;
		spriteX = 0;
		spriteY = 0;
		cameraX = 100;
		cameraY = 100;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				// new Wall(0,HEIGHT,WIDTH,500),
				new Wall(WIDTH, 0, 0, HEIGHT), new CloudPlatform(0, 300, 3), new CloudPlatform(180, 300, 4),
				new CloudPlatform(400, 300, 5), new CloudPlatform(600, 300, 6),}));
		background = new Background();
		background.fillBack(new Color(75, 252, 255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);
		enemies.add(new Glorble(180, 300));
		enemies.add(new Eagle(700, 50));

	}
}
