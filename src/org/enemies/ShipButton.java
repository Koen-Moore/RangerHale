package org.enemies;

import java.awt.image.BufferedImage;

import org.resources.ImagePack;

public class ShipButton extends Enemy {
	public static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("Robot/button1.png"),
			ImagePack.getImage("Robot/button2.png"),

	};
	public ShipButton(int X, int Y) {
		x = X;
		y = Y;
		w = 40;
		h = 8;
		life = 10;
	}
	public void run() {
		if (life < 0)
			image = ani[1];
		else
			image = ani[0];
	}
}
