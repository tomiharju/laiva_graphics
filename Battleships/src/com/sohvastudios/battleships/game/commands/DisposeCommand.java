package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.RadarContainer;

public class DisposeCommand implements Command{

	RadarContainer source;
	public DisposeCommand(RadarContainer source){
		this.source=source;
	}

	@Override
	public void execute(ObjectController target) {
		target.getObject().dispose();
		
	}
}
