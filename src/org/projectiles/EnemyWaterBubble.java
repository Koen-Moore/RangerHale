package org.projectiles;

import static java.awt.Color.red;
import static java.lang.Math.*;

import java.awt.image.BufferedImage;

import org.enemies.Enemy;
import org.players.Player;
import org.resources.*;
import org.rooms.Room;
import org.walls.DamageableWall;
import org.walls.Wall;

public class EnemyWaterBubble extends Projectile{
	public static BufferedImage[]ani=waterbubbleani;
	public int counter=(int)round(1000*random());
	public EnemyWaterBubble(float X,float Y,float vx,float vy){
		image=ani[0];
		dead=false;
		color=red;
		//vx=vy=0;
		x=X-10;y=Y-10;vX=vx*2f;vY=vy*2f;
		life=lifeCapacity=-1-(int)round(150*random());
		h=20;w=20;
		if(vX==0&&vY==0){
			vX=5*(float)random()-2.5f;
			vY=5*(float)random()-2.5f;
		}
		//int counter=0;
		//while(hypot(vX,vY)<1&&counter<10){counter++;vX*=2;vY*=2;}
		synchronized(sync){livePros++;}
	}
	public void run(){
		//boolean frame=Clock.frame;
		counter++;
		if(life!=0){
			life--;
			image=ani[counter/5%4];
			if(life>=0)image=ani[8];
			if(life>2)image=ani[7];
			if(life>4)image=ani[6];
			if(life>6)image=ani[5];
			if(life>8)image=ani[4];
			//Clock.waitFor(frame=!frame);
			//if(Clock.dead)break;
			
			if (life<0){
				//vX*=.98;vY*=.98;
				x+=vX;y-=vY;
			}
			if(life==-200){vY=vX=0; life=10;x+=4-round(8*random());y+=4-round(8*random());}
			if(Collisions.collides(this, Player.player)){
    			Player.damage(3);
    			Player.vMultiplier=.00001f;
    			
			}
    		
    		if(life>=0)return;
			for (Wall wal:Room.walls){
    			//if (vY==0&&vX==0)break;
    			if (Collisions.collides(this, wal)){
    				vY=vX=0; life=10; x+=4-round(8*random());y+=4-round(8*random());
    				//AudioPack.playAudio("BExplosion2.wav",0.05);
					AudioPack.playClip(boom);
    				if (wal.damagable){ ((DamageableWall)wal).life-=5;
    					//if (Jump.kraidLife<=0&&Jump.countdown<0){Jump.countdown=500;
    					//AudioPack.playAudio("Ima Firen Mah Lazor!.wav",0.1);
    					//}
    				}
    			}
    		}
		//	out.println(life);
		}
		else dead=true;
	}
}
