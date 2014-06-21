package com.example.google;

import java.util.Iterator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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
    boolean isRunningCommands = false;
   
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
          drawAlien(canvas);
          drawMoveCommands(canvas);
          drawRunButton(canvas);
    }
    
    public void drawAlien(Canvas canvas){
        alien = Alien.get(context);
        Bitmap alienBitmap = BitmapFactory.decodeResource(getResources(), alien.getIcon());
        canvas.drawBitmap(alienBitmap, alien.xPosition, alien.yPosition, null);
    }
    
    public void drawMoveCommands(Canvas canvas){
    	Iterator<MoveCommand> iter = MoveCommands.getCommands().getCommandIterator();
    	int commandOffset =0;
    	while(iter.hasNext()){
    		MoveCommand command = iter.next();
    		Bitmap commandBitmap = BitmapFactory.decodeResource(getResources(), command.getIcon());
    		canvas.drawBitmap(commandBitmap, 10, ScreenConstants.get(context).GAME_SCREEN_HEIGHT+commandOffset,null);
    		commandOffset+=20;
    	}
    	
    }
    
    public void drawRunButton(Canvas canvas){
    	Bitmap runBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		canvas.drawBitmap(runBitmap, screenConstants.RUN_BUTTON_X, screenConstants.RUN_BUTTON_Y,null);
    }
    
    public void setOnTapListener(){
        this.setOnTouchListener( new View.OnTouchListener() {
  
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int xCoord = (int)event.getX();
				int yCoord = (int)event.getY();
				
				
				if (isTapOnRunButton(xCoord, yCoord)){
					System.out.println("clicked run button");
					//run the code snippet
					isRunningCommands = true;
				}
				return false;
			}
        });
    }
    
    public boolean isTapOnRunButton(int x, int y){
    	BoundingBox box = screenConstants.runButtonBoundingBox;
    	return box.isWithinBox(x, y);
    }
    

    
}