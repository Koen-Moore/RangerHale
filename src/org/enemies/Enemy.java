package org.enemies;

import static java.lang.Math.*;
import static java.lang.System.out;

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import org.resources.AudioPack;
import org.resources.Element;
import org.resources.ImagePack;
import org.resources.LivingObject;

public abstract class Enemy extends LivingObject implements Runnable {

	public static volatile BufferedImage[][] sharkAni, bee1ani = new BufferedImage[][]{
			{ImagePack.getImage("bee1.png"), ImagePack.getImage("bee2.png")},
			{ImagePack.getImage("bee1R.png"), ImagePack.getImage("bee2R.png")}}, bee2ani = new BufferedImage[][]{
			{ImagePack.getImage("smallBeeL.png"), ImagePack.getImage("smallBee2L.png")},
			{ImagePack.getImage("smallBee.png"), ImagePack.getImage("smallBee2.png")}};
	public static volatile BufferedImage[] larvaani = new BufferedImage[]{ImagePack.getImage("airlevel/Larva.png"),
			ImagePack.getImage("airlevel/Larva2.png"), ImagePack.getImage("airlevel/LarvaR.png"),
			ImagePack.getImage("airlevel/Larva2R.png"),}, firepuffani = new BufferedImage[]{ImagePack.getImage("lava/firepuff1.png"),
			ImagePack.getImage("lava/firepuff2.png"), ImagePack.getImage("lava/firepuff3.png"), ImagePack.getImage("lava/firepuff4.png"),};;
	public final static Clip[] screams = new Clip[5];
	static {
		for (int i = 0; i < screams.length; i++) {
			screams[i] = AudioPack.newClip("death.wav", .8);
		}
	};
	public static void load() {
		out.println("loading enemy resources");
		sharkAni = new BufferedImage[][]{
				{ImagePack.getImage("shark1.png"), ImagePack.getImage("shark2.png"), ImagePack.getImage("shark3.png"),
						ImagePack.getImage("shark2.png"), ImagePack.getImage("shark1.png"), ImagePack.getImage("shark6.png"),
						ImagePack.getImage("shark7.png"), ImagePack.getImage("shark6.png"),},
				{ImagePack.getImage("shark1R.png"), ImagePack.getImage("shark2R.png"), ImagePack.getImage("shark3R.png"),
						ImagePack.getImage("shark2R.png"), ImagePack.getImage("shark1R.png"), ImagePack.getImage("shark6R.png"),
						ImagePack.getImage("shark7R.png"), ImagePack.getImage("shark6R.png"),

				}};
	}
	// public boolean dead=false;
	// public float x,y;
	// public int w,h;
	// public BufferedImage image=null;
	public Element element = Element.NORMAL;
	public BufferedImage[] images;
	// public volatile int life;
	public float vx = 0, vy = 0;
	public float vMultiplier = 1;
	public abstract void run();
	public void finalize() {
		out.println("enemyDead");
		// try{thread.interrupt();thread.join();}catch(Exception
		// e){e.printStackTrace();exit(1);}
	}
	public void damage(int d) {
		life -= abs(d);
		/*
		 * for(;d>0;d--){ Room.items.add(new Health(x+w/2,y+h/2,vx,vy)); }
		 */
	}
	public void damage(Element e, double d) {
		damage((int) round(d * damageMultiplier(e, element) * random() * 2));
	}
	public static double damageMultiplier(Element a, Element b) {
		switch (a) {
			case FIRE :
				switch (b) {
					case FIRE :
						return .6;
					case WATER :
						return .4;
					case AIR :
						return 1;
					case EARTH :
						return 1.4;
					case NORMAL :
						return 1.6;
				}
			case WATER :
				switch (b) {
					case FIRE :
						return 1.6;
					case WATER :
						return .6;
					case AIR :
						return 1;
					case EARTH :
						return 1.6;
					case NORMAL :
						return 1.4;
				}
			case AIR :
				switch (b) {
					case FIRE :
						return 1.6;
					case WATER :
						return 1;
					case AIR :
						return .6;
					case EARTH :
						return .4;
					case NORMAL :
						return 1.4;
				}
			case EARTH :
				switch (b) {
					case FIRE :
						return 1.4;
					case WATER :
						return .4;
					case AIR :
						return 1.6;
					case EARTH :
						return .6;
					case NORMAL :
						return 1;
				}
			case NORMAL :
				return 1;
		}
		return 1;
	}
	public boolean preventsNextRoom() {
		return true;
	}
	public void vMult1() {
		vx *= vMultiplier;
		vy *= vMultiplier;
	}
	public void vMult2() {
		vx /= vMultiplier;
		vy /= vMultiplier;

		vMultiplier += .03 * (1 - vMultiplier > 0 ? 1 : (abs(vMultiplier) <= .03 ? 0 : -1));
	}

}
