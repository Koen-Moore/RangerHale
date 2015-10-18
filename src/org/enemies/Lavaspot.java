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

public class Lavaspot extends Enemy {
	private static final BufferedImage[][] ani = new BufferedImage[][]{
			{ImagePack.getImage("Lavaspot.png"), ImagePack.getImage("Lavaspot2.png"), ImagePack.getImage("Lavaspot4.png"),
					ImagePack.getImage("Lavaspot3.png")},
			{ImagePack.getImage("LavaspotRight.png"), ImagePack.getImage("Lavaspot2right.png"), ImagePack.getImage("Lavaspot4Right.png"),
					ImagePack.getImage("Lavaspot3Right.png")}};
	// private double vx=0,vy=2;
	// private boolean left=true;
	private int counter = (int) (300 * Math.random());
	private int jumpd = 0;
	private boolean left = true;
	public Lavaspot(int X, int Y) {
		element = Element.FIRE;
		w = 33;
		h = 17;
		x = X;
		y = Y;
		image = ani[0][0];
		life = lifeCapacity = 10;
		vx = (random() < .5 ? -1 : 1) * (float) random() * 5 + 5;
		vy = (float) random() * 10 - 5;
	}
	public void run() {
		boolean onSurface = (y == Room.HEIGHT - h);
		boolean onWall = (x == Room.WIDTH - w || x == 0);
		left = vx > 0 ? false : true;
		for (Wall wal : Room.walls) {
			onSurface = onSurface || Collisions.onTop(this, wal);
			onWall = onWall || Collisions.touchingSides(this, wal);
		}
		// if(onSurface&&counter%20<9){
		// image=ani[left?0:1][0];

		// }
		// else if(onSurface&&counter%20>9){
		// mage=ani[left?0:1][1];

		// }
		if (onSurface && jumpd <= 0) {
			jumpd = 30;
			vy = -5.5f;

		} else if (onSurface) {
			jumpd--;
		}
		// image=ani[left?0:1][0];

		counter++;
		// image=ani[counter%30<15?0:1][0];
		if (life <= 0) {
			Health.add(this, 6);
			dead = true;
			return;
		}
		Player p = Player.player;
		if (Collisions.collides(p, this)) {
			Player.damage(1);

		}
		// if (false)
		vy += .1;

		if (vMultiplier == 0)
			vMultiplier = 10 * Float.MIN_VALUE;

		vx *= vMultiplier;
		vy *= vMultiplier;
		for (Wall wal : Room.walls) {
			if (Collisions.willCollide(this, wal, vx, 0)) {
				if (vx > 0) {
					x = wal.x - w;
				} else {
					x = wal.x + wal.w;
				}
				vx = 0 - (vx + .2f);
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
		if (!onSurface) {
			x += vx;
		}
		y += vy;
		image = ani[left ? 0 : 1][(counter / 20) % 4];

		vx /= vMultiplier;
		vy /= vMultiplier;

		vMultiplier += .03 * (1 - vMultiplier > 0 ? 1 : (abs(vMultiplier) <= .03 ? 0 : -1));
		// if(dx<0)curFrame=1;
		// else if(dx>0) curFrame=0;
		// image=ani[0][counter%30<15?0:1];
	}
	public boolean preventsNextRoom() {
		return false;
	}
}