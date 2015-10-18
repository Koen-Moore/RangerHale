package org.rooms;
import static java.awt.Color.white;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.HealthBar;
import org.HUDSystem.HudTitle;
import org.enemies.Beehive;
import org.enemies.BossBeeHard;
import org.enemies.Enemy;
import org.items.EscapePod;
import org.main.RoomRunner;
import org.players.Player;
import org.resources.ImagePack;
import org.resources.KeyList;
import org.resources.Position;
import org.resources.ThreadList;
import org.walls.CloudPlatform;
import org.walls.FlyingPlatform;
import org.walls.Wall;

public class AirBossRoomHard extends Room {
	public static final BufferedImage cloud = ImagePack.getImage("cloud.png");
	public static final BufferedImage mountain = ImagePack.getImage("backgrounds/MountainBACK.png");
	public void load() {
		RoomRunner.saveGame();
		HEIGHT = 800;
		WIDTH = 800;
		spriteX = 270;
		spriteY = 0;
		cameraX = 250;
		cameraY = 250;
		visibleHEIGHT = 500;
		visibleWIDTH = 500;
		startLeft = false;
		walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{new Wall(0, 0, 0, HEIGHT), new Wall(0, 0, WIDTH, 0),
				new Wall(0, HEIGHT, WIDTH, 500), new Wall(WIDTH, 0, 0, HEIGHT),

				new CloudPlatform(0, HEIGHT - 50, 12),}));
		// foreground.add(new CloudDoor(255,HEIGHT-50,5));
		// exit=new Wall(0,HEIGHT-1,WIDTH,1);

		walls.add(new CloudPlatform(0, 120, 7));
		walls.add(new CloudPlatform(670, 120, 7));
		walls.add(new CloudPlatform(250, 220, 15));
		walls.add(new CloudPlatform(0, 340, 7));
		walls.add(new CloudPlatform(680, 340, 8));
		walls.add(new CloudPlatform(250, 560, 15));

		foreground.add(new FlyingPlatform(130, 400));
		foreground.add(new FlyingPlatform(560, 400));
		foreground.add(new FlyingPlatform(110, 480));
		foreground.add(new FlyingPlatform(490, 480));
		foreground.add(new FlyingPlatform(130, 620));
		foreground.add(new FlyingPlatform(570, 620));
		foreground.add(new FlyingPlatform(35, 700));
		foreground.add(new FlyingPlatform(660, 700));

		background = new Background();
		background.fillBack(new Color(75, 252, 255));
		background.addToMiddle(cloud, 100);
		background.tessellateOnBottom(mountain);
		background.addAll(walls);

		enemies.add(boss = new BossBeeHard(340, 320));
		hud.add(new HealthBar(boss, 300, 10, 200, 6, Color.red));

		enemies.add(new Beehive(15, 50));
		enemies.add(new Beehive(720, 50));
		enemies.add(new Beehive(20, 730));
		enemies.add(new Beehive(750, 730));

		ep = new EscapePod(400, 750, 0);

	}
	public Enemy boss;
	public EscapePod ep;
	public int state = 0;
	public HudTitle ht;
	public boolean changed = false;
	public int piccount = 160;
	public static BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("menu stuff/starBack.png"),
			ImagePack.getImage("backgrounds/airplanet.png")};
	public boolean isDead() {
		if (state == 0) {
			if (boss.dead) {
				Player.unlockedProjectiles = RoomRunner.appendArray(Player.unlockedProjectiles, new int[]{8});
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
				ht.contents = "PRESS -ENTER- WHILE OVER THE ESCAPE POD";
				backdrop.add(ep);
			}
		}
		if (state == 2 && ep.life == -1) {
			ep.leaving = true;
			state = 3;
			visibleObjects.remove(playerlist);
		}

		if (ep.dead) {
			state = 4;
			hud.add(new Wall(ani[0]));
			hud.add(new Wall(300, 120, ani[1]));
			hud.add(new HudTitle("LOADING...", Position.MIDDLE, white));
		}
		if (state == 4) {
			piccount--;
		}
		if (piccount == 0) {
			RoomRunner.loadRobotLevel();
			return true;
		}
		return false || Player.player.life <= 0;
	}
}
