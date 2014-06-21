package com.example.google;

public class Alien {

	public int SCREEN_WIDTH = 300;
	public int SCREEN_HEIGHT = 700;
	public static int INITIAL_POSITION_X = 10;
	public static int INITIAL_POSITION_Y = 10;
	
	int icon;
	private static Alien instance = null;
	
	int xPosition;
	int yPosition;

	
	public static Alien get(){
		if (instance == null)
			return new Alien(INITIAL_POSITION_X, INITIAL_POSITION_Y);
		return instance;
	}
	
	private Alien (int x, int y){
		xPosition =x;
		yPosition =y;
		icon=R.drawable.ic_launcher;
	}
	
	public String toString(){
		String str = "";
		str +="x: " + xPosition + " y: " + yPosition;
		return str;
		
	}
	
	public int getIcon(){
		return icon;
	}
	
	public void moveUp(int amount){
		yPosition-=amount;
		if (yPosition<0)
			yPosition=0;
	}

	public void moveDown(int amount){
		yPosition+=amount;
		if (yPosition>SCREEN_HEIGHT)
			yPosition=SCREEN_HEIGHT;
	}
	
	public void moveLeft(int amount){
		xPosition-=amount;
		if (xPosition<0)
			xPosition=0;
	}
	
	public void moveRight(int amount){
		xPosition+=amount;
		if (xPosition>SCREEN_WIDTH)
			xPosition=SCREEN_WIDTH;
	}
	
	
}
