package org.enemies;

import static java.lang.Math.min;
import static java.lang.Math.random;

import java.awt.image.BufferedImage;

import org.items.Health;
import org.resources.Element;
import org.resources.ImagePack;
import org.rooms.Room;

public class Popeslug extends Enemy{
	private static volatile BufferedImage[][]ani=new BufferedImage[][]{
		{
			ImagePack.getImage("Popeslug1.png"),
			ImagePack.getImage("Popeslug2.png"),
			ImagePack.getImage("Popeslug3.png"),
			ImagePack.getImage("Popeslug4.png"),
			ImagePack.getImage("Popeslug5.png"),
			ImagePack.getImage("Popeslug6.png"),
			ImagePack.getImage("Popeslug7.png"),
			ImagePack.getImage("Popeslug8.png")
		},
		{
			

		}
	};
	//private double vx=0,vy=2;
	//private boolean left=true;
	private int counter=(int)(300*Math.random());
	public Popeslug(int X){
		element=Element.NORMAL;
		image=ani[0][0];
		w=17;h=22;
		x=X;y=Room.HEIGHT-h;
		life=lifeCapacity=4;
	}
	public void run(){
		if(life<=0){
			for(int d=(int)(4*random());d>0;d--){
   				Room.items.add(new Health(x+w/2,y+h/2,vx,vy));
   			}
			dead=true;return;
		}
		counter++;
		image=ani[0][min((counter%120)/5,7)];
		//image=ani[0][7];
		h=image.getHeight();
		y=Room.HEIGHT-h;
	}
}