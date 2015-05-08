package org.rendering;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import org.resources.Clock;
import org.rooms.Room;

public class GCanvas extends Canvas implements Runnable{
	private static final long serialVersionUID = 7260460683207148228L;
	private int yOffset, xOffset;
	private int w,h;
	private boolean antiAliasing=false;
	private Color background;
	private boolean reset=true;
	
	private AffineTransformOp op;
	public GCanvas(Color c){
		reset=true;
		background =c;
		setVisible(true);
		repaint();
		build();
		//new Thread(this).start();
	}
	public void run(){

		while (true){
			for(int i=0;i<300;i++){
				Clock.waitFor();
			}
			if(Clock.waitFor())
			repaint();
			//try{Thread.sleep(50);}catch(Exception e){e.printStackTrace();}
		}
	}
	public void setAntiAliasing(boolean b){
		antiAliasing=b;
		build();
	}
	public void flipAntiAliasing(){
		setAntiAliasing(!antiAliasing);
	}
	public void build(){
		reset=true;
		w=(int)((((double)Room.visibleWIDTH)/Room.visibleHEIGHT)*Jump.screenHEIGHT);
		if(w>Jump.screenWIDTH){
			w=Jump.screenWIDTH;
			h=(int)((((double)Room.visibleHEIGHT)/Room.visibleWIDTH)*Jump.screenWIDTH);
		}
		else{
			h=Jump.screenHEIGHT;
		}
		xOffset=(Jump.screenHEIGHT-h)/2;
		yOffset=(Jump.screenWIDTH-w)/2;
		AffineTransform a=new AffineTransform(((double)w)/Room.visibleWIDTH,0,0,((double)h)/Room.visibleHEIGHT,0,0);
		//xOffset/=a.getScaleX();
		//yOffset/=a.getScaleY();
		op=new AffineTransformOp(a,antiAliasing?2:1);
	}
	public void update(Graphics g){
		paint(g);
	}
	public void paint(Graphics g){
		//System.out.println("antialiasing "+antiAliasing);
		if(reset){
			reset=false;
			g.setColor(background);
			g.fillRect(0,0,Jump.screenWIDTH,Jump.screenHEIGHT);
		}
		//if(Jump.print){
			g.clipRect(yOffset,xOffset,w, h);
			//g.drawImage(Jump.printScreen,yOffset,xOffset,yOffset+w,xOffset+h,0,0,Room.visibleWIDTH,Room.visibleHEIGHT,Color.blue,null);
			((Graphics2D)g).drawImage(Jump.printScreen, op, yOffset, xOffset);
			//Jump.print=false;
		//}
		//g.drawImage(Jump.printScreen,0,0,400,400,0,0,400,400,Color.blue,null);
		//g.dispose();
	}
}
