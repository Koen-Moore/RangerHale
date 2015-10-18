package org.projectiles;

import static java.lang.Math.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.rendering.*;
import org.resources.AudioPack;
import org.resources.ImagePack;
import org.resources.VisibleObject;
import org.rooms.Room;
import org.walls.Wall;
import org.players.*;
import org.enemies.*;

public class SlurgSpit extends EnemyProjectile {
	public SlurgSpit(float x, float y, float vx, float vy) {
		super(x, y, vx, vy, 5);
		if (abs(vx) > abs(vy)) {
			image = ani[vx > 0 ? 3 : 2];
		} else {
			image = ani[vy > 0 ? 0 : 1];
		}
	}
	public static final BufferedImage[] ani = slurgani;
	public void draw(Graphics g) {
		g.drawImage(image, round(x), round(y), null);
	}

}
