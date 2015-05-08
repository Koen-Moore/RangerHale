package org.rooms;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.HealthBar;
import org.items.FireTree;
import org.resources.*;
import org.walls.*;
import org.enemies.*;

public class Room3 extends Room{
	public static final BufferedImage cloud=ImagePack.getImage("cloud.png");
	public static final BufferedImage mountain=ImagePack.getImage("backgrounds/MountainBACK.png"),
			cliff=ImagePack.getImage("cliff.png"),
			door=ImagePack.getImage("earthlevel/caveDoor.png"),
			door2=ImagePack.getImage("earthlevel/caveDoor2.png");
	public void load(){
		HEIGHT=800;WIDTH=800;
		spriteX=0;spriteY=HEIGHT-38;
		cameraX=100;cameraY=100;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
	    	new Wall(0,0,0,HEIGHT),
	    	new Wall(0,0,WIDTH,0),
	    	new Wall(0,HEIGHT,WIDTH,500),
	    	new Wall(WIDTH,0,0,HEIGHT)
		}));
		exit=new FireExit(WIDTH-40,HEIGHT-80);
		walls.add(new FireDoor(0,HEIGHT-80));
		walls.add(new FirePlatform(200,700,0));
		//background.add(new Wall(0,500,cliff));
		items.add(new FlyingPlatform(100,700));
		walls.add(new Log(300,700,6));
		//foreground.add(new Water(0,780,800,20));
		background=new Background();
		background.fillBack(new Color(75,252,255));
		background.addToMiddle(cloud, 100);
		background.tessellateOnBottom(mountain);
		background.addAll(walls);
		backdrop.add(new CloudDoor(100,600,4));
		for(int i=0;i<400;i+=100){
			//enemies.add(new Clam(i+50,400));
			//enemies.add(new Clam(i,400));
			//enemies.add(new PurpleFly(i,500));
		}
		for(int i=150;i<400;i+=20){
			//enemies.add(new Larva(i,400));
		}
		//enemies.add(new Snail(200,400));
		//enemies.add(new PurpleBug(100,300));

		//enemies.add(new Hoopla(600,720));
		enemies.add(new TentacleMonster(300,HEIGHT-80));
		Enemy b=new BossBeeHard(100,300);
		enemies.add(b);
		hud.add(new HealthBar(b ,200,3,Position.TOP_RIGHT,Color.red ));
		//enemies.add(new FirePuff(100,788,Integer.MAX_VALUE));
	}
}
