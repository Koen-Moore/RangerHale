package org.projectiles;

import static java.lang.Math.random;
import static java.lang.Math.round;

import java.awt.image.BufferedImage;

import org.enemies.Enemy;
import org.players.Player;
import org.resources.AudioPack;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.VisibleObject;
import org.rooms.Room;
import org.walls.DamageableWall;
import org.walls.Wall;

public class EnemyRocket extends Projectile {
	BufferedImage[] ani = rocketani;
	int counter = (int) round(1000 * random());
	public EnemyRocket(float a, float b, float va, float vb) {
		x = a;
		y = b;
		vX = va;
		vY = vb;
		w = 14;
		h = 14;
		life = lifeCapacity = -1;
	}
	public void run() {
		if (life != 0) {
			image = ani[(vX < 0 ? 0 : 2) + (counter / 5 % 2)];
			if (life > 0)
				image = ani[7];
			if (life > 3)
				image = ani[6];
			if (life > 6)
				image = ani[5];
			if (life > 8)
				image = ani[4];
			// Clock.waitFor(frame=!frame);
			// if(Clock.dead)break;

			if (life < 0) {
				x += vX;
				y -= vY;
			}
			life--;

			for (Wall wal : Room.walls) {
				if (Collisions.collides(this, wal)) {
					vY = vX = 0;
					life = 10;
					w = h = 16;
					x -= 5 - round(4 * random());
					y -= 5 - round(4 * random());
					AudioPack.playClip(boom);
				}
			}
			if (Collisions.collides(this, Player.player)) {

				if (life < 0)
					Player.damage(5);
				else if (random() < .3)
					Player.damage(1);
				if (vY == 0 && vX == 0)
					return;
				vY = vX = 0;
				life = 10;
				w = h = 16;
				x -= 5 - round(4 * random());
				y -= 5 - round(4 * random());
				AudioPack.playClip(boom);
			}

		} else
			dead = true;
	}
}
