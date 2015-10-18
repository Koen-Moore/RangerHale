package org.HUDSystem;

import java.awt.Color;
import static java.awt.Color.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import static java.lang.Math.*;
import static java.lang.System.out;

import org.rendering.Jump;
import org.resources.ImagePack;

public class TypeBox extends Button implements KeyListener {
	public String contents;
	int counter = 0;
	public int maxSize;
	public int buttonDelay = 10;
	public boolean selected;
	public boolean hiddenCharacters;
	public static final BufferedImage pic = ImagePack.getImage("menu stuff/textBox.png");
	public TypeBox(int max) {
		this(max, false);
	}
	public TypeBox(int max, boolean hide) {
		hiddenCharacters = hide;
		maxSize = max;
		contents = "";
		image = pic;
		color = Color.white;
		w = 240;
		h = 25;
		Jump.canvas.addKeyListener(this);
	}
	public void finalize() {
		Jump.canvas.removeKeyListener(this);
	}
	public void select() {
		color = Color.white;
		selected = true;
	}
	public void deSelect() {
		color = new Color(200, 200, 200);
		selected = false;
	}
	public void draw(Graphics g) {
		if (buttonDelay > 0)
			buttonDelay--;
		counter++;
		String tempString = new String(contents);
		if (hiddenCharacters) {
			tempString = "";
			for (int i = 0; i < contents.length(); i++)
				tempString += "*";

		}
		g.setColor(black);
		g.drawImage(image, round(x), round(y), null);
		int cursor = (int) g.getFontMetrics().getStringBounds(tempString, g).getWidth();
		if (selected && counter % 60 < 30)
			g.drawLine(round(x) + cursor + 11, round(y) + 5, round(x) + cursor + 11, round(y) + 20);
		g.setColor(color);
		g.drawString(tempString, round(x) + 10, round(y) + 20);
	}
	public void keyTyped(KeyEvent e) {

	}
	public void keyPressed(KeyEvent e) {
		char val = e.getKeyChar();
		int v = e.getKeyCode();
		if (selected && buttonDelay == 0) {
			// out.println("contents1 "+contents);
			if (v == 8 && contents.length() > 0) {
				contents = contents.substring(0, contents.length() - 1);
				// buttonDelay=4;
				// out.println("contents "+contents);
			}
			if (("" + val).matches("[ a-z|A-Z|0-9]") && contents.length() < maxSize) {
				contents += val;
				// buttonDelay=4;
				// out.println("contents "+contents);
			}
		}

	}
	public void keyReleased(KeyEvent e) {

	}

}
