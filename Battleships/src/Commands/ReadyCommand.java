package Commands;

import GameLogic.GameLogicHandler;
import ObjectControllers.GuiController;
import ObjectControllers.ObjectController;
import ObjectControllers.ShipPlacementView;
import Utilities.Turn;

public class ReadyCommand implements Command{

	private ObjectController controller;
	public ReadyCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute(GuiController c) {
		((ShipPlacementView) controller).removeGuiObject(c);
		GameLogicHandler.sendReady(new Turn(Turn.TURN_READY));
		
	}

}
