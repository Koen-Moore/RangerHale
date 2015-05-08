package org.enemies;

import static java.lang.Math.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.items.Health;
import org.players.Player;
import org.projectiles.TentacleShot;
import org.resources.ImagePack;
import org.rooms.Room;

public class PentapusEasy extends Enemy{
	public BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("waterlevel/pentapus/pentattack1.png"),
		ImagePack.getImage("waterlevel/pentapus/pentattack7.png"),
		ImagePack.getImage("waterlevel/pentapus/pentattack6.png"),
		ImagePack.getImage("waterlevel/pentapus/pentattack5.png"),
		ImagePack.getImage("waterlevel/pentapus/pentattack4.png"),
		ImagePack.getImage("waterlevel/pentapus/pentattack3.png"),
		ImagePack.getImage("waterlevel/pentapus/pentattack2.png"),
		ImagePack.getImage("waterlevel/pentapus/pentangry.png"),
	};
	public int counter=(int)round(1000*random());
	public int hitDelay=0;
	public boolean angry;
	public PentapusEasy(int X,int Y){
		x=X;y=Y;
		w=200;h=250;
		life=lifeCapacity=400;
	}
	public void run(){
		counter++;
		
		if(hitDelay>0)
			hitDelay--;
		
		Player p=Player.player;
		float dx=(p.x+p.w/2)-(x+w/2);
		float dy=(p.y+p.h/2)-(y+h/2);
		if(life<=0){
			Health.add(this,30);
			dead=true;
		}
		
		if(hitDelay==0&&abs(dx)<200&&p.y>y+50){
			hitDelay=24;
		}
		if(hitDelay==2&abs(dx)<200&&p.y>y+50){
			Player.damage(10);
			Player.vx=dx<0?-10:10;
			Player.vy+=2;
		}
		if(counter%150>125&&hitDelay==0){
			angry=true;
			if(random()<.02)Room.enemies.add(new Clam(x+8,y+10));
			if(random()>.98)Room.enemies.add(new Crab(x+8,y+10));
			if(random()>.99)Room.enemies.add(new Shark(x+8,y+10));
		}
		else angry=false;
		
		
		
		image=ani[angry?(7):(hitDelay==0?(0):(hitDelay/4))];
	}
	public void draw(Graphics g){
		g.drawImage(image,round(x)-100,round(y),null);
	}

}
