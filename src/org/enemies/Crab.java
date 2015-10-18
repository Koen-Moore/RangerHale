package org.enemies;

import static java.lang.Math.abs;
import static java.lang.Math.random;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;

public class Crab extends Enemy {
	private static final BufferedImage[][] ani = new BufferedImage[][]{
			{ImagePack.getImage("crab1L.png"), ImagePack.getImage("crab2L.png"), ImagePack.getImage("crab4L.png")},
			{ImagePack.getImage("crab1.png"), ImagePack.getImage("crab2.png"), ImagePack.getImage("crab4.png")

			}};
	// private float vx=0,vy=2;
	// private boolean left=true;
	private int counter = (int) (300 * Math.random());
	private boolean left = true;
	public Crab(float f, float g) {
		element = Element.WATER;
		w = 19;
		h = 14;
		x = f;
		y = g;
		image = ani[0][0];
		life = lifeCapacity = 20;
		vx = 1f;
	}
	public void run() {

		if (life <= 0) {
			for (int d = (int) (4 * random()); d > 0; d--) {
				Room.items.add(new Health(x + w / 2, y + h / 2, vx, vy));
			}
			dead = true;
		}
		counter++;
		left = vx > 0 ? false : true;
		image = ani[left ? 1 : 0][(counter % 20) / 7];
		Player p = Player.player;
		if ((x < p.x + p.w && x + w > p.x) && (y < p.y + p.h && y + h > p.y)) {
			Player.damage(1);
		}
		vy += .5f;
		if (vMultiplier == 0)
			vMultiplier = 10 * Float.MIN_VALUE;

		// float first=vx,last=vy;

		vx *= vMultiplier;

		for (Wall wal : Room.walls) {
			if (vx == 0)
				break;
			if ((Collisions.willCollide(this, wal, vx, 0))) {
				if (vx > 0) {
					x = wal.x - w;
				} else {
					x = wal.x + wal.w;
				}
				vx = -vx;
				break;
			}
			if ((Collisions.willCollide(this, wal, 0, vy))) {
				if (vy > 0) {
					y = wal.y - h;
				} else {
					y = wal.y + wal.h;
				}
				vy = 0;
				break;
			}
		}

		x += vx;
		y += vy;
		vx /= vMultiplier;

		vMultiplier += .03 * (1 - vMultiplier > 0 ? 1 : (abs(vMultiplier) <= .03 ? 0 : -1));
		// System.out.println(x+" "+y);
	}
}
