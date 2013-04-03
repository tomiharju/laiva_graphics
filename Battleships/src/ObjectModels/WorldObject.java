package ObjectModels;

import java.util.ArrayList;

import GameLogic.GameLogicHandler;
import ObjectControllers.ShootingMapView;
import ObjectControllers.ObjectController;
import ObjectControllers.ShipPlacementView;

import ObjectControllers.WorldController;

import ObjectRenderers.MapRenderer;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.SeaRenderer;

import ObjectRenderers.WorldRenderer;

public class WorldObject extends ModelObject {

	// Class that handles the game logic
	GameLogicHandler logicHandler;

	public static ArrayList<ModelObject> objects;

	// Game components
	private ShipPlacementViewObject shipPlacementView_o;
	private ShootingMapViewObject shootingMapView_o;

	public WorldObject(ObjectController controller, ObjectRenderer renderer) {
		setController(controller);
		setRenderer(renderer);

		
		objects 			= new ArrayList<ModelObject>();
		shipPlacementView_o = new ShipPlacementViewObject(new ShipPlacementView(5,7.5f,10,10), new SeaRenderer());
		shootingMapView_o 	= new ShootingMapViewObject(new ShootingMapView(5,7.5f,10,10),new MapRenderer());
		((ShipPlacementView) shipPlacementView_o.controller).createShips();
		((ShootingMapView) shootingMapView_o.controller).createWeapons();
		((ShootingMapView) shootingMapView_o.controller).createGuiObjects();
	

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

	public ShipPlacementViewObject shipView() {
		return shipPlacementView_o;
	}

	public ShootingMapViewObject mapView() {
		return shootingMapView_o;
	}

}
