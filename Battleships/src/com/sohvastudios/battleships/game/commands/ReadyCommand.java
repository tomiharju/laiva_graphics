package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.gamelogic.PlayScreen;
import com.sohvastudios.battleships.game.objectControllers.GuiController;


public class ReadyCommand implements Command{

	
	public ReadyCommand(){
		
	}
	@Override
	public void execute(GuiController c) {
		GuiController.removeGuiObject((GuiController) c);
		c.getObject().dispose();
		PlayScreen.logicHandler.sendReady();
		
	}

}
