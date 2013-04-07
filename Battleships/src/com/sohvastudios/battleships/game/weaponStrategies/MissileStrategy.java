package com.sohvastudios.battleships.game.weaponStrategies;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.ShipController;
import com.sohvastudios.battleships.game.objectControllers.ShipPlacementView;

public class MissileStrategy implements WeaponStrategy {

	private Set<ShipController> ships_hit;
	private Vector3 primaryTarget;
	public Vector3 currentTarget;

	public MissileStrategy() {
		ships_hit = new HashSet<ShipController>();
		primaryTarget = new Vector3();
		currentTarget = new Vector3();
	}

	@Override
	public boolean update(Vector3 position) {
		position.x += ((currentTarget.x - position.x) * 1.25f)
				* Gdx.graphics.getDeltaTime() * 2;
		position.y += ((currentTarget.y - position.y) * 1.25f)
				* Gdx.graphics.getDeltaTime() * 2;
		if (position.dst(primaryTarget) < 0.2f) {
			return true;
		} else if (position.dst(currentTarget) < 0.2f) {
			currentTarget.set(primaryTarget);
		}
		return false;

	}

	@Override
	public Vector3 setTarget(Vector3 target) {
		primaryTarget.set(target);
		currentTarget.set(target);
		getShipsInRange(target, 1.5f);

		boolean closerTargetFound = false;
		Vector3 distance_vector = new Vector3(10, 10, 0);
		Vector3 gap_vector = new Vector3(0, 0, 0);
		Vector3 temp_pos = new Vector3(0, 0, 0);

		for (ShipController ship : ships_hit) {
			temp_pos.set(ship.pollPosition());
			gap_vector.set(temp_pos.sub(target));
			if (gap_vector.len() < distance_vector.len()) {
				distance_vector.set(gap_vector);
				closerTargetFound = true;
			}
		}
		if (closerTargetFound)
			primaryTarget.add(distance_vector);

		return null;
	}

	public void getShipsInRange(Vector3 target, float radius) {

		ships_hit.clear();

		for (ShipController sc : ShipPlacementView.shipControllers) {
			if (!ships_hit.contains(sc)) {
				if (target.dst(sc.pollPosition()) < radius)
					ships_hit.add(sc);

			}
		}

	}

	@Override
	public Vector3 getPos() {
		return currentTarget;
	}

}
