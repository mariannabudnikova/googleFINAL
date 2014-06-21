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
	private int highLightedIcon;
	
	public MoveCommand(MoveDirection moveDirection){
		this.moveDirection = moveDirection;
		setIcons();
	}
	
	public int getIcon(){
		return icon;
	}
	
	public int getHighlightedIcon(){
		return highLightedIcon;
	}
	
	public MoveDirection getDirection(){
		return moveDirection;
	}
	
	private void setIcons(){
		switch(moveDirection){
		case UP:
			icon = Icons.UP_ICON;
			highLightedIcon = Icons.UP_ICON_HIGHLIGHT;
			break;
		case DOWN:
			icon = Icons.DOWN_ICON;
			highLightedIcon = Icons.DOWN_ICON_HIGHLIGHT;
			break;
		case RIGHT:
			icon = Icons.RIGHT_ICON;
			highLightedIcon = Icons.RIGHT_ICON_HIGHLIGHT;
			break;
		case LEFT:
			icon = Icons.LEFT_ICON;
			highLightedIcon = Icons.LEFT_ICON_HIGHLIGHT;
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

