package com.example.google;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class ScreenConstants {

	Context context;
	public int SCREEN_WIDTH;
	public int SCREEN_HEIGHT;
	public int GAME_SCREEN_WIDTH;
	public int GAME_SCREEN_HEIGHT;
	
	private static ScreenConstants instance = null;
	
	private ScreenConstants(Context context){
		this.context = context;
		setScreenSizes();
		
	}
	
	public static ScreenConstants get(Context context){
		if (instance == null){
			instance = new ScreenConstants(context);
		}
		return instance;
	}
	
	public void setScreenSizes(){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();	
		Point size = new Point();
		display.getSize(size);
		SCREEN_WIDTH = size.x;
		SCREEN_HEIGHT = size.y;
		GAME_SCREEN_WIDTH = SCREEN_WIDTH;
		GAME_SCREEN_HEIGHT = SCREEN_HEIGHT/2;
	}
}
