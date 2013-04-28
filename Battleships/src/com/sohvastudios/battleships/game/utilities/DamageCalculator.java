package com.sohvastudios.battleships.game.utilities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.ShipController;
import com.sohvastudios.battleships.game.objectModels.WeaponObject;

public class DamageCalculator {

	
	WeaponObject weapon;
	Rectangle ship_bounds;
	float hits_total;
	Vector3 center, hit_marker;
	long startTime;
	float radius;

	public DamageCalculator(Vector3 point, float radius, ShipController ship) {

		this.radius = radius;
		ship_bounds = new Rectangle(ship.pollBounds());
		center = new Vector3(point);
		hit_marker = new Vector3();

	}

	public float calculate() {
		int radius_factor = (int) (radius*100);
		for (int i = (int) (radius*100) ; i > 0 ; i -= 5) {
			for (int a = 0; a <= 360; a += 2) {
				float x = (float) (center.x + Math.cos(Math.toRadians(a))* (radius_factor/100));
				float y = (float) (center.y + Math.cos(Math.toRadians(a))* (radius_factor/100));
				hit_marker.set(x, y, 0);
				if (ship_bounds.contains(hit_marker.x, hit_marker.y)) {
					hits_total++;

				}

			}
			radius_factor -= 5;
		}
		System.out.println("Total hits " + hits_total);
		return hits_total;
	}

}
