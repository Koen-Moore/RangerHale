package org.walls;

import java.awt.Graphics;
import static java.lang.Math.*;
import java.awt.image.BufferedImage;

import org.resources.ImagePack;

public class FirePlatform extends Wall{
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("platforms/fireplatform1.png"),
		ImagePack.getImage("platforms/fireplatform2.png"),
		ImagePack.getImage("platforms/fireplatform3.png"),
		ImagePack.getImage("platforms/fireplatform4.png"),
	};
	public int yoff=0;
	public FirePlatform(int x,int y,int i){
		super(x,y,0,0);
		image=ani[i];
		switch(i){
		case 0:w=33;h=18;break;
		case 1:w=101;h=46;break;
		case 2:w=88;h=46;break;
		case 3:w=71;h=30;break;
		}
	}
	public void draw(Graphics g){
		g.drawImage(image,round(x),round(y-yoff),null);
	}
}
