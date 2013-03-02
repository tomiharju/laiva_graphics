package ObjectModels;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ObjectControllers.BotGuiController;
import ObjectControllers.ShootingMapView;
import ObjectControllers.ObjectController;
import ObjectControllers.ShipPlacementView;
import ObjectControllers.ShipController;
import ObjectControllers.TopGuiController;
import ObjectControllers.WeaponController;
import ObjectControllers.WorldController;
import ObjectModels.ShipObject.ShipType;
import ObjectRenderers.BotGuiRenderer;
import ObjectRenderers.MapRenderer;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.SeaRenderer;
import ObjectRenderers.ShipRenderer;
import ObjectRenderers.TopGuiRenderer;
import ObjectRenderers.WeaponRenderer;
import ObjectRenderers.WorldRenderer;
import Screens.GameLogicHandler;


public class WorldObject extends ModelObject {
	
	//Class that handles the game logic
	GameLogicHandler logicHandler;
	
	public static ArrayList<ModelObject> objects;
	
	//Game components
	private ShipPlacementViewObject 	shipPlacementView_o;
	private ShootingMapViewObject		shootingMapView_o;

	
	
	
	public WorldObject(ObjectController controller,ObjectRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		
		//Create objects with controllers and renderers
		objects 			= new ArrayList<ModelObject>();
		shipPlacementView_o	= new ShipPlacementViewObject(new ShipPlacementView(),new SeaRenderer());
		shootingMapView_o		= new ShootingMapViewObject(new ShootingMapView(),new MapRenderer());
		((ShipPlacementView)shipPlacementView_o.controller).createShips();
		((ShootingMapView)shootingMapView_o.controller).createWeapons();
	
		//Link needed controllers
		((ShipPlacementView)shipPlacementView_o.controller).linkMapController(shootingMapView_o.controller);
		
		
		
		
		
	}
	
	
	
	
	public void update(){
		for(ModelObject o: objects)
			o.update();
		
	}
	
	@Override
	public void setController(ObjectController controller) {
		this.controller=(WorldController)controller;
		this.controller.setObject(this);
	}

	

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=(WorldRenderer)renderer;
		this.renderer.setObject(this);
	}




	public ShipPlacementViewObject getSea_o() {
		return shipPlacementView_o;
	}
	public ShootingMapViewObject getMap_o() {
		return shootingMapView_o;
	}

	





}
