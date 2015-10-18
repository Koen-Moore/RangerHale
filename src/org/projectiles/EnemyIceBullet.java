package org.projectiles;

import static java.awt.Color.red;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.atan;
import static java.lang.Math.hypot;
import static java.lang.Math.random;
import static java.lang.Math.round;

import java.awt.image.BufferedImage;

import org.players.Player;
import org.resources.*;
import org.enemies.*;
import org.rooms.Room;
import org.walls.DamageableWall;
import org.walls.Wall;

public class EnemyIceBullet extends Projectile {
	public static final BufferedImage[] ani = iceani;
	public EnemyIceBullet(float X, float Y, float vx, float vy) {
		image = ani[0];
		dead = false;
		life = lifeCapacity = -1;
		w = 8;
		h = 5;
		if (abs(vx) > abs(vy)) {
			h = 8;
			w = 5;
		}
		color = red;
		x = X;
		y = Y;
		vX = vx * 1.3f;
		vY = vy * 1.3f;
		int counter = 0;
		while (hypot(vX, vY) < 1 && counter < 10) {
			counter++;
			vX *= 2;
			vY *= 2;
		}
		synchronized (sync) {
			livePros++;
		}
		if (abs(abs(atan((double) vY / vX)) - PI / 4) > PI / 8)
			image = ani[abs(vX) > abs(vY) ? (vX < 0 ? 0 : 1) : (vY > 0 ? 2 : 3)];
		else {
			image = ani[vX > 0 ? (vY < 0 ? 4 : 6) : (vY < 0 ? 5 : 7)];
			w = h = 7;
		}
	}
	public void run() {
		// boolean frame=Clock.frame;
		if (life != 0) {
			if (life > -1)
				image = ani[11];
			if (life > 3)
				image = ani[10];
			if (life > 5)
				image = ani[9];
			if (life > 8)
				image = ani[8];
			// Clock.waitFor(frame=!frame);
			// if(Clock.dead)break;
			if (life < 0) {
				x += vX;
				y -= vY;
			}
			life--;
			/*
			 * if (!(x>0&&x<Jump.WIDTH&&y>0&&y<Jump.HEIGHT)&&life<0){vY=vX=0;
			 * life=60; w=h=16; x-=round(10*random()); y-=round(10*random());
			 * //AudioPack.playAudio("BExplosion2.wav",0.05);
			 * AudioPack.playClip(boom); }
			 */
			for (int i = 0; i < Room.walls.size(); i++) {
				Wall wal = Room.walls.get(i);
				if (vY == 0 && vX == 0)
					break;
				if ((x < wal.x + wal.w && x + w > wal.x) && (y < wal.y + wal.h && y + h > wal.y)) {
					vY = vX = 0;
					life = 30 + (int) (60 * random());
					w = h = 16;
					image = ani[8];
					x -= round(10 * random());
					y -= round(10 * random());
					// AudioPack.playAudio("BExplosion2.wav",0.05);
					AudioPack.playClip(boom);
					if (wal.damagable) {
						((DamageableWall) wal).life -= 5;
						// if
						// (Jump.kraidLife<=0&&Jump.countdown<0){Jump.countdown=500;
						// AudioPack.playAudio("Ima Firen Mah Lazor!.wav",0.1);
						// }
					}
				}
			}

			if (Collisions.collides(this, Player.player)) {
				if (life < 0)
					Player.damage(1);
				if (10 * random() <= 1)
					Player.damage(1);
				Player.vMultiplier = .4f;
				if (vX != 0 || vY != 0) {
					// x=en.x+en.w/2;
					image = ani[8];
					vY = vX = 0;
					life = 30 + (int) (60 * random());
					w = h = 16;
					// y=en.y+en.h/2;
					x -= .7 * vX + round(10 * random());
					y -= .7 * vY + round(10 * random());
					// AudioPack.playAudio("BExplosion2.wav",0.1);
					AudioPack.playClip(boom);
				}
			}
		} else
			dead = true;
	}
}
