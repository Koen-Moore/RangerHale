package org.walls;

import java.awt.Graphics;

import org.resources.ImagePack;
import static java.lang.Math.*;
import java.awt.image.*;

public class Log extends Wall {
	private int size;
	private final static BufferedImage[] pics = new BufferedImage[]{ImagePack.getImage("waterlevel/logL.png"),
			ImagePack.getImage("waterlevel/logM.png"), ImagePack.getImage("waterlevel/logR.png"),

	};
	public Log(int a, int b, int c) {
		super(a, b, 15 + 40 * c, 10);
		size = c;
	}
	public void draw(Graphics g) {
		g.drawImage(pics[0], round(x), round(y), null);
		for (int i = 0; i < size; i++) {
			g.drawImage(pics[1], round(x) + 5 + 40 * i, round(y), null);
		}
		g.drawImage(pics[2], round(x) + 5 + 40 * size, round(y), null);
	}
}