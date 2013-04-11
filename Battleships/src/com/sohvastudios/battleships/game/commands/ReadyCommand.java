package com.sohvastudios.battleships.game.commands;

import com.badlogic.gdx.math.Rectangle;
import com.sohvastudios.battleships.game.gamelogic.PlayScreen;
import com.sohvastudios.battleships.game.objectControllers.GuiController;
import com.sohvastudios.battleships.game.objectControllers.SeaController;
import com.sohvastudios.battleships.game.objectControllers.ShipController;


public class ReadyCommand implements Command{

	
	public ReadyCommand(){
		
	}
	@Override
	public void execute(GuiController c) {
		Rectangle safeBounds = new Rectangle(-5,-5,10,10);
		for(ShipController sc : SeaController.shipControllers){
			if(!safeBounds.contains(sc.pollBounds())){
				System.out.println(sc.getObject().getSprite().getColor().toString());
				sc.getObject().getSprite().setColor(0.5f,0,0,1);
				
			}
			return;
		}
		GuiController.removeGuiObject((GuiController) c);
		c.getObject().dispose();
		PlayScreen.logicHandler.sendReady();
		
	}

}
