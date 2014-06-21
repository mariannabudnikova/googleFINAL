package com.example.google;

import java.util.Iterator;
import java.util.LinkedList;

public class MoveCommands {
	
	LinkedList<MoveCommand> moveCommands;
	
	private static MoveCommands instance = null;
	
	public static MoveCommands getCommands(){
		if (instance==null){
			instance =  new MoveCommands();
		}
		return instance;
	}
	
	private MoveCommands(){
		moveCommands = new LinkedList<MoveCommand>();
	}
	
	public void addCommand(MoveCommand command){
		moveCommands.add(command);
	}

	public Iterator<MoveCommand> getCommandIterator(){
		LinkedList<MoveCommand> commandsCopy = new LinkedList<MoveCommand>();
		commandsCopy = (LinkedList<MoveCommand>) moveCommands.clone();
		return commandsCopy.iterator();
	}
	
	public int getNumberOfCommands(){
		return moveCommands.size();
	}
	
	public MoveCommand getMoveCommandAtIndex(int index){
		return moveCommands.get(index);
	}
}
