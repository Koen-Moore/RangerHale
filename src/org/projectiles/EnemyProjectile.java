package org.projectiles;

import org.rendering.*;
import org.resources.AudioPack;
import org.resources.VisibleObject;
import org.rooms.Room;
import org.walls.Wall;
import org.players.*;
import org.enemies.*;

public class EnemyProjectile extends Projectile {
	public int damage;
	public EnemyProjectile(float X, float Y, float vx, float vy, int d) {
		damage = d;
		life = lifeCapacity = -1;
		w = 1;
		h = 1;
		x = X;
		y = Y;
		vX = vx;
		vY = vy;
		isEnemy = true; // (thread=new Thread(this)).start();
		synchronized (sync) {
			livePros++;
		}
	}
	public EnemyProjectile(float X, float Y, float vx, float vy) {
		this(X, Y, vx, vy, 1);
	}
	public void run() {
		// boolean frame=Clock.frame;
		if (life != 0) {
			// Clock.waitFor(frame=!frame);
			// if(Clock.dead)break;
			if (life < 0) {
				x += vX;
				y -= vY;
			}
			life--;
			if (!(x > 0 && x < Jump.WIDTH && y > 0 && y < Jump.HEIGHT) && life < 0) {
				vY = vX = 0;
				life = 10;
				w = h = 8;
				// AudioPack.playAudio("BExplosion2.wav",0.05);
				AudioPack.playClip(boom);
			}
			// if(false)
			for (int i = 0; i < Room.walls.size(); i++) {
				Wall wal = Room.walls.get(i);
				if (vY == 0 && vX == 0)
					break;
				if ((x < wal.x + wal.w && x + w > wal.x) && (y < wal.y + wal.h && y + h > wal.y)) {
					vY = vX = 0;
					life = 10;
					w = h = 8;
					dead = true;
					// AudioPack.playAudio("BExplosion2.wav",0.05);
					AudioPack.playClip(boom);
				}
			}

			Player p = Player.player;
			if ((x < p.x + p.w && x + w > p.x) && (y < p.y + p.h && y + h > p.y && life < 0)) {
				Player.damage(damage);
				vY = vX = 0;
				life = 10;
				w = h = 8;
				dead = true;
				// AudioPack.playAudio("BExplosion2.wav",0.05);
				AudioPack.playClip(boom);
			}
		} else
			dead = true;
	}
}
