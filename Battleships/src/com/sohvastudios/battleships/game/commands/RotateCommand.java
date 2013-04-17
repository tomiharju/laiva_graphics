package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.SeaContainer;

public class RotateCommand implements Command{

	SeaContainer source;
	
	public RotateCommand(SeaContainer source){
		this.source=source;
	}
	@Override
	public void execute(ObjectController target) {
		source.rotate();
		
	}

}
