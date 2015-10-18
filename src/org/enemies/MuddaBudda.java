package org.enemies;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.random;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.AudioPack;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;

public class MuddaBudda extends Enemy implements Runnable {
	private static volatile BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("MuddaBudda1.png"),
			ImagePack.getImage("MuddaBudda2.png"), ImagePack.getImage("MuddaBudda3.png"), ImagePack.getImage("MuddaBudda2.png")};
	public int curFrame;
	// public float vx= -.8f;
	public MuddaBudda(float X, float Y) {
		element = Element.WATER;
		images = ani;
		curFrame = (int) floor(20 * random());
		// image=images[curFrame];
		life = lifeCapacity = 5;
		vx = -.8f;
		x = X;
		y = Y;
		w = 33;
		h = 24;
		// (thread=new Thread(this)).start();
	}
	public void run() {
		if (life <= 0) {
			for (int d = (int) (3 * random()); d > 0; d--) {
				Room.items.add(new Health(x + w / 2, y + h / 2, vx, vy));
			}
			AudioPack.playClip(screams[(int) (screams.length * random())]);
			dead = true;
		}
		if (life > 0 && !dead) {
			// if(Thread.interrupted())break;
			// Clock.waitFor();
			// if(Clock.dead)break;
			curFrame++;
			curFrame %= 20;
			image = images[curFrame / 5];
			if (Collisions.collides(this, Player.player)) {
				Player.damage(1);
			}

			if (vMultiplier == 0)
				vMultiplier = 10 * Float.MIN_VALUE;

			vx *= vMultiplier;
			vy *= vMultiplier;

			x += vx;
			// if(x<0||x>Room.WIDTH){vx=-vx;}
			for (Wall wal : Room.walls) {
				if (vx == 0)
					break;
				if (Collisions.willCollide(this, wal, vx, 0)) {
					if (vx > 0) {
						x = wal.x - w;
					} else {
						x = wal.x + wal.w;
					}
					vx = -vx;
					break;
				}
			}

			vx /= vMultiplier;
			vy /= vMultiplier;

			vMultiplier += .03 * (1 - vMultiplier > 0 ? 1 : (abs(vMultiplier) <= .03 ? 0 : -1));
		}
	}
}