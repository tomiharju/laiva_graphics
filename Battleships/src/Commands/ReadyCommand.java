package Commands;

import GameLogic.GameLogicHandler;
import GameLogic.Turn;
import ObjectControllers.ObjectController;

public class ReadyCommand implements Command{

	private ObjectController controller;
	public ReadyCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute() {
		GameLogicHandler.sendTurn(new Turn(Turn.TURN_READY));
		
	}

}
