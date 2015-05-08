package org.players;

import static java.lang.Math.*;
import static java.lang.System.out;

import java.util.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import org.projectiles.*;
import org.resources.*;
import org.rooms.Room;
import org.walls.DamagingWall;
import org.walls.Wall;

public class Player extends LivingObject implements Runnable{
	public static float vMultiplier;
	public static int projectile; 
	public static int[] unlockedProjectiles=new int[]{
		0,1,2,3,4,5,6,7,8,9
	};
	public static int currentProjectile=0;
	//public static float y;
    //public static float x;
    public static boolean left=false,Jumpable=true;
    public static float vx=0,vy=0;
    public static int spriteW, spriteH;
    //public static int w,h;
    //public static int life=100;
    public static int delay=0;
    public static int proDelay=0;
	private static boolean [] keyboard;
    //public static ArrayList<Projectile> projectiles=Room.projectiles;
    public static List<Wall> walls=Room.walls;
    private static int WIDTH,HEIGHT;
    public static BufferedImage[][]images=Room.haleImages;
    //public static BufferedImage image=images[1][0];
    public static float curFrame=0;
    public static Player player;
    public static float accuracy;
    public static int buttonDelay=0;
    private static Double dx,dy;
    //public static Wall outline;

	public Player(int X,int Y,int W,int H,int l){
		vMultiplier=1;
		player=this;
		life=lifeCapacity=l;vx=0;vy=0;curFrame=0;
		WIDTH=Room.WIDTH;  HEIGHT=Room.HEIGHT;
		//projectiles=Room.projectiles;
		walls=Room.walls;
		images=Room.haleImages;
		y=Y; x=X; w=spriteW=10; h=spriteH=H;
		left=Room.startLeft;
		out.println("starting thread");
		//while(projectiles==null)projectiles = Room.projectiles;
		//while(walls==null)walls = Room.walls;
		//while(!Jump.print){out.print("");}
		keyboard = KeyList.keyboard;
		projectile=0;
		
		//Room.walls.add(outline=new Wall(x+1,y+1,w-2,h-2));
		//new Thread(this).start();
	}
	public void moveTo(int X,int Y){
		player.y=Y;player.x=X;
		WIDTH=Room.WIDTH;  HEIGHT=Room.HEIGHT;
		left=Room.startLeft;
	}
	public void run(){
		//out.println(Arrays.toString(unlockedProjectiles));

		//while(true){
			//Clock.waitFor();
			if(buttonDelay==0){
				if(keyboard[32]&&currentProjectile<unlockedProjectiles.length-1)
					currentProjectile++;
				else if(keyboard[16]&&currentProjectile>0)
					currentProjectile--;
				if(unlockedProjectiles[currentProjectile]!=projectile){
					buttonDelay=15;
					projectile=unlockedProjectiles[currentProjectile];
				}
			}
			else{
				buttonDelay--;
			}
    		if (keyboard[68])left=false;
    		if (keyboard[65])left=true;
    		if(keyboard[37])left=true;
    		if(keyboard[39])left=false;
    		
    		boolean onSurface=false;
    		boolean onWall=false;
   			//boolean onSurface=(y==HEIGHT-spriteH);
   			//boolean onWall=(x==WIDTH-spriteW||x==0);
			for (Wall wal:Room.walls){
   				onSurface=onSurface||Collisions.onTop(player, wal);
   				onWall=onWall||Collisions.touchingSides(player, wal);
   			}

   			curFrame+=2*abs(vx);
   			curFrame%=80;
   			curFrame=abs(curFrame);
   			if (vx==0)curFrame=0;
   			if(!onSurface)curFrame=80;
   			image=images[left?0:1][(int)floor(curFrame/10)];
   			//else image=images[1][(int)floor(curFrame/10)];
   			if(onSurface||!onWall)Jumpable=true;
   			if (onSurface){vy=0;}

   			//if(accuracy>0)accuracy-=.01f;
   			if(!(keyboard[38]||keyboard[37]||keyboard[39]||keyboard[40]))accuracy=.0001f;
   			if(accuracy>.2)accuracy=.2f;
   			if (proDelay==0){
   				boolean add=false;
   				float svx=vMultiplier*vx/2 ,svy=vMultiplier*vy/2;
   				float pvx=svx,pvy=svy;
   				Float px=x,py=y+spriteH/2;
   				if (!left){px=x+spriteW;}
   				if (keyboard[37]){pvx-=3;add=true;}
   				if(keyboard[38]){pvy+=3;add=true;}
   				if(keyboard[39]){pvx+=3;add=true;}
   				if(keyboard[40]){pvy-=3;add=true;}
   				dx=new Double(px);
   				dy=new Double(py);
   				if (add){
   					if(pvx-svx!=0&&pvy-svy!=0){
   						pvx/=sqrt(2);
   						pvy/=sqrt(2);
   					}
   					proDelay=7;
   					switch(projectile){
   					case 0:{
   						//proDelay=2;
   						float a=(float)((pvx-svx)*(.1)+accuracy*random()-accuracy/2);
   						float b=(float)((pvy-svy)*(.1)+accuracy*random()-accuracy/2);
   						Room.projectiles.add(new Bullet(px,py,a,b));
   						accuracy+=.04f;
   						break;
   					}
   					case 1:{
   						Room.projectiles.add(new Fireball(px,py-2,pvx-svx/2,pvy-svy/2));break;
   					}
   					case 2:Room.projectiles.add(new BigFireBall(px,py-6,pvx-svx/2,pvy-svy/2));break;
   					
   					case 3:Room.projectiles.add(new Icebullet(px,py-1,pvx,pvy));break;
   					case 4:Room.projectiles.add(new WaterBubble(px,py,pvx,pvy));break;
   					case 5:{
   						proDelay=12;
   						Room.projectiles.add(new RockProjectile(px,py-3,pvx,pvy+.5f));break;
   					}
   					case 6:{
   						proDelay=12;
   						Room.projectiles.add(new SuperRock(px,py-8,pvx,pvy+.5f));break;
   					}
   					case 7:Room.projectiles.add(new AirBall(px,py-7,pvx-svx/2,pvy-svy/2));break;
   					case 8:Room.projectiles.add(new Lightning(dx,dy,4*(pvx-svx),4*(pvy-svy),5));break;
   					case 9:{
   						proDelay=2;
   						Room.projectiles.add(new LaserBullet(px,py,pvx-svx,pvy-svy));
   						break;
   					}
   					}
   					
   					//AudioPack.playAudio(RocketLauncherFire.wav",0.6);
   				}
   			}
   			else if(proDelay>0)proDelay--;
   			//out.println("player"+projectiles.size());


   			vy-=.7;

    		if (delay>0&&onSurface){delay--;}
    		if (keyboard[87]&&((onSurface&&delay<=0)||(onWall&&Jumpable))){
    			vy=15;delay=4;
    			if (onWall)Jumpable=false;
    		}
    		//if (vx<=4&&vx>= -4){
    			if (onSurface){
    				if (keyboard[65]){
    					if(vx==0){vx=-3;}
    					else if(vx<0){vx-=.8;}
    					else if(vx>-6){vx=0;}
    				}
    				else if (keyboard[68]){
    					if(vx==0){vx=3;}
    					else if(vx>0){vx+=.8;}
    					else if(vx<6){vx=0;}
    				}
    			}
    			else{
    				if (keyboard[65]&&vx>-7){vx-=.1;}
    				else if (keyboard[68]&&vx<7){vx+=.1;}
    			}
    		//}
    		if (onWall){
    			if (keyboard[65]){vx=-4;}
    			else if (keyboard[68]){vx= 4;}
    		}
    		if (onSurface){
    			if (vx>=0.7)vx-=0.7;
    			else if (vx<=-0.7)vx+=0.7;
    			else vx=0;
    		}
    		if(vx>8.8)vx=8.8f;
    		if(vx<-8.8)vx=-8.8f;
    		if(vy>30)vy=30;
    		if(vy<-30)vy=-30;
    		//if (y<HEIGHT-spriteH&&vy<0&&(x==WIDTH-spriteW||x==0)){}
    		if(vMultiplier<0)vMultiplier=0.0001f;
			vx*=vMultiplier;
			vy*=vMultiplier;
   				
   			float xn=(vx>0?Integer.MAX_VALUE:Integer.MIN_VALUE),yn=(vy<0?Integer.MAX_VALUE:Integer.MIN_VALUE);
   			float xn2=(vx>0?Integer.MAX_VALUE:Integer.MIN_VALUE),yn2=(vy<0?Integer.MAX_VALUE:Integer.MIN_VALUE);
   			float nvx=vx,nvy=vy;
   			boolean xchange=false,ychange=false,dchange=false;
   			if (!(vy==0&&vx==0))
   			for (int i=0;i<Room.walls.size();i++){
				Wall wal=Room.walls.get(i);
				//if(wal==outline)continue;
				boolean tcol=false;
    			if (Collisions.willCollide(this, wal, vx, 0)){
    				tcol=true;
    				if (vx>0){xn=min(xn,wal.x-spriteW);}
    				else if(vx<0){xn=max(xn,wal.x+wal.w);}
    				xchange=true; if (wal instanceof DamagingWall)damage(1);
    			}
    			//if (vy==0)continue;
    			if (Collisions.willCollide(this, wal, 0, -vy)){
    				tcol=true;
    				if (vy<0){yn=min(yn,wal.y-spriteH);}
    				else if(vy>0){yn=max(yn,wal.y+wal.h);}
    				ychange=true; if (wal instanceof DamagingWall)damage(1);
    			}
    			if (Collisions.willCollide(this, wal, vx, -vy)&&!tcol){
    				if (vx>0){xn=min(xn,wal.x-spriteW);}
    				else if(vx<0){xn=max(xn,wal.x+wal.w);}
    				if (vy<0){yn=min(yn,wal.y-spriteH);}
    				else if(vy>0){yn=max(yn,wal.y+wal.h);}
    				dchange=true; 
    				if (wal instanceof DamagingWall)damage(1);
    			}
    		}
   			if(xchange){
   				x=(xn);
   				nvx=0;
   			}
   			if(ychange){
   				y=(yn);
   				nvy=0;
   			}
   			if(dchange){
   				y=yn;
   				x=xn;
   				if(abs(vx)>abs(vy)){nvy=0;}
   				else nvx=0;
   			}
   			vx=nvx;
   			vy=nvy;
    		y-=vy;
    		x+=vx;
    		//if (y<0)vy=y=0;
    		//0if (y>HEIGHT-spriteH){y=HEIGHT-spriteH;}
    		//if (x<0)x=0;
    		//if (x>WIDTH-spriteW)x=WIDTH-spriteW;
    		if(!Collisions.collides(this, new Wall(0,0,Room.WIDTH,Room.HEIGHT))){
    			player.life=-1;
    			dead=true;
    		}

			

			vx/=vMultiplier;
			vy/=vMultiplier;
			
			vMultiplier+=.005*(1-vMultiplier>0?1:-1);
			
			//Room.walls.remove(outline);
			//Room.walls.add(outline=new Wall(x+1,y+1,w-2,h-2));
			//out.println("mult "+vMultiplier);
			adjustCamera();
			//out.println("done walking");
		//}
	}
	public static void adjustCamera(){
		int sx=(int)(round(player.x)-Room.cameraX);
    		if (sx<0)sx=0;
    		if (sx+Room.visibleWIDTH>WIDTH)sx=WIDTH-Room.visibleWIDTH;
    		Room.screenX=(int)(sx+Room.cameraXOff);
    		int sy=(int)(round(player.y)-Room.cameraY);
    		if (sy<0)sy=0;
    		if (sy+Room.visibleHEIGHT>HEIGHT)sy=HEIGHT-Room.visibleHEIGHT;
    		Room.screenY=(int)(sy+Room.cameraYOff);
    		
	}
	public final static Clip[]deaths=new Clip[7];
	static {
		for(int i=0;i<deaths.length;i++){
			deaths[i]=AudioPack.newClip("haleScream.wav",.5);
		}
	};
	public static synchronized void damage(int d){
		player.life-=d;
		player.life=min(player.lifeCapacity,player.life);
		if(d>0)
			AudioPack.playClip(deaths[(int)(deaths.length*random())]);
			//Room.items.add(new Health(player.x+player.h/2,player.y+player.w/2,vx,-vy));
		
	}
	public void draw(Graphics g){
		g.drawImage(image, round(x)-4, round(y),null);
	}
}