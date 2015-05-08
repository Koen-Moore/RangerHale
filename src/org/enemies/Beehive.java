package org.enemies;
import static java.lang.Math.*;
import org.items.*;
import org.resources.*;
import org.rooms.Room;
import org.walls.Wall;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Beehive extends Enemy{
	public static final BufferedImage ani=ImagePack.getImage("Beehive.png");
	int counter=(int)(100*random());
	public Wall collisions;
	public Beehive(int X,int Y){
		x=X;y=Y;
		w=16;h=20;
		image=ani;
		life=lifeCapacity=30;
		Room.walls.add(collisions=new Wall(x+1,y+1,w-2,h-2));
	}
	public void run(){
		if(life<0){
			for(int a=3+(int)round(7*random());a>0;a--)
				Room.items.add(new Health(x,y,0,0));
			dead=true;
			Room.walls.remove(collisions);
		}
		counter++;
		if(counter%50<25){
			if(random()<.0025)Room.enemies.add(new Bee(x+8,y+10));
			if(random()>.98)Room.enemies.add(new SmallBee(x+8,y+10));
		}
	}
	public void draw(Graphics g){
		int offset=(counter%50<25?0:1)*(int)round(10*random()-5);
		g.drawImage(ani,round(x)+offset,round(y)+offset,null);
	}
}
