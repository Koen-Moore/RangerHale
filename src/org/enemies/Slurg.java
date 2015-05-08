package org.enemies;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.random;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.projectiles.SlurgSpit;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;

public class Slurg extends Enemy implements Runnable{
	private static volatile BufferedImage[][]ani=new BufferedImage[][]{
			{
					ImagePack.getImage("slurg/slurg1.png"),
					ImagePack.getImage("slurg/slurg2.png"),
					ImagePack.getImage("slurg/slurgANGRY1.png"),
					ImagePack.getImage("slurg/slurgANGRY2.png")
			},
			{
					ImagePack.getImage("slurg/slurgright1.png"),
					ImagePack.getImage("slurg/slurgright2.png"),
					ImagePack.getImage("slurg/slurgANGRYright1.png"),
					ImagePack.getImage("slurg/slurgANGRYright2.png")
			}
		};
	
	public int curFrame;
	//public float vx= -.8f;
	public Slurg(float X,float Y){
		element=Element.EARTH;
		curFrame=(int)floor(20*random());
		//image=images[curFrame];
		life=lifeCapacity=50;
		vx=-.8f;
		x=X; y=Y; w=62; h=28;
		//(thread=new Thread(this)).start();
	}
	public void run(){
		if(life<=0){
			for(int d=(int)(8*random());d>0;d--){
   				Room.items.add(new Health(x+w/2,y+h/2,vx,vy));
   			}
			//AudioPack.playAudio("SlurgDeath.wav",.05);
			dead=true;
		}
		if(life>0&&!dead){
			//if(Thread.interrupted())break;
			//Clock.waitFor();
			//if(Clock.dead)break;
			curFrame++;
			image=ani[vx<0?0:1][(life<35?2:0)+(curFrame%20<10?0:1)];
			Player p=Player.player;
			if((curFrame%15)==0&&life<35){
				if(x<p.x+p.w&&x+w>p.x&&y+h<p.y){
					Room.projectiles.add(new SlurgSpit(x+(vx<0?0:62),y+15,0,(y+h<p.y?-3:3)));
				}
				if(y<p.y+p.h&&y+h>p.y&&(x+w<p.x||x>p.x+p.w)){
					Room.projectiles.add(new SlurgSpit(x+(vx<0?0:62),y+15,(x+w<p.x?3:-3),0));
				}
			}
			if (Collisions.collides(this, Player.player)){
   				Player.damage(1);
   			}
   			
   			if(vMultiplier==0)vMultiplier=10*Float.MIN_VALUE;
   			
   			vx*=vMultiplier;
   			vy*=vMultiplier;
   			
   			x+=vx;
   			//if(x<0||x>Room.WIDTH){vx=-vx;}
   			for (Wall wal:Room.walls){
    			if (vx==0)break;
    			if (Collisions.willCollide(this, wal, vx, 0)){
    				if (vx>0){x=wal.x-w;}
    				else{x=wal.x+wal.w;}
    				vx=-vx;
    				break;
    			}
    		}
   			
   			vx/=vMultiplier;
   			vy/=vMultiplier;
   			
   			vMultiplier+=.03*(1-vMultiplier>0?1:(abs(vMultiplier)<=.03?0:-1));
		}
	}
}