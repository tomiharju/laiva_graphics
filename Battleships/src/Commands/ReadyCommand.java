package Commands;

import ObjectControllers.ObjectController;
import Screens.GameLogicHandler;

public class ReadyCommand implements Command{

	private ObjectController controller;
	public ReadyCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute() {
		GameLogicHandler.sendImReady();
		
	}

}
