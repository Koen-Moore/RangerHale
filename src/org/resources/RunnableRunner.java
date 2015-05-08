package org.resources;

import static java.lang.System.exit;

import java.util.List;

import org.rooms.Room;
import org.rooms.RoomState;

public class RunnableRunner implements Runnable{
	public Thread thread;
	public boolean dead;
	public List<VisibleObject>runnables;
	public RunnableRunner(List<VisibleObject>p){
		runnables=p;
		dead=false;
		thread=new Thread(this);thread.start();
	}
	public void run(){
		while(!dead){
			Clock.passiveWait();
			if(Clock.dead)break;
			if(Room.roomState==RoomState.PLAYING)
				for(int i=0;;i++){
					synchronized(runnables){
						if(i>=runnables.size()||dead)break;
						VisibleObject vo=runnables.get(i);
						if(vo==null||vo.dead||Collisions.outOfBounds(vo, Room.background)){
							runnables.remove(i);
							i--;
						}
						
						else if(vo instanceof Runnable){
							((Runnable)vo).run();
						}
						
					}
				}
		}
		for(int i=0;i<runnables.size();i++){
			runnables.remove(0);
		}
		runnables.clear();
	}
	public void kill(){
		dead=true;
	}
	public void stop(){
    	dead=true;
    	try{
    		thread.join();
    	}catch(Exception e){e.printStackTrace();exit(1);}
	}
}
