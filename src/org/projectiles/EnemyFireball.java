package org.projectiles;

import static java.awt.Color.red;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.atan;
import static java.lang.Math.random;
import static java.lang.Math.round;

import java.awt.image.BufferedImage;

import org.players.Player;
import org.resources.AudioPack;
import org.resources.Collisions;
import org.rooms.Room;
import org.walls.Wall;

public class EnemyFireball extends Projectile {
	public static BufferedImage[] ani = fireani;
	public EnemyFireball(float X, float Y, float vx, float vy) {
		image = ani[0];
		dead = false;
		color = red;
		x = X;
		y = Y;
		vX = vx * 2.5f;
		vY = vy * 2.5f;
		life = lifeCapacity = -1 - (int) round(150 * random());
		h = 8;
		w = 5;
		if (vX == 0 && vY == 0) {
			vX = 5 * (float) random() - 2.5f;
			vY = 5 * (float) random() - 2.5f;
		}
		if (abs(vX) > abs(vY)) {
			h = 5;
			w = 8;
		}
		// int counter=0;
		// while(hypot(vX,vY)<1&&counter<10){counter++;vX*=2;vY*=2;}
		if (abs(abs(atan((double) vY / vX)) - PI / 4) > PI / 8)
			image = ani[abs(vX) > abs(vY) ? (vX < 0 ? 0 : 1) : (vY > 0 ? 2 : 3)];
		else {
			image = ani[vX > 0 ? (vY < 0 ? 4 : 6) : (vY < 0 ? 5 : 7)];
			w = h = 6;
		}
		synchronized (sync) {
			livePros++;
		}
	}
	public void run() {
		// boolean frame=Clock.frame;
		if (life != 0) {
			if (life > 0)
				image = ani[11];
			if (life > 3)
				image = ani[10];
			if (life > 6)
				image = ani[9];
			if (life > 8)
				image = ani[8];
			// Clock.waitFor(frame=!frame);
			// if(Clock.dead)break;

			if (life < 0) {
				vX *= .98;
				vY *= .98;
				x += vX;
				y -= vY;
			}
			if (life == -200) {
				vY = vX = 0;
				life = 10;
				w = h = 16;
				image = ani[8];
				x -= round(10 * random());
				y -= round(10 * random());
			}
			life--;
			for (int i = 0; i < Room.walls.size(); i++) {
				Wall wal = Room.walls.get(i);
				if (vY == 0 && vX == 0)
					break;
				if ((x < wal.x + wal.w && x + w > wal.x) && (y < wal.y + wal.h && y + h > wal.y)) {
					vY = vX = 0;
					life = 10;
					w = h = 16;
					image = ani[8];
					x -= round(10 * random());
					y -= round(10 * random());
					AudioPack.playClip(boom);
				}
			}

			if (Collisions.collides(this, Player.player)) {
				if (life < 0 || life > 2)
					Player.damage(1);
				image = ani[8];
				if (vX != 0 || vY != 0) {
					// x=en.x+en.w/2;
					life = 10;
					w = h = 16;
					// y=en.y+en.h/2;
					x -= .7 * vX + round(10 * random());
					y -= .7 * vY + round(10 * random());
					vY = vX = 0;
					// AudioPack.playAudio("BExplosion2.wav",0.1);
					AudioPack.playClip(boom);
				}
			}

		} else
			dead = true;
	}
}
