package org.items;

import java.awt.image.BufferedImage;

import org.resources.ImagePack;
import static java.lang.Math.*;
import org.resources.VisibleObject;

public class FireTree extends VisibleObject implements Runnable {
	public static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("backgrounds/firetree1.png"),
			ImagePack.getImage("backgrounds/firetree2.png"), ImagePack.getImage("backgrounds/firetree3.png"),
			ImagePack.getImage("backgrounds/firetree4.png"),};
	private int counter = (int) round(10000 * random());
	public FireTree(int a, int b) {
		x = a;
		y = b;
	}
	public void run() {
		counter++;
		image = ani[counter / 8 % 4];
	}
}
