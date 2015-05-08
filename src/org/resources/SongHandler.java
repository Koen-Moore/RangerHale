package org.resources;

public class SongHandler implements Runnable{
	public static String[]songs;
	public static volatile boolean stop;
	public static Thread t;
	public void run(){
		while(!stop){
			AudioPack.startSong(FilePack.getURL(songs[(int)(Math.random()*songs.length)]), 1);
		}
	}
	public static void shuffleSongs(String []a){
		songs=a;
		stop=true;
		AudioPack.kill=true;
		if(t!=null)try{t.join();}catch(Exception e){}
		stop=AudioPack.kill=false;
		(t=new Thread(new SongHandler())).start();
	}

}
