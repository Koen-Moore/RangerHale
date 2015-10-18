package org.HUDSystem;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.resources.ImagePack;
import org.resources.VisibleObject;
import org.rooms.Room;

public class HUD extends VisibleObject {
	public static final BufferedImage menuBack = ImagePack.getImage("menu stuff/menuback3.png"), planets = ImagePack
			.getImage("backgrounds/planets.png");
	private volatile ArrayList<VisibleObject> parts;
	public HUD(int W, int H) {
		x = 0;
		y = 0;
		w = W;
		h = H;
		parts = new ArrayList<VisibleObject>();
	}
	public HUD(int W, int H, ArrayList<VisibleObject> p) {
		this(W, H);
		parts.addAll(p);
	}
	public void add(VisibleObject v) {
		parts.add(v);
	}
	public void remove(VisibleObject v) {
		parts.remove(v);
	}
	public BufferedImage image() {
		return image();
	}
	public void reload() {
		image = new BufferedImage(w, h, 2);
		Graphics g = image.createGraphics();
		for (VisibleObject v : parts) {
			v.draw(g);
		}
		g.dispose();
	}
	public void draw(Graphics g) {
		for (VisibleObject v : parts) {
			v.draw(g);
		}
	}
}
