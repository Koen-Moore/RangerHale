package org.rooms;

import org.HUDSystem.MainMenu;
import org.main.RoomRunner;
import org.rendering.Jump;
import org.resources.Clock;

public class MainMenuRoom extends Room {
	public static boolean dead = false;
	public void load() {
		dead = false;
		System.out.println("starting menu");
		visibleObjects.clear();
		curHUD = new MainMenu(500, 500);
	}
	public void startRoom() {
		Jump.buildGraphics();
		Clock.start();
		while (!dead) {
			Jump.render();
			Clock.waitFor();
		}
		Clock.stop();
		RoomRunner.playSoundTrack();
	}
}
