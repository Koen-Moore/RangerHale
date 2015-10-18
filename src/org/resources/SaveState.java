package org.resources;

import java.util.Arrays;

public class SaveState {
	public String username;
	public String password;
	public int[] rooms = new int[0];
	public int currentRoom;
	public int health;
	public int healthCapacity;
	public int[] projectiles = new int[0];

	public String toString() {
		return "name: " + username + " pass: " + password + " rooms: " + Arrays.toString(rooms) + " curRoom: " + currentRoom + " life: "
				+ health + " lifeCap: " + healthCapacity + " pros: " + Arrays.toString(projectiles);
	}
}
