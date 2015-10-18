package org.enemies;

import static java.lang.Math.round;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.players.Player;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.resources.VisibleObject;
import org.rooms.Room;
import org.walls.Wall;

public class Barrel extends Enemy {
	private static volatile BufferedImage[] ani = new BufferedImage[]{

	ImagePack.getImage("Barrel/Barrel.png"), ImagePack.getImage("Barrel/Barrel2.png"), ImagePack.getImage("Barrel/Barrel3.png"),
			ImagePack.getImage("Barrel/Barrel4.png"), ImagePack.getImage("Barrel/Barrel5.png")};
	private int deathcount = 0;
	private float tw, th, tx, ty;
	private Wall collision;
	public Barrel(int X, int Y) {
		element = Element.NORMAL;
		tw = w = 16;
		th = h = 20;
		tx = x = X;
		ty = y = Y;
		Room.walls.add(collision = new Wall(x + 1, y + 1, w - 2, h - 2));
		image = ani[0];
		life = lifeCapacity = 28;
	}
	public void run() {
		if (life <= 0) {
			image = ani[deathcount % 15 / 3];
			if (deathcount == 0) {
				tx -= 10;
				ty -= 12;
				tw = 35;
				th = 42;
			}
			deathcount++;
			if (deathcount >= 9) {
				if (Collisions.collides(new Wall(tx, ty, tw, th), Player.player)) {
					Player.damage(7);
				}
				synchronized (Room.enemies) {
					for (VisibleObject en : Room.enemies) {
						if (Collisions.collides(new Wall(tx, ty, tw, th), en)) {
							((Enemy) en).damage(7);
						}
					}
				}
			}
			if (deathcount == 14) {
				dead = true;
				Room.walls.remove(collision);
			}
		}
	}
	public void draw(Graphics g) {
		g.drawImage(image, round(tx), round(ty), null);
	}
	public boolean preventsNextRoom() {
		return false;
	}
}
