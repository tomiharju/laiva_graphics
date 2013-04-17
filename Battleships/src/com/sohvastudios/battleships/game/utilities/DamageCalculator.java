package com.sohvastudios.battleships.game.utilities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectModels.WeaponObject;

public class DamageCalculator {

	ShipObject ship;
	WeaponObject weapon;
	Rectangle ship_bounds;
	float hits_total;
	Vector3 center, hit_marker;
	long startTime;
	float radius;

	public DamageCalculator(Vector3 point, float radius, ShipObject ship) {

		this.radius = radius;
		this.ship = ship;
		ship_bounds = new Rectangle(ship.getController().pollBounds());
		center = new Vector3(point);
		hit_marker = new Vector3();

	}

	public float calculate() {

		startTime = System.currentTimeMillis();
		float radius_factor = radius;
		for (float i = radius; i >= 0; i -= 0.1) { // 10 circles in 1 unit
			for (int a = 0; a <= 360; a += 2) {
				float x = (float) (center.x + Math.cos(Math.toRadians(a))
						* radius_factor);
				float y = (float) (center.y + Math.sin(Math.toRadians(a))
						* radius_factor);

				hit_marker.set(x, y, 0);
				if (ship_bounds.contains(hit_marker.x, hit_marker.y)) {
					hits_total++;

				}

			}
			radius_factor -= 0.1;
		}

		long finalTime = System.currentTimeMillis();

		System.out.println("Algoritm took " + (finalTime - startTime)
				+ " milliseconds.");
		System.out.println("Total hits " + hits_total);
		return hits_total;
	}

}
