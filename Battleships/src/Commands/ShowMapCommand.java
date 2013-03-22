package Commands;

import GameLogic.GameLogicHandler;
import ObjectControllers.ObjectController;

public class ShowMapCommand implements Command{
	private ObjectController controller;
	public ShowMapCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute() {
		GameLogicHandler.lockMapView();
		
	}

}
