package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.RadarController;

public class FireCommand implements Command{

	RadarController source;
	public FireCommand(RadarController source){
		this.source=source;
	}
	@Override
	public void execute(ObjectController target) {
		source.fire();
		
	}

}
