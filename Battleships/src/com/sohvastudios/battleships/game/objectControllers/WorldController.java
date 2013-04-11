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
	private RadarController mapView;
	private GuiController guiView;
	private ArrayList<Vector2> result;
	private Vector3 touchPoint;
	
	
	
	public WorldController(float x, float y, float w, float h) {
		super(x, y, w, h);
		touchPoint = new Vector3();
		
	}

	public void initialize() {
		shipView = (SeaController) ((WorldObject) object).shipView().getController();
		mapView = (RadarController) ((WorldObject) object).mapView().getController();
		guiView = (GuiController)((WorldObject)object).guiView().getController();
		shipView.show();
		mapView.show();
		result = new ArrayList<Vector2>();

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
		if(p.y>5){
			touchPoint.set(p.x,p.y-5,0);
			shipView.handleInputDown(touchPoint);
		}
		else if(p.y<5 && p.x>5){
			touchPoint.set(p.x,p.y,0);
			mapView.handleInputDown(touchPoint);
		}
		else if(p.y<5 && p.x<5){
			System.out.println("Sending touch to gui..");
			guiView.handleInputDown(p);
		}
		

	}
	
	public void doubleTap(Vector3 p){
	
		if(p.y>5){
			shipView.handleDoubleTap(touchPoint);
			
		}
		else if(p.y<5 && p.x>5){
			mapView.handleDoubleTap(touchPoint);
		}
		else if(p.y<5 && p.x<5){
			System.out.println("Sending touch to gui..doubletap");
			guiView.handleInputDown(p);
		}
	}

	public void touchDragged(Vector3 p) {
		if(p.y>5){
			touchPoint.set(p.x,p.y-5,0);
			shipView.handleInputDrag(touchPoint);
		}
		else if(p.y<5 && p.x>5){
			touchPoint.set(p.x,p.y,0);
			mapView.handleInputDrag(touchPoint);
		}

	}

}
