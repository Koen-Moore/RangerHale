package org.enemies;

import java.awt.Color;
import static java.lang.System.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.Collisions;
import org.resources.ImagePack;
import static java.lang.Math.*;

public class Lava extends Enemy {
	private int counter = 10 * (int) Math.round(1000f * Math.random());
	private int[][] values = new int[0][];
	private static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("lava/lava1.png"),
			ImagePack.getImage("lava/lava2.png"), ImagePack.getImage("lava/lava3.png"), ImagePack.getImage("lava/lava4.png"),
			ImagePack.getImage("lava/lavamid1.png"), ImagePack.getImage("lava/lavamid2.png"), ImagePack.getImage("lava/lavamid3.png"),
			ImagePack.getImage("lava/lavamid4.png"),};
	public Lava(int a, int b, int W, int H) {
		x = a;
		y = b;
		w = W;
		h = H;
		values = new int[1 + h / 16][1 + w / 16];
		for (int i = 0; i < values.length; i++) {
			for (int x = 0; x < values[i].length; x++) {
				values[i][x] = (int) (random() * 4);
			}
		}
	}
	public void run() {
		if (Collisions.collides(this, Player.player)) {
			Health.add(this, .5f);
			Player.damage(5);
		}
	}
	public void draw(Graphics g) {
		counter++;
		// g.setColor(new Color(216,0,0));
		// g.fillRect(round(x),round(y)+17, w,h-17);
		// g.clipRect(round(x), round(y), w, h);

		if (counter % 15 == 0)
			for (int i = 0; i < values.length; i++) {
				for (int x = 0; x < values[i].length; x++) {
					values[i][x] = (int) ((values[i][x] + 1) % 4);
				}
			}
		for (int i = round(x); i < x + w; i += 16) {
			g.drawImage(ani[values[0][(i - round(x)) / 16]], i, round(y), null);
			for (int a = 0; a + 1 < values.length; a++) {
				g.drawImage(ani[4 + (values[1 + a][(i - round(x)) / 16])], i, (int) round(y + 14 + a * 16), null);
			}
		}
	}
	public boolean preventsNextRoom() {
		return false;
	}

}
