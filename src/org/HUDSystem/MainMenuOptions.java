package org.HUDSystem;
import static java.awt.Color.white;

import org.rendering.Jump;
import org.resources.*;
import org.rooms.Room;
import org.rooms.RoomState;
import org.walls.Wall;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;

public class MainMenuOptions extends HUD{
	public int buttonDelay;
	public int curButton;
	public List<Button>buttons=new ArrayList<>();
	public static final BufferedImage[]ani=new BufferedImage[]{
		ImagePack.getImage("menu stuff/back.png"),ImagePack.getImage("menu stuff/backs.png"),
		ImagePack.getImage("menu stuff/anti.png"),ImagePack.getImage("menu stuff/antis.png"),
	};
	public HUD backRef;
	public MainMenuOptions(int w,int h,HUD hud){
		super(w,h);
		backRef=hud;
		add(new Wall(0,0,planets));

		VisibleObject t;
		add(t=new Wall(0,0,menuBack));
		Collisions.alignWith(t, this, Position.MIDDLE);
		add(t=new HudTitle("Options",Position.MIDDLE,white));
		t.y-=70;
		
		Button b;
		add(b=new Button(ani[0],ani[1]));
		Collisions.alignWith(b, this, Position.MIDDLE);
		b.y-=15;
		buttons.add(b);
		add(b=new Button(ani[2],ani[3]));
		Collisions.alignWith(b, this, Position.MIDDLE);
		b.y+=15;
		buttons.add(b);
	}
	public void draw(Graphics g){
		if(buttonDelay==0){
			int move=0;
			if(KeyList.keyboard[38]){
				buttons.get(curButton).deSelect();
				move=-1;
			}
			else if(KeyList.keyboard[40]){

				buttons.get(curButton).deSelect();
				move=1;
			}
			if(move!=0)buttonDelay=10;
			curButton=(curButton+move+buttons.size())%buttons.size();
			buttons.get(curButton).select();
			if(KeyList.keyboard[8]){
				Room.curHUD=backRef;
			}
			if(KeyList.keyboard[10]||KeyList.keyboard[32]){
				buttonDelay=10;
				switch(curButton){
				case 0:
					Room.curHUD=backRef;
					break;
				case 1:
					Jump.canvas.flipAntiAliasing();
					break;
				}
			}
		}
		buttonDelay=Math.max(0,--buttonDelay);
		
		
		super.draw(g);
		
	}
}
