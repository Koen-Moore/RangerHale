package org.walls;

import static java.awt.Color.red;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static java.lang.Math.*;

import org.resources.Collisions;
import org.resources.LivingObject;
import org.resources.Position;
import org.resources.VisibleObject;
import org.rooms.Room;

public class Wall extends LivingObject{
	public boolean damagable=false;
	public boolean damages=false;
	public Wall(int X,int Y,int W,int H){
		x=X;y=Y;w=W;h=H;color=null;
	}
	public Wall(float X,float Y,int W,int H,BufferedImage b){
		x=X;y=Y;w=W;h=H;color=null;image=b;
	}
	public Wall(float X,float Y,BufferedImage b){
		this(X,Y,b.getWidth(),b.getHeight(),b);
	}
	public Wall(BufferedImage b){
		this(0,0,b);
	}
	public Wall(BufferedImage b,Position p,int xoff,int yoff){
		this(b);
		Collisions.alignWith(this, new Wall(0,0,Room.WIDTH,Room.HEIGHT) , p);
		x+=xoff;
		y+=yoff;
	}
	public Wall(float X,float Y,int W,int H,Color c){
		x=X;y=Y;w=W;h=H;color=c;
	}
	public Wall(float a,float b,float c,float d){
		this((int)a,(int)b,(int)c,(int)d);
	}
	public Wall(VisibleObject v) {
		this(v.x,v.y,v.w,v.h);
	}
}