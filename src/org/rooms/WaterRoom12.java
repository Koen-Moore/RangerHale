package org.rooms;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Clam;
import org.enemies.TentacleMonster;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.Log;
import org.walls.Wall;

public class WaterRoom12 extends Room {
	static final BufferedImage gay = ImagePack.getImage("backgrounds/hard.png");
	public void load() {

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
				new Wall(0, HEIGHT, WIDTH, 500), new Wall(WIDTH, 0, 0, HEIGHT), new Log(200, 300, 200), new Log(300, 220, 100),
				new Log(400, 140, 80), new Log(0, 400, 2),}));

		background = new Background();
		background.tessellate(gay);
		background.addAll(walls);
		enemies.add(new Clam(500, 90));
		enemies.add(new Clam(500, 150));
		enemies.add(new Clam(500, 230));
		enemies.add(new TentacleMonster(200, 420));
		enemies.add(new TentacleMonster(250, 420));
		enemies.add(new TentacleMonster(450, 420));
		enemies.add(new TentacleMonster(300, 420));
		enemies.add(new TentacleMonster(400, 420));
		enemies.add(new TentacleMonster(350, 420));
	}

}