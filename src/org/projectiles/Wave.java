package org.projectiles;

import static java.awt.Color.red;
import static java.lang.Math.*;

import java.awt.image.BufferedImage;
import static java.lang.System.*;
import org.rooms.*;
import org.resources.AudioPack;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.resources.VisibleObject;
import org.walls.DamageableWall;
import org.walls.Wall;
import org.enemies.*;

public class Wave extends Projectile {
	public static BufferedImage[] ani = waterani;
	public Wave(float X, float Y, float vx, float vy) {
		image = ani[0];
		dead = false;
		color = red;
		x = X - 10;
		y = Y - 10;
		vX = vx * 2.5f;
		vY = vy * 2.5f;
		life = lifeCapacity = -1 - (int) round(150 * random());
		h = 20;
		w = 20;
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
				// vX*=.98;vY*=.98;
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
			for (Wall wal : Room.walls) {
				// if (vY==0&&vX==0)break;
				if (Collisions.collides(this, wal)) {
					vY = vX = 0;
					life = 10;
					w = h = 16;
					x -= round(10 * random());
					y -= round(10 * random());
					// AudioPack.playAudio("BExplosion2.wav",0.05);
					AudioPack.playClip(boom);
					if (wal.damagable) {
						w = h = 16;
						((DamageableWall) wal).life -= 5;
						// if
						// (Jump.kraidLife<=0&&Jump.countdown<0){Jump.countdown=500;
						// AudioPack.playAudio("Ima Firen Mah Lazor!.wav",0.1);
						// }
					}
				}
			}
			for (VisibleObject en : Room.enemies) {
				if (Collisions.collides(this, en)) {
					((Enemy) en).damage(Element.WATER, 10);
					((Enemy) en).vMultiplier = .00001f;

				}
			}
		} else
			dead = true;
	}
}