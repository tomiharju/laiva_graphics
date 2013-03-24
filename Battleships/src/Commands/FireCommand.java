package Commands;

import GameLogic.GameLogicHandler;
import GameLogic.Turn;
import ObjectControllers.GuiController;
import ObjectControllers.ObjectController;
import ObjectControllers.ShootingMapView;

public class FireCommand implements Command{
	private ObjectController controller;
	public FireCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute(GuiController c) {
		if(!GameLogicHandler.fireButtonLocked)
			((ShootingMapView) controller).fire();
		
	}

}
