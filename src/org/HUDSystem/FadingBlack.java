package org.HUDSystem;

import java.awt.Color;
import java.awt.Graphics;

import org.resources.*;
import org.rooms.Room;
import org.rooms.RoomState;
public class FadingBlack extends VisibleObject {
	int countdown,startval,delay;
	HUD temp;
	public FadingBlack(int d,int sv){
		countdown=startval=sv;
		delay=d;
		color=Color.black;
		
	}
	public void draw(Graphics g){
		if(temp==null){
			temp=Room.pauseHUD;
			Room.changeState(RoomState.PAUSED);
		}
		if(delay>0){
			Room.pauseHUD=Room.hud;
			Room.changeState(RoomState.PAUSED);
			delay--;
			if(delay==0){
				Room.pauseHUD=temp;
				Room.changeState(RoomState.PLAYING);
			}
		}
		else{
			if(countdown>0){
				countdown--;
			}
			color=new Color(0,0,0,Math.round(255*countdown/startval));
		}
		g.setColor(color);
		g.fillRect(0, 0, Room.visibleHEIGHT, Room.visibleWIDTH);
	}
}
