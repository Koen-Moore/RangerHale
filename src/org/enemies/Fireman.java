package org.enemies;

import static java.lang.Math.*;
import static java.lang.Math.random;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;

public class Fireman extends Enemy {
	private static volatile BufferedImage[][] ani = {
			{ImagePack.getImage("fireman1.png"), ImagePack.getImage("fireman2.png"), ImagePack.getImage("fireman3.png"),
					ImagePack.getImage("fireman4.png"), ImagePack.getImage("fireman5.png")},
			{ImagePack.getImage("fireman1R.png"), ImagePack.getImage("fireman2R.png"), ImagePack.getImage("fireman3R.png"),
					ImagePack.getImage("fireman4R.png"), ImagePack.getImage("fireman5R.png")}};

	// private boolean left = true;
	private int counter = (int) (300.0D * Math.random());

	public Fireman(int X, int Y) {
		this.element = Element.FIRE;
		this.w = 17;
		this.h = 23;
		this.x = X;
		this.y = Y;
		this.image = ani[0][0];
		this.life = (this.lifeCapacity = 10);
	}

	public void run() {
		if (this.life <= 0) {
			Health.add(this, 4);
			this.dead = true;
			return;
		}
		Player p = Player.player;
		vx += (p.x < x ? -.08 : .08);
		vy += (p.y < y ? -.08 : .08);
		vx = max(-4, min(vx, 4));
		vy = max(-4, min(vy, 4));

		if (vMultiplier == 0)
			vMultiplier = 10 * Float.MIN_VALUE;

		vx *= vMultiplier;
		vy *= vMultiplier;
		if (Collisions.collides(p, this) && counter % 30 == 0) {
			Room.enemies.add(new PlayerFirePuff());
			// Player.damage(10);
		}
		for (Wall wal : Room.walls) {
			if ((Collisions.willCollide(this, wal, vx, 0)))
				vx = 0;
			if ((Collisions.willCollide(this, wal, vx, vy)))
				vy = 0;
		}
		x += vx;
		y += vy;
		this.counter += 1;
		this.image = ani[(p.x < x ? 0 : 1)][(this.counter % 30 / 6)];

		vx /= vMultiplier;
		vy /= vMultiplier;

		vMultiplier += .03 * (1 - vMultiplier > 0 ? 1 : (abs(vMultiplier) <= .03 ? 0 : -1));
	}
}