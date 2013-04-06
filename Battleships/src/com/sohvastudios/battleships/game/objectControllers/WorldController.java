package com.sohvastudios.battleships.game.objectControllers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.objectModels.HitMarkerObject;
import com.sohvastudios.battleships.game.objectModels.ProjectileObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectModels.WeaponObject;
import com.sohvastudios.battleships.game.objectModels.WorldObject;
import com.sohvastudios.battleships.game.objectRenderers.HitMarkerRenderer;
import com.sohvastudios.battleships.game.objectRenderers.ProjectileRenderer;
import com.sohvastudios.battleships.game.objectRenderers.ShipRenderer;
import com.sohvastudios.battleships.game.utilities.DamageCalculator;
import com.sohvastudios.battleships.game.utilities.Turn;


public class WorldController extends ObjectController {

	
	private final int SHIP_VIEW = 1;
	private final int MAP_VIEW = 2;
	private ShipPlacementView shipView;
	private ShootingMapView mapView;
	private ObjectController active_view;
	private ArrayList<Vector2> result;
	
	public WorldController(float x, float y, float w, float h) {
		super(x, y, w, h);

	}

	public void initialize() {
		shipView = (ShipPlacementView) ((WorldObject) object).shipView()
				.getController();
		mapView = (ShootingMapView) ((WorldObject) object).mapView()
				.getController();
		active_view = shipView;
		shipView.show();
		mapView.hide();
		
	
		result 		= new ArrayList<Vector2>();
		
	}

	public void lockToShipView() {
		shipView.show();
		mapView.hide();
		active_view = shipView;
	}

	public void lockToMapView() {
		mapView.show();
		shipView.hide();
		active_view = mapView;
	}

	public boolean allShipsDestroyed() {
		for (ShipController sc : ShipPlacementView.shipControllers) {
			if (!((ShipObject) sc.getObject()).isDestroyed())
				return false;
		}
		return true;
	}

	public void calculateDamageTaken(Vector3 point, int weapon_type) {
		WeaponObject weapon = (WeaponObject) ShootingMapView.weaponControllers.get(weapon_type).getObject();
		float radius =  weapon.getWeapon().getRadius();
		
		
		switch (weapon_type) {
		//HE Grenade
		case 0: {
			new ProjectileObject(new ProjectileController(5f,12.5f,1f,1.5f),new ProjectileRenderer(),weapon_type).setTarget(point,radius);
			break;
			}
		
		case 1: {
			
			new ProjectileObject(new ProjectileController(5f,12.5f,1f,1.5f),new ProjectileRenderer(),weapon_type).setTarget(point,radius);
			break;
		}
		case 2: {
			// Mortar
			break;
		}
		case 3: {
			// NavalGun
			break;
		}
		case 4: {
			// Phalanx CIWS
			break;
		}
		}
		
	
	}



	public void touchDown(Vector3 touchPoint) {
		active_view.handleInputDown(touchPoint);

	}

	public void touchUp(Vector3 pos) {
		active_view.handleInputUp(pos);

	}

	public void touchDragged(Vector3 pos) {
		active_view.handleInputDrag(pos);

	}

}
