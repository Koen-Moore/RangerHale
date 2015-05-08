package org.resources;

import static java.lang.Math.round;
import static java.lang.System.out;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class VisibleObject implements VOb{
	public boolean dead=false;
	public float x=0,y=0;
	public int w=0,h=0;
	public BufferedImage image=null;
	public Color color;
	public float x(){
		return x;
	}
	public float y(){
		return y;
	}
	public int w(){
		return w;
	}
	public int h(){
		return h;
	}
	public BufferedImage image(){
		return image;
	}
	public void draw(Graphics g){
		if(image==null){
			if(color==null)return;
			g.setColor(color);
			g.fillRect(round(x), round(y), w,h);
		}
		else
			g.drawImage(image, round(x), round(y), null);
	}
	public void finalize(){
		out.println("VO is dead!");
	}
	
}
interface VOb{
	float x();
	float y();
	int w();
	int h();
	BufferedImage image();
}
