package org.projectiles;

import static java.awt.Color.red;
import static java.lang.Math.*;

import java.awt.image.BufferedImage;

import org.players.Player;
import org.resources.*;
import org.enemies.*;
import org.rooms.Room;
import org.walls.DamageableWall;
import org.walls.Wall;

public class EnemySuperRock extends Projectile {
	public static final BufferedImage[] ani = bigrockani;
	public int counter = (int) (100 * random());
	public EnemySuperRock(float X, float Y, float vx, float vy) {
		image = ani[counter % 8];
		dead = false;
		life = lifeCapacity = -1;
		h = 16;
		w = 16;
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
			int p = (counter / 3) % 8;
			image = ani[p];

			if (life > -1) {
				image = ani[min(life, 8) / 2 + 8];
			}

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
						((DamageableWall) wal).life -= 40;
						// if
						// (Jump.kraidLife<=0&&Jump.countdown<0){Jump.countdown=500;
						// AudioPack.playAudio("Ima Firen Mah Lazor!.wav",0.1);
						// }
					}
				}
			}

			if (Collisions.collides(this, Player.player)) {
				collided = true;
				Player.damage(40);

			}
			if (collided) {
				double offset = Math.PI * ((80 * random()) % 8) / 4;
				Room.projectiles.add(new RockProjectile(x, y, (float) cos(offset) * 3, (float) sin(offset) * 3));
				Room.projectiles.add(new RockProjectile(x, y, (float) cos(offset + PI * 2 / 3) * 3, (float) sin(offset + PI * 2 / 3) * 3));
				Room.projectiles.add(new RockProjectile(x, y, (float) cos(offset + PI * 4 / 3) * 3, (float) sin(offset + PI * 4 / 3) * 3));

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
