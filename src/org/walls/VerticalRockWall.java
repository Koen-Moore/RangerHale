package org.walls;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Math.*;

import org.resources.ImagePack;

public class VerticalRockWall extends Wall {
	public static final BufferedImage[] i = new BufferedImage[]{ImagePack.getImage("backgrounds/rockWallT.png"),
			ImagePack.getImage("backgrounds/rockWall.png"), ImagePack.getImage("backgrounds/rockWallB.png"),};
	public VerticalRockWall(int a, int b, int height) {
		super(a, b, 10, height);

	}
	public void draw(Graphics g) {
		for (int a = round(y) + 5; a + 20 < y + h - 5; a += 20)
			g.drawImage(i[1], round(x), a, null);

		g.drawImage(i[1], round(x), round(y) + h - 25, null);
		g.drawImage(i[0], round(x), round(y), null);
		g.drawImage(i[2], round(x), round(y) + h - 5, null);
	}
}
