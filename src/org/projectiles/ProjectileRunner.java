package org.projectiles;

import static java.lang.System.exit;

import java.util.ArrayList;
import java.util.List;

import org.resources.*;
import org.rooms.*;


public class ProjectileRunner implements Runnable{
	public static Thread thread;
	public static boolean dead;
	public static List<VisibleObject>projectiles;
	public ProjectileRunner(List<VisibleObject>p){
		projectiles=p;
		dead=false;
		thread=new Thread(this);thread.start();
	}
	public void run(){
		while(!dead){
			try{
			Clock.passiveWait();
			if(Clock.dead)break;
			if(Room.roomState==RoomState.PLAYING){
				tick(projectiles);
			}
			}finally{}
		}
		/*for(int i=0;i<projectiles.size();i++){
			projectiles.remove(0);
		}*/
		synchronized(projectiles){projectiles.clear();}
	}
	public static void tick(List<VisibleObject>projectiles){
		Projectile pro=null;
		for(int i=0;;i++){
					
					synchronized(projectiles){
						if(i>=Room.projectiles.size()||dead)break;
						pro=(Projectile)projectiles.get(i);
					}
					if(!(pro==null)&&!pro.dead)pro.run();
					else if(pro==null||pro.dead){
						synchronized(projectiles){
							if(projectiles.remove(pro))
								i--;
						}
					}
				}
	}
	public static void kill(){
		dead=true;
	}
	public static void stop(){
    	dead=true;
    	try{
    		thread.join();
    	}catch(Exception e){e.printStackTrace();exit(1);}
	}
}