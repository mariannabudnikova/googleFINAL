package com.example.google;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
public class GameLoopThread extends Thread {
    static long FPS = 2;
    private GameView view;
    private boolean running = false;
    Context context;
   
    public GameLoopThread(GameView view, Context context) {
          this.view = view;
          this.context = context;
    }

    public void setRunning(boolean run) {
          running = run;
    }

    @SuppressLint("WrongCall")
	@Override
    public void run() {
          long ticksPS = 1000 / FPS;
          long startTime;
          long sleepTime;
          while (running) {
                 Canvas c = null;
                 startTime = System.currentTimeMillis();
                 try {
                        c = view.getHolder().lockCanvas();
                        
                    	performTheDrawings(c);
                        
                    	try {
							sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    	
                    	checkIfWon();
                        if (view.isRunningCommands){
                        	runNextCommand();
                        }
                        
                    	
                       
                 } finally {
                        if (c != null) {
                               view.getHolder().unlockCanvasAndPost(c);
                        }
                 }
                 sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
                 try {
                        if (sleepTime > 0)
                               sleep(sleepTime);
                        else
                               sleep(10);
                 } catch (Exception e) {}
          }
    }
    
    public void performTheDrawings(Canvas c){
    	 synchronized (view.getHolder()) {
             view.onDraw(c);
      }
    }
    
    
    public void runNextCommand(){
    	MoveCommands commands = MoveCommands.getCommands();
    	int numberOfCommands = commands.getNumberOfCommands();
    	view.currentlyExecutingCommand ++;
    	if (view.currentlyExecutingCommand>=numberOfCommands){
    		view.isRunningCommands = false;
    		return;
    	}
    	MoveCommand command = commands.getMoveCommandAtIndex(view.currentlyExecutingCommand);
    	Alien alien = Alien.get(context);
    	alien.executeCommand(command);
    	
    	
    }
    
    public void checkIfWon(){
    	BoundingBox alienBox = Alien.get(context).alienBox;
    	BoundingBox goalBox = ScreenConstants.get(context).goalBoundingBox;
    	
    	if (alienBox.intersectsWithAnotherBox(goalBox)){
    		view.gameOver = true;
    	}
    }
}
