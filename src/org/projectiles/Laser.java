package org.projectiles;

import static java.awt.Color.red;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.min;
import static java.lang.Math.round;
import static java.lang.Math.sin;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import org.enemies.*;
import org.rendering.Jump;
import org.resources.AudioPack;
import org.resources.Element;
import org.resources.ImagePack;
import org.resources.VisibleObject;
import org.rooms.Room;
import org.walls.DamageableWall;
import org.walls.Wall;

public class Laser extends Projectile implements Runnable {
	public static final BufferedImage[] ani = lazerani;
	public Laser(float X, float Y, float vx, float vy) {
		image = ani[0];
		dead = false;
		life = lifeCapacity = -1;
		w = 2;
		h = 2;
		color = red;
		x = X;
		y = Y;
		vX = 2 * vx;
		vY = 2 * vy; // thread=new Thread(this);thread.start();
		synchronized (sync) {
			livePros++;
		}
		if (abs(abs(atan((double) vY / vX)) - PI / 4) < PI / 8)
			image = ani[vY * vX > 0 ? 2 : 3];
		else {
			image = ani[abs(vX) > abs(vY) ? 0 : 1];
			w = h = 7;
		}
		// try{current.join();}catch(Exception e){e.printStackTrace();exit(0);}
	}
	public void run() {
		// boolean frame=Clock.frame;
		if (life != 0) {
			// image=ani[vX<0?0:1];
			// if(life>0)image=ani[2];
			// Clock.waitFor(frame=!frame);
			// if(Clock.dead)break;
			/*
			 * if(abs(abs(atan((double)vY/vX))-PI/4)<PI/8)
			 * image=ani[vY*vX<0?2:3]; else{ image=ani[abs(vX)>abs(vY)?0:1];
			 * w=h=7; }
			 */

			if (life < 0) {
				x += vX;
				y -= vY;
			} else if (life % 2 == 0) {
				image = ani[4 - min(life / 2, 4)];
			}
			life--;
			if (!(x > 0 && x < Jump.WIDTH && y > 0 && y < Jump.HEIGHT) && life < 0) {
				vY = vX = 0;
				life = 10;
				w = h = 8;
				x -= 5;
				y -= 5;
				// AudioPack.playAudio("BExplosion2.wav",0.05);
				AudioPack.playClip(boom);
			}
			for (int i = 0; i < Room.walls.size(); i++) {
				Wall wal = Room.walls.get(i);
				if (vY == 0 && vX == 0)
					break;
				if ((x < wal.x + wal.w && x + w > wal.x) && (y < wal.y + wal.h && y + h > wal.y)) {
					vY = vX = 0;
					life = 10;
					w = h = 11;
					// AudioPack.playAudio("BExplosion2.wav",0.05);
					AudioPack.playClip(boom);
					if (wal.damagable) {
						((DamageableWall) wal).life--;
						// if
						// (Jump.kraidLife<=0&&Jump.countdown<0){Jump.countdown=500;
						// AudioPack.playAudio("Ima Firen Mah Lazor!.wav",0.1);
						// }
					}
				}
			}

			synchronized (Room.enemies) {
				for (VisibleObject en : Room.enemies) {
					if ((x < en.x + en.w && x + w > en.x) && (y < en.y + en.h && y + h > en.y)) {
						if (life < 0)
							((Enemy) en).damage(Element.NORMAL, 5);
						if (vX != 0 || vY != 0) {
							x = en.x + en.w / 2;
							vY = vX = 0;
							life = 10;
							w = h = 16;
							y = en.y + en.h / 2;
							x -= 5;
							y -= 5;
							// AudioPack.playAudio("BExplosion2.wav",0.1);
							AudioPack.playClip(boom);
						}
					}
				}
			}
		} else
			dead = true;
	}
	public void draw(Graphics g) {
		if (life >= 0) {
			g.drawImage(image, round(x), round(y), null);
			return;
		}
		Stroke old = ((Graphics2D) g).getStroke();
		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.setColor(red);
		g.drawLine(round(x), round(y), (int) round(x + 10 * cos(atan(vY / vX))), (int) round(y - 10 * sin(atan(vY / vX))));
		((Graphics2D) g).setStroke(old);
	}
}
