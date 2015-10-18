package org.projectiles;

import static java.awt.Color.red;
import static java.lang.Math.*;
import static java.lang.System.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import org.enemies.Enemy;
import org.resources.*;
import org.rooms.Room;
import org.walls.Wall;

public class Lightning extends Projectile implements Runnable {
	private double endX, endY;
	private Double a, b;
	public Lightning(Double X, Double Y, double vx, double vy, int branches) {
		a = X;
		b = Y;
		x = (float) (double) X;
		y = (float) (double) Y;
		vX = (float) vx;
		vY = -(float) vy;
		color = Color.red;
		life = lifeCapacity = 4;
		dead = false;

		double angle = getAngle(vx, vy);
		// out.println(vx+" "+vy+" "+angle);

		double closestDist = 75;
		Enemy closestEnemy = null;
		double bestX = 0, bestY = 0;

		for (VisibleObject en : Room.enemies) {
			double a = en.x + en.w / 2;
			double b = en.y + en.h / 2;
			double dist = hypot(a - x, b - y);

			if (dist < closestDist && abs(getAngle(a - x, -b + y) - angle) < PI / 4) {
				closestDist = dist;
				closestEnemy = (Enemy) en;
				bestX = a;
				bestY = b;
			}
		}

		if (closestEnemy == null) {
			endX = x + vx * 5 + 2 * random() - 1;
			endY = y - vy * 5 + 2 * random() - 1;
			// if(branches==0){
			// return;
			// }
			// Room.projectiles.add(new
			// Lightning(endX,endY,vx+(6*random()-3),vy+(6*random()-3),branches-1));
			// Room.projectiles.add(new
			// Lightning(endX,endY,vx+(6*random()-3),vy+(6*random()-3),branches-1));
		} else {
			endX = bestX;
			endY = bestY;
			// double finangle=getAngle(endX-x,y-endY);
			// double avg=(abs(vx)+abs(vy))/2;
			// Room.projectiles.add(new
			// Lightning(endX,endY,avg*cos(finangle)-(6*random()-3),(vy<0?-1:1)*avg*sin(finangle)+(6*random()-3),branches-1));
			// Room.projectiles.add(new
			// Lightning(endX,endY,avg*cos(finangle)+(6*random()-3),(vy<0?-1:1)*avg*sin(finangle)-(6*random()-3),branches-1));
		}
		// out.println(vX+" "+vY);

		vX = (float) endX - x;
		vY = (float) (endY - y);
		Point2D p2 = getWallCollide();
		if (p2 != null) {
			endX = p2.getX();
			endY = p2.getY();
			branches = 0;
		}
		/*
		 * else if(closestEnemy!=null){
		 * ((Enemy)closestEnemy).damage(branches/2+3);
		 * ((Enemy)closestEnemy).vMultiplier=.01f; }
		 */
		for (VisibleObject en : Room.enemies) {
			if (Collisions.collides(new Wall((float) endX, (float) endY, 0, 0), en) && en instanceof Enemy) {
				((Enemy) en).damage(branches / 2 + 3);
				((Enemy) en).vMultiplier = .01f;
			}
		}
		if (branches == 0) {
			return;
		}
		// out.println("wall: "+getWallCollide());
		Room.projectiles.add(new Lightning(endX, endY, vx + (6 * random() - 3), vy + (6 * random() - 3), branches - 1));
		Room.projectiles.add(new Lightning(endX, endY, vx + (6 * random() - 3), vy + (6 * random() - 3), branches - 1));

	}
	public Point2D.Float getWallCollide() {
		w = 2;
		h = 2;
		color = red;

		double d1 = (vX > 0 ? x : endX);
		double d2 = (vX > 0 ? endX : x);
		double r1 = (vY > 0 ? y : endY);
		double r2 = (vY > 0 ? endY : y);
		if (vX == 0)
			d1 = d2 = x;
		if (vY == 0)
			r1 = r2 = y;

		float xhit = 0, yhit = 0, closeDist = Integer.MAX_VALUE;
		VisibleObject closest = null;

		// main:
		for (Wall vo : Room.walls) {
			if (vo == null)
				continue;
			// Inside
			if (Collisions.collides(this, vo)) {
				xhit = x;
				yhit = y;
				closeDist = 0;
				closest = vo;
				break;
			}

			// Top
			if (vY > 0) {
				float curX = (-y + vo.y) * vX / vY + x;
				float curY = vo.y;
				float curDist = (float) hypot(curX - x, curY - y);
				if (curX >= vo.x && curX <= vo.x + vo.w) {
					if (curX >= d1 && curX <= d2 && curY >= r1 && curY <= r2 && curDist < closeDist) {
						xhit = curX;
						yhit = curY;
						closeDist = curDist;
						closest = vo;
					}
				}
			}

			// Bottom
			if (vY < 0) {
				float curX = (-y + vo.y + vo.h) * vX / vY + x;
				float curY = vo.y + vo.h;
				float curDist = (float) hypot(curX - x, curY - y);
				if (curX >= vo.x && curX <= vo.x + vo.w) {
					if (curX >= d1 && curX <= d2 && curY >= r1 && curY <= r2 && curDist < closeDist) {
						xhit = curX;
						yhit = curY;
						closeDist = curDist;
						closest = vo;
					}
				}
			}

			// Left
			if (vX > 0) {
				float curY = (vo.x - x) * vY / vX + y;
				float curX = vo.x;
				float curDist = (float) hypot(curX - x, curY - y);
				if (curY >= vo.y && curY <= vo.y + vo.h) {
					if (curX >= d1 && curX <= d2 && curY >= r1 && curY <= r2 && curDist < closeDist) {
						xhit = curX;
						yhit = curY;
						closeDist = curDist;
						closest = vo;
					}
				}
			}

			// Right
			if (vX < 0) {
				float curY = (vo.x + vo.w - x) * vY / vX + y;
				float curX = vo.x + vo.w;
				float curDist = (float) hypot(curX - x, curY - y);
				if (curY >= vo.y && curY <= vo.y + vo.h) {
					if (curX >= d1 && curX <= d2 && curY >= r1 && curY <= r2 && curDist < closeDist) {
						xhit = curX;
						yhit = curY;
						closeDist = curDist;
						closest = vo;
					}
				}
			}

		}

		if (closest == null) {
			return null;
		}
		// System.exit(1);
		// out.println("lightning collide:"+xhit+" "+yhit);
		return new Point2D.Float(xhit, yhit);

	}
	public static double getAngle(double vx, double vy) {
		if (vx == 0)
			return PI / 2 * (vy > 0 ? 1 : 3);
		return (atan(vy / vx) + (vx < 0 ? PI : 0) + 4 * PI) % (2 * PI);
	}
	public void run() {
		if (life == 0)
			dead = true;
		life--;
	}
	@Override
	public void draw(Graphics g) {
		// out.println("drawing");
		// Stroke old=((Graphics2D)g).getStroke();
		// ((Graphics2D)g).setStroke(new BasicStroke(2));
		g.setColor(Color.white);
		// g.drawRect(round(x),round(y),1,1);
		// g.drawRect((int)round(endX),(int)round(endY),1,1);
		g.drawLine((int) round(a), (int) round(b), (int) round(endX), (int) round(endY));
		// ((Graphics2D)g).setStroke(old);
	}
}
