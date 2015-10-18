package org.HUDSystem;

import static java.lang.Math.random;
import static java.lang.Math.round;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.main.RoomRunner;
import org.resources.ImagePack;
import org.resources.KeyList;
import org.resources.VisibleObject;
import org.rooms.Bridge;
import org.rooms.Room;
import org.walls.Wall;

public class ComputerScreen extends HUD {
	public static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("menu stuff/computerMessage.png"),
			ImagePack.getImage("menu stuff/computerError.png"), ImagePack.getImage("menu stuff/computerError2.png"),
			ImagePack.getImage("menu stuff/computerError3.png"), ImagePack.getImage("menu stuff/planetSelect.png"),
			ImagePack.getImage("menu stuff/computerButtons1.png"), ImagePack.getImage("menu stuff/computerButtons2.png"),
			ImagePack.getImage("menu stuff/computerButtons3.png"), ImagePack.getImage("menu stuff/computerButtons4.png"),
			ImagePack.getImage("menu stuff/computerButtons5.png"),};
	int computerstate = 0;
	int counter = 0;
	int buttonDelay = 40;
	int curButton = 0;
	VisibleObject button;
	public ComputerScreen() {
		super(300, 300);
		x = 100;
		y = 100;
		add(button = new Wall(round(x + 360), round(y + 33), ani[5]));
	}
	public void draw(Graphics g) {
		if (computerstate < 2) {
			if (computerstate == 0)
				image = ani[0];
			if (computerstate == 1) {
				counter++;
				image = ani[1 + counter / 20 % 2];
				if (counter > 200)
					image = ani[3];
				if (counter < 60) {
					Room.cameraXOff = (int) round(10 * random() - 5);
					Room.cameraYOff = (int) round(10 * random() - 5);
				}
				if (counter == 60) {
					Room.cameraXOff = Room.cameraYOff = 0;
				}

			}
		} else {
			image = ani[4];
		}
		if (computerstate == 3)
			Bridge.dead = true;

		if (buttonDelay == 0) {
			int move = 0;
			if (KeyList.keyboard[38]) {
				move = -1;
			} else if (KeyList.keyboard[40]) {
				move = 1;
			}
			if (move != 0) {
				buttonDelay = 10;
			}
			curButton = (curButton + move + 5) % 5;
			button.image = ani[5 + curButton];
			if (KeyList.keyboard[10] || KeyList.keyboard[32]) {
				buttonDelay = 10;
				switch (curButton) {
					case 0 :
						if (computerstate < 2)
							computerstate++;
						counter = 0;
						buttonDelay = 10;
						break;
					case 1 :
					case 2 :
					case 3 :
					case 4 :
						if (computerstate == 2) {
							buttonDelay = 10;

							switch (curButton) {
								case 1 :
									RoomRunner.loadFireLevel(true);
									break;
								case 2 :
									RoomRunner.loadWaterLevel(true);
									break;
								case 3 :
									RoomRunner.loadRockLevel(true);
									break;
								case 4 :
									RoomRunner.loadAirLevel(true);
									break;
							}
							Room.endFast = true;
							break;
						}

				}
			}
		}
		buttonDelay = Math.max(0, --buttonDelay);

		g.drawImage(image, round(Room.screenX) + 100, round(Room.screenY) + 100, null);
		super.draw(g);
		// System.out.println(computerstate+" "+counter+" "+buttonDelay+" "+(KeyList.keyboard[10])+" "+(KeyList.keyboard[32]));
	}

}
