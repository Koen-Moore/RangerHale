package org.enemies;

import static java.lang.Math.abs;
import static java.lang.Math.random;
import static java.lang.Math.round;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;

public class SmallBee extends Enemy {
	private static volatile BufferedImage[][] ani = bee2ani;
	private int curFrame;
	// private double vx=0,vy=2;
	private boolean left = true;
	private int counter = (int) (300 * Math.random());
	public SmallBee(float X, float Y) {
		element = Element.AIR;
		curFrame = 0;
		image = ani[left ? 0 : 1][curFrame];
		life = lifeCapacity = 2;
		x = X;
		y = Y;
		w = 3;
		h = 3;
	}
	public void run() {
		counter++;
		if (life <= 0) {
			for (int d = (int) round(random()); d > 0; d--) {
				Room.items.add(new Health(x + w / 2, y + h / 2, vx, vy));
			}
			dead = true;
			return;
		}
		if (Collisions.collides(this, Player.player)) {
			dead = true;
			Player.vMultiplier -= .3;
		}
		Player p = Player.player;
		if (counter % 10 == 0) {
			vx = (float) ((x - p.x - p.w / 2 > 0 ? -1 : 1) * (Math.random() * 5 - 1.6));
		}
		if (counter % 2 == 0) {
			vy = (float) ((y - p.y - p.h / 2 > 0 ? -1 : 1) * (Math.random() * 5 - 1.6));
		}
		vy += (counter % 10 < 5 ? -1 : 1);
		if (counter % 8 < 4)
			curFrame = 0;
		else
			curFrame = 1;
		boolean changed = false;
		// if (false)
		if (vMultiplier == 0)
			vMultiplier = 10 * Float.MIN_VALUE;

		vx *= vMultiplier;
		vy *= vMultiplier;

		for (Wall wal : Room.walls) {

			if (Collisions.willCollide(this, wal, vx, 0)) {
				if (vx > 0) {
					x = wal.x - w;
				} else {
					x = wal.x + wal.w;
				}
				vx = 0;
				changed = true;
			}
			if (Collisions.willCollide(this, wal, vx, vy)) {
				if (vy > 0) {
					y = wal.y - h;
				} else {
					y = wal.y + wal.h;
				}
				vy = 0;
				changed = true;
			}
			if (changed) {
				// x-=(dx>0?-1:1);
				// y-=(dy>0?-1:1);
				// break;
			}
		}
		x += vx;
		y += vy;
		vx /= vMultiplier;
		vy /= vMultiplier;

		vMultiplier += .03 * (1 - vMultiplier > 0 ? 1 : (abs(vMultiplier) <= .03 ? 0 : -1));

		double dx = x - p.x;
		if (dx < 0)
			left = false;
		else if (dx > 0)
			left = true;
		image = ani[left ? 0 : 1][curFrame];
	}
	public boolean preventsNextRoom() {
		return false;
	}
}