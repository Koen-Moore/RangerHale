package org.enemies;

import java.awt.image.BufferedImage;

import org.players.Player;
import org.resources.Collisions;
import org.resources.ImagePack;
import static java.lang.Math.*;

public class FirePuff extends Enemy {
	static final BufferedImage[] ani = firepuffani;
	int counter = (int) round(1000 * random());
	public FirePuff(int a, int b, int l) {
		x = a;
		y = b;
		life = lifeCapacity = l;
		w = 10;
		h = 12;
	}
	public void run() {
		counter++;
		life--;
		if (life <= 0)
			dead = true;
		image = ani[counter / 8 % 4];
		if (counter % 5 == 0 && Collisions.collides(Player.player, this))
			Player.damage(1);
	}
	public boolean preventsNextRoom() {
		return false;
	}

}
