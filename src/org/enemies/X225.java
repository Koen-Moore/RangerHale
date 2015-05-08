package org.enemies;

import java.awt.image.*;
import static java.lang.Math.*;
import org.players.Player;
import org.projectiles.*;
import org.resources.*;
import org.rooms.Room;
import org.walls.Wall;

public class X225 extends Enemy{
	public int[]projectiles;
	public int curPro;
	public int deathDelay=-1;
	public int proDelay=0;
	public int counter=0;
	public int standDelay=0;
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("Robot/Evil robomoveL.png"),
		ImagePack.getImage("Robot/Evil roboLookR.png"),
		ImagePack.getImage("Robot/Evil roboLookL.png"),
		ImagePack.getImage("Robot/Evil robomoveR.png"),
		ImagePack.getImage("Robot/Evil robostanding.png"),
		ImagePack.getImage("Robot/deadRobot.png")
	};
	public X225(float a,float b,int[]c){
		x=a;y=b;projectiles=c;
		w=11;h=25;
		life=lifeCapacity=500;
		curPro=projectiles[projectiles.length-1];
	}
	public void run(){
		if(life<=0&&deathDelay<0){
			deathDelay=100;
			//vy=-6f;
			h=7;
			image=ani[4];
		}
		if(deathDelay>0){
			deathDelay--;
			if(deathDelay<25)
				y+=vy;
		}
		if(deathDelay==0){
			vy+=.5;
			if(y+vy+h>Room.HEIGHT){
				y=Room.HEIGHT-h;
				image=ani[5];
				vy=0;
			}
			y+=vy;
		}
		if(deathDelay>=0)
			return;
		if(standDelay>0)
			standDelay--;
		
		counter++;
		if(proDelay>0)proDelay--;
		
		
		Player p=Player.player;
		double dx=-(x+w/2)+(p.x+p.w/2),dy=-(y+h/2)+(p.y+p.h/2);
		if(counter%125==0){
			curPro=projectiles[max(min((int)(random()*projectiles.length),projectiles.length-1),0)];
		}
		if(proDelay==0&&counter%125<80){
			float svx=vMultiplier*vx/2 ,svy=vMultiplier*vy/2;
			float pvy=0,pvx=0;
			if(abs(abs(atan(dy/dx))-PI/4)>PI/8)
				if(abs(dx)>abs(dy))
					pvx=(dx<0?-3:3);
				else
					pvy=dy>0?-3:3;
			else{
				pvx=(dx<0?-3:3);
				pvy=dy>0?-3:3;
			}
			pvx+=svx;pvy+=svy;
			if(pvx-svx!=0&&pvy-svy!=0){
				pvx/=sqrt(2);
				pvy/=sqrt(2);
			}
			float px=x+(dx<0?0:w),py=y+h/2;
			
			proDelay=7;
			switch(curPro){
				case 0:{
					//proDelay=2;
					float a=(float)(pvx-svx);
					float b=(float)(pvy-svy);
					Room.projectiles.add(new EnemyBullet(px,py,a,b));
					break;
				}
				case 1:{
					
					Room.projectiles.add(new EnemyFireball(px,py-2,pvx-svx/2,pvy-svy/2));break;
				}
				case 2:Room.projectiles.add(new EnemyBigFireBall(px,py-6,pvx-svx/2,pvy-svy/2));break;
				
				case 3:Room.projectiles.add(new EnemyIceBullet(px,py-1,pvx,pvy));break;
				case 4:Room.projectiles.add(new EnemyWaterBubble(px,py,pvx,pvy));break;
				case 5:{
					proDelay=12;
					Room.projectiles.add(new EnemyRockProjectile(px,py-3,pvx,pvy+.5f));break;
				}
				case 6:{
					proDelay=12;
					Room.projectiles.add(new EnemySuperRock(px,py-8,pvx,pvy+.5f));break;
				}
				case 7:Room.projectiles.add(new EnemyAirBall(px,py-7,pvx-svx/2,pvy-svy/2));break;
				case 8:Room.projectiles.add(new EnemyLightning(dx,dy,4*(pvx-svx),4*(pvy-svy),5));break;
				case 9:{
					proDelay=2;
					Room.projectiles.add(new EnemyLaserBullet(px,py,pvx-svx,pvy-svy));
					break;
				}
			}
		}
		
		
		float pv=Math.hypot(dx, dy)>400?1f:-1f;
		vx+=(float)(pv*dx/Math.hypot(dx, dy));
		vy+=(float)(pv*dy/Math.hypot(dx, dy));
		
		if(standDelay==0&&(Collisions.collides(new Wall(x,0,w,Room.HEIGHT),p)||Collisions.collides(new Wall(0,y,Room.WIDTH,h),p))){
			standDelay=60;
		}
		if(standDelay==10){
			vx=(int)round(20*random()-10);
			vy=(int)round(20*random()-10);
		}
		
		if(standDelay>10)
			vx=vy=0;
		
		
		vMult1();
		if (Collisions.willCollide(this, p, vx, vy)){
			if (vx>0){x=p.x-w;}
			else{x=p.x+p.w;}
			vx=-vx;vy=-vy;
			Player.damage(5);
		}
		for (Wall wal:Room.walls){
			if (Collisions.willCollide(this, wal, vx, 0)){
    			if (vx>0){x=wal.x-w;}
    			else{x=wal.x+wal.w;}
				vx=-vx;
			}
			if (Collisions.willCollide(this, wal, vx, vy)){
    			if (vy>0){y=wal.y-h;}
    			else{y=wal.y+wal.h;}
				vy=-vy;
			}
    	}
   		x+=vx;
   		y+=vy;
   		vMult2();
		
		
		image=ani[(vx<0?0:2)+(x+w/2<p.x+p.w/2?1:0)];
		
	}
	public boolean preventsNextRoom(){
		return deathDelay!=0;
	}
}
