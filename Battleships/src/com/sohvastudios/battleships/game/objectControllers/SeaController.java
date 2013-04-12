package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.commands.DisposeCommand;
import com.sohvastudios.battleships.game.commands.ReadyCommand;
import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject.ShipType;
import com.sohvastudios.battleships.game.objectModels.UserInterfaceObject;
import com.sohvastudios.battleships.game.objectRenderers.ShipRenderer;
import com.sohvastudios.battleships.game.objectRenderers.UserInterfaceRenderer;
import com.sohvastudios.battleships.game.utilities.Turn;

public class SeaController extends ObjectController {

	public static ArrayList<ShipController> shipControllers  = new ArrayList<ShipController>(); 
	
	private UserInterfaceObject weaponDescription;
	private ShipController activeController;
	private boolean shipsLocked;
	

	public SeaController(float x, float y, float w, float h) {
		super(x, y, w, h);
		
	}
	public void initialize(){
		activeController = null;
		weaponDescription =null;
		new UserInterfaceObject(new UserInterfaceController(0, -6f, 3f, 1.5f,
				new ReadyCommand(this)), new UserInterfaceRenderer(), "button_ready.png").addToSea(this);
	
	
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
		//Check if button is touched
		for(UserInterfaceController uc : guiControllers){
			uc.handleInputDown(pos);
		}
		
		if(shipsLocked)
			return;
		
			boolean weaponSelected=false;
			
			if(weaponDescription!=null){
				((UserInterfaceController) weaponDescription.getController()).executeCommand();
			}
		
			for (ShipController sc : SeaController.shipControllers){
				sc.deSelect();
			if (sc.pollBounds().contains(pos.x,pos.y)){
				sc.select();
				weaponSelected=true;
				activeController=sc;
				if(GameLogicHandler.state!=Turn.TURN_BEGINNING){
					int weapon = ((ShipObject)sc.getObject()).shipWeapon.ordinal();
					weaponDescription = new UserInterfaceObject(new UserInterfaceController(2.5f,2.5f,5,5,new DisposeCommand(this)),new UserInterfaceRenderer(),"wicon"+weapon+".png");
				}
			}
			
		}
			if(!weaponSelected){
				weaponDescription = new UserInterfaceObject(new UserInterfaceController(2.5f,2.5f,5,5,new DisposeCommand(this)),new UserInterfaceRenderer(),"wicon-1.png");;
			}
		

	}

	public void handleInputDrag(Vector3 pos) {
		if(shipsLocked)
			return;
		
		if (activeController != null) {
			activeController.handleInputDrag(pos);
		}
	}
	public void handleDoubleTap(Vector3 pos){
		if(shipsLocked)
			return;
		
		if(activeController!=null){
			activeController.rotate90();
		}
	}

}
