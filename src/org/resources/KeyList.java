package org.resources;

import static java.lang.System.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyList implements KeyListener{
	public static Object sync=new Object();
	public static boolean[] keyboard=new boolean[KeyEvent.CHAR_UNDEFINED+1];
	public KeyList(){
		Arrays.fill(keyboard,false);
	}
	public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {

    	out.println(e.getKeyChar()+" "+e.getKeyCode());
    	if (e.getKeyCode()==27){System.exit(0);}
    	keyboard[e.getKeyCode()]=true;
    	keyEvent();
    }
    public void keyReleased(KeyEvent e) {
    	keyboard[e.getKeyCode()]=false;
    	keyEvent();
    }
    public void keyEvent(){
    	try{synchronized(sync){
    		sync.notifyAll();
    	}}catch(Exception e){e.printStackTrace();exit(1);}
    }
    public static void waitFor(int num){
    	while(!keyboard[num]){
    		try{synchronized(sync){
    			sync.wait(100);
    		}}catch(Exception e){e.printStackTrace();exit(1);}
    	}
    }
}