package org.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.projectiles.EnemyLaserBullet;
import org.projectiles.TentacleShot;
import org.resources.Collisions;
import org.resources.ImagePack;
import org.resources.VisibleObject;
import org.rooms.Room;
import org.walls.Wall;

import static java.lang.Math.*;

public class FireBossHard extends Enemy{
	int deathDelay=-1;
	int hitDelay=0;
	boolean left;
	int proDelay=0;
	int counter;
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("firelevel/bobStand.png"),
		ImagePack.getImage("firelevel/bobStandR.png"),
		ImagePack.getImage("firelevel/bobHit.png"),
		ImagePack.getImage("firelevel/bobShootL.png"),
		ImagePack.getImage("firelevel/bobShootR.png"),
		ImagePack.getImage("firelevel/bobMove.png"),
		ImagePack.getImage("firelevel/bobMoveR.png"),
		ImagePack.getImage("firelevel/bobDeath.png"),
	};
	public FireBossHard(int X,int Y){
		x=X;y=Y;w=40;h=60;
		life=lifeCapacity=1000;
	}
	public void run(){
		counter++;
		if(hitDelay>0)
			hitDelay--;
		if(proDelay>0)
			proDelay--;
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
		float dx=(p.x+p.w/2)-(x+w/2);
		//float dy=(p.y+p.h/2)-(y+h/2);
		if(life>300){
			
			if(hitDelay==0&&abs(dx)<60&&p.y+p.h>y+50){
				hitDelay=10;
			}
			if(hitDelay==5&abs(dx)<60&&p.y+p.h>y+50){
				Player.damage(10);
				Player.vx=dx<0?-9:9;
			}
			if(hitDelay==0&&counter%60<30){
				vx=5f*(dx<0?-1:1);
			}
			if(Collisions.collides(new Wall(x,0,w,Room.HEIGHT), p)){
				vx=0;
			}
			
		}
		else if(life>0){
			

			if(counter%60<30){
				vx=5f*(dx<0?-1:1);
			}
			else{
				left=dx<0;

				if(counter%18==0){
					float a=(x)+(17),b=y+15;
					Room.projectiles.add(new EnemyLaserBullet(x+8,y+15,(dx<0?-5:5),(float)((dx<0?5:-5)*(p.y+p.h/2-b)/(p.x+p.w/2-a))));
					Room.projectiles.add(new EnemyLaserBullet(x+23,y+15,(dx<0?-5:5),(float)((dx<0?5:-5)*(p.y+p.h/2-b)/(p.x+p.w/2-a))));
					proDelay=10;
				}
			}
			if(Collisions.collides(new Wall(x,0,w,Room.HEIGHT), p)){
				vx=0;
			}
			
		}
		if(hitDelay>0)vx=0;
		if(vx>0)left=false;
	    else if(vx<0)left=true;
		vy+=.5;
	    
	    vMult1();
	    for(Wall wal:Room.walls){
	    	if(Collisions.willCollide(this, wal, vx, 0)){
	    		vx=0;
	    	}
	    	if(Collisions.willCollide(this, wal, vx, vy)){
	    		vy=0;
	    	}
	    }
	    x+=vx;
	    y+=vy;
	    vMult2();
	    
	    if(life>100)
	    	image=ani[vx==0?(hitDelay==0?(left?(0):(1)):(2)):(vx<0?(5):(6))];
	    else if(life>0)
	    	image=ani[vx==0?(dx<0?3:4):(vx<0?(5):(6))];
	    else 
	    	image=ani[7];
	}
	public void draw(Graphics g){
		g.drawImage(image, round(x)-20, round(y), null);
	}

}
