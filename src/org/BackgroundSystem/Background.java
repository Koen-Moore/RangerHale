package org.BackgroundSystem;

import static java.awt.Color.black;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static java.lang.System.out;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.resources.VisibleObject;
import org.rooms.Room;
import org.walls.Wall;

public class Background extends VisibleObject{
	public Background(){
		x=0;y=0;w=Room.WIDTH;h=Room.HEIGHT;fillBack(black);
	}
	public Background(int W,int H){
		x=0;y=0;w=W;h=H;fillBack(black);
	}
	public Background(BufferedImage b){
		x=0;y=0;w=Room.WIDTH;h=Room.HEIGHT;fillBack(black);
		add(b);		
	}
	public void fillBack(Color c){
		BufferedImage back=new BufferedImage(Room.WIDTH,Room.HEIGHT,5);
    	Graphics2D background=(back.createGraphics());
    	background.setColor(c);
    	background.fillRect(0,0,Room.WIDTH,Room.HEIGHT);
    	image=back;
    	background.dispose();
	}
	public void add(BufferedImage b){
		Graphics g=image.createGraphics();
		g.drawImage(b,0,0,null);
		g.dispose();
	}
	public void add(BufferedImage b,int x,int y){
		Graphics g=image.createGraphics();
		g.drawImage(b,x,y,null);
		g.dispose();
	}
	public void add(VisibleObject v){
		image.createGraphics().drawImage(v.image,round(v.x),round(v.y),null);
	}
	public void addAll(VisibleObject[]vo){
		Graphics2D g=image.createGraphics();
		for(VisibleObject b:vo){
			b.draw(g);
		}
		g.dispose();
	}
	public <T extends VisibleObject>void addAll(List<T>vo){
		addAll(vo.toArray(new VisibleObject[0]));
	}
	public void tessellate(BufferedImage b){
		out.println("tessellating");
		Graphics g=image.createGraphics();
		for(int x=0;x<image.getWidth();x+=b.getWidth()){
			for(int y=0;y<image.getHeight();y+=b.getHeight()){
				g.drawImage(b,x,y,null);
			}
		}
		g.dispose();
	}
	public void tessellateOnBottom(BufferedImage b){
		out.println("tessellating");
		Graphics g=image.createGraphics();
		for(int x=0;x<image.getWidth();x+=b.getWidth()){
			g.drawImage(b,x,h-b.getHeight(),null);
		}
		g.dispose();
	}
	public void tessellateOnTop(BufferedImage b){
		out.println("tessellating");
		Graphics g=image.createGraphics();
		for(int x=0;x<image.getWidth();x+=b.getWidth()){
			g.drawImage(b,x,0,null);
		}
		g.dispose();
	}
	public void tessellateOnLeft(BufferedImage b){
		out.println("tessellating");
		Graphics g=image.createGraphics();
		for(int y=0;y<image.getHeight();y+=b.getHeight()){
			g.drawImage(b,0,y,null);
		}
		g.dispose();
	}
	public void tessellateOnRight(BufferedImage b){
		out.println("tessellating");
		Graphics g=image.createGraphics();
		for(int y=0;y<image.getHeight();y+=b.getHeight()){
			g.drawImage(b,w-b.getWidth(),y,null);
		}
		g.dispose();
	}
	public void randomTessellate(BufferedImage b,int xmargin,int ymargin){
		out.println("random tessellating");
		Graphics g=image.createGraphics();
		for(int x=-xmargin;x<image.getWidth();x+=b.getWidth()-xmargin*2){
			for(int y=-ymargin;y<image.getHeight();y+=b.getHeight()-ymargin*2){
				g.drawImage(b,x+(int)round(xmargin*2*random()-xmargin),y+(int)round(ymargin*2*random()-ymargin),null);
			}
		}
		g.dispose();
	}
	public void addToGround(BufferedImage b,int total){
		Graphics g=image.createGraphics();
		for(int i=0;i<total;i++){
			g.drawImage(b,(int)round(random()*(2*b.getWidth()+image.getWidth())-b.getWidth()),image.getHeight()-b.getHeight(),null);
		}
		g.dispose();
	}

	public void addToMiddle(BufferedImage b,int total){
		Graphics g=image.createGraphics();
		for(int i=0;i<total;i++){
			g.drawImage(b,(int)round(random()*(2*b.getWidth()+image.getWidth())-b.getWidth()),(int)round(random()*(2*b.getHeight()+image.getHeight())-b.getHeight()),null);
		}
		g.dispose();
	}
}