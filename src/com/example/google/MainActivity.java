package com.example.google;

import com.example.google.MoveCommand.MoveDirection;
import com.example.google.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;
 

public class MainActivity extends Activity implements SimpleGestureListener{
	private SimpleGestureFilter detector;
	public static int MOVEMENT_AMOUNT=100;
	GameView gameView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        gameView = new GameView(this);
        setContentView(gameView);
        
     // Detect touched area 
        detector = new SimpleGestureFilter(this,this);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
         this.detector.onTouchEvent(me);
       return super.dispatchTouchEvent(me);
    }
    @Override
     public void onSwipe(int direction) {
		String str = "";
		
		Alien alien = Alien.get(getApplicationContext());
		
		MoveCommand command=null;
		
		if (!gameView.gameStarted)
			return;
		
		switch (direction) {
		case SimpleGestureFilter.SWIPE_RIGHT:
			str = "Swipe Right";
			//alien.moveRight(MOVEMENT_AMOUNT);
			command = new MoveCommand(MoveDirection.RIGHT);
			System.out.println(alien);
			break;
		case SimpleGestureFilter.SWIPE_LEFT:
			str = "Swipe Left";
			//alien.moveLeft(MOVEMENT_AMOUNT);
			command = new MoveCommand(MoveDirection.LEFT);
			break;
		case SimpleGestureFilter.SWIPE_DOWN:
			str = "Swipe Down";
			//alien.moveDown(MOVEMENT_AMOUNT);
			command = new MoveCommand(MoveDirection.DOWN);
			break;
		case SimpleGestureFilter.SWIPE_UP:
			str = "Swipe Up";
			//alien.moveUp(MOVEMENT_AMOUNT);
			command = new MoveCommand(MoveDirection.UP);
			break;

		}
		if (command!=null){
			MoveCommands.getCommands().addCommand(command);
		}
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
     }
      
     @Override
     public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
     }

}