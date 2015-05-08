package org.enemies;

import static java.lang.Math.*;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.*;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;
public class Voggie extends Enemy{
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("earthlevel/voggies/voggyRun1.png"),
		ImagePack.getImage("earthlevel/voggies/voggyRun2.png"),
		ImagePack.getImage("earthlevel/voggies/voggySit1.png"),
		ImagePack.getImage("earthlevel/voggies/voggySit2.png"),
		ImagePack.getImage("earthlevel/voggies/voggyRun1R.png"),
		ImagePack.getImage("earthlevel/voggies/voggyRun2R.png"),
		ImagePack.getImage("earthlevel/voggies/voggySit1R.png"),
		ImagePack.getImage("earthlevel/voggies/voggySit2R.png"),
	};
	public boolean sitting=true;
	public boolean left=false;
	int runDelay=0;
	int counter=(int)round(1000*random());
	public Voggie(int a,int b){
		//w=h=26;
		w=43;h=29;
		x=a;y=b;
		//vx=2;
		life=lifeCapacity=50;
	}
	public void run(){
		if(life<=0){
			Health.add(this, 4);
			dead=true;
		}
		counter++;
		if(runDelay>0)
			runDelay--;
		
		/*boolean onSurface=false;
		for(Wall wal:Room.walls){
			if(Collisions.onTop(this,wal))
				onSurface=true;
		}*/
		Player p=Player.player;
   		double dx=x-p.x-p.w/2;
   		//double dy=y-p.y-p.h/2;
		if(counter%100==0&&sitting){
			h=27;
			y+=2;
			sitting=false;
			//vx=7*(vx<0?-1:1);
			if(y+h>Room.HEIGHT)y=Room.HEIGHT-h;
			
		}
		if(counter%100==25&&!sitting||(Collisions.collides(new Wall(x,0,w,Room.HEIGHT), p))){
			h=29;
			//vy=-5;
			vx=0;
			if(!sitting)
				y-=2;
			sitting=true;
		}
		if(!sitting&&runDelay==0)
			vx=7*(dx<0?1:-1);
		vy+=.5;
		
		vMult1();
		if (Collisions.willCollide(this, p, vx, 0)){
			if (vx>0){x=p.x-w;}
			else{x=p.x+p.w;}
			
			vx=-2*vx;
			runDelay=10;
			sitting=false;
			Player.damage(5);
		}
		
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
   		
   		if(runDelay==0){
   			if(vx<0)left=true;
   			if(vx>0)left=false;
   		}
		image=ani[(left?0:4)+(sitting?(counter%100>50&&counter%100<75?3:2):(counter/10%2))];
		//image=ani[counter/5%2];
		//System.out.println(rolling+" "+life);
	}

}
