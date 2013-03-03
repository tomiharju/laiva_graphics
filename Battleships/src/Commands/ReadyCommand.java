package Commands;

import ObjectControllers.ObjectController;

public class ReadyCommand implements Command{

	private ObjectController controller;
	public ReadyCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
