package org.HUDSystem;

import static java.lang.Math.round;

import java.awt.Color;
import java.awt.Graphics;

public class FadingHudTitle extends HudTitle {
	int countdown, startval, delay;
	public FadingHudTitle(String a, int X, int Y, Color c, int d, int count) {
		super(a, X, Y, c);
		contents = a;
		x = X;
		y = Y;
		color = c;
		countdown = startval = count;
		delay = d;
	}
	public void draw(Graphics g) {
		if (delay > 0)
			delay--;
		else {
			if (countdown > 0)
				countdown--;
			color = new Color(color.getRed(), color.getGreen(), color.getBlue(), round(255 * countdown / startval));
		}
		g.setColor(color);
		g.drawString(contents, round(x), round(y));
	}
}