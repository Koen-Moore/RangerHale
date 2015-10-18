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

public class Clam extends Enemy {
	private static final BufferedImage[][] ani = new BufferedImage[][]{
			{ImagePack.getImage("waterlevel/clam1L.png"), ImagePack.getImage("waterlevel/clam2L.png"),
					ImagePack.getImage("waterlevel/clam3L.png"), ImagePack.getImage("waterlevel/clam2L.png"),},
			{ImagePack.getImage("waterlevel/clam1R.png"), ImagePack.getImage("waterlevel/clam2R.png"),
					ImagePack.getImage("waterlevel/clam3R.png"), ImagePack.getImage("waterlevel/clam2R.png"),}};
	private int curFrame;
	// private double vx=0,vy=2;
	private int counter = (int) (300 * Math.random());
	public Clam(float X, float Y) {
		element = Element.AIR;
		// images=ani;
		curFrame = 0;
		image = ani[0][curFrame];
		life = lifeCapacity = 40;
		x = X;
		y = Y;
		w = 40;
		h = 40;
		vy = 2;
	}
	public void run() {
		counter++;
		if (life <= 0) {
			Health.add(this, 4);
			dead = true;
			return;
		}
		Player p = Player.player;
		if ((x < p.x + p.w && x + w > p.x) && (y < p.y + p.h && y + h > p.y)) {
			life = 0;
			Player.damage(10);
			dead = true;
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
		if (dx < 0)
			curFrame = 1;
		else if (dx > 0)
			curFrame = 0;
		image = ani[curFrame][counter / 10 % 4];
	}
}