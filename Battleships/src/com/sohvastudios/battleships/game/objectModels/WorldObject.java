package com.sohvastudios.battleships.game.objectModels;

import java.util.ArrayList;

import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.objectControllers.GuiController;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.RadarController;
import com.sohvastudios.battleships.game.objectControllers.SeaController;
import com.sohvastudios.battleships.game.objectControllers.WorldController;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;
import com.sohvastudios.battleships.game.objectRenderers.RadarRenderer;
import com.sohvastudios.battleships.game.objectRenderers.SeaRenderer;
import com.sohvastudios.battleships.game.objectRenderers.WorldRenderer;





public class WorldObject extends ModelObject {

	// Class that handles the game logic
	GameLogicHandler logicHandler;

	public static ArrayList<ModelObject> objects;

	// Game components
	private SeaObject seaObject;
	private RadarObject radarObject;
	private GuiObject	guiObject;
	public WorldObject(ObjectController controller, ObjectRenderer renderer) {
		setController(controller);
		setRenderer(renderer);

		
		objects 			= new ArrayList<ModelObject>();
		
		radarObject			= new RadarObject(new RadarController(2.5f,2.5f,5,5),new RadarRenderer());
		seaObject 			= new SeaObject(new SeaController(5f,5f,10,10), new SeaRenderer());
		((SeaController) seaObject.controller).createShips();
		guiObject			= new GuiObject(new GuiController());

		
		
		

	}

	public void update() {
		for (int i = 0; i < objects.size() ; i++)
			objects.get(i).update();

	}

	@Override
	public void setController(ObjectController controller) {
		this.controller = (WorldController) controller;
		this.controller.setObject(this);
	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer = (WorldRenderer) renderer;
		this.renderer.setObject(this);
	}

	public SeaObject shipView() {
		return seaObject;
	}

	public RadarObject mapView() {
		return radarObject;
	}
	public GuiObject guiView(){
		return guiObject;
	}

}
