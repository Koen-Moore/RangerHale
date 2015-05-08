package org.main;

import static java.lang.System.exit;
import static java.lang.System.out;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import org.HUDSystem.LoadMenu;
import org.HUDSystem.MainMenu;
import org.enemies.Enemy;
import org.players.Player;
import org.projectiles.Projectile;
import org.rendering.Jump;
import org.resources.BufferedSaves;
import org.resources.KeyList;
import org.resources.SaveState;
import org.resources.SongHandler;
import org.resources.VisibleObject;
import org.rooms.AirBossRoomEasy;
import org.rooms.AirBossRoomHard;
import org.rooms.AirRoom0;
import org.rooms.AirRoom1;
import org.rooms.AirRoom10;
import org.rooms.AirRoom11;
import org.rooms.AirRoom12;
import org.rooms.AirRoom13;
import org.rooms.AirRoom14;
import org.rooms.AirRoom15;
import org.rooms.AirRoom2;
import org.rooms.AirRoom3;
import org.rooms.AirRoom4;
import org.rooms.AirRoom5;
import org.rooms.AirRoom6;
import org.rooms.AirRoom8;
import org.rooms.AirRoom9;
import org.rooms.Bridge;
import org.rooms.EarthBossRoomEasy;
import org.rooms.EarthBossRoomHard;
import org.rooms.EarthRoom0;
import org.rooms.EarthRoom1;
import org.rooms.EarthRoom10;
import org.rooms.EarthRoom11;
import org.rooms.EarthRoom12;
import org.rooms.EarthRoom13;
import org.rooms.EarthRoom14;
import org.rooms.EarthRoom15;
import org.rooms.EarthRoom16;
import org.rooms.EarthRoom2;
import org.rooms.EarthRoom3;
import org.rooms.EarthRoom4;
import org.rooms.EarthRoom5;
import org.rooms.EarthRoom6;
import org.rooms.EarthRoom7;
import org.rooms.EarthRoom8;
import org.rooms.EarthRoom9;
import org.rooms.FireBossRoomEasy;
import org.rooms.FireBossRoomHard;
import org.rooms.FireRoom0;
import org.rooms.FireRoom1;
import org.rooms.FireRoom10;
import org.rooms.FireRoom11;
import org.rooms.FireRoom12;
import org.rooms.FireRoom13;
import org.rooms.FireRoom14;
import org.rooms.FireRoom15;
import org.rooms.FireRoom16;
import org.rooms.FireRoom2;
import org.rooms.FireRoom3;
import org.rooms.FireRoom4;
import org.rooms.FireRoom5;
import org.rooms.FireRoom6;
import org.rooms.FireRoom7;
import org.rooms.FireRoom8;
import org.rooms.FireRoom9;
import org.rooms.JumpTeachRoom;
import org.rooms.JumpTeachRoom2;
import org.rooms.MainMenuRoom;
import org.rooms.RobotBoss;
import org.rooms.RobotRoom0;
import org.rooms.RobotRoom1;
import org.rooms.RobotRoom2;
import org.rooms.RobotRoom3;
import org.rooms.RobotRoom4;
import org.rooms.RobotRoom5;
import org.rooms.RobotRoom6;
import org.rooms.RobotRoom7;
import org.rooms.RobotRoom8;
import org.rooms.Room;
import org.rooms.Room0;
import org.rooms.Room1;
import org.rooms.Room2;
import org.rooms.Room3;
import org.rooms.Room4;
import org.rooms.Room5;
import org.rooms.StartingRoom;
import org.rooms.SupplyRoom;
import org.rooms.WaterBossRoomEasy;
import org.rooms.WaterBossRoomHard;
import org.rooms.WaterRoom0;
import org.rooms.WaterRoom1;
import org.rooms.WaterRoom10;
import org.rooms.WaterRoom11;
import org.rooms.WaterRoom12;
import org.rooms.WaterRoom13;
import org.rooms.WaterRoom14;
import org.rooms.WaterRoom15;
import org.rooms.WaterRoom2;
import org.rooms.WaterRoom3;
import org.rooms.WaterRoom4;
import org.rooms.WaterRoom5;
import org.rooms.WaterRoom6;
import org.rooms.WaterRoom7;
import org.rooms.WaterRoom8;
import org.rooms.WaterRoom9;

public class RoomRunner{
	public static void main(String[]args)throws IOException{
		try{
			begin();
		}catch(Throwable t){t.printStackTrace();exit(1);}
	}

	public static boolean goToMainMenu=false,goToLoadMenu=false;
	public static SaveState playState=new SaveState();
	public static void saveGame(){
		try{
			playState.health=Player.player.life;
			playState.healthCapacity=Player.player.lifeCapacity;
			playState.projectiles=Player.unlockedProjectiles;

			BufferedSaves.saveState(playState);
		}catch(Exception e){}
	}
	public static void loadSave(String a){
		try{
			playState=BufferedSaves.getSaveState(a);
			Player.player.life=playState.health;
			Player.player.lifeCapacity=playState.healthCapacity;
			Player.unlockedProjectiles=playState.projectiles;
			Player.currentProjectile=0;
		}catch(Exception e){}
	}
	public static void begin() {
		JFrame p=new JFrame("Loading");
		p.setSize(400,70);
		//p.setSize(Jump.screenWIDTH,Jump.screenHEIGHT-20);
		p.setLocation(Jump.screenWIDTH/2-200,Jump.screenHEIGHT/2-25);
		//p.setLocation(0,0);
		JProgressBar j=new JProgressBar(0,100);
		j.setVisible(true);
		//j.setStringPainted(true);
		p.getContentPane().add(j);
		p.setVisible(true);
		for(Mixer.Info m:AudioSystem.getMixerInfo()){
			out.println(m);
		}
		j.setValue(3);
		//out.println(getProperty("user.dir"));
    	new KeyList();
		j.setValue(10);
		Projectile.load();
		j.setValue(40);
		Enemy.load();
		j.setValue(60);
		Room.reset();
    	Room[]rooms=new Room[]{
    		new RobotRoom0(),
    		new SupplyRoom(),
    		new RobotBoss(),
    		new EarthRoom0(),
    		new WaterBossRoomHard(),
    		new WaterBossRoomEasy(),

        		new FireRoom1(),
        		new FireRoom2(),
        		/*new FireRoom3(),
        		new FireRoom4(),
        		new FireRoom5(),
        		new FireRoom6(),
        		new FireRoom7(),
        		new FireRoom8(),
        		new FireRoom9(),
        		new FireRoom10(),
        		new FireRoom11(),
        		new FireRoom12(),
        		new FireRoom13(),
        		new FireRoom14(),*/
        		new FireRoom15(),
        		new FireRoom16(),
    		new AirRoom0(),
    		new AirRoom1(),
    		new AirRoom2(),
    		new AirRoom3(),
    		new AirRoom15(),
    		new AirRoom14(),
    		new AirRoom13(),
    		new AirRoom12(),
    		new AirRoom11(),
    		new AirRoom10(),
    		new AirRoom9(),
    		new AirRoom8(),
    		new AirRoom6(),
    		new AirRoom5(),
    		new AirRoom4(),
    		new FireBossRoomHard(),
    		new FireBossRoomEasy(),
    		new AirBossRoomHard(),
    		new AirBossRoomEasy(),
      		new EarthBossRoomEasy(),
    		new EarthBossRoomHard(),
    		new WaterRoom0(),
    		new WaterRoom1(),
    		new WaterRoom2(),
    		new WaterRoom3(),
    		new WaterRoom4(),
    		new WaterRoom5(),
    		new WaterRoom6(),
    		new WaterRoom7(),
    		new WaterRoom8(),
    		new WaterRoom9(),
    		new WaterRoom10(),
    		new WaterRoom11(),
    		new FireRoom0(),
    		new EarthRoom0(),
    		new StartingRoom(),
    		new JumpTeachRoom(),
    		new JumpTeachRoom2(),
    		new Bridge(),
    		new EarthRoom16(),
    		new EarthRoom15(),
    		new EarthRoom14(),
    		new EarthRoom13(),
    		new EarthRoom12(),
    		new EarthRoom11(),
    		new EarthRoom10(),
     		new EarthRoom9(),
    		new EarthRoom8(),
    		new EarthRoom7(),
        	new EarthRoom6(),
        	new EarthRoom5(),
        	new EarthRoom4(),
        	new EarthRoom3(),
    		new EarthRoom2(),
    		new EarthRoom1(),
    		new FireRoom14(),
    		new FireRoom1(),
    		new FireRoom4(),
    		new FireRoom6(),
    		new FireRoom7(),
    		new FireRoom10(),
    		new FireBossRoomHard(),
    		new Room3(),
    		//new AirRoomOne(),
    		new Room2(),
    		new Room5(),
    		new Room4(),
    		new Room0(),
    		new Room1()
    	};
    	//playSoundTrack();
		j.setValue(70);
    	for(int i=0;i<rooms.length;i++){
    		Room r=rooms[i];
    		j.setValue(70+i*30/rooms.length);
    		r.load();out.println(r);
    	}
		j.setValue(100);
		p.setVisible(false);
		Jump.startDisplay();
		//loadRobotLevel();
		if(true){
			goToMainMenu=true;
			while(true)
			runState();
		}
		/*
    	while(true){
    		new Player(0,0,Room.spriteW,Room.spriteH,100);
    		for(Room r:rooms){
    			if(goToMainMenu||goToLoadMenu)break;
    			runRoom(r);
    			if(Player.player.life<=0)break;
    		}
    		out.println("waiting");
    		if(goToMainMenu){
    			goToMainMenu=false;
    			loadRoom(new MainMenuRoom());
    			((MainMenu)Room.curHUD).buttonDelay=10;
    			Room.curRoom.startRoom();
    		}
    		else if(goToLoadMenu){
    			goToLoadMenu=false;
    			loadRoom(new MainMenuRoom());
    			((MainMenu)Room.curHUD).buttonDelay=10;
    			Room.curHUD=((MainMenu)Room.curHUD).load;
    			((LoadMenu)Room.curHUD).buttonDelay=10;
    			Room.curRoom.startRoom();
    		}
    		else
    			KeyList.waitFor(32);
    		out.println("end of while");
    		//try{Thread.sleep(5000);}catch(Exception e){e.printStackTrace();exit(0);}
    	}
    	//*/

    }
	public static void playSoundTrack(){
    	SongHandler.shuffleSongs(new String[]{
    		"music/AquaticRuin.wav",
    		"music/DancetoThisSong.wav",
    		"music/DraconianEyes.wav",
    		"music/EnterTheHall.wav",
    		"music/HolyMountains.wav",
    		"music/Justice.wav",
    		"music/Nepenth.wav",
    		"music/Oskar.wav",
    		"music/Seppuku.wav",
    		"music/StrappingYoungLad.wav",
    		"music/TastesLikeKevinBacon.wav"}

    	);
	}
	public static void runState(){

    	for(int r:playState.rooms){
    		getRoom(r).load();out.println(r);
    	}
		new Player(0,0,Room.spriteW,Room.spriteH,playState.health);
		Player.unlockedProjectiles=playState.projectiles;

		//out.println("runstate"+Arrays.toString(Player.unlockedProjectiles));
		Player.player.lifeCapacity=playState.healthCapacity;
		for(;playState.currentRoom<playState.rooms.length;playState.currentRoom++){
			out.println(playState);
			if(goToMainMenu||goToLoadMenu)break;
			runRoom(getRoom(playState.rooms[playState.currentRoom]));
			if(Player.player.life<=0)break;
			if(Room.endFast)break;
		}
		out.println("waiting");
		if(goToMainMenu){
			goToMainMenu=false;
			loadRoom(new MainMenuRoom());
			((MainMenu)Room.curHUD).buttonDelay=10;
			out.println(Room.curRoom);
			Room.curRoom.startRoom();
		}
		else if(goToLoadMenu){
			goToLoadMenu=false;
			loadRoom(new MainMenuRoom());
			((MainMenu)Room.curHUD).buttonDelay=10;
			Room.curHUD=((MainMenu)Room.curHUD).load;
			((LoadMenu)Room.curHUD).buttonDelay=10;
			Room.curRoom.startRoom();
		}
		else if(Player.player.life<=0){

			loadSave(playState.username);
			out.println("dead "+playState);
			KeyList.waitFor(32);
		}
		out.println("end of while");
		//try{Thread.sleep(5000);}catch(Exception e){e.printStackTrace();exit(0);}

	}
	public static void runRoom(Room r){
		loadRoom(r);
		Room.curRoom.startRoom();
	}
	public static void loadRoom(Room r){
		Room.endFast=true;
		Room.reset();out.println("reset");
		Room.curRoom=r;
		Room.curRoom.load();out.println("loaded");
	}
	public static Room getRoom(int i){
		switch(i/100){
		case 0:{
			switch(i%100){
			case 0:return new StartingRoom();
			case 1:return new JumpTeachRoom();
			case 2:return new JumpTeachRoom2();
			case 3:return new Bridge();
			}
		}
		case 1:{
			switch(i%100){
			case 0:return new  FireRoom0();
			case 1:return new FireRoom10();
			case 2:return new FireRoom2();
			case 3:return new  FireRoom3();
			case 4:return new  FireRoom4();
			case 5:return new  FireRoom5();
			case 6:return new  FireRoom6();
			case 7:return new  FireRoom7();
			case 8:return new  FireRoom8();
			case 9:return new  FireRoom9();
			case 10:return new  FireRoom10();
			case 11:return new  FireRoom11();
			case 12:return new FireRoom12();
			case 13:return new  FireRoom13();
			case 14:return new  FireRoom14();
			case 15:return new  FireRoom15();
			case 16:return new  FireRoom16();
			case 17:return new FireBossRoomEasy();
			case 18:return new FireBossRoomHard();
			}
		}
		case 2:{
			switch(i%100){

			case 0:return new WaterRoom0();
			case 1:return new WaterRoom1();
			case 2:return new WaterRoom2();
			case 3:return new WaterRoom3();
			case 4:return new WaterRoom4();
			case 5:return new WaterRoom5();
			case 6:return new WaterRoom6();
			case 7:return new WaterRoom7();
			case 8:return new WaterRoom8();
			case 9:return new WaterRoom9();
			case 10:return new WaterRoom10();
			case 11:return new WaterRoom11();
			case 12:return new WaterRoom12();
			case 13:return new WaterRoom13();
			case 14:return new WaterRoom14();
			case 15:return new WaterRoom15();
			case 16:return new WaterRoom15();
			case 17:return new WaterBossRoomEasy();
			case 18:return new WaterBossRoomHard();
			}
		}
		case 3:{
			switch(i%100){
			case 0:return new EarthRoom0();
			case 1:return new EarthRoom1();
			case 2:return new EarthRoom2();
			case 3:return new EarthRoom3();
			case 4:return new EarthRoom4();
			case 5:return new EarthRoom5();
			case 6:return new EarthRoom6();
			case 7:return new EarthRoom7();
			case 8:return new EarthRoom8();
			case 9:return new EarthRoom9();
			case 10:return new EarthRoom10();
			case 11:return new EarthRoom11();
			case 12:return new EarthRoom12();
			case 13:return new EarthRoom13();
			case 14:return new EarthRoom14();
			case 15:return new EarthRoom15();
			case 16:return new EarthRoom16();
			case 17:return new EarthBossRoomEasy();
			case 18:return new EarthBossRoomHard();
			}
		}
		case 4:{
			switch(i%100){

			case 0:return new AirRoom0();
			case 1:return new AirRoom1();
			case 2:return new AirRoom2();
			case 3:return new AirRoom3();
			case 4:return new AirRoom4();
			case 5:return new AirRoom5();
			case 6:return new AirRoom6();
			case 7:
			case 8:return new AirRoom8();
			case 9:return new AirRoom9();
			case 10:return new AirRoom10();
			case 11:return new AirRoom11();
			case 12:return new AirRoom12();
			case 13:return new AirRoom13();
			case 14:return new AirRoom14();
			case 15:
			case 16:return new AirRoom15();
			case 17:return new AirBossRoomEasy();
			case 18:return new AirBossRoomHard();
			}
		}
		case 5:{
			switch(i%100){
			case 0:return new RobotRoom0();
			case 1:return new RobotRoom1();
			case 2:return new RobotRoom2();
			case 3:return new RobotRoom3();
			case 4:return new RobotRoom4();
			case 5:return new RobotRoom5();
			case 6:return new RobotRoom6();
			case 7:return new RobotRoom7();
			case 8:return new RobotRoom8();
			case 9:return new SupplyRoom();
			case 10:return new RobotBoss();
			}
		}

		}



		return null;
	}
	public static void loadNewGame(String user,String pass){
		playState=new SaveState();
		playState.username=user;
		playState.password=pass;
		playState.currentRoom=0;
		playState.rooms=new int[]{0,1,2,3};
		playState.health=playState.healthCapacity=100;
		playState.projectiles=new int[]{0};
		Room.endFast=true;

		out.println("load"+Arrays.toString(playState.projectiles));
	}
	public static void loadFireLevel(boolean first){

		if(first)
			playState.rooms=appendArray(new int[]{100},appendArray(randomizeArray(Arrays.copyOfRange(shakeArray(new int[]{101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116}),0,8 )),new int[]{117}));
		else
			playState.rooms=appendArray(new int[]{100},appendArray(randomizeArray(Arrays.copyOfRange(shakeArray(new int[]{101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116}),8,16 )),new int[]{118}));
		playState.currentRoom=0;


		Room.endFast=true;
	}
	public static void loadWaterLevel(boolean first){
		if(first)
			playState.rooms=appendArray(new int[]{200},appendArray(randomizeArray(Arrays.copyOfRange(shakeArray(new int[]{201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216}),0,8 )),new int[]{217}));
		else
			playState.rooms=appendArray(new int[]{200},appendArray(randomizeArray(Arrays.copyOfRange(shakeArray(new int[]{201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216}),8,16 )),new int[]{218}));
		playState.currentRoom=0;


		Room.endFast=true;
	}
	public static void loadRockLevel(boolean first){
		if(first)
			playState.rooms=appendArray(new int[]{300},appendArray(randomizeArray(Arrays.copyOfRange(shakeArray(new int[]{301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316}),0,8 )),new int[]{317}));
		else
			playState.rooms=appendArray(new int[]{300},appendArray(randomizeArray(Arrays.copyOfRange(shakeArray(new int[]{301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316}),8,16 )),new int[]{318}));
		playState.currentRoom=0;


		Room.endFast=true;
	}
	public static void loadAirLevel(boolean first){
		if(first)
			playState.rooms=appendArray(new int[]{400},appendArray(randomizeArray(Arrays.copyOfRange(shakeArray(new int[]{401,402,403,404,405,406,408,409,410,411,412,413,414,416}),0,8 )),new int[]{417}));
		else
			playState.rooms=appendArray(new int[]{400},appendArray(randomizeArray(Arrays.copyOfRange(shakeArray(new int[]{401,402,403,404,405,406,408,409,410,411,412,413,414,416}),6,14 )),new int[]{418}));
		playState.currentRoom=0;


		Room.endFast=true;
	}
	public static void loadRobotLevel(){
		playState.rooms=appendArray(new int[]{500},appendArray(randomizeArray(new int[]{
				501,502,503,504,505,506,507,508,
		}),new int[]{
				509,510
		}));
		playState.currentRoom=0;


		Room.endFast=true;
	}
	public static int[] randomizeArray(int[] array){
		Random rgen = new Random();  // Random number generator
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
		return array;
	}
	public static int[] shakeArray(int[] array){
		for (int i=0; i<array.length-1; i++) {
		    if(Math.random()<.3){
		    	int temp = array[i];
		    	array[i] = array[i+1];
		    	array[i+1] = temp;
		    }
		}
		for (int i=array.length-1; i>1; i--) {
		    if(Math.random()<.3){
		    	int temp = array[i];
		    	array[i] = array[i-1];
		    	array[i-1] = temp;
		    }
		}
		return array;
	}
	public static int[] appendArray(int[]a,int []b){
		int[]temp=new int[a.length+b.length];
		for(int i=0;i<a.length;i++){
			temp[i]=a[i];
		}
		for(int i=0;i<b.length;i++){
			temp[a.length+i]=b[i];
		}
		return temp;
	}
}

class Rectange extends VisibleObject{
	public Color color;
}
