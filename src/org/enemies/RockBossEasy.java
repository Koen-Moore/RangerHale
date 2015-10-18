package org.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.projectiles.EnemyLaserBullet;
import org.projectiles.EnemyRockProjectile;
import org.projectiles.TentacleShot;
import org.resources.Collisions;
import org.resources.ImagePack;
import org.resources.VisibleObject;
import org.rooms.Room;
import org.walls.Wall;

import static java.lang.Math.*;

public class RockBossEasy extends Enemy {
	int deathDelay = -1;
	int hitDelay = 0;
	int counter;
	int proAdd = 0;
	public static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("earthlevel/rockboss.png"),
			ImagePack.getImage("earthlevel/rockboss4.png"), ImagePack.getImage("earthlevel/rockboss.png"),
			ImagePack.getImage("earthlevel/rockboss2.png"), ImagePack.getImage("earthlevel/rockboss3.png"),};
	public RockBossEasy(int X, int Y) {
		x = X;
		y = Y;
		w = 84;
		h = 85;
		life = lifeCapacity = 400;
		image = ani[0];
	}
	public void run() {
		counter++;
		if (hitDelay > 0)
			hitDelay--;

		if (deathDelay > 0) {
			Room.cameraXOff = (int) round(10 * random() - 5);
			Room.cameraYOff = (int) round(10 * random() - 5);
			deathDelay--;
		}
		if (deathDelay == 0) {
			Health.add(this, 30);
			Room.cameraYOff = Room.cameraXOff = 0;
			dead = true;
			return;
		}
		if (life <= 0 && deathDelay < 0) {
			deathDelay = 200;
		}
		if (random() * 100 < proAdd) {
			Room.projectiles.add(new EnemyRockProjectile((int) round(150 + 500 * random()), 110, 0, 0));
			proAdd--;
		}

		boolean onSurface = false;
		for (Wall wal : Room.walls) {
			if (Collisions.onTop(this, wal))
				onSurface = true;
		}
		Player p = Player.player;
		float dx = (p.x + p.w / 2) - (x + w / 2);
		// float dy=(p.y+p.h/2)-(y+h/2);
		if (life > 0) {
			if (hitDelay == 0 && abs(dx) < 60 && p.y + p.h > y + 50 && p.y < y + h) {
				hitDelay = 31;
			}
			if (hitDelay == 5 & abs(dx) < 60 && p.y + p.h > y + 50 && p.y < y + h) {
				Player.damage(30);
				Player.vx = dx < 0 ? -9 : 9;
			}
			if (onSurface) {
				vx = 0;
			}
			if (hitDelay == 0 && counter % 120 == 0 && onSurface) {
				vx = 5f * (dx < 0 ? -1 : 1);
				vy = -6;
			}
		}

		if (hitDelay > 0)
			vx = 0;
		vy += .35;

		// vMult1();
		for (Wall wal : Room.walls) {
			if (Collisions.willCollide(this, wal, vx, 0)) {
				if (vx > 0) {
					x = wal.x - w;
				} else {
					x = wal.x + wal.w;
				}
				vx = 0;
			}
			if (Collisions.willCollide(this, wal, vx, vy)) {
				if (vy > 0) {
					y = wal.y - h;
				} else {
					y = wal.y + wal.h;
				}
				vy = 0;
			}
		}
		x += vx;
		y += vy;

		if (Collisions.collides(this, p) && vy > 0) {
			Player.damage(5);
			Player.vx = dx < 0 ? -9 : 9;
		}
		// vMult2();

		image = ani[!onSurface ? (4) : (hitDelay > 0 ? (hitDelay / 8) : 0)];

	}
	public void draw(Graphics g) {
		g.drawImage(image, round(x), round(y), null);
	}
	public void damage(int i) {
		proAdd += i;
		super.damage(i);
		if (counter % 2 == 0) {
			Room.projectiles.add(new EnemyRockProjectile(round(x) + round(random() * w), round(y) + round(random() * h), 0, 0));
		}
	}

}
