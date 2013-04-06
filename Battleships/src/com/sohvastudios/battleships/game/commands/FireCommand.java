package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.objectControllers.GuiController;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.ShootingMapView;


public class FireCommand implements Command{
	private ObjectController controller;
	public FireCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute(GuiController c) {
		if(!GameLogicHandler.fireButtonLocked)
			((ShootingMapView) controller).fire();
		
	}

}
