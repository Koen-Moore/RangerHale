package org.enemies;

import static java.lang.Math.abs;
import static java.lang.Math.random;

import org.items.Health;
import org.players.Player;
import org.resources.Collisions;
import org.resources.Element;
import org.rooms.Room;
import org.walls.Wall;

public class Shark extends Enemy {
	private boolean left = true;
	private int counter = (int) (300 * Math.random());
	private int jumpd = 0;
	public Shark(float f, float g) {
		element = Element.WATER;
		w = 69;
		h = 24;
		x = f;
		y = g;
		image = sharkAni[0][0];
		life = lifeCapacity = 25;
		counter = (int) (300 * random());
		vx = -1.5f;
		vy = 2;
	}
	public void run() {
		boolean onSurface = (y == Room.HEIGHT - h);
		boolean onWall = (x == Room.WIDTH - w || x == 0);
		left = vx > 0 ? false : true;
		for (Wall wal : Room.walls) {
			onSurface = onSurface || Collisions.onTop(this, wal);
			onWall = onWall || Collisions.touchingSides(this, wal);
		}
		// if(onSurface&&counter%20<9){
		// image=ani[left?0:1][0];

		// }
		// else if(onSurface&&counter%20>9){
		// mage=ani[left?0:1][1];

		// }
		if (onSurface && jumpd <= 0) {
			jumpd = 30;
			vy = -5.5f;

		} else if (onSurface) {
			jumpd--;
		}
		if (life <= 0) {
			Health.add(this, 8);
			dead = true;
			return;
		}
		counter++;
		left = vx > 0 ? false : true;
		image = sharkAni[left ? 0 : 1][(counter % 48) / 6];
		Player p = Player.player;
		if ((x < p.x + p.w && x + w > p.x) && (y < p.y + p.h && y + h > p.y)) {
			Player.damage(1);
		}

		if (vMultiplier == 0)
			vMultiplier = 10 * Float.MIN_VALUE;

		// float first=vx,last=vy;

		vy += .1;

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
				vx = 0 - (vx + .2f);
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
		if (!onSurface) {
			x += vx;
		}
		y += vy;

		vx /= vMultiplier;
		vy /= vMultiplier;

		vMultiplier += .03 * (1 - vMultiplier > 0 ? 1 : (abs(vMultiplier) <= .03 ? 0 : -1));
	}
	public boolean preventsNextRoom() {
		return false;
	}
}