package com.example.google;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class Alien {
	
	private static Alien instance = null;
	
	BoundingBox alienBox;
	
	ScreenConstants screenConstants;
	
	Context context;

	
	public static Alien get(Context context){
		if (instance == null){
			
			instance = new Alien(context);
		}
		return instance;
	}
	
	

	
	private Alien (Context context){
		this.context = context;
		screenConstants = ScreenConstants.get(context);
		BoundingBox box = screenConstants.alienBoudingBox;
		alienBox = box.clone();
		
	}
	
	public String toString(){
		String str = "";
		str +="x: " + alienBox.getBeginPositionX() + " y: " + alienBox.getBeginPositionY();
		return str;
		
	}
	
	public void moveUp(int amount){
		alienBox.decrementBeginPositionY(amount);
		if (alienBox.getBeginPositionY()<0)
			alienBox.setBeginPositionY(0);
	}

	public void moveDown(int amount){
		alienBox.incrementBeginPositionY(amount);
		if (alienBox.getBeginPositionY()>screenConstants.GAME_SCREEN_HEIGHT)
			alienBox.setBeginPositionY(screenConstants.GAME_SCREEN_HEIGHT);
	}
	
	
	public void moveLeft(int amount){
		alienBox.decrementBeginPositionX(amount);
		if (alienBox.getBeginPositionX()<0)
			alienBox.setBeginPositionX(0);
	}
	
	
	
	public void moveRight(int amount){
		alienBox.incrementBeginPositionX(amount);
		if (alienBox.getBeginPositionX()>screenConstants.GAME_SCREEN_WIDTH)
			alienBox.setBeginPositionX(screenConstants.GAME_SCREEN_WIDTH);
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
	
	public int getXPosition(){
		return alienBox.beginPositionX;
	}
	
	public int getYPosition(){
		return alienBox.beginPositionY;
	}
	
}
