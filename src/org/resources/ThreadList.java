package org.resources;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class ThreadList<E> extends Vector<E>implements Iterable<E>{

	private static final long serialVersionUID = 1L;
	public ThreadList(){
		super();
	}
	public ThreadList(Collection<E>c){
		super(c);
	}
	public ThreadList(int a){
		super(a);
	}
	public ThreadList(int a,int b){
		super(a,b);
	}
	public E get(int i){
		try{
			return (E)super.get(i);
		}
		catch(Exception e){
			return lastElement();
		}
	}
	public synchronized Iterator<E> iterator(){
		try{
			return new Vector<E>(this).iterator();
		}catch(Exception e){}
		return null;
	}
}
