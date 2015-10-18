package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.FadingBlack;
import org.HUDSystem.FadingHudTitle;
import org.enemies.Shark;
import org.enemies.Crab;
import org.main.RoomRunner;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.WaterExit;
import org.walls.WaterPlatform;
import org.walls.Wall;
import org.enemies.Clam;

public class WaterRoom0 extends Room {
	static final BufferedImage gay = ImagePack.getImage("backgrounds/easy.png");
	public void load() {
		RoomRunner.saveGame();

		HEIGHT = 500;
		WIDTH = 800;
		spriteX = 0;
		spriteY = 0;
		exit = new Wall(799, 0, 1, 500);
		cameraX = 100;
		cameraY = 100;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, 500), new Wall(WIDTH, 0, 0, HEIGHT),}));

		exit = new WaterExit(760, 420);
		background = new Background();
		background.tessellate(gay);
		background.addAll(walls);

		hud.add(new FadingBlack(100, 60));
		hud.add(new FadingHudTitle("VANDUO SEKSIS:", 150, 200, Color.white, 100, 60));
		hud.add(new FadingHudTitle("by Jacob Jones", 180, 230, Color.white, 100, 60));
	}

}
