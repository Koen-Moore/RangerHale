package org.resources;

import java.net.*;
import static java.lang.System.*;
//import java.util.*;
//import java.io.*;

public class NetPack {
	public static void main(String[] args) throws Exception {
		out.println(InetAddress.getLocalHost());
		InetAddress i = InetAddress.getByName("127.0.0.1");
		out.println(i.isReachable(5000));
		Socket s = new Socket(i, 7);
		out.println(s);
		s.connect(new InetSocketAddress("127.0.0.1", 8888), 5000);
		out.println("connected");
	}

}
