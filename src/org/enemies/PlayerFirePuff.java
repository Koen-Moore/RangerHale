package org.enemies;

import java.awt.Graphics;

import org.players.Player;
import org.resources.Collisions;

import static java.lang.Math.*;

public class PlayerFirePuff extends FirePuff{
	int xo,yo;
	public PlayerFirePuff(){
		super(0,0,(int)round(200*random()));
		xo=-5+(int)round(Player.player.w*random());
		yo=-6+(int)round(Player.player.h*random());
	}
	public void run(){
		counter++;
		life--;
		x=round(Player.player.x)+xo;
		y=round(Player.player.y)+yo;
		if(life<=0)dead=true;
		image=ani[counter/8%4];
		if(counter%5==0&&Collisions.collides(Player.player, this))
			Player.damage(1);
	}

}
