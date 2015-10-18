package org.walls;
import static java.lang.Math.round;

import java.awt.Graphics;
import java.awt.image.*;

import org.resources.ImagePack;

public class WaterPlatform extends Wall {
	static final BufferedImage[] pics = new BufferedImage[]{ImagePack.getImage("waterlevel/wplatL.png"),
			ImagePack.getImage("waterlevel/wplatM.png"), ImagePack.getImage("waterlevel/wplatR.png"),};
	int size;
	public WaterPlatform(int a, int b, int c) {
		super(a, b, 4 + 4 * c, 8);
		size = c;
	}
	public void draw(Graphics g) {
		g.drawImage(pics[0], round(x), round(y), null);
		for (int i = 0; i < size; i++) {
			g.drawImage(pics[1], round(x) + 1 + 4 * i, round(y), null);
		}
		g.drawImage(pics[2], round(x) + 1 + 4 * size, round(y), null);
	}

}
