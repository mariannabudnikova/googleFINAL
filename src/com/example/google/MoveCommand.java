package com.example.google;


public class MoveCommand {
	
	public enum MoveDirection{
		UP,
		DOWN,
		RIGHT,
		LEFT
	};
	
	private MoveDirection moveDirection;
	private int icon;
	
	public MoveCommand(MoveDirection moveDirection){
		this.moveDirection = moveDirection;
		setIcon();
	}
	
	public int getIcon(){
		return icon;
	}
	
	private void setIcon(){
		switch(moveDirection){
		case UP:
			icon = R.drawable.ic_launcher;
			break;
		case DOWN:
			icon = R.drawable.ic_launcher;
			break;
		case RIGHT:
			icon = R.drawable.ic_launcher;
			break;
		case LEFT:
			icon = R.drawable.ic_launcher;
			break;
			
		}
		
			
	}

	
	public MoveDirection getMoveDirection(){
		return moveDirection;
	}
}

