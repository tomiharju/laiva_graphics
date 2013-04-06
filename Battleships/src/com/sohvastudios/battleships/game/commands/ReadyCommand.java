package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.objectControllers.GuiController;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.ShipPlacementView;
import com.sohvastudios.battleships.game.utilities.Turn;


public class ReadyCommand implements Command{

	private ObjectController controller;
	public ReadyCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute(GuiController c) {
		((ShipPlacementView) controller).removeGuiObject(c);
		GameLogicHandler.sendReady(new Turn(Turn.TURN_READY));
		
	}

}
