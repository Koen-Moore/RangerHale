package org.rooms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.FadingBlack;
import org.HUDSystem.FadingHudTitle;
import org.enemies.*;
import org.main.RoomRunner;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.CloudPlatform;
import org.walls.FireDoor;
import org.walls.FireExit;
import org.walls.FirePlatform;
import org.walls.MachinePlatform;
import org.walls.Wall;
import org.walls.WaterDoor;
import org.walls.WaterExit;

public class FireRoom0 extends Room {
	public static final BufferedImage stars = ImagePack.getImage("backgrounds/redClouds.png");
	public static final BufferedImage tree = ImagePack.getImage("Tree.png");
	public static final BufferedImage tree2 = ImagePack.getImage("Tree2.png");
	public static final BufferedImage tree3 = ImagePack.getImage("Tree3.png");
	public static final BufferedImage tree4 = ImagePack.getImage("Tree4.png");
	public void load() {
		RoomRunner.saveGame();
		HEIGHT = 800;
		WIDTH = 500;
		spriteX = 0;
		spriteY = 0;
		cameraX = 100;
		cameraY = 100;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),}));
		exit = new FireExit(500 - 40, 800 - 80);
		background = new Background();

		background.randomTessellate(stars, 10, 10);
		/*
		 * background.addToGround(tree4,2); background.addToGround(tree3,2);
		 * background.addToGround(tree2,2); background.addToGround(tree,3);
		 */
		background.addAll(walls);

		hud.add(new FadingBlack(100, 60));
		hud.add(new FadingHudTitle("PHORA SEKSIS:", 150, 200, Color.white, 100, 60));
		hud.add(new FadingHudTitle("by Kyle Husband", 180, 230, Color.white, 100, 60));
	}
}
