package com.example.google;

import java.util.Iterator;
import java.util.LinkedList;

public class MoveCommands {
	
	LinkedList<MoveCommand> moveCommands;
	
	private static MoveCommands instance = null;
	
	public static MoveCommands get(){
		if (instance==null){
			return new MoveCommands();
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
		return moveCommands.iterator();
	}
}
