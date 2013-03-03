package Commands;

import ObjectControllers.ObjectController;
import Screens.GameLogicHandler;

public class HideCommand implements Command{
	private ObjectController controller;
	public HideCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute() {
		GameLogicHandler.setGameState(1);
		
	}

}
