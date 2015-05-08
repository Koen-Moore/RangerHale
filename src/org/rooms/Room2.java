package org.rooms;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.enemies.Glorble;
import org.items.Health;
import org.resources.ImagePack;
import org.resources.ThreadList;
import org.walls.Wall;

public class Room2 extends Room{
	public static final BufferedImage stars=ImagePack.getImage("backgrounds/redClouds.png");
	public static final BufferedImage tree=ImagePack.getImage("Tree.png");
	public static final	BufferedImage tree2=ImagePack.getImage("Tree2.png");
	public static final	BufferedImage tree3=ImagePack.getImage("Tree3.png");
	public static final	BufferedImage tree4=ImagePack.getImage("Tree4.png");
	public void load(){
		exit=new Wall(799,0,1,800);
		HEIGHT=800;WIDTH=800;
		spriteX=0;spriteY=0;
		cameraX=100;cameraY=100;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
	    	new Wall(0,0,0,HEIGHT),
	    	new Wall(0,0,WIDTH,0),
	    	new Wall(0,HEIGHT,WIDTH,500),
	    	new Wall(WIDTH,0,0,HEIGHT)
		}));
		//enemies.add(new Tredbot(50,750));
		background=new Background();
		background.randomTessellate(stars,8,8);
		background.addToGround(tree4,5);
		background.addToGround(tree3,5);
		background.addToGround(tree2,5);
		background.addToGround(tree,5);
		for(int x=100;x<400;x+=20){
			items.add(new Health(50,70,0,0));
		}
		for(int x=100;x<700;x+=50){
			enemies.add(new Glorble(x,700));
		}
	}
}