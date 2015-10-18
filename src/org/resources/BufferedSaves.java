package org.resources;

import static java.lang.System.out;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BufferedSaves {
	private static Map<String, BigInteger> loadedSaves = new HashMap<>();

	static {
		loadProfiles();
	}
	public static Map<String, BigInteger> getSaves() {
		return loadedSaves;
	}
	public static boolean profileExists(String user) {
		return loadedSaves.containsKey(user);
	}
	public static BigInteger getProfile(String user) {
		if (!loadedSaves.containsKey(user))
			return null;
		return loadedSaves.get(user);
	}
	public static boolean correctPassword(String a, String b) {
		SaveReader.setSave(getProfile(a));
		return b.equals(SaveReader.nextDynamicString());
	}
	public static SaveState getSaveState(String a) {
		SaveState temp = new SaveState();
		SaveReader.setSave(getProfile(a));
		temp.username = a;
		temp.password = SaveReader.nextDynamicString();
		temp.rooms = SaveReader.nextIntArray();
		temp.currentRoom = SaveReader.nextInt();
		temp.health = SaveReader.nextInt();
		temp.healthCapacity = SaveReader.nextInt();
		temp.projectiles = SaveReader.nextIntArray();
		out.println(Arrays.toString(temp.rooms));
		return temp;
	}
	public static void saveState(SaveState s) {
		SaveWriter.reset();
		SaveWriter.addDynamicString(s.password);
		SaveWriter.addIntArray(s.rooms);
		SaveWriter.addInt(s.currentRoom);
		SaveWriter.addInt(s.health);
		SaveWriter.addInt(s.healthCapacity);
		SaveWriter.addIntArray(s.projectiles);
		updateProfile(s.username, SaveWriter.getSave());

		out.println("saving" + s);
		out.println("new state:" + getSaveState(s.username));
	}
	public static void updateProfile(String a, BigInteger b) {
		loadedSaves.put(a, b);
		saveProfile(a, b);
	}
	private static void saveProfile(String a, BigInteger b) {
		try {
			new File("./" + a + ".save").delete();
			File f = new File("./saves/" + a + ".save");
			f.mkdirs();
			f.delete();
			f.createNewFile();
			out.println(f);
			FileWriter fw = new FileWriter(f);
			fw.append(a + ":\n" + b.toString(36));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	public static String[] usernames() {
		return loadedSaves.keySet().toArray(new String[0]);
	}
	public static void loadProfiles() {
		try {

			List<File> files = new LinkedList<File>(Arrays.asList(new File(".").listFiles()));
			files.addAll(new LinkedList<File>(Arrays.asList(new File("./saves").listFiles())));

			for (File a : files) {
				if (a.toString().matches(".*[.]save")) {
					out.println(a);
					Scanner yolo;
					try {
						yolo = new Scanner(a);
					} catch (IOException e) {
						out.println("bad scanner");
						continue;
					}
					try {
						while (yolo.hasNext()) {
							String username = yolo.next().split(":")[0];
							BigInteger pro = yolo.nextBigInteger(36);
							loadedSaves.put(username, pro);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					yolo.close();
				}
			}
		} catch (Exception e) {
		}
		out.println("saves loaded");
	}
}
