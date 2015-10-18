package org.enemies;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.Collisions;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;

import static java.lang.Math.*;

public class SmashBlock extends Enemy {
	static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("Robot/smashblock1.png"),
			ImagePack.getImage("Robot/smashblock2.png"), ImagePack.getImage("Robot/smashblock3.png"),
			ImagePack.getImage("Robot/smashblock4.png"), ImagePack.getImage("Robot/smashblock5.png"),
			ImagePack.getImage("Robot/smashblock6.png"), ImagePack.getImage("Robot/smashblockd1.png"),
			ImagePack.getImage("Robot/smashblockd2.png"),};
	int counter = (int) round(1000 * random());
	int lastHealth;
	public boolean smashing = false;
	public SmashBlock(int a, int b) {
		x = a;
		y = b;
		w = 15;
		h = 25;
		lastHealth = life = lifeCapacity = 100;
	}
	public void run() {
		if (life <= 0) {
			Health.add(this, 4);
			dead = true;
		}
		counter++;
		image = ani[smashing ? 6 + counter / 5 % 2 : counter / 5 % 6];

		Player p = Player.player;
		if ((x < p.x + p.w && x + w > p.x) && (y < p.y + p.h && y + h > p.y)) {
			life = 0;
			Player.damage(30);
			dead = true;
		}
		double dx = x - p.x - p.w / 2;
		double dy = y - p.y - p.h / 2;
		if (counter % 60 == 0) {
			vx = (float) (.4 * (dx > 0 ? -1 : 1) * (Math.random() * 5 - 2.5));
		}
		if (counter % 60 == 0) {
			vy = (float) (.4 * (dy > 0 ? -1 : 1) * (Math.random() * 2 - 1));
		}
		if (!smashing && (Collisions.collides(new Wall(x, y, w, Room.HEIGHT), Player.player) || lastHealth != life)) {
			smashing = true;
		}
		if (smashing) {
			lastHealth = life;
			h = 40;
			vx = 0;
			vy = 10;
		} else {
			h = 25;
		}
		// vy-=(counter%10)-4.5;

		vMult1();
		for (Wall wal : Room.walls) {
			if (Collisions.willCollide(this, wal, vx, 0)) {
				if (vx > 0) {
					x = wal.x - w;
				} else {
					x = wal.x + wal.w;
				}
				vx = 0;
			}
			if (Collisions.willCollide(this, wal, vx, vy)) {
				if (vy > 0) {
					y = wal.y - h;
				} else {
					y = wal.y + wal.h;
				}
				vy = 0;
				if (smashing) {
					smashing = false;
					lastHealth = life;
					vy = -10;
				}
			}
		}
		x += vx;
		y += vy;
		vMult2();

	}
	public boolean preventsNextRoom() {
		return false;
	}
}
