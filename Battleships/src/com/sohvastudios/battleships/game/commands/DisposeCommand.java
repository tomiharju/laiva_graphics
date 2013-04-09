package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.objectControllers.GuiController;

public class DisposeCommand implements Command{

	public DisposeCommand(){
		
	}
	@Override
	public void execute(GuiController c) {
		GuiController.removeGuiObject((GuiController) c);
		c.getObject().dispose();
		
	}
}
