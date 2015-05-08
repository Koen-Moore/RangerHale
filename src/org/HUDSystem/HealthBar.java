package org.HUDSystem;

import static java.lang.Math.round;
import static java.lang.System.out;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


import org.resources.Collisions;
import org.resources.LivingObject;
import org.resources.Position;
import org.resources.VisibleObject;
import org.rooms.Room;

public class HealthBar extends VisibleObject{
	public LivingObject target;
	public Position position;
	boolean lockedWidth;
	float pixelsPerHealth;
	public HealthBar(LivingObject lo,int W,int H,Position p,Color c){
		//image=new BufferedImage(W,H,2);
		target=lo;out.println(target);
		w=W;h=H;
		position=p;
		color=c;
		Collisions.alignWith(this,Room.hud,p);
		lockedWidth=true;
	}
	public HealthBar(LivingObject lo,float pPH,int H,Position p,Color c){
		target=lo;out.println(target);
		pixelsPerHealth=pPH;h=H;
		position=p;
		color=c;
		Collisions.alignWith(this,Room.hud,p);
		lockedWidth=false;
	}
	public HealthBar(LivingObject lo,int a,int b,int W,int H,Color c){
		target=lo;
		x=a;y=b;
		w=W;
		h=H;
		color=c;
		lockedWidth=true;
	}
	public void draw(Graphics g){
		g.setColor(color);
		if(!lockedWidth)
			w=round(1+pixelsPerHealth*target.lifeCapacity);
		g.drawRect(-1+round(x), round(y), w, h-1);
		//out.println(Player.player.life+" "+ target.life+" "+((w-1)*(target.life/target.lifeCapacity)));
		g.fillRect(round(x), round(y)+1, (int)((w-1)*((double)target.life/target.lifeCapacity)), h-2);
	}
	/*public void reload(){
		//image=new BufferedImage(w,h,2);
		Graphics g=image.createGraphics();
		g.setColor(color);
		g.drawRect(-1, 0, w, h-1);
		//out.println(Player.player.life+" "+ target.life+" "+((w-1)*(target.life/target.lifeCapacity)));
		g.fillRect(0, 0, (int)((w-1)*((double)target.life/target.lifeCapacity)), h);
		g.dispose();
	}*/
}
