package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.objectControllers.ObjectController;

public interface Command {
	
	public void execute(ObjectController target);

}
