package org.resources;
import static java.lang.System.*;
import java.io.*;
import java.util.*;
import java.math.*;

public class SaveWriter {
	private static List<BigInteger>primes=new LinkedList<>();
	private static BigInteger workingSave=BigInteger.ONE;
	private static BigInteger currentPrime=BigInteger.ONE;
	private static String curUser;
	static{primes.add(BigInteger.valueOf(2l));}
	public static void main(String[]args)throws IOException{
		reset();
		Scanner yolo=new Scanner(in);
		out.println("Username?");
		curUser=yolo.next();
		out.println("Password?:");
		String pass=yolo.next();
		yolo.close();
		out.println("calculating");
		if(pass.length()>Byte.MAX_VALUE){
			out.println("Too Big!!");
			exit(1);
		}
		addDynamicString(pass);
		out.println("saving");
		BufferedSaves.updateProfile(curUser, workingSave);
		out.println("done");
	}
	public static void reset(){
		workingSave=BigInteger.ONE;
		currentPrime=BigInteger.valueOf(1l);
	}
	public static BigInteger getSave(){
		return workingSave;
	}
	public static void writeSave()throws IOException{
		File f=new File("./saves.save");
		f.delete();
		f.createNewFile();
		FileWriter fw=new FileWriter(f,true);
		fw.append(""+workingSave.toString(36));
		fw.close();
	}
	public static void addSave()throws IOException{
		FileWriter fw=new FileWriter(new File("saves.save"),true);
		fw.append(workingSave.toString(36)+"\n");
		fw.close();
	}
	public static void add(Boolean b){
		currentPrime=nextPrime(currentPrime);
		if(b)workingSave=workingSave.multiply(currentPrime);
	}
	public static void addDynamicString(String s){
		addByte((byte)s.length());
		addString(s.substring(0,Math.min(s.length(),Byte.MAX_VALUE)));
	}
	public static void addString(String s){
		for(char c:s.toCharArray()){
			addChar(c);
		}
	}
	public static void addIntArray(int[]i){
		addInt(i.length);
		for(int a:i){
			addInt(a);
		}
	}
	public static void addByte(byte b){
		for(int i=0;i<8;i++){
			add((b&1)==1);
			b=(byte)(b>>1);
		}
	}
	public static void addChar(char c){
		addByte((byte)c);
	}

	public static void addShort(short b){
		for(int i=0;i<16;i++){
			add((b&1)==1);
			b=(short)(b>>1);
		}
	}
	public static void addInt(int b){
		for(int i=0;i<32;i++){
			add((b&1)==1);
			b=(int)(b>>1);
		}
	}
	public static void addLong(long b){
		for(int i=0;i<64;i++){
			add((b&1)==1);
			b=(long)(b>>1);
		}
	}
	public static BigInteger nextPrime(BigInteger b){
		if(b.compareTo(primes.get(primes.size()-1))<0){
			for(BigInteger p:primes){
				if(p.compareTo(b)>0)return p;
			}
		}
		main:
		while (true){
			b=b.add(BigInteger.ONE);
			for(BigInteger p:primes){
				if(b.mod(p).equals(BigInteger.ZERO)){
					continue main;
				}
			}
			primes.add(b);
			return b;
		}
	}
}
