package org.rooms;

import static java.awt.Color.white;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.HealthBar;
import org.HUDSystem.HudTitle;
import org.enemies.Enemy;
import org.enemies.RockBossEasy;
import org.items.EscapePod;
import org.items.HealthPack;
import org.main.RoomRunner;
import org.players.Player;
import org.resources.ImagePack;
import org.resources.KeyList;
import org.resources.Position;
import org.resources.ThreadList;
import org.resources.VisibleObject;
import org.walls.CaveDoor;
import org.walls.RockPlatform;
import org.walls.VerticalRockWall;
import org.walls.Wall;

public class EarthBossRoomEasy extends Room {
	public static final BufferedImage cave1 = ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2 = ImagePack.getImage("backgrounds/caveback2.png");
	public void load() {
		RoomRunner.saveGame();
		HEIGHT = 600;
		WIDTH = 800;
		spriteX = 10;
		spriteY = 100 - 38;
		cameraX = 250;
		cameraY = 250;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, HEIGHT), new Wall(WIDTH, 0, 0, HEIGHT),}));
		walls.add(new VerticalRockWall(-1, 0, 100 - 70));
		walls.add(new VerticalRockWall(-1, 100, HEIGHT));
		walls.add(new VerticalRockWall(WIDTH - 9, 0, HEIGHT));
		// walls.add(new VerticalRockWall(WIDTH-9,100,HEIGHT));

		walls.add(new VerticalRockWall(100, 200, 300));
		walls.add(new VerticalRockWall(700, 100, 400));

		walls.add(new CaveDoor(0, 100 - 80));
		// exit=new CaveExit(WIDTH-40,100-80);
		exit = null;

		// walls.add(new RockPlatform(0,100,2));
		// walls.add(new RockPlatform(WIDTH-140,240,10));

		// items.add(new HealthPack(200,85,false));
		// items.add(new HealthPack(250,85,false));

		walls.add(new RockPlatform(0, 100, 35));
		walls.add(new RockPlatform(330, 190, 6));
		walls.add(new RockPlatform(230, 250, 6));
		walls.add(new RockPlatform(440, 250, 6));
		walls.add(new RockPlatform(150, 310, 6));
		walls.add(new RockPlatform(550, 310, 6));
		walls.add(new RockPlatform(100, 500, 30));

		boss = new RockBossEasy(350, 300);
		enemies.add(boss);
		hud.add(new HealthBar(boss, 300, 10, 200, 6, Color.red));

		background = new Background();
		background.fillBack(new Color(68, 37, 18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);

		ep = new EscapePod[]{new EscapePod(325, 450, 1), new EscapePod(375, 450, 2),
				// new EscapePod(425,450,3),
				new EscapePod(475, 450, 4),};

	}
	public Enemy boss;
	public EscapePod[] ep;
	public EscapePod selected;
	public int state = 0;
	public HudTitle ht;
	public boolean changed = false;
	public int piccount = 160;
	public static BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("menu stuff/starBack.png"),
			ImagePack.getImage("backgrounds/mars.png")};
	public boolean isDead() {
		if (state == 0) {
			if (boss.dead) {
				Player.unlockedProjectiles = RoomRunner.appendArray(Player.unlockedProjectiles, new int[]{5});
				hud.add(ht = new HudTitle("USE -SHIFT- AND -SPACE- TO SCROLL THROUGH WEAPONS", 100, 200, Color.white));
				state = 1;
			}
		}
		if (state == 1) {
			if (KeyList.keyboard[32]) {
				changed = true;
			}
			if (changed && (KeyList.keyboard[37] || KeyList.keyboard[38] || KeyList.keyboard[39] || KeyList.keyboard[40])) {
				state = 2;
				ht.contents = "PRESS -ENTER- WHILE OVER AN ESCAPE POD";
				for (VisibleObject e : ep) {
					backdrop.add(e);
				}
			}
		}
		if (state == 2) {
			for (EscapePod e : ep) {
				if (e.life == -1) {
					e.leaving = true;
					selected = e;
					state = 3;
					visibleObjects.remove(playerlist);
					break;
				}
			}
		}

		if (state == 3 && selected.dead) {
			state = 4;
			hud.add(new Wall(ani[0]));
			hud.add(new Wall(300, 120, ani[1]));
			hud.add(new HudTitle("LOADING...", Position.MIDDLE, white));
		}
		if (state == 4) {
			piccount--;
		}
		if (piccount == 0) {

			switch (selected.type) {
				case 1 :
					RoomRunner.loadFireLevel(false);
					break;
				case 2 :
					RoomRunner.loadWaterLevel(false);
					break;
				case 3 :
					RoomRunner.loadRockLevel(false);
					break;
				case 4 :
					RoomRunner.loadAirLevel(false);
					break;
			}
			return true;
		}

		return false || Player.player.life <= 0;
	}
}
