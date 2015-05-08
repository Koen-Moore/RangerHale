package org.resources;

import static java.lang.System.getProperty;
import static java.lang.System.out;

import java.net.URL;

public class FilePack {
	public static final Object reference=new Object();
	public static final String root=getProperty("user.dir");
	static{
		out.println("root: "+root);
	}
	public static java.net.URL getURL(String a){
		URL x;
		out.println("accessing: "+a);
		try{
			x=new URL("file:/"+root+"/resources/Resources/"+a);
			x.openStream();
			if(x!=null)return x;
		}catch(Exception e){}
		try{
			x= reference.getClass().getResource("/resources/Resources/"+a);
			out.println(x);
			if(x!=null)return x;
		}catch(Exception e){e.printStackTrace();}
		try{
			x=reference.getClass().getResource("/Resources/"+a);
			out.println(x);
			if(x!=null)return x;
			//out.println("cannot be read");
		}catch(Exception e){}
		try{
			x=reference.getClass().getResource("/Resources."+a);
			out.println(x);
			if(x!=null)return x;
			//out.println("cannot be read");
		}catch(Exception e){}
		try{
			x=reference.getClass().getResource("/resources/"+a);
			out.println(x);
			if(x!=null)return x;
			//out.println("cannot be read");
		}catch(Exception e){}
		out.println("bad source!");
		return null;
	}
}