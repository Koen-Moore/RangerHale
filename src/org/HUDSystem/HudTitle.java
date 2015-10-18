package org.HUDSystem;

import static java.lang.Math.round;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.rendering.Jump;
import org.resources.Collisions;
import org.resources.Position;
import org.resources.VisibleObject;
import org.rooms.Room;

public class HudTitle extends VisibleObject {
	public String contents = "";
	public Position position;
	public static FontMetrics fm;
	static {
		Graphics g = new BufferedImage(1, 1, 5).getGraphics();
		fm = g.getFontMetrics();
		g.dispose();
	}
	// public static FontMetrics fm=Jump.g.getFontMetrics();
	public HudTitle(String a, int xoff, int yoff, Position p, Color c) {
		contents = a;
		position = p;
		color = c;
		if (Jump.g != null) {
			fm = Jump.g.getFontMetrics();
		}
		w = (int) (fm.getStringBounds(contents, Jump.g).getWidth());
		h = (int) (fm.getStringBounds(contents, Jump.g).getHeight());
		Collisions.alignWith(this, Room.hud, p);
		x += xoff;
		y += yoff;
	}
	public HudTitle(String a, Position p, Color c) {
		this(a, 0, 0, p, c);
	}
	public HudTitle(String a, int X, int Y, Color c) {
		contents = a;
		x = X;
		y = Y;
		color = c;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawString(contents, round(x), round(y));
	}
}