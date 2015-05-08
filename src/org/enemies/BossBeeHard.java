package org.enemies;

import static java.lang.Math.abs;
import static java.lang.Math.random;
import static java.lang.Math.round;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.rooms.Room;
import org.walls.Wall;

public class BossBeeHard extends Enemy {
	private static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("airlevel/kingBee.png"),
		ImagePack.getImage("airlevel/kingBee2.png"),
		ImagePack.getImage("airlevel/angryKingBee.png"),
		ImagePack.getImage("airlevel/angryKingBee2.png"),
		ImagePack.getImage("airlevel/sadKingBee.png"),
		ImagePack.getImage("airlevel/kingBeeR.png"),
		ImagePack.getImage("airlevel/kingBee2R.png"),
		ImagePack.getImage("airlevel/angryKingBeeR.png"),
		ImagePack.getImage("airlevel/angryKingBee2R.png"),
		ImagePack.getImage("airlevel/sadKingBeeR.png"),
	};
	boolean angry;
	//private double vx=0,vy=2;
	int deathDelay=-1;
	private int counter=(int)(300*Math.random());
	public BossBeeHard(float X,float Y){
		element=Element.AIR;
		//images=ani;
		image=ani[0];
		life=lifeCapacity=1000;
		x=X;y=Y;
		w=52;h=53;
		vy=2;
	}
	public void run(){
		counter++;
		if(deathDelay>0){
			Room.cameraXOff=(int)round(10*random()-5);
			Room.cameraYOff=(int)round(10*random()-5);
			deathDelay--;
		}
		if(deathDelay==0){
			Health.add(this,30);
			Room.cameraYOff=Room.cameraXOff=0;
			dead=true;return;
		}
		if(life<=0&&deathDelay<0){
			deathDelay=200;
		}
		Player p=Player.player;
   		double dx=x-p.x-p.w/2;
   		double dy=y-p.y-p.h/2;
   		if(counter%10==0){
   			vx=(float)((dx>0?-1:1)*(Math.random()*5-1.6));
   		}
   		if(counter%2==0){
   			vy=(float)((dy>0?-1:1)*(Math.random()*5-1.6));
   		}
   		vy+=(counter%10<5?-1:1 );
		boolean changed=false;
		//if (false)

		if(counter%200<=30&&deathDelay<0){
			vx=vy=0;
			angry=true;
			if(counter%3==0)
				Room.enemies.add(new SmallBee(x+25,y+25));
			if(counter%10==0)
				Room.enemies.add(new Bee(x+25,y+25));
		}
		else{
			angry=false;
		}
		if(deathDelay>0){
			vx=vy=0;
		}
		
		if(vMultiplier==0)vMultiplier=10*Float.MIN_VALUE;
		
		vx*=vMultiplier;
		vy*=vMultiplier;
		for (Wall wal:Room.walls){

			if (Collisions.willCollide(this, wal, vx, 0)){
    			if (vx>0){x=wal.x-w;}
    			else{x=wal.x+wal.w;}
				vx=0;changed=true;
			}
			if (Collisions.willCollide(this, wal, vx, vy)){
    			if (vy>0){y=wal.y-h;}
    			else{y=wal.y+wal.h;}
				vy=0;changed=true;
			}
			if(changed){
				//x-=(dx>0?-1:1);
				//y-=(dy>0?-1:1);
				//break;
			}
    	}
   		x+=vx;
   		y+=vy;
   		
   		vx/=vMultiplier;
		vy/=vMultiplier;
		
		vMultiplier+=.03*(1-vMultiplier>0?1:(abs(vMultiplier)<=.03?0:-1));
		
		if(deathDelay>0)
			image=ani[dx<0?9:4];
		else
			image=ani[(dx<0?5:0)+(angry?2:0)+(counter%6<3?0:1)];
	}
}