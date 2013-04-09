package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject.ShipType;
import com.sohvastudios.battleships.game.objectRenderers.ShipRenderer;

public class SeaController extends ObjectController {

	public static ArrayList<ShipController> shipControllers;
	

	private ShipController activeController;

	

	public SeaController(float x, float y, float w, float h) {
		super(x, y, w, h);
		shipControllers = new ArrayList<ShipController>();
		activeController = null;
	}

	public void createShips() {
		float values[] = { 9,7, 5, 3, 1 };
		for (ShipType ship : ShipType.values()) {
			new ShipObject(ship, new ShipController(3, values[ship.ordinal()],
					ship.getLenght(), ship.getWidth()), new ShipRenderer());
		}

	}

	
	public void hide() {
		object.setHidden();
		for (ShipController sc : shipControllers)
			sc.hide();
	
	}

	public void show() {
		object.setVisible();
		for (ShipController sc : shipControllers)
			sc.show();
		
	}

	@Override
	public void handleInputDown(Vector3 pos) {
		for (ShipController sc : SeaController.shipControllers){
			sc.deSelect();
			if (sc.pollBounds().contains(pos.x,pos.y)){
				sc.select();
				activeController=sc;
			}
		}

	}

	public void handleInputDrag(Vector3 pos) {
		if (activeController != null) {
			activeController.handleInputDrag(pos);
		}
	}
	public void handleDoubleTap(Vector3 pos){
		if(activeController!=null){
			activeController.rotate90();
		}
	}

}
