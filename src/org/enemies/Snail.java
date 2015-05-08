package org.enemies;

import static java.lang.Math.*;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;
public class Snail extends Enemy{
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("firelevel/firesnail.png"),
		ImagePack.getImage("firelevel/firesnail2.png"),
		ImagePack.getImage("firelevel/firesnailR.png"),
		ImagePack.getImage("firelevel/firesnail2R.png"),
		ImagePack.getImage("firelevel/snailshell1.png"),
		ImagePack.getImage("firelevel/snailshell2.png"),
		ImagePack.getImage("firelevel/snailshell3.png"),
		ImagePack.getImage("firelevel/snailshell4.png"),
		ImagePack.getImage("firelevel/snailshell5.png"),
		ImagePack.getImage("firelevel/snailshell6.png"),
		ImagePack.getImage("firelevel/snailshell7.png"),
		ImagePack.getImage("firelevel/snailshell8.png"),
	};
	public boolean rolling=false;
	public boolean left=false;
	int counter=(int)round(1000*random());
	public Snail(int a,int b){
		//w=h=26;
		w=45;h=34;
		x=a;y=b;
		vx=2;
		life=lifeCapacity=50;
	}
	public void run(){
		if(life<=0){
			Health.add(this, 4);
			dead=true;
		}
		counter++;
		boolean onSurface=false;
		for(Wall wal:Room.walls){
			if(Collisions.onTop(this,wal))
				onSurface=true;
		}
		
		if(rolling)
			vx=7*(left?-1:1);
		else if(!rolling&&counter/20%2==1)
			vx=2*(left?-2:2);
		else vx=0;
		if(onSurface&&counter%200==0&&rolling){
			w=45;h=34;
			y-=8;rolling=false;
			vx=2*(vx<0?-1:1);
			if(y+w>Room.WIDTH)y=Room.WIDTH-w;
		}
		else if(onSurface&&counter%200==150&&!rolling){
			w=h=26;
			rolling=true;
			vy=-5;
			y+=8;
			vx=7*(vx<0?-1:1);
		}
		vy+=.5;
		
		vMult1();
		for (Wall wal:Room.walls){
			if (Collisions.willCollide(this, wal, vx, 0)){
    			if (vx>0){x=wal.x-w;}
    			else{x=wal.x+wal.w;}
				vx=rolling?0:-vx;
			}
			if (Collisions.willCollide(this, wal, vx, vy)){
    			if (vy>0){y=wal.y-h;}
    			else{y=wal.y+wal.h;}
				vy=0;
			}
    	}
		Player p=Player.player;
		if (Collisions.willCollide(this, p, vx, 0)){
			if (vx>0){x=p.x-w;}
			else{x=p.x+p.w;}
			vx=rolling?0:-vx;
			Player.damage(4-(rolling?3:0));
		}
   		x+=vx;
   		y+=vy;
   		vMult2();
   		
   		if(vx<0)left=true;
   		if(vx>0)left=false;
		
		image=ani[!rolling?(counter/20%2+(left?0:2)):4+(left?(counter/4%8):7-counter/4%8)];
		//System.out.println(rolling+" "+life);
	}
	@Override
	public void damage(Element e,double a){
		if(!rolling)super.damage(e,a);
		else super.damage(e,a/3);
	}

}
