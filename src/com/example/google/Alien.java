package com.example.google;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class Alien {

	public static int INITIAL_POSITION_X = 10;
	public static int INITIAL_POSITION_Y = 10;
	
	int icon;
	private static Alien instance = null;
	
	int xPosition;
	int yPosition;
	
	ScreenConstants screenConstants;
	
	Context context;

	
	public static Alien get(Context context){
		if (instance == null){
			
			instance = new Alien(INITIAL_POSITION_X, INITIAL_POSITION_Y, context);
		}
		return instance;
	}
	
	

	
	private Alien (int x, int y, Context context){
		this.context = context;
		screenConstants = ScreenConstants.get(context);
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
		if (yPosition>screenConstants.GAME_SCREEN_HEIGHT)
			yPosition=screenConstants.GAME_SCREEN_HEIGHT;
	}
	
	public void moveLeft(int amount){
		xPosition-=amount;
		if (xPosition<0)
			xPosition=0;
	}
	
	public void moveRight(int amount){
		xPosition+=amount;
		if (xPosition>screenConstants.GAME_SCREEN_WIDTH)
			xPosition=screenConstants.GAME_SCREEN_WIDTH;
	}
	
	
	public void executeCommand(MoveCommand command){
		switch (command.getDirection()){
		case UP:
			moveUp(screenConstants.ALIEN_STEP);
			break;
		case DOWN:
			moveDown(screenConstants.ALIEN_STEP);
			break;
		case RIGHT:
			moveRight(screenConstants.ALIEN_STEP);
			break;
		case LEFT:
			moveLeft(screenConstants.ALIEN_STEP);
			break;
		}
	}
	
}
