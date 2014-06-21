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
	public int RUN_BUTTON_X;
	public int RUN_BUTTON_Y;
	public int RUN_BUTTON_WIDTH;
	public int RUN_BUTTON_HEIGHT;
	
	public int ALIEN_STEP = 30;
	
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
		RUN_BUTTON_X = SCREEN_WIDTH-150;
		RUN_BUTTON_Y = GAME_SCREEN_HEIGHT;
		RUN_BUTTON_HEIGHT = 200;
		RUN_BUTTON_WIDTH = 100;
	}
}
