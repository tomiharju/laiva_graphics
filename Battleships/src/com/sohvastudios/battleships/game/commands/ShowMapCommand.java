package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.objectControllers.GuiController;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;


public class ShowMapCommand implements Command{
	private ObjectController controller;
	public ShowMapCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute(GuiController c) {
		
		if(!GameLogicHandler.mapViewLocked)
			GameLogicHandler.lockMapView();
		
	}

}
