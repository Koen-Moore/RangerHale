package org.resources;
import static java.lang.System.out;
public class GarbageSuggester implements Runnable{
	public static void suggest(){
		out.println("suggesting");
		new Thread(new GarbageSuggester()).start();
	}
	public void run(){
		System.gc();
	}
}
