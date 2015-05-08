package org.enemies;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.resources.Collisions;
import org.resources.ImagePack;
import org.walls.*;
import org.rooms.*;
import static java.lang.Math.*;
public class PurpleFly extends Enemy{
	private int counter=(int )round(1000*random());
	private static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("PurpleFly1.png"),
		ImagePack.getImage("PurpleFly2.png"),
		ImagePack.getImage("PurpleFly1r.png"),
		ImagePack.getImage("PurpleFly2r.png"),
	};
	public PurpleFly(int X,int Y){
		x=X;y=Y;w=38;h=26;
		life=lifeCapacity=50;
	}
	public void run(){
		if(life<=0){
			dead=true;
			Health.add(x+19,y+13,5);
			return;
		}
		Player p=Player.player;
		double px=x+w/2-(p.x+p.w/2);
		double py=y+h/2-(p.y+p.h/2);
		double hx=(px<0?.5:-.5);
		double hy=(py<0?.5:-.5);
		double dx=0,dy=0;
		if(counter%10==0){
   			dx=(float)((hx>0?-1:1)*(Math.random()*5-1.6));
   		}
   		if(counter%10==0){
   			dy=(float)((hy>0?-1:1)*(Math.random()*5-1.6));
   		}
		image=ani[(counter/5%2==0?0:1)+(hx<0?0:2)];
		counter++;
		vx+=hx;
		vy+=hy;
   		vy+=(counter%10<5?-1:1 );
		vx=max(-4,min(vx,4));
		vy=max(-4,min(vy,4));
		if(Collisions.willCollide(this, p, vx+dx, vy+dy)){
			//vx=(float)((px<0?10:-10)*abs(cos(atan(py/px))));
			//vy=(float)((py<0?10:-10)*abs(sin(atan(py/px))));
			vx=-2*vx;
			vy=-2*vy;
			dx=dy=0;
			
			Player.damage(4);
		}
		for(Wall wal:Room.walls){
			if(Collisions.willCollide(this, wal, vx+dx, 0)){
				vx=-.2f*vx;dx=0;
			}
			if(Collisions.willCollide(this, wal, 0, vy+dy)){
				vy=-.2f*vy;dy=0;
			}
		}
		x+=vx+dx;
		y+=vy+dy;
		
	}

}
