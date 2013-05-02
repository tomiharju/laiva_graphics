package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectModels.WorldObject;
import com.sohvastudios.battleships.game.objectRenderers.WorldRenderer;

public class WorldController extends ObjectController {

	private SeaContainer shipView;
	private RadarContainer radarView;
	private Vector3 projTouch;
	private Plane xzPlane = new Plane(new Vector3(0,0,1),0);
	private Vector3 intersection = new Vector3();
	
	
	
	public WorldController(float x, float y, float w, float h) {
		super(x, y, w, h);
		
		
	}

	public void initialize() {
		shipView = (SeaContainer) ((WorldObject) object).shipView().getController();
		radarView = (RadarContainer) ((WorldObject) object).mapView().getController();
	
		shipView.show();
		
		projTouch = new Vector3();

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
		Ray pickray = WorldRenderer.seaCam.getPickRay(p.x,p.y);
		Intersector.intersectRayPlane(pickray, xzPlane, intersection);
		
		shipView.handleInputDown(intersection);
	
		projTouch.set(p);
		WorldRenderer.radarCam.unproject(projTouch);
		radarView.handleInputDown(projTouch);
	
	}
	
	public void touchDragged(Vector3 p) {
	
		Ray pickray = WorldRenderer.seaCam.getPickRay(p.x,p.y);
		Intersector.intersectRayPlane(pickray, xzPlane, intersection);
	
		shipView.handleInputDrag(intersection);
		
		projTouch.set(p);
		WorldRenderer.radarCam.unproject(projTouch);
		radarView.handleInputDrag(projTouch);
		
		
		
	}

	@Override
	public void initialize(ObjectController parent) {
		// TODO Auto-generated method stub
		
	}	

}
