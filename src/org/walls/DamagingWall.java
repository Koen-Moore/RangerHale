package org.walls;

import java.awt.Color;

public class DamagingWall extends Wall{
	public DamagingWall(int x,int y,int w,int h,Color c){
		super(x,y,w,h,c);damages=true;
	}
}