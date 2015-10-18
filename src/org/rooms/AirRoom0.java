package org.rooms;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.FadingBlack;
import org.HUDSystem.FadingHudTitle;
import org.enemies.Beehive;
import org.enemies.Eagle;
import org.enemies.Glorble;
import org.enemies.PurpleBug;
import org.enemies.Shark;
import org.main.RoomRunner;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CloudPlatform;
import org.walls.Wall;

public class AirRoom0 extends Room {
	public static final BufferedImage cloud = ImagePack.getImage("cloud.png");
	public void load() {
		RoomRunner.saveGame();
		HEIGHT = 1000;
		WIDTH = 500;
		spriteX = 100;
		spriteY = 0;
		exit = new Wall(0, 999, WIDTH, 1);
		cameraX = 100;
		cameraY = 100;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				// new Wall(0,HEIGHT,WIDTH,500),
				new Wall(WIDTH, 0, 0, HEIGHT)}));
		walls.add(new Wall(0, 800, ImagePack.getImage("cliff.png")));
		background = new Background();
		background.fillBack(new Color(75, 252, 255));
		background.addToMiddle(cloud, 100);
		background.addAll(walls);

		hud.add(new FadingBlack(100, 60));
		hud.add(new FadingHudTitle("ORO SEKSIS:", 150, 200, Color.white, 100, 60));
		hud.add(new FadingHudTitle("by Chrisopher Brannum", 180, 230, Color.white, 100, 60));
	}
}