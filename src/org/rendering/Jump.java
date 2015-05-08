package org.rendering;

import static java.lang.Math.round;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import org.resources.*;
import org.rooms.*;

public class Jump{
	public static volatile boolean print=false;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	public static int screenWIDTH = ((int) tk.getScreenSize().width);
	public static int screenHEIGHT=((int) tk.getScreenSize().height);
	public static int WIDTH,HEIGHT;
    public static int spriteW=Room.spriteW, spriteH=Room.spriteH;
	public static int refRate=GraphicsEnvironment
		.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
	public static void startDisplay(){
		createAndShowGUI();
    	Clock.stop();
	}
	public static GCanvas canvas;
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Super Awesome Game Dog");
        frame.setIconImage(ImagePack.getImage("crab1.png"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWIDTH,screenHEIGHT);
        //frame.setSize(100,100);
        frame.setUndecorated(true);
    	canvas=new GCanvas(new Color(5,25,50));
    	canvas.setBackground(new Color(5,25,50));
    	canvas.setFocusable(true);
    	canvas.addKeyListener(new KeyList());
    	canvas.setFocusTraversalKeysEnabled(false);
    	frame.getContentPane().add(canvas);
        frame.setVisible(true);
    }

    
    public static BufferedImage printScreen;
    public static BufferedImage screen;
    public static Graphics2D g;
    public static Graphics2D g2;
    public static void buildGraphics(){
    	WIDTH=Room.WIDTH; HEIGHT=Room.HEIGHT;
    	printScreen=new BufferedImage(WIDTH,HEIGHT,5);
    	g=printScreen.createGraphics();
    	screen=new BufferedImage(WIDTH,HEIGHT,5);
    	g2=screen.createGraphics();
    	g2.setColor(Color.red);
    	canvas.build();
    }
    public static void disposeGraphics(){
    	g.dispose();
    	g2.dispose();
    }


   
    //public static ArrayList<Projectile> projectiles=Room.projectiles;
    //public static ArrayList<Enemy>enemies=Room.enemies;
    //public static wall[]walls;
    public static void render(){
    	/*WIDTH=Room.WIDTH; HEIGHT=Room.HEIGHT;
    	//projectiles=Room.projectiles;
		//countdown=-1;kraidLife=100;
		//walls=Room.walls;
    	
    	g2.setColor(Color.RED);
		out.println("rendering");
		long time=currentTimeMillis();
    	Clock.start();
    	int frames=0;
    	while(!Room.curRoom.isDead()){
    		frames++;
    		print=true;*/

    		//enemies=Room.enemies;

    		//g2.drawImage(back,0,0,null);
    		//g2.drawImage(Player.image,round(Player.x),round(Player.y),null);

    		/*synchronized(Room.curRoom){
	    		for (int i=0;i<enemies.size();i++){
	    			Enemy en=enemies.get(i);
	    			if (!en.dead)
	    				g2.drawImage(en.image,round(en.x),round(en.y),null);
	    			else{
	    				enemies.remove(i);
	    				i--;
	    			}
	    		}

	    		//out.println(projectiles.size());
	    		for (int i=0;i<projectiles.size();i++){
	    			Projectile pro=projectiles.get(i);
	    			if (pro==null)continue;
	    			if (!pro.dead()){
	    				if(pro.isEnemy)g2.setColor(blue);else {g2.setColor(red);}
	    				g2.drawRect(round(pro.x)-pro.w/2,round(pro.y)-pro.h/2,pro.w,pro.h);
	    			}
	    			else{
	    				//try{pro.thread.join();}catch(Exception e){e.printStackTrace();exit(1);}
	    				projectiles.remove(i);
	    				i--;
	    			}
	    		}
    		}*/


    		
    		VisibleObject v=null;
    		//synchronized(Room.visibleObjects){
    			for(int i=0;i<Room.visibleObjects.size();i++){
    				for(int x=0;x<Room.visibleObjects.get(i).size();x++){
    					synchronized(Room.visibleObjects.get(i)){
    						if(x<Room.visibleObjects.get(i).size())
   								v=Room.visibleObjects.get(i).get(x);
    					}
    						//out.print(v instanceof Enemy);
						if(v==null)continue;
						//if(v.image!=null){
							//out.print("enemy");
							//g2.drawImage(v.image,null,round(v.x),round(v.y));
							v.draw(g2);
						/*}
						else {
							Color old=g2.getColor();
							g2.setColor(v.color);
							g2.drawRect(round(v.x)-v.w/2,round(v.y)-v.h/2,v.w,v.h);
							g2.setColor(old);
						}*/
    						//out.println();
    				}
    			}
    		//}
    		
    		
    		/*g2.setColor(blue);
    		g2.drawRect(Room.screenX-1,Room.screenY,101,3);
    		g2.fillRect(Room.screenX+0,Room.screenY,Player.life,3);
    		g2.setColor(red);
    		g2.drawRect(Room.screenX+Room.visibleWIDTH-101,Room.screenY,101,3);
    		if(Room.curRoom instanceof Room1)
    			g2.fillRect(Room.screenX+Room.visibleWIDTH-Room1.kraid.life,Room.screenY,100,3);
			*/
    		
			/*g2.setColor(white);
			int curTime=(int)(currentTimeMillis()-time);
			g2.drawString(Integer.toString(curTime),Room.screenX+Room.visibleWIDTH/2-30,Room.screenY+10);
    		*/
    		//g2.drawImage(Room.hud.image, Room.screenX+round(Room.hud.x),Room.screenY+round(Room.hud.y),null);

    		g2.translate(Room.screenX,Room.screenY);
    		Room.curHUD.draw(g2);
    		g2.translate(-Room.screenX,-Room.screenY);
			g.drawImage(screen,Room.cameraXOff,Room.cameraYOff,Room.visibleWIDTH+Room.cameraXOff,Room.visibleHEIGHT+Room.cameraYOff,Room.screenX,Room.screenY,Room.screenX+Room.visibleWIDTH,Room.screenY+Room.visibleHEIGHT,null);
    		//out.println("entering hud");
    		//Room.hud.reload();


			canvas.repaint();
    		
    		//print=true;
    		//out.println("done printing");
    		//new Thread(Player.player).start();
    		//Clock.waitFor();

    		//if (KeyList.keyboard[61]&&Room.visibleHEIGHT>400){Room.visibleHEIGHT-=5;Room.visibleWIDTH-=5;}
    		//if (KeyList.keyboard[45]&&Room.visibleHEIGHT<500){Room.visibleHEIGHT+=5;Room.visibleWIDTH+=5;}
    	/*}
    	out.println(frames*1000.0/(currentTimeMillis()-time));
    	Clock.start();
    	g.setColor(white);
    	if (Player.player.life<=0)

    		g.drawString("You Done Lost...",Room.visibleWIDTH/2-48,Room.visibleHEIGHT/2);
    		//g.drawString("You Done Lost...",Room.cameraX+Room.visibleWIDTH/2-30,Room.cameraY+Room.visibleHEIGHT/2-10);
    	else
    		g.drawString("giành chien thang!!",Room.visibleWIDTH/2-48,Room.visibleHEIGHT/2);
    	canvas.repaint();
    	Clock.waitFor();Clock.waitFor();
    	Clock.stop();
    	canvas.repaint();
    	g.dispose();
    	g2.dispose();
   
    	//printScreen=screen=back=null;*/
    }
}