package org.walls;

import java.awt.image.BufferedImage;
import static java.lang.Math.*;
import org.resources.ImagePack;
import org.resources.VisibleObject;

public class Computer extends VisibleObject implements Runnable {
	public static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("backgrounds/computerscreen1.png"),
			ImagePack.getImage("backgrounds/computerscreen2.png"),};
	int counter = (int) round(1000 * random());
	public Computer(int X, int Y) {
		x = X;
		y = Y;
		w = 35;
		h = 45;
	}
	public void run() {
		counter++;
		image = ani[counter / 60 % 2];
	}
}
