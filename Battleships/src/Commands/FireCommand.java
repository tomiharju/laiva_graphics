package Commands;

import ObjectControllers.ObjectController;
import ObjectControllers.ShootingMapView;

public class FireCommand implements Command{
	private ObjectController controller;
	public FireCommand(ObjectController controller){
		this.controller=controller;
	}
	@Override
	public void execute() {
		((ShootingMapView) controller).fire();
		
	}

}
