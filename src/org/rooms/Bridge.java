package org.rooms;

import static java.awt.Color.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.ComputerScreen;
import org.HUDSystem.HUD;
import org.HUDSystem.HudTitle;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.resources.VisibleObject;
import org.walls.*;
import org.enemies.*;

public class Bridge extends Room {
	static final BufferedImage hull = ImagePack.getImage("backgrounds/shipHull.png"), hull2 = ImagePack
			.getImage("backgrounds/shipHullWindow2.png"), hull3 = ImagePack.getImage("backgrounds/shipHullDark.png"), vent = ImagePack
			.getImage("backgrounds/shipVent.png");
	public static boolean dead = false, added = false;
	public static VisibleObject computer;
	public void load() {
		computer = new ComputerScreen();
		dead = added = false;
		HEIGHT = 500;
		WIDTH = 560;
		spriteX = 10;
		spriteY = 320 - 38;
		cameraX = 100;
		cameraY = 50;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, 500), new Wall(WIDTH, 0, 0, HEIGHT)}));
		walls.add(new MachinePlatform(0, 320, 50));
		walls.add(new VerticalShipWall(0, 0, 320 - 80));
		walls.add(new VerticalShipWall(0, 320, 800));
		walls.add(new VerticalShipWall(WIDTH - 9, 0, HEIGHT));
		exit = new Computer(460, 320 - 45);
		backdrop.add(new ShipDoor(0, 320 - 80));
		// walls.add(new MachinePlatform(100,260,24));
		backdrop.add(exit);
		for (int i = 0; i < 4; i++) {
			// walls.add(new FirePlatform(100+105*i,380,i));
		}
		background = new Background();
		// background.fillBack(new Color(200,200,200));
		background.tessellate(hull);
		for (int i = 0; i < 800; i += 80) {
			background.add(new Wall(i, 326, hull3));
			background.add(new Wall(i, 406, hull3));
			background.add(new Wall(i, 486, hull3));
		}
		backdrop.add(exit);
		for (int i = 95; i < 800; i += 160) {
			background.add(vent, i, 153);
		}
		background.add(new Wall(400, 240, hull2));
		background.addAll(walls);
		for (int i = 100; i < 400; i += 100) {
			// enemies.add(new Rocketbot(i+50,500));
			// enemies.add(new Tredbot(i,537));
			// enemies.add(new SmashBlock(i+150,70));

		}

		/*
		 * backdrop.add(new
		 * HudTitle("ELEVATORS ARE DOWN, YOU MUST",260,35,white));
		 * backdrop.add(new
		 * HudTitle("WALL JUMP TO REACH THIS EXIT",260,65,white));
		 * backdrop.add(new
		 * HudTitle("WALL JUMP BY HOLDING W AFTER",100,300,white));
		 * backdrop.add(new
		 * HudTitle("JUMPING AT A WALL AND THEN USE",100,330,white));
		 * backdrop.add(new
		 * HudTitle("A OR D TO PUSH OFF OF THE WALL",100,360,white));
		 */
	}

	public boolean isDead() {
		if (super.isDead()) {
			if (roomState != RoomState.PAUSED)
				changeState(RoomState.PAUSED);
			pauseHUD = hud;
			if (!added) {
				foreground.add(computer);
				added = true;
			}
		}
		return dead;
	}
}
