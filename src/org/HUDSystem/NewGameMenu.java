package org.HUDSystem;
import static java.awt.Color.white;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.main.RoomRunner;
import org.resources.BufferedSaves;
import org.resources.Collisions;
import org.resources.ImagePack;
import org.resources.KeyList;
import org.resources.Position;
import org.resources.VisibleObject;
import org.rooms.MainMenuRoom;
import org.rooms.Room;
import org.walls.Wall;

public class NewGameMenu extends HUD {
	public int buttonDelay;
	public int curButton = 1;
	public List<Button> buttons = new ArrayList<>();
	public static final BufferedImage[] ani = new BufferedImage[]{ImagePack.getImage("menu stuff/back.png"),
			ImagePack.getImage("menu stuff/backs.png"), ImagePack.getImage("menu stuff/make.png"),
			ImagePack.getImage("menu stuff/makes.png"), ImagePack.getImage("menu stuff/textUsername.png"),
			ImagePack.getImage("menu stuff/textPassword.png"),};
	public HUD backRef;
	public TypeBox username, password;
	public MainMenuOptions options;
	// public HudTitle error;
	public NewGameMenu(int w, int h, HUD u) {
		super(w, h);
		backRef = u;
		// error=new HudTitle("!Profile Not Found!",0,90,Position.MIDDLE,red);
		options = new MainMenuOptions(w, h, this);
		add(new Wall(0, 0, planets));

		VisibleObject t;
		add(t = new Wall(0, 0, menuBack));
		Collisions.alignWith(t, this, Position.MIDDLE);
		add(t = new HudTitle("New Game", Position.MIDDLE, white));
		t.y -= 70;

		Button b;
		add(b = new Button(ani[0], ani[1]));
		Collisions.alignWith(b, this, Position.MIDDLE);
		b.y -= 50;
		buttons.add(b);

		add(username = new TypeBox(10));
		Collisions.alignWith(username, this, Position.MIDDLE);
		buttons.add(username);
		username.y -= 10;

		add(new Wall(username.x, username.y - 12, ani[4]));

		add(password = new TypeBox(10, true));
		Collisions.alignWith(password, this, Position.MIDDLE);
		buttons.add(password);
		password.y += 30;

		add(new Wall(password.x, password.y - 12, ani[5]));

		add(b = new Button(ani[2], ani[3]));
		Collisions.alignWith(b, this, Position.MIDDLE);
		b.y += 55;
		buttons.add(b);

	}
	public void draw(Graphics g) {
		if (buttonDelay == 0) {
			int move = 0;
			if (KeyList.keyboard[38]) {
				buttons.get(curButton).deSelect();
				move = -1;
			} else if (KeyList.keyboard[40] || KeyList.keyboard[9]) {

				buttons.get(curButton).deSelect();
				move = 1;
			}
			if (move != 0)
				buttonDelay = 10;
			curButton = (curButton + move + buttons.size()) % buttons.size();
			buttons.get(curButton).select();
			if (curButton == 0 || curButton == 3) {
				if (KeyList.keyboard[8]) {
					Room.curHUD = backRef;
				}
			} else {

			}
			if (KeyList.keyboard[10] || KeyList.keyboard[32]) {
				buttonDelay = 10;
				switch (curButton) {
					case 0 :
						Room.curHUD = backRef;
						break;
					case 1 :

					case 2 :
						if (!KeyList.keyboard[10])
							break;
					case 3 :
						if (username.contents.length() > 0) {
							RoomRunner.loadNewGame(username.contents, password.contents);
							BufferedSaves.saveState(RoomRunner.playState);
							MainMenuRoom.dead = true;
						}
				}
			}
		}
		buttonDelay = Math.max(0, --buttonDelay);

		super.draw(g);

	}
}
