package org.walls;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Math.*;
import org.resources.ImagePack;
import org.rooms.Room;


public class FlyingPlatform extends Wall implements Runnable{
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("platforms/Flyplatform1.png"),
		ImagePack.getImage("platforms/Flyplatform2.png"),
		ImagePack.getImage("platforms/Flyplatform3.png"),
		ImagePack.getImage("platforms/Flyplatform4.png"),
	};
	public int counter=0;
	public FlyingPlatform(int x,int y){
		super(x,y,61,4);
		Room.walls.add(this);
	}
	public void run(){
		counter++;
		image=ani[counter/3%4];
	}
	public void draw(Graphics g){
		g.drawImage(image,round(x),round(y),null);
	}
	
	
	
}
