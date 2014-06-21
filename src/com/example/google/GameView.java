package com.example.google;

import java.util.ArrayList;
import java.util.Iterator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView {

	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;
	private int x = 0;
	Alien alien;
	Context context;
	ScreenConstants screenConstants;
	boolean gameStarted = false;
	boolean isRunningCommands = false;
	boolean gameOver = false;
	
	int currentlyExecutingCommand = -1;

	public GameView(Context context) {
		super(context);
		this.context = context;
		this.screenConstants = ScreenConstants.get(context);
		gameLoopThread = new GameLoopThread(this, context);
		holder = getHolder();
		setOnTapListener();
		holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
		alien = Alien.get(context);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		
		if (!gameStarted){
			drawLaunchScreen(canvas);
			return;
		}
		drawGrid(canvas);
		drawMoveCommands(canvas);
		drawRunButton(canvas);
		drawGoal(canvas);
		drawAlien(canvas);
		if (gameOver){
			drawVictoryScreen(canvas);
			drawCode(canvas);
		}
	}
	
	public void drawLaunchScreen(Canvas canvas){
		Bitmap launchBitmap = BitmapFactory.decodeResource(getResources(), Icons.LAUNCH_SCREEN);
		canvas.drawBitmap(launchBitmap, 0, 0, null);
	}

	public void drawGrid(Canvas canvas) {
		Bitmap gridBitmap = BitmapFactory.decodeResource(getResources(),Icons.GRID_ICON);
		BoundingBox gridBox = ScreenConstants.get(context).gridBoundingBox;
		canvas.drawBitmap(gridBitmap, gridBox.beginPositionX,
				gridBox.beginPositionY, null);
	}

	public void drawAlien(Canvas canvas) {
		alien = Alien.get(context);
		Bitmap alienBitmap = BitmapFactory.decodeResource(getResources(),
				Icons.ALIEN_ICON);
		canvas.drawBitmap(alienBitmap, alien.getXPosition(),
				alien.getYPosition(), null);
	}

	public void drawMoveCommands(Canvas canvas) {
		Iterator<MoveCommand> iter = MoveCommands.getCommands()
				.getCommandIterator();
		int commandOffset = screenConstants.COMMAND_OFFSET + screenConstants.MARGIN;
		int commandIndex = 0;
		while (iter.hasNext()) {
			ScreenConstants screenConstants = ScreenConstants.get(context);
			MoveCommand command = iter.next();
			
			Bitmap commandBitmap;
			if (commandIndex == currentlyExecutingCommand){
				commandBitmap = BitmapFactory.decodeResource(getResources(), command.getHighlightedIcon());
			}
			else{
				commandBitmap = BitmapFactory.decodeResource(getResources(), command.getIcon());
			}
			canvas.drawBitmap(commandBitmap, screenConstants.MARGIN, screenConstants.GAME_SCREEN_HEIGHT + commandOffset, null);
			commandOffset += screenConstants.COMMAND_BUTTON_HEIGHT + screenConstants.COMMAND_OFFSET;
			commandIndex ++;
		}

	}

	public void drawRunButton(Canvas canvas) {
		Bitmap runBitmap = BitmapFactory.decodeResource(getResources(),
				Icons.RUN_BUTTON_ICON);
		canvas.drawBitmap(runBitmap, screenConstants.RUN_BUTTON_X,
				screenConstants.RUN_BUTTON_Y, null);
	}

	public void drawGoal(Canvas canvas) {
		Bitmap runBitmap = BitmapFactory.decodeResource(getResources(),
				Icons.GOAL_ICON);
		canvas.drawBitmap(runBitmap, screenConstants.GOAL_POSITION_X, screenConstants.GOAL_POSITION_Y, null);
	}
	
	public void drawVictoryScreen(Canvas canvas){
		Bitmap runBitmap = BitmapFactory.decodeResource(getResources(), Icons.GAME_OVER);
		canvas.drawBitmap(runBitmap, screenConstants.VICTORY_SCREEN_POSITION_X, screenConstants.VICTORY_SCREEN_POSITION_Y, null);
	}
	
	
	public void drawCode(Canvas canvas){
		MoveCommands commands = MoveCommands.getCommands();
		CodeToStringConverter codeToStr = new CodeToStringConverter();
		ArrayList<String> code = codeToStr.convertCodeToString(commands);
		
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		int font = 40;
		int lineHeight = 415 + font;
		
		paint.setTextSize(font);
		for (String codeLine: code){
			canvas.drawText(codeLine, 42, lineHeight, paint);
			lineHeight +=font*2;
		}
		
	}

	public void setOnTapListener() {
		this.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (!gameStarted){
					gameStarted = true;
					return false;
				}
				int xCoord = (int) event.getX();
				int yCoord = (int) event.getY();

				if (isTapOnRunButton(xCoord, yCoord)) {
					System.out.println("clicked run button");
					// run the code snippet
					isRunningCommands = true;
				}
				return false;
			}
		});
	}

	public boolean isTapOnRunButton(int x, int y) {
		BoundingBox box = screenConstants.runButtonBoundingBox;
		return box.isWithinBox(x, y);
	}

}