package org.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Math.*;

import org.resources.Collisions;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;
import org.items.Health;
import org.players.*;
import org.projectiles.TentacleShot;

public class Hoopla extends Enemy{
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("earthlevel/hoopla/hoopla.png"),
		ImagePack.getImage("earthlevel/hoopla/hoopla2.png"),
		ImagePack.getImage("earthlevel/hoopla/hoopla3.png"),
		ImagePack.getImage("earthlevel/hoopla/hoopla4.png"),
		ImagePack.getImage("earthlevel/hoopla/hoopla5.png"),
		ImagePack.getImage("earthlevel/hoopla/hooplaR.png"),
		ImagePack.getImage("earthlevel/hoopla/hoopla2R.png"),
		ImagePack.getImage("earthlevel/hoopla/hoopla3R.png"),
		ImagePack.getImage("earthlevel/hoopla/hoopla4R.png"),
		ImagePack.getImage("earthlevel/hoopla/hoopla5R.png"),
	};
	boolean left=false;
	int xoff,yoff;
	int hitDelay;
	int counter=(int)round(1000*random());
	public Hoopla(int a,int b){
		x=a;y=b;w=40;h=29;
		life=lifeCapacity=100;
	}
	public void run(){
		counter++;
		
		if(hitDelay>0)
			hitDelay--;
		
		Player p=Player.player;
		float dx=(p.x+p.w/2)-(x+w/2);
		float dy=(p.y+p.h/2)-(y+h/2);
		//if(counter%150==0)
			//left=!left;
		
		if(life<=0){
			Health.add(this,5);
			dead=true;
		}
		
		if(hitDelay==0&&abs(dx)<30&&abs(dy)<40){
			hitDelay=27;
		}
		if(hitDelay!=0){
			left=dx<0;
			//System.out.println(hitDelay+" "+(1+3-abs(hitDelay/4-3)));
		}
		if(hitDelay==18&abs(dx)<30&&abs(dy)<40){
			Player.damage(10);
			Player.vx=dx<0?-9:9;
		}

		vx=2.5f*(dx<0?-1:1);
		if(hitDelay!=0)
			vx=0;
		if(Collisions.collides(new Wall(x,0,w,Room.HEIGHT), p)){
			vx=0;
		}
   		if(1+3-abs(hitDelay/4-3)>3&&left){
   			xoff=9;yoff=2;
   		}
   		else{
   			xoff=yoff=0;
   		}
		vy+=.1;
		vMult1();
		for (Wall wal:Room.walls){
			if (Collisions.willCollide(this, wal, vx, 0)){
    			if (vx>0){x=wal.x-w;}
    			else{x=wal.x+wal.w;}
				vx=0;
			}
			if (Collisions.willCollide(this, wal, vx, vy)){
    			if (vy>0){y=wal.y-h;}
    			else{y=wal.y+wal.h;}
				vy=0;
			}
    	}
		
   		x+=vx;
   		y+=vy;
   		vMult2();
		

   		if(vx<0)left=true;
   		if(vx>0)left=false;
		
		image=ani[(left?0:5)+(hitDelay==0?(counter/10%2):(1+3-abs(hitDelay/4-3)))];
		
	}
	public void draw(Graphics g){
		g.drawImage(image, round(x)-xoff,round(y)-yoff,null);
	}
}
