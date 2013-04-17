package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.RadarContainer;

public class FireCommand implements Command{

	RadarContainer source;
	public FireCommand(RadarContainer source){
		this.source=source;
	}
	@Override
	public void execute(ObjectController target) {
		source.fire();
		
	}

}
