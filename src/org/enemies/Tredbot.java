package org.enemies;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.projectiles.*;
import org.resources.Collisions;
import org.resources.Element;
import org.resources.ImagePack;
import org.resources.VisibleObject;
import org.rooms.*;
import org.walls.*;

public class Tredbot extends Enemy{
	private static volatile BufferedImage[][] ani = { 
		{ 
		    ImagePack.getImage("Robot/tredBot3.png"),
		    ImagePack.getImage("Robot/tredBot2.png"),
		    ImagePack.getImage("Robot/tredBot1.png"),
		},{
		    ImagePack.getImage("Robot/tredBot3r.png"),
		    ImagePack.getImage("Robot/tredBot2r.png"),
		    ImagePack.getImage("Robot/tredBot1r.png"), 
		} 
	};

	  //private boolean left = true;
	private int counter = (int)(300.0D * Math.random());
	private int piccounter;
	boolean left=true;
	public Tredbot(int X, int Y) { 
		this.element = Element.FIRE;
		this.w = 17; this.h = 23;
		this.x = X; this.y = Y;
		this.image = ani[0][0];
		this.life = (this.lifeCapacity = 100); 
	}

	public void run() {
		if (this.life <= 0) {
			Health.add(this, 10);
			this.dead = true;
			return;
	    }
		counter++;
	    image = ani[left?0:1][(piccounter % 15 / 5)];
	    Player p=Player.player;
	    if(counter%120<60){
	    	if(p.y<y+h&&p.y+p.h>y&&counter%20==0)
	    		Room.projectiles.add(new EnemyLaserBullet(x+(left?0:w),y+4,p.x>x?1:-1,0));
	    	piccounter++;
	    	vx=(x+w/2<p.x+p.w/2?3:-3);
	    }
	    else
	    	vx=0;
	    
	    vy+=.5;
	    if(vx>0)left=false;
	    else if(vx<0)left=true;
	    
	    vMult1();
	    for(Wall wal:Room.walls){
	    	if(Collisions.willCollide(this, wal, vx, 0)){
	    		vx=0;
	    	}
			if (Collisions.willCollide(this, wal, vx, vy)){
    			if (vy>0){y=wal.y-h;}
    			else{y=wal.y+wal.h;}
				vy=0;
			}
	    }
	    for(VisibleObject wal:Room.enemies){
	    	if(wal==this)continue;
	    	if(Collisions.willCollide(this, wal, vx, vy)){
	    		vy=vx=0;
	    	}

	    }
	    x+=vx;
	    y+=vy;
	    vMult2();
	    
	}
}
