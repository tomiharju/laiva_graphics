package ObjectControllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ObjectModels.ShipObject;
import ObjectModels.WeaponObject;
import ObjectModels.WeaponObject.Weapon;
import ObjectModels.WorldObject;

public class WorldController extends ObjectController {

	int state;
	private final int SHIP_VIEW = 1;
	private final int MAP_VIEW = 2;
	private ShipPlacementView shipView;
	private ShootingMapView mapView;
	private ObjectController active_view;

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
		state = 1;
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

		float radius = 0;
		int tester=0;
		switch (weapon_type) {

		case 0: {
			radius = 0.25f;
			for (ShipController ship : getShipsInRange(point, radius)) {
				((ShipObject) ship.getObject())
						.dealDamage(new DamageCalculator(point, radius,
								(ShipObject) ship.getObject()).run());
				tester++;
			}
			System.out.println("Ships hit "+tester);
			break;
			// HE Grenade
		}
		case 1: {
			radius = .5f;
			for (ShipController ship : getShipsInRange(point, radius)) {
				((ShipObject) ship.getObject())
						.dealDamage(new DamageCalculator(point, radius,
								(ShipObject) ship.getObject()).run());
				tester++;
			}
			System.out.println("Ships hit "+tester);
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

	}

	public Set<ShipController> getShipsInRange(Vector3 pos, float radius) {
		Vector3 hitIndicator = new Vector3();
		Set<ShipController> ships_hit = new HashSet<ShipController>();
		for (ShipController sc : ShipPlacementView.shipControllers) {
			if (!ships_hit.contains(sc))
				for (int a = 0; a <= 360; a++) {
					float x = (float) (pos.x + Math.cos(Math.toRadians(a))
							* radius);
					float y = (float) (pos.y + Math.sin(Math.toRadians(a))
							* radius);
					hitIndicator.set(x, y, 0);
					if (sc.pollBounds()
							.contains(hitIndicator.x, hitIndicator.y)) {
						ships_hit.add(sc);
					}
				}
		}
		return ships_hit;

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
