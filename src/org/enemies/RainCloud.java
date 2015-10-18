package org.enemies;

import static java.lang.Math.random;
import static java.lang.Math.round;

import java.awt.image.BufferedImage;

import org.players.Player;
import org.resources.Collisions;
import org.resources.ImagePack;

public class RainCloud extends Enemy {
	public static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("waterlevel/cloud1.png"),
			ImagePack.getImage("waterlevel/cloud2.png"), ImagePack.getImage("waterlevel/cloud3.png"),};
	private int counter = (int) round(1000 * random());
	public RainCloud(int a, int b) {
		x = a;
		y = b;
		w = 10;
		h = 30;
	}
	public void run() {
		counter++;
		image = ani[counter / 10 % 3];
		if (Collisions.collides(this, Player.player) && counter % 10 == 0) {
			Player.damage(1);
		}
	}
}
