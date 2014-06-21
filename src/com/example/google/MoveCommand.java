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
	
	public MoveDirection getDirection(){
		return moveDirection;
	}
	
	private void setIcon(){
		switch(moveDirection){
		case UP:
			icon = Icons.UP_ICON;
			break;
		case DOWN:
			icon = Icons.DOWN_ICON;
			break;
		case RIGHT:
			icon = Icons.RIGHT_ICON;
			break;
		case LEFT:
			icon = Icons.LEFT_ICON;
			break;
			
		}
		
			
	}
	
	public MoveDirection getMoveDirection(){
		return moveDirection;
	}
	
	public String toString(){
		
		switch(moveDirection){
		case UP:
			return "moveUp();";
		case DOWN:
			return "moveDown();";
		case RIGHT:
			return "moveRight();";
		case LEFT:
			return "moveLeft();";
			
		}
		return "";
	}
}

