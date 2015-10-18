package org.projectiles;

import static java.awt.Color.red;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.random;
import static java.lang.Math.round;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import org.resources.ImagePack;

public class EnemyLaserBullet extends EnemyBullet {
	public static final BufferedImage[] ani = lazerani;
	public EnemyLaserBullet(float X, float Y, float vx, float vy) {
		super(X, Y, vx, vy);
		life = lifeCapacity = 10;
		x += round(-2 + 4 * random());
		y += round(-2 + 4 * random());
		synchronized (sync) {
			livePros++;
		}
	}
	public void run() {
	}
	public void draw(Graphics g) {
		// out.println("drawing"+x+" "+y+" "+vX+" "+vY);
		if (life > 9) {

			Stroke old = ((Graphics2D) g).getStroke();
			((Graphics2D) g).setStroke(new BasicStroke(2));

			g.setColor(red);
			// g.drawLine(ax,ay,bx,by);
			g.drawLine(round(startx), round(starty), round(x), round(y));

			((Graphics2D) g).setStroke(old);
		} else if (life >= 0) {
			g.drawImage(ani[4 - max(min(life / 2, 4), 0)], round(x - 5), round(y - 5), null);
		}
		life--;
		if (life == 0)
			dead = true;
	}
}
