package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.commands.DisposeCommand;
import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.objectModels.GuiObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject.ShipType;
import com.sohvastudios.battleships.game.objectRenderers.GuiRenderer;
import com.sohvastudios.battleships.game.objectRenderers.ShipRenderer;
import com.sohvastudios.battleships.game.utilities.Turn;

public class SeaController extends ObjectController {

	public static ArrayList<ShipController> shipControllers;
	
	private GuiObject weaponDescription;
	private ShipController activeController;

	

	public SeaController(float x, float y, float w, float h) {
		super(x, y, w, h);
		shipControllers = new ArrayList<ShipController>();
		activeController = null;
		weaponDescription =null;
	}

	public void createShips() {
	
		for (ShipType ship : ShipType.values()) {
			new ShipObject(ship, new ShipController(0, 6 ,
					ship.getLenght(), ship.getWidth()), new ShipRenderer()).getController();
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
		
			boolean weaponSelected=false;
			if(weaponDescription!=null){
				((GuiController) weaponDescription.getController()).executeCommand();
			}
		
			for (ShipController sc : SeaController.shipControllers){
				sc.deSelect();
			if (sc.pollBounds().contains(pos.x,pos.y)){
				sc.select();
				weaponSelected=true;
				activeController=sc;
				if(GameLogicHandler.state!=Turn.TURN_BEGINNING){
					int weapon = ((ShipObject)sc.getObject()).shipWeapon.ordinal();
					weaponDescription = new GuiObject(new GuiController(2.5f,2.5f,5,5,new DisposeCommand()),new GuiRenderer(),"wicon"+weapon+".png");
				}
			}
			
		}
			if(!weaponSelected){
				weaponDescription = new GuiObject(new GuiController(2.5f,2.5f,5,5,new DisposeCommand()),new GuiRenderer(),"wicon-1.png");;
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
