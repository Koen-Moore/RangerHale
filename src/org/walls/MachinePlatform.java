package org.walls;

import java.awt.Graphics;

import org.resources.ImagePack;
import static java.lang.Math.*;
import java.awt.image.*;

public class MachinePlatform extends Wall{
	private int size;
	private final static BufferedImage[]pics=new BufferedImage[]{
		ImagePack.getImage("platforms/machinePlatformLeft.png"),
		ImagePack.getImage("platforms/machinePlatformMid.png"),
		ImagePack.getImage("platforms/machinePlatformRight.png"),
		
	};
	public MachinePlatform(int a,int b,int c){
		super(a,b,12+18*c,8);
		size=c;
	}
	public void draw(Graphics g){
		g.drawImage(pics[0],round(x),round(y),null);
		for(int i=0;i<size;i++){
			g.drawImage(pics[1],round(x)+6+18*i,round(y),null);
		}
		g.drawImage(pics[2],round(x)+6+18*size,round(y),null);
	}
}
