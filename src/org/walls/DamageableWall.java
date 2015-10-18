package org.walls;

import java.awt.Color;

public class DamageableWall extends Wall {
	public DamageableWall(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		damagable = true;
		life = lifeCapacity = 100;
	}
}
