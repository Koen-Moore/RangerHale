package org.projectiles;

import static java.awt.Color.red;
import static java.lang.Math.random;
import static java.lang.Math.round;

import java.awt.image.BufferedImage;

import org.enemies.Enemy;
import org.resources.AudioPack;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.VisibleObject;
import org.rooms.Room;
import org.walls.DamageableWall;
import org.walls.Wall;

public class RockProjectile extends Projectile {
	public static final BufferedImage[][] ani = rockani;
	public int counter = (int) (100 * random());
	public RockProjectile(float X, float Y, float vx, float vy) {
		image = ani[counter % 4][0];
		dead = false;
		life = lifeCapacity = -1;
		h = 9;
		w = 9;
		color = red;
		x = X;
		y = Y;
		vX = (float) (vx * 2f + random() - .5);
		vY = (float) (vy * 2f + random() - .5);
		synchronized (sync) {
			livePros++;
		}

	}
	public void run() {
		// boolean frame=Clock.frame;
		if (life != 0) {
			counter++;
			vY -= .07;
			int p = (counter / 12) % 4;
			image = ani[counter % 4][0];
			if (life > -1)
				image = ani[p][1];
			if (life > 2)
				image = ani[p][2];
			if (life > 4)
				image = ani[p][3];
			if (life > 6)
				image = ani[p][4];
			if (life > 8)
				image = ani[p][5];
			// Clock.waitFor(frame=!frame);
			// if(Clock.dead)break;
			life--;
			if (life < 0) {
				x += vX;
				y -= vY;
			} else
				return;
			/*
			 * if (!(x>0&&x<Jump.WIDTH&&y>0&&y<Jump.HEIGHT)&&life<0){vY=vX=0;
			 * life=60; w=h=16; x-=round(10*random()); y-=round(10*random());
			 * //AudioPack.playAudio("BExplosion2.wav",0.05);
			 * AudioPack.playClip(boom); }
			 */

			boolean collided = false;
			for (int i = 0; i < Room.walls.size(); i++) {
				Wall wal = Room.walls.get(i);
				if (vY == 0 && vX == 0)
					break;
				if (Collisions.collides(this, wal)) {

					collided = true;
					if (wal.damagable) {
						((DamageableWall) wal).life -= 20;
						// if
						// (Jump.kraidLife<=0&&Jump.countdown<0){Jump.countdown=500;
						// AudioPack.playAudio("Ima Firen Mah Lazor!.wav",0.1);
						// }
					}
				}
			}

			for (VisibleObject en : Room.enemies) {
				if (Collisions.collides(this, en)) {
					collided = true;
					((Enemy) en).damage(Element.EARTH, 20);
				}
			}
			if (collided) {
				life = 12;
				w = h = 11;
				x -= round(vY * random());
				y -= round(vX * random());
				vY = vX = 0;
				// AudioPack.playAudio("BExplosion2.wav",0.05);
				AudioPack.playClip(boom);
			}

		} else
			dead = true;
	}
}
