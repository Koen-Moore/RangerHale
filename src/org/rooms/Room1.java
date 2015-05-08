package org.rooms;

import static java.awt.Color.RED;
import static java.awt.Color.black;
import static java.awt.Color.blue;
import static java.awt.Color.darkGray;
import static java.awt.Color.gray;
import static java.awt.Color.red;
import static java.lang.Math.abs;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static java.lang.System.out;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.HealthBar;
import org.enemies.Barrel;
import org.enemies.Bee;
import org.enemies.Beehive;
import org.enemies.Crab;
import org.enemies.Fireman;
import org.enemies.Lavaspot;
import org.enemies.MuddaBudda;
import org.enemies.Shark;
import org.enemies.SmallBee;
import org.players.Player;
import org.projectiles.Fireball;
import org.resources.ImagePack;
import org.resources.Position;
import org.resources.ThreadList;
import org.walls.DamageableWall;
import org.walls.DamagingWall;
import org.walls.Wall;
public class Room1 extends Room{
private static BufferedImage back=ImagePack.getImage("backgrounds/metroid-kraid.png");
	public static DamageableWall kraid;
	public static int countdown=-1;
	public static int kraidLife=100;
	//countdown=-1;kraidLife=100;
	public void load(){
		//vy=0;vx=0;x=0;y=0;
		countdown=-1;kraidLife=100;
		spriteW=19; spriteH=38;
		HEIGHT=500; WIDTH=800;
		spriteX=0; spriteY=375-spriteH;
		cameraX=100; cameraY=100;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		out.println("Constructing Room");
		int xof=400,yof=100;
    	walls = new ThreadList<Wall>(Arrays.asList(new Wall[]{
    		new Wall(0,0,0,HEIGHT),
    		new Wall(0,0,WIDTH,0),
    		new Wall(0,HEIGHT,WIDTH,500),
    		new Wall(WIDTH,0,0,HEIGHT),
    		new Wall(0,325,3,53,black),
    		new Wall(3,325,10,3,black),
    		new Wall(0,375,50,3,black),
    		new Wall(50,450,30,3,red),
    		new Wall(100,300,30,3,new Color(30,50,10)),
    		new Wall(xof+50,yof+350,30,3,red),
    		new Wall(xof+80,yof+320,30,3,darkGray),
    		new Wall(xof+110,yof+290,30,3,blue),
    		new Wall(xof+140,yof+260,30,3,gray),
    		new Wall(xof+170,yof+230,30,3,new Color(0,50,0)),
    		new Wall(xof+110,yof+170,30,3,red),
    		new Wall(xof+50,yof+155,60,3,blue),
    		kraid=new DamageableWall(xof+290,yof+120,40,50,null),
    		new DamagingWall(xof+255,yof+195,40,75,null),
    		new DamagingWall(xof+220,yof+195,40,30,null),
    		new DamagingWall(xof+273,yof+153,40,60,null),
    		new DamagingWall(xof+220,yof+270,120,130,null),
    		new DamagingWall(xof+330,yof+350,70,50,null)
    	}));out.println("walls made");

		background=new Background(back);
		background.addAll(walls);
		out.println("all added");
		out.println(background.image.getType());
		back=background.image;
		hud.add(new HealthBar(kraid,100,3,Position.TOP_RIGHT,RED));
    	
    	/*enemies.add(new JoeMamma(300,470,-3,-3));
		enemies.add(new JoeMamma(30,470,-3,3));
		enemies.add(new JoeMamma(300,40,3,3));
    	enemies.add(new JoeMamma(300,400,3,-3));
    	enemies.add(new JoeMamma(350,400,-3,3));
    	enemies.add(new JoeMamma(500,10,3,-3));*/
		for(int i=50;i<500;i+=15){
    		enemies.add(new Barrel(i,HEIGHT-20));
    	}

    	
    	for(int i=100;i<=450;i+=50){
    		enemies.add(new MuddaBudda(i,HEIGHT-24-20));
    	}
    	
    	enemies.add(new Beehive(350,120));
    	enemies.add(new Beehive(300,120));
    	enemies.add(new Bee(100,300));
    	enemies.add(new SmallBee(100,310));
    	enemies.add(new Fireman(100,400));
    	for(int i=100;i<200;i+=20){
    		enemies.add(new Crab(100,450));
    	}
    	enemies.add(new Lavaspot(50,400));
    	enemies.add(new Shark(200,400));
    	enemies.add(new Shark(100,400));
    	enemies.add(new Shark(300,400));
    	enemies.add(new Shark(200,200));
    	enemies.add(new Shark(100,200));
    	enemies.add(new Shark(300,200));
    	
    	//enemies.add(new Popeslug(300));
    	
	}
	//public float vy=0,vx=0,x=0,y=0;
	public boolean isDead(){
		if(countdown<0&&(kraid.life<0)){
			countdown=400;
			//AudioPack.playAudio("Ima Firen Mah Lazor!.wav",0.1);
		}
		if(abs(countdown)%50>30){
		}
		else{
			cameraXOff=cameraYOff=0;
		}
			countdown--;
		if(countdown>=0){
			cameraXOff=(int)round(10*random()-5);
			cameraYOff=(int)round(10*random()-5);
		}

		if (countdown>0&&countdown%4==0){
				Room.projectiles.add(new Fireball((float)(400+290+20f*random()),(float)(220+20f*random()),0.1f,0));
			}
		
		if(countdown==0||Player.player.life<=0)return true;
		return false;
	}
}