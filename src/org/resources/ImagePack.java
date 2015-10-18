package org.resources;

import static java.lang.System.exit;

import java.awt.image.BufferedImage;
import java.util.*;

import javax.imageio.ImageIO;

public class ImagePack {
	public static Map<String, BufferedImage> pics = new HashMap<>();
	public static BufferedImage getImage(String path) {
		if (!pics.containsKey(path)) {
			try {
				pics.put(path, ImageIO.read(FilePack.getURL(path)));
			} catch (Exception e) {
				e.printStackTrace();
				exit(0);
				return null;
			}
		}
		return pics.get(path);
	}
}
