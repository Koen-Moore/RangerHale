package org.resources;

import static java.lang.System.*;

public class Collisions {
	public static boolean collides(VisibleObject a,VisibleObject b){
		try{return((a.x+a.w>b.x&&a.x<b.x+b.w)&&(a.y+a.h>b.y&&a.y<b.y+b.h));}
		catch(Exception e){return false;}
	}
	public static boolean willCollide(VisibleObject a,VisibleObject b,double vx,double vy){
		try{return((a.x+vx+a.w>b.x&&a.x+vx<b.x+b.w)&&(a.y+vy+a.h>b.y&&a.y+vy<b.y+b.h));}
		catch(Exception e){return false;}
	}
	public static boolean outOfBounds(VisibleObject a,VisibleObject b){
		try{return(a.x<b.x||a.x+a.w>b.x+b.w||a.y<b.y||a.y+a.h>b.y+b.h);}
		catch(Exception e){return false;}
	}
	public static boolean touchingSides(VisibleObject a,VisibleObject b){
		try{return((a.x+a.w==b.x||a.x==b.x+b.w)&&(a.y+a.h>b.y&&a.y<b.y+b.h));}
		catch(Exception e){return false;}
	}
	public static boolean onTop(VisibleObject a,VisibleObject b){
		try{return((a.x<b.x+b.w&&a.x+a.w>b.x)&&a.y+a.h==b.y);}
		catch(Exception e){return false;}
	}
	public static void alignWith(VisibleObject a,VisibleObject b,Position p){
		try{
		switch(p){
		
		case TOP_LEFT:a.x=0;a.y=0;break;
		case TOP:a.x=(b.w-a.w)/2;a.y=0;break;
		case TOP_RIGHT:a.x=b.w-a.w;a.y=0;break;
		case LEFT:a.x=0;a.y=(b.h-a.h)/2;break;
		case MIDDLE:a.x=(b.w-a.w)/2;a.y=(b.h-a.h)/2;break;
		case RIGHT:a.x=b.w-a.w;a.y=(b.h-a.h)/2;break;
		case BOTTOM_LEFT:a.x=0;a.y=b.h-a.h;break;
		case BOTTOM:a.x=(b.w-a.w)/2;a.y=b.h-a.h;break;
		case BOTTOM_RIGHT:a.x=b.w-a.w;a.y=b.h-a.h;break;
			
		}}
		catch(Exception e){return;}
	}
}
