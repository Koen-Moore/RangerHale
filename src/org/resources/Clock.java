package org.resources;

import static java.lang.System.exit;
import static java.lang.System.out;

import org.rendering.Jump;


public class Clock implements Runnable{
	public static volatile boolean dead=true;
	public static boolean frame;
	private static final Runnable clock=new Clock();
	private static Thread thread;
	private Clock(){
		//dead=true;
		//clock=this;
	}
	public static  void start(){
		//stop();
		dead=false;
		thread=new Thread(clock);
		thread.start();
	}
	public static  void stop(){
		dead=true;
		if(thread!=null)
		try{
			thread.join();
		}catch(Exception e){e.printStackTrace();exit(0);}
		out.println("clock has stopped");
	}
	public static boolean waitFor(){
		try{
			synchronized(clock){(clock).wait();}
		}catch(Exception e){e.printStackTrace();exit(0);};
		if (dead)return false;
		return true;
	}
	public static void waitFor(boolean in){
		while(frame!=in){
			try{
				synchronized(clock){(clock).wait();}
			}catch(Exception e){e.printStackTrace();exit(0);};
		}
	}
	public static void passiveWait(){
		try{
			if(dead)return;
			synchronized(clock){clock.wait();}
		}catch(Exception e){e.printStackTrace();exit(1);}
	}
	public void run(){
		out.println("clock started: "+Jump.refRate+" frames per second");
		if (Jump.refRate!=60){out.println("you have a weird refresh rate");}
		while(true){
			try{
				//Thread.sleep(100);
				Thread.sleep(16,666666);
				frame=!frame;
				tick();
			}catch(Exception e){e.printStackTrace();exit(0);}
			if (dead)break;
		}
		tick();
		out.println("clock is dead");
	}
	public static void tick(){
		synchronized(clock){
			clock.notifyAll();
		}
	}
}