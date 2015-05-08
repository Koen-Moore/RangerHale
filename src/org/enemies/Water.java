package org.enemies;

import java.awt.Color;
import static java.lang.System.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.players.Player;
import org.resources.Collisions;
import org.resources.ImagePack;
import static java.lang.Math.*;

public class Water extends Enemy{
	private int counter=10*(int)Math.round(1000f*Math.random());
	private static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("water/waterTop1.png"),
		ImagePack.getImage("water/waterTop2.png"),
		ImagePack.getImage("water/waterTop3.png"),
		ImagePack.getImage("water/waterTop4.png"),
	};
	public Water(int a,int b,int W,int H){
		x=a;y=b;w=W;h=H;
	}
	public void run(){
		if(Collisions.collides(Player.player, this)){
			Player.vMultiplier=.6f;
			out.println("hello");
		}
	}
	public void draw(Graphics g){
		counter++;
		g.setColor(new Color(0,148,255,182));
		g.fillRect(round(x),round(y)+16, w,h-16);
		g.clipRect(round(x), round(y), w, h);
		
		for(int i=round(x);i<x+w;i+=16){
			g.drawImage(ani[3-(counter/15)%4],i,round(y),null);
		}
		g.setClip(null);
	}
	public boolean preventsNextRoom(){
		return false;
	}

}