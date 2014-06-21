package com.example.google;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class ScreenConstants {

	Context context;
	
	public BoundingBox alienBoudingBox, runButtonBoundingBox, goalBoundingBox, gridBoundingBox;
	
	public int SCREEN_WIDTH;
	public int SCREEN_HEIGHT;
	public int GAME_SCREEN_WIDTH;
	public int GAME_SCREEN_HEIGHT;
	
	public int RUN_BUTTON_X;
	public int RUN_BUTTON_Y;
	public int RUN_BUTTON_WIDTH = 260;
	public int RUN_BUTTON_HEIGHT = 150;

	
	public static int GRID_POSITION_X = 60;
	public static int GRID_POSITION_Y = 0;
	public static int GRID_WIDTH = 600;
	public static int GRID_HEIGHT = 600;
	
	public int COMMAND_BUTTON_WIDTH = 100;
	public int COMMAND_BUTTON_HEIGHT = 100;
	
	public int COMMAND_OFFSET = 0;
	public int MARGIN = 60;
	
	public static int ALIEN_INITIAL_POSITION_X = 60;
	public static int ALIEN_INITIAL_POSITION_Y = 0;
	public static int ALIEN_WIDTH = 99;
	public static int ALIEN_HEIGHT = 99;
	public int ALIEN_STEP = 100;
	
	public static int GOAL_POSITION_X;
	public static int GOAL_POSITION_Y;
	public static int GOAL_WIDTH = 100;
	public static int GOAL_HEIGHT = 100;
	
	
	public static int VICTORY_SCREEN_POSITION_X;
	public static int VICTORY_SCREEN_POSITION_Y;
	public static int VICTORY_SCREEN_WIDTH = 500;
	public static int VICTORY_SCREEN_HEIGHT = 600;
	
	private static ScreenConstants instance = null;
	
	private ScreenConstants(Context context){
		this.context = context;
		setScreenSizes();
		createBoudingBoxes();
		
	}
	
	public void createBoudingBoxes(){
		alienBoudingBox = new BoundingBox(ALIEN_INITIAL_POSITION_X, ALIEN_INITIAL_POSITION_Y, ALIEN_WIDTH, ALIEN_HEIGHT);
		runButtonBoundingBox = new BoundingBox(RUN_BUTTON_X, RUN_BUTTON_Y, RUN_BUTTON_WIDTH, RUN_BUTTON_HEIGHT);
		goalBoundingBox = new BoundingBox(GOAL_POSITION_X, GOAL_POSITION_Y, GOAL_WIDTH, GOAL_HEIGHT);
		gridBoundingBox = new BoundingBox(GRID_POSITION_X, GRID_POSITION_Y, GRID_WIDTH, GRID_HEIGHT);
		
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
		RUN_BUTTON_X = SCREEN_WIDTH - RUN_BUTTON_WIDTH - MARGIN;
		RUN_BUTTON_Y = GAME_SCREEN_HEIGHT+MARGIN;
		//GOAL_POSITION_X = GRID_POSITION_X + GRID_WIDTH - GOAL_WIDTH;
		//GOAL_POSITION_Y = GRID_POSITION_Y + GRID_HEIGHT - GOAL_HEIGHT;
		GOAL_POSITION_X = 160;
		GOAL_POSITION_Y = 100;
		//VICTORY_SCREEN_POSITION_X = SCREEN_WIDTH/2 - VICTORY_SCREEN_WIDTH/2;
		//VICTORY_SCREEN_POSITION_Y = SCREEN_HEIGHT/2 - VICTORY_SCREEN_HEIGHT/2;
		VICTORY_SCREEN_POSITION_X = 0;
		VICTORY_SCREEN_POSITION_Y = 0;
	}
}
