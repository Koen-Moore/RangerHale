package org.enemies;

import java.awt.image.BufferedImage;

import org.resources.*;
import org.players.*;
import org.rooms.*;
import org.walls.*;

import static java.lang.Math.*;
public class Larva extends Enemy{
	public static final BufferedImage[]ani=larvaani;
	int counter=(int)round(1000*random());
	boolean left;
	public Larva(int a,int b){
		this(a,b,0,0);
	}
	public Larva(float a,float b,float va,float vb){
		vx=va;vy=vb;
		x=a;y=b;
		w=10;h=4;
		life=lifeCapacity=5;
	}
	public void run(){
		if(life<=0){
			dead=true;
			return;
		}
		if(Collisions.collides(this, Player.player)){
			Player.damage(2);
			life=0;dead=true;
			return;
		}
		
		boolean onSurface=(y==Room.HEIGHT-h);
		for(Wall wal:Room.walls){
			onSurface=onSurface||Collisions.onTop(this, wal);
		}
		
		Player p=Player.player;
		if(abs(vx)>.5){
			vx+=vx<0?.5:-.5;
		}
    	vx=(x+w/2<p.x+p.w/2?.2f:-.2f);
		if(Collisions.collides(new Wall(x,0,w,Room.HEIGHT), Player.player)){
			vx=0;
			//image=ani[]
		}
		else{
			counter++;
		}
		if(vx!=0)left=vx<0;
    	image=ani[(left?0:2)+(vx!=0?counter/10%2:0)];
    	vy+=.5f;
		
    	if(onSurface&&counter%60==0){
    		vy=-10;
    	}
    	
    	vMult1();
 	    for(Wall wal:Room.walls){
 	    	if(Collisions.willCollide(this, wal, vx, 0)){
 	    		vx=0;
 	    	}
 	    	if(Collisions.willCollide(this, wal, 0,vy)){
 	    		if(vy>0)y=wal.y-h;
 	    		else y=wal.y+wal.w;
 	    		vy=0;
 	    	}
 	    }
 	    x+=vx;y+=vy;
 	    vMult2();
	}
	public boolean preventsNextRoom(){
		return false;
	}
}
