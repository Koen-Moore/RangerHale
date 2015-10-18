package org.projectiles;

import java.awt.image.BufferedImage;

public class TentacleShot extends EnemyProjectile {
	public TentacleShot(float x, float y, float vx, float vy) {
		super(x, y, vx, vy, 3);
		w = 8;
		h = 5;
		image = ani[vx < 0 ? 0 : 1];
	}
	public static final BufferedImage[] ani = tentani;
}
