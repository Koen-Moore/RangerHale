package org.resources;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.min;
import static java.lang.System.exit;
import static java.lang.System.out;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;



public class AudioPack implements Runnable{
	public URL url;
	public volatile double volume;
	public static volatile int songs=0,liveSongs=0;
	public static Object sync=new Object();
	public volatile boolean done=false;
	public volatile Thread thread;
	public static volatile boolean kill=false;
	//public static final String root="./Resources/";
	public AudioPack(URL path,double v){
		volume=v;url=path;done=false;
		(thread=new Thread(this)).start();//out.println(url);
	}
    public static void playAudio(String path){//if(true)return;
    	playAudio(path,1.0);
    }
    public static void playAudio(String path,Double v){
    	new AudioPack(FilePack.getURL(path),v);
    }
    public static Clip newClip(String path,double v){
    	v=min(1,abs(v));
    	try{
    		AudioInputStream inputStream = AudioSystem.getAudioInputStream(FilePack.getURL(path));
    		//out.println(inputStream.getFormat());
    		
    		//Clip clip=null;
    		Clip clip=(AudioSystem.getClip(AudioSystem.getMixerInfo()[0]));
        	
    		/*byte[]bites=new byte[(int)inputStream.getFrameLength()];
    		for(int i=0;i<bites.length||true;i++){
    			byte b=(byte)inputStream.read();
        		if (b==-1)break;
        		//b=((byte)round((double)b*1.1));
        		bites[i]=b;
        	}
    		clip.open(inputStream.getFormat(),bites,0,bites.length);*/
    		clip.open(inputStream);
        	//out.println(""+bites.length+" "+clip.getFrameLength());
        	//assert true:"/n/nbbbb/n";
        	FloatControl f=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        	//out.println(f);
        	//((BooleanControl)AudioSystem.getMixer(null).getControl(BooleanControl.Type.MUTE)).setValue(true);
        	//((BooleanControl)AudioSystem.getMixer(null).getControl(BooleanControl.Type.MUTE)).setValue(false);
        	float num=((float)min((1-v)*f.getMinimum()/2,f.getMaximum()));
        	out.println(num);
        	f.setValue(num);
        	out.println(f);//out.println(f.getMaximum()+" "+f.getPrecision());
        	clip.stop();
    		return clip;
    	}catch(Exception e){e.printStackTrace();exit(1);}
    	return null;
    }
    public static void playClip(Clip c){
    	synchronized(sync){
    		//out.println("p: "+c.getLongFramePosition());
    		//c.loop(2);
    		//if(c.getLongFramePosition()>c.getFrameLength()*.5||!c.isRunning())
    		if(!c.isRunning()){
    			c.stop();
    			c.setMicrosecondPosition(0);
    		}
    		//c.loop(10);
    		c.start();
    		//try{Thread.sleep(50);}catch(Exception e){e.printStackTrace();exit(1);}
    	}
    }
	public void finalize(){
		synchronized(sync){liveSongs--;}
		try{thread.join();}catch(Exception e){e.printStackTrace();exit(1);}
		out.println("songDead "+liveSongs+" remaining");
	}
	public void run(){
		startSong(url,volume);
	}
	public static void startSong(URL a,double v){
		synchronized(sync){liveSongs++;}//if(true)return;
    	try{
        	AudioInputStream inputStream = AudioSystem.getAudioInputStream(a);
        	SourceDataLine soundLine = AudioSystem.getSourceDataLine(inputStream.getFormat());
        	soundLine.open(inputStream.getFormat());
        	soundLine.start();
        	byte[]buffer=new byte[1000];
        	int bytes=0;
        	//int loops=0;
        	while(bytes!=-1&&!kill){//loops++;
        		soundLine.write(buffer,0,bytes);
        		bytes=inputStream.read(buffer);
        		for (int i=0;i<bytes;i++){
        			buffer[i]=(byte)floor(buffer[i]*v);
        		}
        	}
        	soundLine.drain();
        	soundLine.stop();
        	inputStream.close();
        	//out.println(loops+" "+url);
        	buffer=null;
    	} catch(Exception e){e.printStackTrace();exit(0);}
    	//out.println("song finished");
    	return;
	}
}