package com.sohvastudios.battleships.game.commands;

import com.badlogic.gdx.math.Rectangle;
import com.sohvastudios.battleships.game.gamelogic.PlayScreen;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.SeaContainer;
import com.sohvastudios.battleships.game.objectControllers.ShipController;


public class ReadyCommand implements Command{

	ObjectController source;
	public ReadyCommand(ObjectController source){
		this.source=source;
	}
	@Override
	public void execute(ObjectController target) {
		Rectangle safeBounds = new Rectangle(-10,-10,20,20);
		boolean checkSuccess = true;
		for (ObjectController oc : source.controllers){
			if(oc instanceof ShipController)
				for (ObjectController SC : source.controllers)
					if(oc instanceof ShipController)
						if (!oc.equals(SC))
							if (oc.pollBounds().overlaps(SC.pollBounds())|| !safeBounds.contains(oc.pollBounds())){
								checkSuccess=false;
				}
			}
	
		
		if(checkSuccess){
			((SeaContainer) source).lockShips();
			for(ObjectController oc : source.controllers){
				if(oc instanceof ShipController)
					oc.getObject().baseSprite.setColor(0.9f,0,0,0);
			}
		PlayScreen.logicHandler.sendReady();
		}
	}

}
