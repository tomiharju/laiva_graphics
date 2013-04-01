package Commands;

import GameLogic.GameLogicHandler;
import ObjectControllers.GuiController;
import ObjectControllers.ObjectController;

public class ShowMapCommand implements Command{
	private ObjectController controller;
	public ShowMapCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute(GuiController c) {
		
		if(!GameLogicHandler.mapViewLocked)
			GameLogicHandler.lockMapView();
		
	}

}
