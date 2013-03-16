package Commands;

import ObjectControllers.ObjectController;
import Screens.GameLogicHandler;

public class ShowShipCommand implements Command{
	private ObjectController controller;
	public ShowShipCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute() {
		GameLogicHandler.lockShipView();
		
	}
}
