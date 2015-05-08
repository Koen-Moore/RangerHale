package org.resources;

public abstract class LivingObject extends VisibleObject implements Runnable{
	public int lifeCapacity;
	public int life;
	public boolean isDead(){
		return dead;
	}
	public void run(){
		
	}
}
