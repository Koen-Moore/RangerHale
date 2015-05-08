package org.projectiles;

import static java.awt.Color.red;
import static java.lang.Math.*;

import java.awt.image.BufferedImage;
import static java.lang.System.*;
import org.rooms.*;
import org.resources.AudioPack;
import org.resources.Element;
import org.resources.ImagePack;
import org.resources.VisibleObject;
import org.walls.DamageableWall;
import org.walls.Wall;
import org.enemies.*;

public class BigFireBall extends Projectile {
	public static BufferedImage[]ani=bigfireani;
	public BigFireBall(float X,float Y,float vx,float vy){
		image=ani[0];
		dead=false;
		color=red;
		if(vx<0)X-=20;
		x=X;y=Y;vX=vx*2.5f;vY=vy*2.5f;
		life=lifeCapacity=-1-(int)round(150*random());
		w=13;h=20;
		if(vX==0&&vY==0){
			vX=5*(float)random()-2.5f;
			vY=5*(float)random()-2.5f;
		}
		if(abs(vX)>abs(vY)){h=13;w=20;}
		//int counter=0;
		//while(hypot(vX,vY)<1&&counter<10){counter++;vX*=2;vY*=2;}
		if(abs(abs(atan((double)vY/vX))-PI/4)>PI/8)
			image=ani[abs(vX)>abs(vY)?(vX<0?0:1):(vY>0?2:3)];
		else{
			image=ani[vX>0?(vY<0?4:6):(vY<0?5:7)];
			if(vy>0)y-=12;
			w=h=20;
		}
		synchronized(sync){livePros++;}
	}
	public void run(){
		//boolean frame=Clock.frame;
		if(life!=0){
			if(life>0)image=ani[13];
			if(life>4)image=ani[12];
			if(life>8)image=ani[11];
			if(life>12)image=ani[10];
			if(life>16)image=ani[9];
			if(life>20)image=ani[8];
			//Clock.waitFor(frame=!frame);
			//if(Clock.dead)break;
			
			if (life<0){
				vX*=.98;vY*=.98;
				x+=vX;y-=vY;
			}
			if(life==-200){vY=vX=0; life=10; w=h=16;image=ani[8];x-=round(10*random());y-=round(10*random());}
			life--;
			for (int i=0;i<Room.walls.size();i++){
				Wall wal=Room.walls.get(i);
    			if (vY==0&&vX==0)break;
    			if ((x<wal.x+wal.w&&x+w>wal.x)&&(y<wal.y+wal.h&&y+h>wal.y)){
    				vY=vX=0; life=20; w=h=50;image=ani[13];x-=round(25*random());
    				y-=round(25*random());
    				//AudioPack.playAudio("BExplosion2.wav",0.05);
					AudioPack.playClip(boom);
    				if (wal.damagable){w=h=16; ((DamageableWall)wal).life-=15;
    					//if (Jump.kraidLife<=0&&Jump.countdown<0){Jump.countdown=500;
    					//AudioPack.playAudio("Ima Firen Mah Lazor!.wav",0.1);
    					//}
    				}
    			}
    		}
    		synchronized(Room.enemies){
    		for (VisibleObject en:Room.enemies){
    			if((x<en.x+en.w&&x+w>en.x)&&(y<en.y+en.h&&y+h>en.y)){
    				if(life<0||life>2)
	    				((Enemy)en).damage(Element.FIRE,4);image=ani[13];
	    			if(vX!=0||vY!=0){
	    				//x=en.x+en.w/2;
	    				life=20; w=h=50; 
	    				//y=en.y+en.h/2;
	    				x-=.7*vX+round(25*random());
	    				y-=.7*vY+round(25*random());
	    				vY=vX=0; 
	    				//AudioPack.playAudio("BExplosion2.wav",0.1);
						AudioPack.playClip(boom);
						break;
	    			}
    			}
    		}
    		}
		}
		else dead=true;
	}
}