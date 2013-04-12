package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectModels.ProjectileObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectModels.WeaponObject.Weapon;
import com.sohvastudios.battleships.game.objectModels.WorldObject;
import com.sohvastudios.battleships.game.objectRenderers.ProjectileRenderer;

public class WorldController extends ObjectController {

	private SeaController shipView;
	private RadarController radarView;
	
	private ArrayList<Vector2> result;
	private Vector3 touchPoint;
	
	
	
	public WorldController(float x, float y, float w, float h) {
		super(x, y, w, h);
		touchPoint = new Vector3();
		
	}

	public void initialize() {
		shipView = (SeaController) ((WorldObject) object).shipView().getController();
		radarView = (RadarController) ((WorldObject) object).mapView().getController();
	
		shipView.show();
		result = new ArrayList<Vector2>();

	}

	public void showRadar(){
		radarView.show();
		radarView.unlockRadar();
	}
	public void hideRadar(){
		radarView.hide();
		radarView.lockRadar();
	}

	public boolean allShipsDestroyed() {
		for (ShipController sc : SeaController.shipControllers) {
			if (!((ShipObject) sc.getObject()).isDestroyed())
				return false;
		}
		return true;
	}

	public void calculateDamageTaken(Vector3 point, int weapon_type) {
		
		float radius = Weapon.values()[weapon_type].getRadius();

		switch (weapon_type) {

		case 0: {
			// Grenade
			new ProjectileObject(new ProjectileController(5f, 15f, 1f, 1.5f),
					new ProjectileRenderer(), weapon_type).setTarget(point,radius);
			break;
		}

		case 1: {
			// Heatseeker
			new ProjectileObject(new ProjectileController(5f, 15f, 1f, 1.5f),
					new ProjectileRenderer(), weapon_type).setTarget(point,radius);
			break;
		}
		case 2: {
			// Mortar
			new ProjectileObject(new ProjectileController(5f, 15f, 1f, 1.5f),
					new ProjectileRenderer(), weapon_type).setTarget(point,radius);
			break;
		}
		case 3: {
			// NavalGun
			new ProjectileObject(new ProjectileController(5f, 15f, 1f, 1.5f),
					new ProjectileRenderer(), weapon_type).setTarget(point,radius);
			break;
		}
		case 4: {
			// Phalanx CIWS
			new ProjectileObject(new ProjectileController(5f, 15f, 1f, 1.5f),
					new ProjectileRenderer(), weapon_type).setTarget(point,radius);
			break;
		}
		}

	}

	public void touchDown(Vector3 p) {
		
			if(!radarView.isLocked()){
				radarView.handleInputDown(p);
			}
			else{
				shipView.handleInputDown(p);
			}
	
		
	
		
		
		
		

	}
	
	public void doubleTap(Vector3 p){
			shipView.handleDoubleTap(p);
			radarView.handleDoubleTap(p);
		
	
			
	}

	public void touchDragged(Vector3 p) {
	
		
		if(!radarView.isLocked()){
			radarView.handleInputDrag(p);
		}
		else{
			shipView.handleInputDrag(p);
		}
	}
	public void touchLong(Vector3 p){
			radarView.handleLongPress(p);
		
		
	}

}
