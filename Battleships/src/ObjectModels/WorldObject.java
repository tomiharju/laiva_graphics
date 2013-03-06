package ObjectModels;

import java.util.ArrayList;

import ObjectControllers.ShootingMapView;
import ObjectControllers.ObjectController;
import ObjectControllers.ShipPlacementView;

import ObjectControllers.WorldController;

import ObjectRenderers.MapRenderer;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.SeaRenderer;

import ObjectRenderers.WorldRenderer;
import Screens.GameLogicHandler;

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
		shipPlacementView_o = new ShipPlacementViewObject(new ShipPlacementView(), new SeaRenderer());
		shootingMapView_o 	= new ShootingMapViewObject(new ShootingMapView(),new MapRenderer());
		((ShipPlacementView) shipPlacementView_o.controller).createShips();
		((ShootingMapView) shootingMapView_o.controller).createWeapons();
		((ShipPlacementView) shipPlacementView_o.controller).linkMapController(shootingMapView_o.controller);

	}

	public void update() {
		for (ModelObject o : objects)
			o.update();

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
