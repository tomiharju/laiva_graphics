package Commands;

import ObjectControllers.ObjectController;
import Screens.GameLogicHandler;

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
