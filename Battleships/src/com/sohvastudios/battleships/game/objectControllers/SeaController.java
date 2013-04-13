package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.commands.ReadyCommand;
import com.sohvastudios.battleships.game.commands.RotateCommand;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject.ShipType;
import com.sohvastudios.battleships.game.objectModels.UserInterfaceObject;
import com.sohvastudios.battleships.game.objectRenderers.ShipRenderer;
import com.sohvastudios.battleships.game.objectRenderers.UserInterfaceRenderer;

public class SeaController extends ObjectController {

	public static ArrayList<ShipController> shipControllers  = new ArrayList<ShipController>(); 
	
	private UserInterfaceObject weaponDescription;
	private ShipController activeController;
	private boolean shipsLocked;
	

	public SeaController(float x, float y, float w, float h) {
		super(x, y, w, h);
		
	}
	public void initialize(){
		createShips();
		activeController = null;
		weaponDescription =null;
		new UserInterfaceObject(new UserInterfaceController(0, -6f, 3f, 1.5f,
				new ReadyCommand(this)), new UserInterfaceRenderer(), "button_ready.png").addToSea(this);
	
		new UserInterfaceObject(new UserInterfaceController(-2.5f, -6f, 1.5f, 1.5f,
				new RotateCommand(this)), new UserInterfaceRenderer(), "button_ready.png").addToSea(this);
		
		object.setVisible();
		for(UserInterfaceController uc : guiControllers){
			uc.show();
		}
		for (ShipController sc : shipControllers)
			sc.show();
		
	}

	public void createShips() {
	
		for (ShipType ship : ShipType.values()) {
			new ShipObject(ship, new ShipController(0, 6 ,
					ship.getLenght(), ship.getWidth()), new ShipRenderer()).getController();
		}

	}
	public void lockShips(){
		shipsLocked=true;
	}

	
	public void hide() {
		object.setHidden();
		for(UserInterfaceController uc : guiControllers){
			uc.hide();
		}
		for (ShipController sc : shipControllers)
			sc.hide();
	
	}

	public void show() {
		
	}

	@Override
	public void handleInputDown(Vector3 pos) {
		//Check if button is touched
		for (int i = 0; i < guiControllers.size() ; i++)
			guiControllers.get(i).handleInputDown(pos);
		
		cleanTrash();
		
		if(shipsLocked)
			return;
		
			
			
			if(weaponDescription!=null){
				((UserInterfaceController) weaponDescription.getController()).executeCommand();
			}
		
			for (ShipController sc : SeaController.shipControllers){
				sc.deSelect();
			if (sc.pollBounds().contains(pos.x,pos.y)){
				sc.select();
				activeController=sc;
				
			}
			
		}
		

	}

	public void handleInputDrag(Vector3 pos) {
		if(shipsLocked)
			return;
		
		if (activeController != null) {
			activeController.handleInputDrag(pos);
		}
	}
	public void rotate(){
		if(shipsLocked)
			return;
		if (activeController != null) {
			activeController.rotate90();
		}
		
	}
	@Override
	public void removeObject(ObjectController obj) {
		object.dispose();
		
	}
	@Override
	public void cleanTrash() {
		guiControllers.removeAll(removeList);
		removeList.clear();
		
	}

}
