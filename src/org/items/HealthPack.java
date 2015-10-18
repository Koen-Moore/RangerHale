package org.items;

import java.awt.image.BufferedImage;

import org.players.Player;
import org.resources.Collisions;
import org.resources.ImagePack;
import org.resources.LivingObject;

public class HealthPack extends LivingObject {
	public static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("healthPack.png"),
			ImagePack.getImage("healthupPack.png"),};
	public boolean up;
	public HealthPack(int a, int b, boolean type) {
		x = a;
		y = b;
		up = type;
		image = ani[up ? 1 : 0];
	}
	public void run() {
		if (Collisions.collides(this, Player.player)) {
			if (up)
				Player.player.lifeCapacity += 100;
			else
				Player.damage(-100);
			dead = true;
		}
	}
}
