package org.walls;

import java.awt.Graphics;

import org.resources.ImagePack;
import static java.lang.Math.*;
import java.awt.image.*;

public class CloudPlatform extends Wall{
	private int size;
	private static final BufferedImage[]pics=new BufferedImage[]{
		ImagePack.getImage("platforms/CloudLeft.png"),
		ImagePack.getImage("platforms/MidCloud.png"),
		ImagePack.getImage("platforms/CloudRight.png"),
		
	};
	public CloudPlatform(int a,int b,int c){
		super(a,b+1,10+20*c,8);
		size=c;
	}
	public void draw(Graphics g){
		g.drawImage(pics[0],round(x),round(y)-1,null);
		for(int i=0;i<size;i++){
			g.drawImage(pics[1],round(x)+5+20*i,round(y)-1,null);
		}
		g.drawImage(pics[2],round(x)+5+20*size,round(y)-1,null);
	}
}
