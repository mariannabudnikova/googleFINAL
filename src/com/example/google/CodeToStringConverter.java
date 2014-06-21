package com.example.google;

import java.util.ArrayList;
import java.util.LinkedList;

import android.graphics.Path.Direction;

public class CodeToStringConverter {

	public ArrayList<String> convertCodeToString(MoveCommands moveCommands) {
		LinkedList<MoveCommand> commands = moveCommands.getMoveCommandList();
		ArrayList<String> code = new ArrayList<String>();
		String toShow = "";
		for (int i = 0; i < commands.size(); i++) {
			MoveCommand command = commands.get(i);

			if (i == commands.size() - 1 || command.getDirection() != commands.get(i + 1).getDirection())
				toShow = command.toString() + "\r\n";
			else {
				int n = 1;
				while ((i != commands.size() - 1)
						&& (command.getDirection() == commands.get(i)
								.getDirection())) {
					n++;
					i++;
				}
				toShow = "for(int i = 1; i <= " + n + "; i++)"
						+ command.toString() + "\r\n";
			}

			code.add(toShow);
		}
		return code;
	}

}
