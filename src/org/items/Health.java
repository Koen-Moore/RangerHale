package org.items;

import static java.lang.Math.log10;
import static java.lang.Math.random;
import static java.lang.Math.round;

import java.awt.image.BufferedImage;

import org.resources.Collisions;
import org.resources.ImagePack;
import org.resources.LivingObject;
import org.resources.VisibleObject;
import org.rooms.Room;
import org.walls.Wall;
import org.players.*;

public class Health extends LivingObject implements Runnable {
	public static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("health.png"), ImagePack.getImage("healthup.png"),};
	public boolean free = false;
	boolean healthup;
	public float vx, vy;
	// public boolean dead=false;
	public Health(float X, float Y, float VX, float VY) {
		life = (int) (200 * log10(random()) / log10(.5));
		dead = false;
		x = X;
		y = Y;
		vx = (float) (VX + random() - .5);
		vy = (float) (VY + random() - .5);
		w = 4;
		h = 4;
		if (Math.random() < .03)
			healthup = true;
		image = ani[healthup ? 1 : 0];
	}
	public void run() {
		if (!free && !Collisions.collides(this, Player.player))
			free = true;
		else if (free && Collisions.collides(this, Player.player)) {
			if (healthup)
				Player.player.lifeCapacity += 3;
			else
				Player.damage(-3);
			dead = true;
		}
		life--;
		if (life < 0)
			dead = true;
		vy += .7;
		for (int i = 0; i < Room.walls.size(); i++) {
			Wall wal = Room.walls.get(i);
			if (vy == 0 && vx == 0)
				break;
			if (Collisions.willCollide(this, wal, vx, 0)) {
				if (vx > 0) {
					x = wal.x - w;
				} else {
					x = wal.x + wal.w;
				}
				vx = (float) -.8 * vx;
			}
			if (vy == 0)
				continue;
			if (Collisions.willCollide(this, wal, vx, vy)) {
				if (vy > 0) {
					y = wal.y - h;
				} else {
					y = wal.y + wal.h;
				}
				vy = (float) -.8 * vy;
			}
		}

		x += vx;
		y += vy;
	}
	public static void add(float x, float y, float num) {
		for (int a = (int) round(num / 2 + (num * random())); a > 0; a--)
			Room.items.add(new Health(x, y, 0, 0));
	}
	public static void add(VisibleObject a, float b) {
		add(a.w / 2 + a.x, a.h / 2 + a.y, b);
	}
}
