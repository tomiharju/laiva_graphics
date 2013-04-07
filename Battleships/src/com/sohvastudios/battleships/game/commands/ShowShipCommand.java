package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.objectControllers.GuiController;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;

public class ShowShipCommand implements Command {

	public ShowShipCommand(ObjectController controller) {

	}

	@Override
	public void execute(GuiController c) {

		if (!GameLogicHandler.shipViewLocked) {
			GameLogicHandler.lockShipView();
		}

	}
}
