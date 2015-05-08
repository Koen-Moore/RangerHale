package org.rooms;

import static java.awt.Color.white;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.BackgroundSystem.Background;
import org.HUDSystem.HealthBar;
import org.HUDSystem.HudTitle;
import org.enemies.*;
import org.items.EscapePod;
import org.items.FireTree;
import org.main.RoomRunner;
import org.players.Player;
import org.resources.ImagePack;
import org.resources.KeyList;
import org.resources.Position;
import org.resources.ThreadList;
import org.resources.VisibleObject;
import org.walls.*;

public class FireBossRoomEasy extends Room{
	public static final BufferedImage cave1=ImagePack.getImage("backgrounds/caveback.png");
	public static final BufferedImage cave2=ImagePack.getImage("backgrounds/caveback2.png");
	public void load(){
		RoomRunner.saveGame();
		exit=new Wall(799,0,1,500);
		HEIGHT=500;WIDTH=800;
		spriteX=0;spriteY=160-38;
		cameraX=100;cameraY=100;
		visibleHEIGHT=500;visibleWIDTH=500;
		startLeft=false;
		walls=new ThreadList<Wall>(Arrays.asList(new Wall[]{
	    	new Wall(0,0,0,HEIGHT),
	    	new Wall(0,0,WIDTH,0),
	    	new Wall(0,HEIGHT,WIDTH,HEIGHT),
	    	new Wall(WIDTH,0,0,HEIGHT),
	    	new FirePlatform(30,360,2),
	    	new FirePlatform(230,360,1),
	    	new FirePlatform(430,360,1),
	    	new FirePlatform(630,360,2),
		}));
		walls.add(new FireDoor(0,80));
		walls.add(new FirePlatform(0,160,3));
		background=new Background();
		background.fillBack(new Color(68,37,18));
		background.tessellateOnBottom(cave1);
		background.tessellateOnTop(cave2);
		background.addAll(walls);
		boss=new FireBossEasy(300,0);
		enemies.add(boss);
		hud.add(new HealthBar(boss ,200,3,Position.TOP_RIGHT,Color.red ));
		for(int x=100;x<=700;x+=100){
			backdrop.add(new FireTree(x,500-56));
		}
		ep=new EscapePod[]{
				new EscapePod(325,450,1),
				new EscapePod(375,450,2),
				//new EscapePod(425,450,3),
				new EscapePod(475,450,4),
			};
			
			
		}
		public Enemy boss;
		public EscapePod []ep;
		public EscapePod selected;
		public int state=0;
		public HudTitle ht;
		public boolean changed=false;
		public int piccount=160;
		public static BufferedImage[]ani=new BufferedImage[]{
			ImagePack.getImage("menu stuff/starBack.png"),ImagePack.getImage("backgrounds/volcanicplanet.png")
		};
		public boolean isDead(){
			if(state==0){
				if(boss.dead){
					Player.unlockedProjectiles=RoomRunner.appendArray(Player.unlockedProjectiles, new int[]{5});
					hud.add(ht=new HudTitle("USE -SHIFT- AND -SPACE- TO SCROLL THROUGH WEAPONS",100,200,Color.white));
					state=1;
				}
			}
			if(state==1){
				if(KeyList.keyboard[32]){
					changed=true;
				}
				if(changed&&(KeyList.keyboard[37]||KeyList.keyboard[38]||KeyList.keyboard[39]||KeyList.keyboard[40])){
					state=2;
					ht.contents="PRESS -ENTER- WHILE OVER AN ESCAPE POD";
					for(VisibleObject e:ep){
						backdrop.add(e);
					}
				}
			}
			if(state==2){
				for(EscapePod e:ep){
					if(e.life==-1){
						e.leaving=true;
						selected=e;
						state=3;
						visibleObjects.remove(playerlist);
						break;
					}
				}
			}
			
			if(state==3&&selected.dead){
				state=4;
				hud.add(new Wall(ani[0]));
				hud.add(new Wall(300,120,ani[1]));
				hud.add(new HudTitle("LOADING...",Position.MIDDLE,white));
			}
			if(state==4){
				piccount--;
			}
			if(piccount==0){

				switch(selected.type){
				case 1:RoomRunner.loadFireLevel(false);break;
				case 2:RoomRunner.loadWaterLevel(false);break;
				case 3:RoomRunner.loadRockLevel(false);break;
				case 4:RoomRunner.loadAirLevel(false);break;
				}
				return true;
			}
			
			return false||Player.player.life<=0;
		}
	}
