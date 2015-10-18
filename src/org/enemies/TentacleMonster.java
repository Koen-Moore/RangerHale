package org.enemies;

import java.awt.image.BufferedImage;
import static java.lang.Math.*;
import org.resources.ImagePack;
import org.rooms.Room;
import org.items.Health;
import org.players.*;
import org.projectiles.TentacleShot;

public class TentacleMonster extends Enemy {
	public static final BufferedImage[] ani = new BufferedImage[]{

	ImagePack.getImage("waterlevel/tentacle1L.png"), ImagePack.getImage("waterlevel/tentacle2L.png"),
			ImagePack.getImage("waterlevel/tentacle3L.png"), ImagePack.getImage("waterlevel/tentaclehit1.png"),
			ImagePack.getImage("waterlevel/tentaclehit2.png"), ImagePack.getImage("waterlevel/tentaclehit3.png"),
			ImagePack.getImage("waterlevel/tentaclehit4.png"), ImagePack.getImage("waterlevel/tentacle1R.png"),
			ImagePack.getImage("waterlevel/tentacle2R.png"), ImagePack.getImage("waterlevel/tentacle3R.png"),
			ImagePack.getImage("waterlevel/tentaclehitR1.png"), ImagePack.getImage("waterlevel/tentaclehitR2.png"),
			ImagePack.getImage("waterlevel/tentaclehitR3.png"), ImagePack.getImage("waterlevel/tentaclehitR4.png"),};
	boolean left = false;
	int hitDelay;
	int counter = (int) round(1000 * random());
	public TentacleMonster(int a, int b) {
		x = a;
		y = b;
		w = 30;
		h = 80;
		life = lifeCapacity = 100;
	}
	public void run() {
		counter++;

		if (hitDelay > 0)
			hitDelay--;

		Player p = Player.player;
		float dx = (p.x + p.w / 2) - (x + w / 2);
		float dy = (p.y + p.h / 2) - (y + h / 2);
		if (counter % 150 == 0)
			left = !left;

		if (life <= 0) {
			Health.add(this, 5);
			dead = true;
		}

		if (hitDelay == 0 && abs(dx) < 30 && p.y + p.h > y + 50) {
			hitDelay = 24;
		}
		if (hitDelay != 0) {
			left = dx < 0;
		}
		if (hitDelay == 12 & abs(dx) < 30 && p.y + p.h > y + 50) {
			Player.damage(10);
			Player.vx = dx < 0 ? -9 : 9;
		}
		if (counter % 150 > 125 && hitDelay == 0 && counter % 5 == 0 && abs(dy) < 60 && (left ? -1 : 1) * dx > 0 && abs(dx) > 60) {
			float a = (x) + (left ? 0 : 29) - 4, b = y + 10;
			Room.projectiles.add(new TentacleShot(a, y + 10, (left ? -5 : 5), (float) ((left ? 5 : -5) * (p.y + p.h / 2 - b) / (p.x + p.w
					/ 2 - a))));
		}

		image = ani[(left ? 0 : 7) + (hitDelay == 0 ? (abs(counter / 10 % 5 - 2)) : (3 + 3 - abs(hitDelay / 6 - 4)))];
	}
}
