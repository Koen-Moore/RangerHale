package org.enemies;

import static java.lang.System.exit;
import static java.lang.System.out;

import java.util.List;

import org.resources.Clock;
import org.resources.Collisions;
import org.resources.LivingObject;
import org.resources.VisibleObject;
import org.rooms.Room;
import org.rooms.RoomState;

public class EnemyRunner implements Runnable{
	public static volatile Thread thread;
	private static volatile boolean dead;
	public static volatile List<VisibleObject>enemies;
	public EnemyRunner(List<VisibleObject>e){
		enemies=e;
		dead=false;
		thread=new Thread(this);thread.start();
	}
	public void run(){
		while(!dead){
			Clock.passiveWait();
			if(Room.roomState==RoomState.PLAYING)
				for(int i=0;;i++){
					synchronized(enemies){
						if(i>=enemies.size()||Clock.dead)break;
						LivingObject en=(LivingObject)enemies.get(i);
						
						if(en==null||en.dead||Collisions.outOfBounds(en, Room.background)){
							enemies.remove(i);
							i--;
							out.println("killing enemy!!! "+Collisions.outOfBounds(en, Room.background));
						}
						else {
							
							//float first=en.vx,last=en.vy;
							
							//en.vx*=en.vMultiplier;
							//en.vy*=en.vMultiplier;
							
							en.run();
							
							//en.vx=first;
							//en.vy=last;
							
						}
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