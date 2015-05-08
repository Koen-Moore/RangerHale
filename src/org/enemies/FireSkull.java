package org.enemies;

import static java.lang.Math.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;
public class FireSkull extends Enemy{
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("firelevel/skull1.png"),
		ImagePack.getImage("firelevel/skull2.png"),
		ImagePack.getImage("firelevel/skull3.png"),
		ImagePack.getImage("firelevel/skull4.png"),
	};
	//public boolean rolling=false;
	//public boolean left=false;
	int counter=(int)round(1000*random());
	public FireSkull(int a,int b){
		//w=h=26;
		w=40;h=63;
		x=a;y=b;
		vx=2;
		life=lifeCapacity=40;
	}
	public void run(){
		Player p=Player.player;
		if(life<=0){
			Health.add(this, 7);
			dead=true;
		}
		counter++;
		boolean onSurface=false;
		for(Wall wal:Room.walls){
			if(Collisions.onTop(this,wal))
				onSurface=true;
		}
		if(counter%20==0){
			vx=(x+w/2<p.x+p.w/2?4:-4);
		}
		if(onSurface&&counter%50==0){
			vy=-7;
			vx=2*(vx<0?-1:1);
		}
		vy+=.1;
		if(Collisions.collides(this, p)){
			Player.damage(2);
			vx=-vx;
		}
		
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
   		
		if(counter%5==0)
			image=ani[(int)(random()*4)];
	}
	public void draw(Graphics g){
		g.drawImage(image,round(x)-10,round(y)-27,null);
	}
}
