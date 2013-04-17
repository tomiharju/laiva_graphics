package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectModels.WorldObject;

public class WorldController extends ObjectController {

	private SeaContainer shipView;
	private RadarContainer radarView;
	
	private ArrayList<Vector2> result;
	
	
	
	
	public WorldController(float x, float y, float w, float h) {
		super(x, y, w, h);
		
		
	}

	public void initialize() {
		shipView = (SeaContainer) ((WorldObject) object).shipView().getController();
		radarView = (RadarContainer) ((WorldObject) object).mapView().getController();
	
		shipView.show();
		result = new ArrayList<Vector2>();

	}

	public void lockRadar(){
		radarView.lockRadar();
		radarView.hide();
	}
	public void unlockRadar(){
		radarView.unlockRadar();
		radarView.show();
	}
	public void showRadar(){
		radarView.show();
	}
	public void hideRadar(){
		radarView.hide();
	}

	public boolean allShipsDestroyed() {
		for (ObjectController oc : shipView.controllers) {
			if(oc instanceof ShipController)
				if (!((ShipObject) oc.getObject()).isDestroyed())
					return false;
		}
		return true;
	}

	public void calculateDamageTaken(Vector3 point, int weapon_type) {
		shipView.calculateDamageTaken(point, weapon_type);
	}
	public void drawResult(HashMap<ArrayList<Vector3>,ArrayList<Vector3>> result){
		radarView.drawResult(result);
	}

	public void touchDown(Vector3 p) {
		
			if(!radarView.isLocked()){
				radarView.handleInputDown(p);
			}
			else{
				shipView.handleInputDown(p);
			}
	
	}
	
	public void touchDragged(Vector3 p) {
	
		
		if(!radarView.isLocked()){
			radarView.handleInputDrag(p);
		}
		else{
			shipView.handleInputDrag(p);
		}
	}

	@Override
	public void initialize(ObjectController parent) {
		// TODO Auto-generated method stub
		
	}	

}
