package org.rooms;

import static java.awt.Color.blue;
import static java.awt.Color.white;
import static java.lang.System.exit;
import static java.lang.System.out;

import java.awt.image.BufferedImage;
import java.util.*;

import org.projectiles.ProjectileRunner;
import org.rendering.*;
import org.resources.Clock;
import org.resources.GarbageSuggester;
import org.resources.ImagePack;
import org.resources.Position;
import org.resources.VisibleObject;
import org.walls.Wall;
import org.HUDSystem.*;
import org.resources.*;
import org.enemies.*;
import org.BackgroundSystem.*;
import org.players.*;

public class Room{
	public static VisibleObject exit;
	public static Room curRoom;
	public static List<Wall>walls=new ThreadList<>();;
	public static List<List<VisibleObject>>visibleObjects=new ThreadList<>();
	public static List<VisibleObject>backdrop=new ThreadList<>();
	public static List<VisibleObject>playerlist=new ThreadList<>();
	public static List<VisibleObject>projectiles=new ThreadList<>();
	public static List<VisibleObject>enemies=new ThreadList<>();
	public static List<VisibleObject>items=new ThreadList<>();
	public static List<VisibleObject>foreground=new ThreadList<>();
	public static HUD hud,pauseHUD,curHUD;
	public static int spriteW, spriteH;
	public static int HEIGHT,WIDTH;
	public static int spriteX=19,spriteY=38;
	public static int screenX=0,screenY=0;
	public static int cameraX=0,cameraY=0,cameraXOff=0,cameraYOff=0;
	public static int visibleHEIGHT=500,visibleWIDTH=500;
	public static boolean startLeft;
	public static float startVX;
	public static RoomState roomState=RoomState.PLAYING;
	public static BufferedImage back=null;
	public static Background background=null;
	public static boolean endFast=false;
	static{
		visibleObjects.clear();
		visibleObjects.add(new ThreadList<VisibleObject>());
		visibleObjects.get(0).add(background);
		visibleObjects.add(backdrop);
		visibleObjects.add(new ThreadList<VisibleObject>());
		visibleObjects.get(2).add(Player.player);
		visibleObjects.add(enemies);
		visibleObjects.add(projectiles);
		visibleObjects.add(items);
		visibleObjects.add(foreground);
	}
	public static final BufferedImage[][] defaultHaleImages=new BufferedImage[][]{
		{
			ImagePack.getImage("walkingAnimation/RangerHaleL0.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleL1.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleL2.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleL3.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleL4.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleL5.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleL6.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleL7.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleL8.png")
		},{
			ImagePack.getImage("walkingAnimation/RangerHaleR0.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleR5.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleR6.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleR7.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleR4.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleR1.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleR2.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleR3.png"),
			ImagePack.getImage("walkingAnimation/RangerHaleR8.png")
		}
	};
	public static BufferedImage[][]haleImages=defaultHaleImages;
	public static void reset(){
		endFast=false;
		curHUD=hud=pauseHUD=null;;
		hud=new HUD(visibleWIDTH,visibleHEIGHT);
		hud.add(new HealthBar(Player.player,1f,6,Position.TOP_LEFT,blue));
		//hud.add(new FadingBlack(300));
		backdrop.clear();
		playerlist.clear();
		projectiles.clear();
		enemies.clear();
		items.clear();
		foreground.clear();
		visibleObjects.clear();
		visibleObjects.add(new ThreadList<VisibleObject>());
		visibleObjects.get(0).add(background);
		visibleObjects.add(backdrop);
		visibleObjects.add(new ThreadList<VisibleObject>());
		visibleObjects.get(2).add(Player.player);
		visibleObjects.add(enemies);
		visibleObjects.add(projectiles);
		visibleObjects.add(items);
		visibleObjects.add(foreground);
		spriteW=19;spriteH=38;
		HEIGHT=500;WIDTH=500;
		spriteX=0;spriteY=0;
		screenX=0;screenY=0;
		cameraX=0;cameraY=0;
		cameraXOff=0;cameraYOff=0;
		startLeft=false;
		startVX=.0003f;
		roomState=RoomState.PLAYING;
		background=null;
		haleImages=defaultHaleImages;
	}
	public void load(){out.println("default load");}
	public void startRoom(){
		cameraX=250;cameraY=250;
		pauseHUD=new PauseMenu(visibleWIDTH,visibleHEIGHT);
		//new Player(Room.spriteX,Room.spriteY,Room.spriteW,Room.spriteH,Player.life);
		Player.player.moveTo(spriteX,spriteY);
		visibleObjects.clear();
		visibleObjects.add(new ThreadList<VisibleObject>());
		visibleObjects.get(0).add(background);
		visibleObjects.add(backdrop);
		playerlist.add(Player.player);
		visibleObjects.add(playerlist);
		visibleObjects.add(enemies);
		visibleObjects.add(projectiles);
		visibleObjects.add(items);
		visibleObjects.add(foreground);
		Player.vx=startVX;
		Clock.start();
		RunnableRunner a=new RunnableRunner(backdrop);
		RunnableRunner b=new RunnableRunner(items);
		RunnableRunner c=new RunnableRunner(foreground);
		new EnemyRunner(enemies);
		new ProjectileRunner(projectiles);
		Jump.buildGraphics();
		//int display=0;
		while(!curRoom.isDead()&&!endFast){
			if(roomState==RoomState.PLAYING){
				curHUD=hud;
				Player.player.run();
				Jump.render();
			}
			else{
				curHUD=pauseHUD;
				//if(display%5==0)
					Jump.render();
				//display++;
			}
			tryPause();
			Clock.waitFor();
			//if(roomState==RoomState.PAUSED)waitForState(RoomState.PLAYING);
		}
		if(Player.player.life>0){
			hud.add(new HudTitle("LOADING...",Position.MIDDLE,white));
		}
		else
			hud.add(new HudTitle("YOU DONE LOST",Position.MIDDLE,white));
		Jump.render();Jump.render();
		Clock.stop();
		Jump.disposeGraphics();
		EnemyRunner.kill();
		ProjectileRunner.kill();
		a.kill();
		b.kill();
		c.kill();
		out.println("killed");
		EnemyRunner.stop();out.println("er dead");
		ProjectileRunner.stop();out.println("pr dead");
		a.stop();b.stop();c.stop();out.println("r dead");
		out.println("room dead");
		GarbageSuggester.suggest();
	}
	public static void waitForState(RoomState r){
		while(roomState!=r){
			try{
				synchronized(roomState){
					roomState.wait();
					out.println(roomState+" "+r);
				}
			}catch(Exception e){e.printStackTrace();exit(1);}
		}
	}
	public static void changeState(RoomState r){
		GarbageSuggester.suggest();
		roomState=r;
		out.println(r);
		synchronized(roomState){
			roomState.notifyAll();
			out.println("state changed: "+roomState);
		}
	}
	private static int stateDelay=0;
	public static void tryPause(){
		if(KeyList.keyboard[80]&&stateDelay==0){
			stateDelay=15;
			changeState(roomState==RoomState.PAUSED?RoomState.PLAYING:RoomState.PAUSED);
			
		}
		if(stateDelay>0)stateDelay--;
		else stateDelay=0;
	}
	public boolean readyToEnd(){
		for(VisibleObject vo:enemies){
			if(((Enemy)vo).preventsNextRoom())return false;
		}
		return true;
	}
	public boolean isDead(){
		return((readyToEnd()&&Collisions.collides(Player.player,exit))||Player.player.life<=0);
	}
	}