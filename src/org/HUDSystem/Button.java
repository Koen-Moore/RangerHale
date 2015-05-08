package org.HUDSystem;
import java.awt.image.BufferedImage;

import org.resources.*;

public class Button extends VisibleObject{
	public boolean selected;
	public BufferedImage[]ani;
	public Button(){
		
	}
	public Button(BufferedImage a,BufferedImage b){
		this(0, 0, a, b);
	}
	public Button(int X,int Y,BufferedImage a,BufferedImage b){
		x=X;y=Y;
		ani=new BufferedImage[]{
			a,b
		};
		w=a.getWidth();
		h=a.getHeight();
		image=a;
		selected=false;
	}
	public void select(){
		selected=true;
		image=ani[1];
	}
	public void deSelect(){
		selected=false;
		image=ani[0];
	}
}
