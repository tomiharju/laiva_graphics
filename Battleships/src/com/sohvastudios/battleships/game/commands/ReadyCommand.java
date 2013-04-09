package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.objectControllers.GuiController;
import com.sohvastudios.battleships.game.utilities.Turn;


public class ReadyCommand implements Command{

	
	public ReadyCommand(){
		
	}
	@Override
	public void execute(GuiController c) {
		GuiController.removeGuiObject((GuiController) c);
		c.getObject().dispose();
		GameLogicHandler.sendReady(new Turn(Turn.TURN_READY));
		
	}

}
