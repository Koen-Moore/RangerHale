package org.walls;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Math.*;

import org.resources.ImagePack;

public class VerticalCloudWall extends Wall {
	public static final BufferedImage[] i = new BufferedImage[]{ImagePack.getImage("platforms/Cloudtop.png"),
			ImagePack.getImage("platforms/verticalCloud.png"), ImagePack.getImage("platforms/Cloudbottom.png"),};
	public VerticalCloudWall(int a, int b, int height) {
		super(a, b, 10, height);

	}
	public void draw(Graphics g) {
		g.drawImage(i[0], round(x), round(y), null);
		for (int a = round(y) + 5; a + 20 < y + h - 5; a += 20)
			g.drawImage(i[1], round(x), a, null);
		g.drawImage(i[1], round(x), round(y) + h - 25, null);
		g.drawImage(i[2], round(x), round(y) + h - 5, null);
	}
}
