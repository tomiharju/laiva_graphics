package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.SeaController;

public class RotateCommand implements Command{

	SeaController source;
	
	public RotateCommand(SeaController source){
		this.source=source;
	}
	@Override
	public void execute(ObjectController target) {
		source.rotate();
		
	}

}
