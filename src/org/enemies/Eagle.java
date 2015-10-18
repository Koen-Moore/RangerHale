package org.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.projectiles.EnemyRockProjectile;
import org.resources.Collisions;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;

import static java.lang.Math.*;

public class Eagle extends Enemy {
	static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("earthlevel/rockeagle/eagle1.png"),
			ImagePack.getImage("earthlevel/rockeagle/eagle2.png"), ImagePack.getImage("earthlevel/rockeagle/eagleSwoop1.png"),
			ImagePack.getImage("earthlevel/rockeagle/eagleSwoop2.png"), ImagePack.getImage("earthlevel/rockeagle/eagle1R.png"),
			ImagePack.getImage("earthlevel/rockeagle/eagle2R.png"), ImagePack.getImage("earthlevel/rockeagle/eagleSwoop1R.png"),
			ImagePack.getImage("earthlevel/rockeagle/eagleSwoop2R.png"),};
	int counter = (int) round(1000 * random());
	int lastHealth;
	int swoopcount;
	boolean left;
	int xoff, yoff;
	public boolean swooping = false;
	public Eagle(int a, int b) {
		x = a;
		y = b;
		w = 76;
		h = 23;
		vx = 5;
		lastHealth = life = lifeCapacity = 30;
	}
	public void run() {
		if (life <= 0) {
			Health.add(this, 8);
			dead = true;
		}
		counter++;
		if (swoopcount > 0)
			swoopcount--;
		if (swoopcount == 0 && !swooping)
			vy = 0;
		image = ani[(left ? 0 : 4) + (vy != 0 ? (vy < 0 ? 2 : 3) : (counter / 40 % 2))];

		Player p = Player.player;
		// double dx=x-p.x-p.w/2;
		// double dy=y-p.y-p.h/2;

		if (swoopcount == 0 && !swooping && (Collisions.collides(new Wall(x, y, w, Room.HEIGHT), Player.player) || lastHealth != life)) {
			swooping = true;
		}
		if (counter % 6 == 0 && Collisions.collides(new Wall(x, y, w, Room.HEIGHT), Player.player)) {
			Room.projectiles.add(new EnemyRockProjectile(x + (left ? 70 : 15), y + 17, 0, 0));
		}
		if (swooping) {
			xoff = vy < 0 ? 10 : 0;
			yoff = 20;
			lastHealth = life;
			h = 40;
			vy = 10;
		} else {
			xoff = counter / 40 % 2 == 0 ? 10 : 0;
			yoff = counter / 40 % 2 == 1 ? 20 : 0;
			h = 25;
			// vy=0;
		}
		vx = 5 * (vx < 0 ? -1 : 1);
		// vy-=(counter%10)-4.5;

		vMult1();
		for (Wall wal : Room.walls) {
			if (Collisions.willCollide(this, wal, vx, 0)) {
				// if (vx>0){x=wal.x-w;}
				// else{x=wal.x+wal.w;}
				vx = -vx;
			}
			if (Collisions.willCollide(this, wal, vx, vy)) {
				// if (vy>0){y=wal.y-h;}
				// else{y=wal.y+wal.h;}
				vy = 0;
				if (swooping) {
					swooping = false;
					swoopcount = 30;
					lastHealth = life;
					vy = -10;
				}
			}
		}
		/*
		 * if (Collisions.willCollide(this, p, vx, vy)){ if (vx>0){x=p.x-w;}
		 * else{x=p.x+p.w;} if (vy>0){y=p.y-h;} else{y=p.y+p.h;} vy=0;
		 * 
		 * vx=-2*vx; swooping=false; Player.damage(5); }
		 */
		x += vx;
		y += vy;
		vMult2();
		if (vx < 0)
			left = true;
		if (vx > 0)
			left = false;

	}
	public void draw(Graphics g) {
		g.drawImage(image, round(x) - xoff, round(y) - yoff, null);
	}

}
