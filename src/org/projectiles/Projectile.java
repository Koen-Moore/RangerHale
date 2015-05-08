package org.projectiles;

import static java.awt.Color.red;
import static java.lang.Math.floor;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static java.lang.System.out;

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import org.rendering.*;
import org.resources.*;
import org.walls.*;
import org.rooms.*;
import org.enemies.*;

public class Projectile extends LivingObject{
	public static volatile int livePros=0;
	public static volatile int pros=0;
	//public volatile boolean dead=false;
	public static Object sync=new Object();
	public boolean isEnemy=false;
	public volatile float vX,vY;
	//public volatile int life=-1;
	public final static Clip[]booms=new Clip[10];
	static {
		for(int i=0;i<booms.length;i++){
			booms[i]=AudioPack.newClip("BExplosion2-1.wav",.5);
		}
	};
	public final Clip boom=booms[(int)floor(booms.length*random())];
	public Projectile(){}
	public Projectile(float X,float Y,float vx,float vy){
		//image=ani[0];
		dead=false;
		life=lifeCapacity=-2;
		w=2;h=2;color=red;
		x=X;y=Y;vX=vx;vY=vy;  //thread=new Thread(this);thread.start();
		synchronized(sync){livePros++;}
		//try{current.join();}catch(Exception e){e.printStackTrace();exit(0);}
	}
	public void finalize(){
		dead=true;synchronized(sync){pros++;}
		out.println("prodead "+pros+" / "+livePros);
	}
	public boolean dead(){
		return dead;
	}
	public static final BufferedImage[] fireani=new BufferedImage[]{
		ImagePack.getImage("MiniFireballLeft.png"),
		ImagePack.getImage("MiniFireballRight.png"),
		ImagePack.getImage("MiniFireballUp.png"),
		ImagePack.getImage("MiniFireballDown.png"),
		ImagePack.getImage("MiniFireballDRight.png"),
		ImagePack.getImage("MiniFireballDLeft.png"),
		ImagePack.getImage("MiniFireballURight.png"),
		ImagePack.getImage("MiniFireballULeft.png"),
		ImagePack.getImage("splosion.png"),
		ImagePack.getImage("splosion2.png"),
		ImagePack.getImage("splosion3.png"),
		ImagePack.getImage("splosion4.png"),
	};
	public static final BufferedImage[] bigfireani=new BufferedImage[]{
		ImagePack.getImage("fireball/FireballLeft.png"),
		ImagePack.getImage("fireball/FireballRight.png"),
		ImagePack.getImage("fireball/FireballUp.png"),
		ImagePack.getImage("fireball/FireballDown.png"),
		ImagePack.getImage("fireball/FireballDRight.png"),
		ImagePack.getImage("fireball/FireballDLeft.png"),
		ImagePack.getImage("fireball/FireballURight.png"),
		ImagePack.getImage("fireball/FireballULeft.png"),
		ImagePack.getImage("fireball/splosion1.png"),
		ImagePack.getImage("fireball/splosion2.png"),
		ImagePack.getImage("fireball/splosion3.png"),
		ImagePack.getImage("fireball/splosion4.png"),
		ImagePack.getImage("fireball/splosion5.png"),
		ImagePack.getImage("fireball/splosion6.png"),
	};
	public static final BufferedImage[]airani=new BufferedImage[]{
		ImagePack.getImage("Airshot/airbulletL.png"),
		ImagePack.getImage("Airshot/airbulletR.png"),
		ImagePack.getImage("Airshot/airbulletU.png"),
		ImagePack.getImage("Airshot/airbulletD.png"),
		ImagePack.getImage("Airshot/airbulletDR.png"),
		ImagePack.getImage("Airshot/airbulletDL.png"),
		ImagePack.getImage("Airshot/airbulletUR.png"),
		ImagePack.getImage("Airshot/airbulletUL.png"),
		ImagePack.getImage("AirBall/AirSplosion.png"),
		ImagePack.getImage("AirBall/AirSplosion2.png"),
		ImagePack.getImage("AirBall/AirSplosion3.png"),
		ImagePack.getImage("AirBall/AirSplosion4.png"),
	};
	public static final BufferedImage[][] rockani=new BufferedImage[][]{
		{
			ImagePack.getImage("rockPro/rock1.png"),
			ImagePack.getImage("rockPro/rockExp1.png"),
			ImagePack.getImage("rockPro/rockSupExp1.png"),
			ImagePack.getImage("rockPro/rockMegExp1.png"),
			ImagePack.getImage("rockPro/rockFade1.png"),
			ImagePack.getImage("rockPro/rockLast1.png"),
		},
		{
			ImagePack.getImage("rockPro/rock2.png"),
			ImagePack.getImage("rockPro/rockExp2.png"),
			ImagePack.getImage("rockPro/rockSupExp2.png"),
			ImagePack.getImage("rockPro/rockMegExp2.png"),
			ImagePack.getImage("rockPro/rockFade2.png"),
			ImagePack.getImage("rockPro/rockLast2.png"),
		},
		{
			ImagePack.getImage("rockPro/rock3.png"),
			ImagePack.getImage("rockPro/rockExp3.png"),
			ImagePack.getImage("rockPro/rockSupExp3.png"),
			ImagePack.getImage("rockPro/rockMegExp3.png"),
			ImagePack.getImage("rockPro/rockFade3.png"),
			ImagePack.getImage("rockPro/rockLast3.png"),
		},
		{
			ImagePack.getImage("rockPro/rock4.png"),
			ImagePack.getImage("rockPro/rockExp4.png"),
			ImagePack.getImage("rockPro/rockSupExp4.png"),
			ImagePack.getImage("rockPro/rockMegExp4.png"),
			ImagePack.getImage("rockPro/rockFade4.png"),
			ImagePack.getImage("rockPro/rockLast4.png"),
		}
	};
	public static final BufferedImage[] bigrockani=new BufferedImage[]{
		ImagePack.getImage("Superrock/superrock0.png"),
		ImagePack.getImage("Superrock/superrock45.png"),
		ImagePack.getImage("Superrock/superrock90.png"),
		ImagePack.getImage("Superrock/superrock135.png"),
		ImagePack.getImage("Superrock/superrock180.png"),
		ImagePack.getImage("Superrock/superrock225.png"),
		ImagePack.getImage("Superrock/superrock270.png"),
		ImagePack.getImage("Superrock/superrock315.png"),
		ImagePack.getImage("rockPro/rockExp4.png"),//8
		ImagePack.getImage("rockPro/rockSupExp4.png"),
		ImagePack.getImage("rockPro/rockMegExp4.png"),
		ImagePack.getImage("rockPro/rockFade4.png"),
		ImagePack.getImage("rockPro/rockLast4.png"),//12
	};
	public static final BufferedImage[] iceani=new BufferedImage[]{
		ImagePack.getImage("icebulletL.png"),
		ImagePack.getImage("icebulletR.png"),
		ImagePack.getImage("icebulletU.png"),
		ImagePack.getImage("icebulletD.png"),
		ImagePack.getImage("icebulletUL.png"),
		ImagePack.getImage("icebulletUR.png"),
		ImagePack.getImage("icebulletDL.png"),
		ImagePack.getImage("icebulletDR.png"),
		ImagePack.getImage("icesplosion.png"),
		ImagePack.getImage("icesplosion1.png"),
		ImagePack.getImage("icesplosion2.png"),
		ImagePack.getImage("icesplosion3.png"),
	};
	public static final BufferedImage[] waterani=new BufferedImage[]{
		ImagePack.getImage("waterwave/waterwaveL.png"),
		ImagePack.getImage("waterwave/waterwaveR.png"),
		ImagePack.getImage("waterwave/waterwaveU.png"),
		ImagePack.getImage("waterwave/waterwaveD.png"),
		ImagePack.getImage("waterwave/waterwaveDR.png"),
		ImagePack.getImage("waterwave/waterwaveDL.png"),
		ImagePack.getImage("waterwave/waterwaveUR.png"),
		ImagePack.getImage("waterwave/waterwaveUL.png"),
		ImagePack.getImage("icesplosion.png"),
		ImagePack.getImage("icesplosion1.png"),
		ImagePack.getImage("icesplosion2.png"),
		ImagePack.getImage("icesplosion3.png"),
	};
	public static final BufferedImage[]waterbubbleani=new BufferedImage[]{
		ImagePack.getImage("waterbubble/waterwaveL0.png"),
		ImagePack.getImage("waterbubble/waterwaveL1.png"),
		ImagePack.getImage("waterbubble/waterwaveL2.png"),
		ImagePack.getImage("waterbubble/waterwaveL3.png"),
		ImagePack.getImage("waterbubble/splash0.png"),
		ImagePack.getImage("waterbubble/splash1.png"),
		ImagePack.getImage("waterbubble/splash2.png"),
		ImagePack.getImage("waterbubble/splash3.png"),
		ImagePack.getImage("waterbubble/splash4.png"),
	};
	public static final BufferedImage[]lazerani=new BufferedImage[]{
		ImagePack.getImage("Robot/lasplo1.png"),
		ImagePack.getImage("Robot/lasplo2.png"),
		ImagePack.getImage("Robot/lasplo3.png"),
		ImagePack.getImage("Robot/lasplo4.png"),
		ImagePack.getImage("Robot/lasplo5.png"),
	};
	public static final  BufferedImage[]slurgani=new BufferedImage[]{
		ImagePack.getImage("slurgSpit/slurgSpitu.png"),
		ImagePack.getImage("slurgSpit/slurgSpitd.png"),
		ImagePack.getImage("slurgSpit/slurgSpitl.png"),
		ImagePack.getImage("slurgSpit/slurgSpitr.png"),
	};
	public static final BufferedImage[]rocketani=new BufferedImage[]{
		ImagePack.getImage("Robot/rocketL1.png"),
		ImagePack.getImage("Robot/rocketL2.png"),
		ImagePack.getImage("Robot/rocketR1.png"),
		ImagePack.getImage("Robot/rocketR2.png"),
		fireani[8],
		fireani[9],
		fireani[10],
		fireani[11],
	};
	public static final BufferedImage[]tentani=new BufferedImage[]{
		ImagePack.getImage("waterlevel/tentshotL.png"),
		ImagePack.getImage("waterlevel/tentshotR.png"),
	};
	public static void load(){
		
	}
	public void run(){
		//boolean frame=Clock.frame;
		if(life!=0){
			//image=ani[vX<0?0:1];
			//if(life>0)image=ani[2];
			//Clock.waitFor(frame=!frame);
			//if(Clock.dead)break;
			if (life<0){x+=vX;y-=vY;}
			life--;
			if (!(x>0&&x<Jump.WIDTH&&y>0&&y<Jump.HEIGHT)&&life<0){vY=vX=0; life=10; w=h=8;
				x-=round(10*random());
				y-=round(10*random());
				//AudioPack.playAudio("BExplosion2.wav",0.05);
				AudioPack.playClip(boom);
			}
			for (Wall wal:Room.walls){
    			if (vY==0&&vX==0)break;
    			if ((x<wal.x+wal.w&&x+w>wal.x)&&(y<wal.y+wal.h&&y+h>wal.y)){
    				vY=vX=0; life=10; w=h=8;x-=round(10*random());
    				y-=round(10*random());
    				//AudioPack.playAudio("BExplosion2.wav",0.05);
					AudioPack.playClip(boom);
    				if (wal.damagable){w=h=16; ((DamageableWall)wal).life--;
    					//if (Jump.kraidLife<=0&&Jump.countdown<0){Jump.countdown=500;
    					//AudioPack.playAudio("Ima Firen Mah Lazor!.wav",0.1);
    					//}
    				}
    			}
    		}
    		
    		synchronized(Room.enemies){
    		for (VisibleObject en:Room.enemies){
    			if((x<en.x+en.w&&x+w>en.x)&&(y<en.y+en.h&&y+h>en.y)){
    				if(life<0)
	    				((Enemy)en).damage(1);
	    			if(vX!=0||vY!=0){
	    				x=en.x+en.w/2;
	    				vY=vX=0; life=10; w=h=16; 
	    				y=en.y+en.h/2;
	    				x-=2+round(10*random());
	    				y-=2+round(10*random());
	    				//AudioPack.playAudio("BExplosion2.wav",0.1);
						AudioPack.playClip(boom);
	    			}
    			}
    		}
    		}
		}
		else dead=true;
	}
}