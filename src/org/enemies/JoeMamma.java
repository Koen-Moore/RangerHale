package org.enemies;

import static java.lang.Math.random;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.projectiles.EnemyBullet;
import org.resources.AudioPack;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;

public class JoeMamma extends Enemy implements Runnable {
	private static BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("joeMamma1.png"), ImagePack.getImage("joeMamma2.png")};
	public int curFrame;
	public boolean left = false;
	private int counter = (int) (random() * 300);
	public JoeMamma(float X, float Y, float vX, float vY) {
		element = Element.EARTH;
		vx = vX;
		vy = vY;
		images = ani;
		curFrame = 0;
		image = images[0];
		life = lifeCapacity = 100;
		x = X;
		y = Y;
		w = 31;
		h = 25;
		// (thread=new Thread(this)).start();
	}
	public void run() {
		if (life <= 0) {
			for (int d = (int) (3 * random()); d > 0; d--) {
				Health.add(this, 3);
			}
			AudioPack.playClip(screams[(int) (screams.length * random())]);
			dead = true;
		}
		if (life > 0 && !dead) {
			// Clock.waitFor();
			// if(Clock.dead)break;
			counter++;
			curFrame++;
			curFrame %= 50;
			image = images[vx >= 0 ? 0 : 1];

			vMult1();

			Player p = Player.player;
			if ((counter % 6) == 0) {
				if (x < p.x + p.w && x + w > p.x && y + h < p.y) {
					Room.projectiles.add(new EnemyBullet(x + (left ? 12 : 18), y + 25, (float) (.1 * (random() - .5)), (float) (-.5)));
				}
				// (y<p.y+p.h&&y+h>p.y&&(x+w<p.x||x>p.x+p.w)){
				// Room.projectiles.add(new
				// EnemyProjectile(x+(left?12:18),y+25,(x+w<p.x?3:-3),0));
				// }
			}
			if (Collisions.collides(this, p)) {
				Player.damage(1);
			}
			boolean changed = false;
			for (Wall wal : Room.walls) {
				if ((x + vx < wal.x + wal.w && x + vx + w > wal.x) && (y < wal.y + wal.h && y + h > wal.y)) {
					if (vx > 0) {
						x = wal.x - w;
					} else {
						x = wal.x + wal.w;
					}
					vx = -vx;
					changed = true;
					left = !left;
				}
				if ((x + vx < wal.x + wal.w && x + vx + w > wal.x) && (y - vy < wal.y + wal.h && y - vy + h > wal.y)) {
					if (vy < 0) {
						y = wal.y - h;
					} else {
						y = wal.y + wal.h;
					}
					vy = -vy;
					break;
				}
				if (changed)
					break;
			}
			x += vx;
			y -= vy;

			vMult2();
		}
	}
}