package com.sohvastudios.battleships.game.commands;

import com.badlogic.gdx.math.Rectangle;
import com.sohvastudios.battleships.game.gamelogic.PlayScreen;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.SeaController;
import com.sohvastudios.battleships.game.objectControllers.ShipController;


public class ReadyCommand implements Command{

	ObjectController source;
	public ReadyCommand(ObjectController source){
		this.source=source;
	}
	@Override
	public void execute(ObjectController target) {
		Rectangle safeBounds = new Rectangle(-5,-5,10,10);
		boolean checkSuccess = true;
		for (ShipController sc : SeaController.shipControllers){
			for (ShipController SC : SeaController.shipControllers)
				if (!sc.equals(SC))
					if (sc.pollBounds().overlaps(SC.pollBounds())|| !safeBounds.contains(sc.pollBounds())){
						checkSuccess=false;
				}
			}
	
		
		if(checkSuccess){
			((SeaController) source).lockShips();
			for(ShipController sc : SeaController.shipControllers){
				sc.getObject().baseSprite.setColor(0.9f,0,0,0);
			}
		source.removeList.add(target);
		
		PlayScreen.logicHandler.sendReady();
		}
	}

}
