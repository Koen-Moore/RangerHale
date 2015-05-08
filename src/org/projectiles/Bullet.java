package org.projectiles;

import static java.awt.Color.*;
import static java.lang.Math.floor;
import static java.lang.Math.hypot;
import static java.lang.Math.random;
import static java.lang.Math.round;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.sound.sampled.Clip;

import org.resources.*;
import org.rooms.Room;
import org.walls.DamageableWall;
import org.walls.Wall;
import org.enemies.*;

public class Bullet extends Projectile implements Runnable{
	public final Clip boom=booms[(int)floor(booms.length*random())];
	public float startx,starty;
	public Bullet(){};
	public Bullet(float X,float Y,float vx,float vy){
		image=null;
		dead=false;
		life=-1;lifeCapacity=40;
		w=2;h=2;color=red;
		x=startx=X;y=starty=Y;vX=vx;vY=-vy; //thread=new Thread(this);thread.start();
		synchronized(sync){livePros++;}
		
		float d1=(vX>0?round(x):0);
		float d2=(vX>0?Room.WIDTH:round(x));
		float r1=(vY>0?round(y):0);
		float r2=(vY>0?Room.HEIGHT:round(y));
		if(vX==0)d1=d2=x;
		if(vY==0)r1=r2=y;
		
		float xhit=0,yhit=0,closeDist=Integer.MAX_VALUE;
		VisibleObject closest=null;
		
		main:
		for(int i=0;i<2;i++){
			VisibleObject[]it;
			it=(i==0?Room.walls.toArray(new VisibleObject[0]):Room.enemies.toArray(new VisibleObject[0]));
			for (int a=0;a<it.length;a++){
				VisibleObject vo=it[a];
				if (vo==null)continue;
				//Inside
				if(Collisions.collides(this, vo)){
					xhit=x;yhit=y;closeDist=0;
					closest=vo;
					break main;
				}
				
				//Top
				if(vY>0){
					float curX=(-y+vo.y)*vX/vY+x;
					float curY=vo.y;
					float curDist=(float)hypot(curX-x,curY-y);
					if(curX>=vo.x&&curX<=vo.x+vo.w){
						if(curX>=d1&&curX<=d2&&curY>=r1&&curY<=r2&&curDist<closeDist){
							xhit=curX;yhit=curY;closeDist=curDist;
							closest=vo;
						}
					}
				}
				
				//Bottom
				if(vY<0){
					float curX=(-y+vo.y+vo.h)*vX/vY+x;
					float curY=vo.y+vo.h;
					float curDist=(float)hypot(curX-x,curY-y);
					if(curX>=vo.x&&curX<=vo.x+vo.w){
						if(curX>=d1&&curX<=d2&&curY>=r1&&curY<=r2&&curDist<closeDist){
							xhit=curX;yhit=curY;closeDist=curDist;
							closest=vo;
						}
					}
				}
				
				//Left
				if(vX>0){
					float curY=(vo.x-x)*vY/vX+y;
					float curX=vo.x;
					float curDist=(float)hypot(curX-x,curY-y);
					if(curY>=vo.y&&curY<=vo.y+vo.h){
						if(curX>=d1&&curX<=d2&&curY>=r1&&curY<=r2&&curDist<closeDist){
							xhit=curX;yhit=curY;closeDist=curDist;
							closest=vo;
						}
					}
				}
				
				//Right
				if(vX<0){
					float curY=(vo.x+vo.w-x)*vY/vX+y;
					float curX=vo.x+vo.w;
					float curDist=(float)hypot(curX-x,curY-y);
					if(curY>=vo.y&&curY<=vo.y+vo.h){
						if(curX>=d1&&curX<=d2&&curY>=r1&&curY<=r2&&curDist<closeDist){
							xhit=curX;yhit=curY;closeDist=curDist;
							closest=vo;
						}
					}
				}
			}
		}
		
		if(closest==null){dead=true;return;}
		else{
			life=40;
			AudioPack.playClip(boom);
			x=xhit;y=yhit;
			if(closest instanceof Enemy)((Enemy)closest).damage(Element.NORMAL,5);
			if(closest instanceof DamageableWall)((DamageableWall)closest).life-=5;
		}
	}
	public void run(){
		life--;
		if(life==0)dead=true;
	}
	public void draw(Graphics g){
		//int d=150;
		//Color c=new Color(d,d,d);
		//g.setColor(white);
		float a=(float)(.97f-.97f*life/lifeCapacity);
		float b=.03f+a;
		int ax=round(startx+4000*a*(vX));
		int ay=round(starty+4000*a*(vY));
		int bx=round(startx+4000*b*(vX));
		int by=round(starty+4000*b*(vY));
		if((x-ax)*vX<0||(y-ay)*vY<0){
			ax=round(x);ay=round(y);
			dead=true;
		}
		if((x-bx)*vX<0||(y-by)*vY<0){
			bx=round(x);by=round(y);
		}
		((Graphics2D)g).setPaint(new GradientPaint(ax,ay,new Color(0,0,0,1),bx,by,white));
		g.drawLine(ax,ay,bx,by);
		g.setColor(BLACK);
		g.drawRect(bx,by,0,0);
	}
}
