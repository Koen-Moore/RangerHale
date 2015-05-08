package org.walls;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Math.*;

import org.resources.ImagePack;

public class VerticalShipWall extends Wall {
	public static final BufferedImage[] i=new BufferedImage[]{
		ImagePack.getImage("backgrounds/shipWall.png"),
		ImagePack.getImage("backgrounds/shipWallEnd.png"),
	};
	public VerticalShipWall(int a,int b,int height){
		super(a,b,9,height);
		image=i[0];
	}
	public void draw(Graphics g){
		for(int a=round(y);a<y+h;a+=20)
			g.drawImage(image,round(x),a,null);
		g.drawImage(i[1],round(x),round(y)+h,null);
	}
}
