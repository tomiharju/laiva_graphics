package com.sohvastudios.battleships.game.objectModels;

import java.util.ArrayList;

import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.RadarContainer;
import com.sohvastudios.battleships.game.objectControllers.SeaContainer;
import com.sohvastudios.battleships.game.objectControllers.WorldController;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;
import com.sohvastudios.battleships.game.objectRenderers.RadarRenderer;
import com.sohvastudios.battleships.game.objectRenderers.SeaRenderer;
import com.sohvastudios.battleships.game.objectRenderers.WorldRenderer;





public class WorldObject extends ModelObject {

	// Class that handles the game logic
	GameLogicHandler logicHandler;

	public static ArrayList<ModelObject> objects;
	public static ArrayList<ModelObject> removelist;
	public static ArrayList<ModelObject> addlist;

	// Game components
	private SeaObject seaObject;
	private RadarObject radarObject;

	public WorldObject(ObjectController controller, ObjectRenderer renderer,ObjectController parent) {
		setController(controller);
		setRenderer(renderer);

		
		objects 			= new ArrayList<ModelObject>();
		removelist			= new ArrayList<ModelObject>();
		addlist				= new ArrayList<ModelObject>();
		
		radarObject			= new RadarObject(new RadarContainer(0,0,8,8),new RadarRenderer(),this.controller);
		seaObject 			= new SeaObject(new SeaContainer(0,0,10,10), new SeaRenderer(),this.controller);
	
		

		
		
		

	}

	public void update() {
		for (ModelObject o : objects)
			o.update();
		
		for(ModelObject o : removelist)
			o.dispose();
		
		objects.removeAll(removelist);
		removelist.clear();
		
		objects.addAll(addlist);
		addlist.clear();
		
		if(seaObject.controller.addlist.size()>0 || seaObject.controller.removelist.size()>0){
			seaObject.controller.controllers.removeAll(seaObject.controller.removelist);
			seaObject.controller.controllers.addAll(seaObject.controller.addlist);
			seaObject.controller.removelist.clear();	
			seaObject.controller.addlist.clear();
		
		}
		if(radarObject.controller.addlist.size()>0 || radarObject.controller.removelist.size()>0){
			radarObject.controller.controllers.removeAll(radarObject.controller.removelist);
			radarObject.controller.controllers.addAll(radarObject.controller.addlist);
			radarObject.controller.removelist.clear();
			radarObject.controller.addlist.clear();
		
		}
		
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
	
}
