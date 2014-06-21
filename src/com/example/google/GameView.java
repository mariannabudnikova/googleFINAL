package com.example.google;

import java.util.Iterator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private int x = 0; 
    Alien alien;
    Context context;
   
    public GameView(Context context) {
          super(context);
          this.context = context;
          gameLoopThread = new GameLoopThread(this);
          holder = getHolder();
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
          bmp = BitmapFactory.decodeResource(getResources(), alien.getIcon());
    }

    @Override
    protected void onDraw(Canvas canvas) {
          canvas.drawColor(Color.BLACK);
          drawAlien(canvas);
          drawMoveCommands(canvas);
    }
    
    public void drawAlien(Canvas canvas){
        alien = Alien.get(context);
        canvas.drawBitmap(bmp, alien.xPosition, alien.yPosition, null);
    }
    
    public void drawMoveCommands(Canvas canvas){
    	Iterator<MoveCommand> iter = MoveCommands.getCommands().getCommandIterator();
    	int commandOffset =0;
    	while(iter.hasNext()){
    		MoveCommand command = iter.next();
    		Bitmap commandBitmap = BitmapFactory.decodeResource(getResources(), command.getIcon());
    		canvas.drawBitmap(bmp, 10, ScreenConstants.get(context).GAME_SCREEN_HEIGHT+commandOffset,null);
    		commandOffset+=20;
    	}
    	
    }
}