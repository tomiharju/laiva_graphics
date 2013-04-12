package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.UserInterfaceController;

public class DisposeCommand implements Command{

	ObjectController source;
	public DisposeCommand(ObjectController source){
		this.source=source;
	}
	@Override
	public void execute(ObjectController target) {
		source.removeGuiObject((UserInterfaceController)target);
	}
}
