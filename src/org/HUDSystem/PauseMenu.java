package org.HUDSystem;
import org.main.RoomRunner;
import org.rendering.Jump;
import org.resources.*;
import org.rooms.MainMenuRoom;
import org.rooms.Room;
import org.rooms.RoomState;
import org.walls.Wall;

import static java.awt.Color.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;

public class PauseMenu extends HUD {
	public int buttonDelay;
	public int curButton;
	public List<Button> buttons = new ArrayList<>();
	public static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("menu stuff/resume.png"),
			ImagePack.getImage("menu stuff/resumes.png"), ImagePack.getImage("menu stuff/option.png"),
			ImagePack.getImage("menu stuff/options.png"), ImagePack.getImage("menu stuff/load.png"),
			ImagePack.getImage("menu stuff/loads.png"), ImagePack.getImage("menu stuff/main.png"),
			ImagePack.getImage("menu stuff/mains.png"), ImagePack.getImage("menu stuff/exit.png"),
			ImagePack.getImage("menu stuff/exits.png"),};
	public PauseOptions options;
	public PauseMenu(int w, int h) {
		super(w, h);
		options = new PauseOptions(w, h, this);
		add(new Wall(0, 0, w, h, new Color(0, 0, 0, 150)));

		VisibleObject t;
		add(t = new Wall(0, 0, menuBack));
		Collisions.alignWith(t, this, Position.MIDDLE);
		add(t = new HudTitle("Paused", Position.MIDDLE, white));
		t.y -= 70;

		Button b;
		add(b = new Button(ani[0], ani[1]));
		Collisions.alignWith(b, this, Position.MIDDLE);
		b.y -= 60;
		buttons.add(b);

		add(b = new Button(ani[2], ani[3]));
		Collisions.alignWith(b, this, Position.MIDDLE);
		b.y -= 30;
		buttons.add(b);

		add(b = new Button(ani[4], ani[5]));
		Collisions.alignWith(b, this, Position.MIDDLE);
		buttons.add(b);

		add(b = new Button(ani[6], ani[7]));
		Collisions.alignWith(b, this, Position.MIDDLE);
		b.y += 30;
		buttons.add(b);

		add(b = new Button(ani[8], ani[9]));
		Collisions.alignWith(b, this, Position.MIDDLE);
		b.y += 60;
		buttons.add(b);
	}
	public void draw(Graphics g) {
		if (buttonDelay == 0) {
			int move = 0;
			if (KeyList.keyboard[38]) {
				buttons.get(curButton).deSelect();
				move = -1;
			} else if (KeyList.keyboard[40]) {

				buttons.get(curButton).deSelect();
				move = 1;
			}
			if (move != 0)
				buttonDelay = 10;
			curButton = (curButton + move + buttons.size()) % buttons.size();
			buttons.get(curButton).select();
			if (KeyList.keyboard[8]) {
				Room.changeState(RoomState.PLAYING);
			}
			if (KeyList.keyboard[10] || KeyList.keyboard[32]) {
				buttonDelay = 10;
				switch (curButton) {
					case 0 :
						Room.changeState(RoomState.PLAYING);
						break;
					case 1 :
						Room.pauseHUD = options;
						options.buttonDelay = 10;
						break;
					case 2 :
						RoomRunner.goToLoadMenu = true;
						Room.endFast = true;
						break;
					case 3 :
						RoomRunner.goToMainMenu = true;
						Room.endFast = true;
						break;
					case 4 : {
						System.exit(0);
					}
				}
			}
		}
		buttonDelay = Math.max(0, --buttonDelay);

		super.draw(g);

	}
}
