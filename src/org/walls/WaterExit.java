package org.walls;

import java.awt.image.BufferedImage;

import org.resources.ImagePack;
import org.rooms.Room;

public class WaterExit extends Wall implements Runnable {
	public static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("waterlevel/wDoor.png"),
			ImagePack.getImage("waterlevel/wDoor2.png"), ImagePack.getImage("waterlevel/wDoorR.png"),
			ImagePack.getImage("waterlevel/wDoor2R.png"),};
	public WaterExit(int x, int y) {
		super(x + 35, y, 5, 80);
		Room.walls.add(new Wall(x, y, 40, 10, ani[2]));
		Room.foreground.add(new Wall(x, y, image = ani[3]));
	}

}
