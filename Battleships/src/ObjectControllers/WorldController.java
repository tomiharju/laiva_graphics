package ObjectControllers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import GameLogic.GameLogicHandler;
import ObjectModels.HitMarkerObject;
import ObjectModels.ProjectileObject;
import ObjectModels.ShipObject;
import ObjectModels.WeaponObject;
import ObjectModels.WorldObject;
import ObjectRenderers.HitMarkerRenderer;
import ObjectRenderers.ProjectileRenderer;
import ObjectRenderers.ShipRenderer;
import Utilities.DamageCalculator;
import Utilities.Turn;

public class WorldController extends ObjectController {

	
	private final int SHIP_VIEW = 1;
	private final int MAP_VIEW = 2;
	private ShipPlacementView shipView;
	private ShootingMapView mapView;
	private ObjectController active_view;
	private ArrayList<Vector2> result;
	Set<ShipController> ships_hit; 
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
		
		ships_hit 	= new HashSet<ShipController>();
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
		
		new ProjectileObject(new ProjectileController(5f,12.5f,1f,1.5f),new ProjectileRenderer(),weapon_type).setTarget(point);
		switch (weapon_type) {
		//HE Grenade
		case 0: {
			System.out.println("Shooting Grenade");
			getShipsInRange(point,radius);
			for (ShipController ship : ships_hit ) {
				((ShipObject) ship.getObject())
						.dealDamage(new DamageCalculator(point,radius,
								(ShipObject) ship.getObject()).run());
				
			}
		
			break;
			
		}
		case 1: {
			getShipsInRange(point,radius);
			for (ShipController ship : ships_hit ) {
				((ShipObject) ship.getObject())
						.dealDamage(new DamageCalculator(point, radius,
								(ShipObject) ship.getObject()).run());
			
			}
		
			// Homing missile
			// Get closest ship, direct missile towards that ship 1unit,
			// calculate dmg done.
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
		
		GameLogicHandler.sendResult((new Turn(Turn.TURN_RESULT,result)));
	}

	public void getShipsInRange(Vector3 pos, float radius) {
		Vector3 hitIndicator = new Vector3();
		ships_hit.clear();
		result.clear();
		for (ShipController sc : ShipPlacementView.shipControllers) {
			if (!ships_hit.contains(sc))
				for (int a = 0; a <= 360; a++) {
					float x = (float) (pos.x + Math.cos(Math.toRadians(a))
							* radius);
					float y = (float) (pos.y + Math.sin(Math.toRadians(a))
							* radius);
					hitIndicator.set(x, y, 0);
					if (sc.pollBounds()
							.contains(hitIndicator.x, hitIndicator.y) && !ships_hit.contains(sc)) {
						ships_hit.add(sc);
						result.add(new Vector2(hitIndicator.x,hitIndicator.y));
						
					}
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
