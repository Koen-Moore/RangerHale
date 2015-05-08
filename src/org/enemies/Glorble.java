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
import static java.lang.System.*;

public class Glorble extends Enemy {
	private static volatile BufferedImage[][]ani=new BufferedImage[][]{
		{
			ImagePack.getImage("glorble/glorble1.png"),
			ImagePack.getImage("glorble/glorble2.png"),
			ImagePack.getImage("glorble/glorbleJump1.png"),
			ImagePack.getImage("glorble/glorbleJump2.png")
		},{
			ImagePack.getImage("glorble/glorble1F.png"),
			ImagePack.getImage("glorble/glorble2F.png"),
			ImagePack.getImage("glorble/glorbleJump1F.png"),
			ImagePack.getImage("glorble/glorbleJump2F.png")
		}
	};
	//private int curFrame;
	//private double vx=0,vy=2;
	private int counter=(int)(300*Math.random());
	private boolean left=true;
	private int jumpd=0;
	public Glorble(float X,float Y){
		element=Element.EARTH;
		
		//images=ani;
		//curFrame=0;
		image=ani[0][0];
		life=lifeCapacity=30;
		x=X;y=Y;
		w=35;h=30;
		vx=(random()<.5?-1:1)*(float)random()*5+5;
		vy=(float)random()*10-5;
	}
	public void run(){
		boolean onSurface=(y==Room.HEIGHT-h);
		boolean onWall=(x==Room.WIDTH-w||x==0);
		left=vx>0?false:true;
		for(Wall wal:Room.walls){
			onSurface=onSurface||Collisions.onTop(this, wal);
			onWall=onWall||Collisions.touchingSides(this, wal);
		}
			//if(onSurface&&counter%20<9){
				//image=ani[left?0:1][0];
				
			//}
			//else if(onSurface&&counter%20>9){
				//mage=ani[left?0:1][1];
				
			//}
		if(onSurface&&jumpd<=0){
			jumpd=30;
			h=40;
			y-=10;
			vy=-5;
					
		}
		else if(onSurface){
			jumpd--;
		}
		image=ani[left?0:1][(onSurface?0:2)+(counter%20<10?0:1)];
		
		
		counter++;
		//image=ani[counter%30<15?0:1][0];
		if(life<=0){
			Health.add(this,8);
			dead=true;return;
		}
		Player p=Player.player;
		if ((x<p.x+p.w&&x+w>p.x)&&(y<p.y+p.h&&y+h>p.y)){
   			Player.damage(1);
   			
   		}
		//if (false)
		vy+=.1;
		
		if(vMultiplier==0)vMultiplier=10*Float.MIN_VALUE;
		
		vx*=vMultiplier;
		vy*=vMultiplier;
		for (Wall wal:Room.walls){

			if (Collisions.willCollide(this, wal, vx, 0)){
    			if (vx>0){x=wal.x-w;}
    			else{x=wal.x+wal.w;}
				vx=0-vx;
			}
			if (Collisions.willCollide(this, wal, vx, vy)){
    			if (vy>0){h=30;y=wal.y-h;}
    			else{y=wal.y+wal.h;}
				vy=0;
			}
		}
		if(!onSurface){
			x+=vx;
		}
   		y+=vy;
   		
   		vx/=vMultiplier;
		vy/=vMultiplier;
		
		vMultiplier+=.03*(1-vMultiplier>0?1:(abs(vMultiplier)<=.03?0:-1));
		//if(dx<0)curFrame=1;
		//else if(dx>0) curFrame=0;
		//image=ani[0][counter%30<15?0:1];
	}
}