package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.FadingBlack;
import org.HUDSystem.FadingHudTitle;
import org.enemies.*;
import org.main.RoomRunner;
import org.resources.*;
import org.walls.*;

public class EarthRoom0 extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		RoomRunner.saveGame();
		HEIGHT = 500;
		WIDTH = 600;
		spriteX = 50;
		spriteY = 0;
		cameraX = 225;
		cameraY = 225;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),}));
		// walls.add(new VerticalRockWall(WIDTH-9,0,HEIGHT-70));
		walls.add(new VerticalRockWall(400, 200, 400));
		for (int i = 0; i < 6; i++) {
			walls.add(new RockPlatform(100 + 50 * i, 445 - i * 50, 4));
		}
		// walls.add(new CaveDoor(0,200-80));
		exit = new CaveExit(600 - 40, HEIGHT - 80);
		background = new Background();
		// background.fillBack(new Color(68,37,18));
		// background.tessellateOnBottom(cave1);
		// background.tessellateOnTop(cave2);
		background.randomTessellate(ImagePack.getImage("backgrounds/starBack.png"), 10, 10);
		background.addAll(walls);
		background.add(new Wall(500, 340, ImagePack.getImage("backgrounds/beware.png")));

		hud.add(new FadingBlack(100, 60));
		hud.add(new FadingHudTitle("ZEMI SEKSIS:", 150, 200, Color.white, 100, 60));
		hud.add(new FadingHudTitle("by Dylan Winkler", 180, 230, Color.white, 100, 60));
	}
}
