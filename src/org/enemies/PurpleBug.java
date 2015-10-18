package org.enemies;

import static java.lang.Math.abs;
import static java.lang.Math.random;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;

public class PurpleBug extends Enemy {
	private static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("airlevel/purplebug.png"),
			ImagePack.getImage("airlevel/purplebug2.png"), ImagePack.getImage("airlevel/purplebugR.png"),
			ImagePack.getImage("airlevel/purplebug2R.png"),};
	// private int curFrame;
	int layDelay = 0;
	boolean left;
	private int counter = (int) (300 * Math.random());
	public PurpleBug(float X, float Y) {
		element = Element.AIR;
		// images=ani;
		image = ani[0];
		life = lifeCapacity = 40;
		x = X;
		y = Y;
		w = 37;
		h = 20;
		vy = 2;
	}
	public void run() {
		counter++;
		if (life <= 0) {
			for (int d = (int) (2 * random()); d > 0; d--) {
				Health.add(this, 2);
			}
			dead = true;
			return;
		}
		Player p = Player.player;
		if (Collisions.collides(this, p)) {
			Player.damage(1);
		}
		double dx = x - p.x - p.w / 2;
		double dy = y - p.y - p.h / 2;
		if (counter % 10 == 0) {
			vx = (float) ((dx > 0 ? -1 : 1) * (Math.random() * 5 - 1.6));
		}
		if (counter % 2 == 0) {
			vy = (float) ((dy > 0 ? -1 : 1) * (Math.random() * 5 - 1.6));
		}
		vy += (counter % 10 < 5 ? -1 : 1);

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
			}
		}

		if (layDelay > 0) {
			vx = vy = 0;
			layDelay--;
		}
		if (vx < 0)
			left = true;
		if (vx > 0)
			left = false;
		if (layDelay <= 0 && counter % 160 == 0) {
			Room.enemies.add(new Larva(x + (!left ? 6 : 31), y + 14, vx - 3f, vy - 6));
			Room.enemies.add(new Larva(x + (!left ? 6 : 31), y + 14, vx + 3f, vy - 6));
			layDelay = 30;
			vx = vy = 0;
		}
		x += vx;
		y += vy;

		vMult2();

		image = ani[(left ? 0 : 2) + (layDelay > 0 ? 1 : 0)];
	}
}