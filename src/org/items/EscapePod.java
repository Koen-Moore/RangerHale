package org.items;

import java.awt.Graphics;

import static java.lang.Math.*;

import java.awt.image.BufferedImage;

import org.players.Player;
import org.resources.Collisions;
import org.resources.ImagePack;
import org.resources.KeyList;
import org.resources.LivingObject;
import org.resources.VisibleObject;
import org.rooms.Room;

public class EscapePod extends LivingObject implements Runnable{
	public int yoff;
	public boolean leaving=false;
	public int deathDelay=-1;
	public int type;
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("escape pod.png"),
		ImagePack.getImage("firePod.png"),
		ImagePack.getImage("waterPod.png"),
		ImagePack.getImage("rockPod.png"),
		ImagePack.getImage("windPod.png"),
		
	};
	public EscapePod(int X,int Y,int t){
		type=t;
		x=X;y=Y;w=24;h=50;image=ani[type];
		yoff=Math.round(-y);
	}
	public void run(){
		if(KeyList.keyboard[10]&&Collisions.collides(this,Player.player)&&yoff==0){
			life=-1;
		}
		if(yoff<0&&life==0){
			yoff+=10;
			yoff=min(yoff,0);
		}
		if(leaving){
			Room.cameraXOff=(int)round(10*random()-5);
			Room.cameraYOff=(int)round(10*random()-5);
			yoff-=10;
			if(-yoff>y&&deathDelay==-1){
				deathDelay=100;
			}
		}

		if(deathDelay>0){
			deathDelay--;
		}
		if(deathDelay==0){
			Room.cameraYOff=Room.cameraXOff=0;
			dead=true;
		}
	}
	public void draw(Graphics g){
		if(deathDelay==-1)
			g.drawImage(image,round(x),round(y)+yoff,null);
	}
}
