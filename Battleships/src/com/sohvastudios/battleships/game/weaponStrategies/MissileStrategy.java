package com.sohvastudios.battleships.game.weaponStrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.ShipController;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectRenderers.ProjectileRenderer;
import com.sohvastudios.battleships.game.utilities.DamageCalculator;

public class MissileStrategy implements WeaponStrategy {

	private ObjectController parent;
	private Set<ShipController> ships_hit;
	private Vector3 primaryTarget;
	public Vector3 currentTarget;
	public Vector3 virtualposition;

	public MissileStrategy(ObjectController parentController) {
		this.parent=parentController;
		ships_hit = new HashSet<ShipController>();
		primaryTarget = new Vector3();
		currentTarget = new Vector3();
		virtualposition = new Vector3();
	}

	@Override
	public boolean animate(Sprite sprite, Vector3 position) {
		sprite.setRotation((float) Math.toDegrees(Math.atan2(currentTarget.y
				- position.y, currentTarget.x - position.x)
				- Math.PI / 2));
		position.x += ((currentTarget.x - position.x) * 1.25f)* Gdx.graphics.getDeltaTime() * 2;
		position.y += ((currentTarget.y - position.y) * 1.25f)* Gdx.graphics.getDeltaTime() * 2;
		if (position.dst(primaryTarget) < 0.2f) {
			return true;
		} else if (position.dst(currentTarget) < 0.2f) {
			currentTarget.set(primaryTarget);
		}
		return false;

	}
	public Vector3 simulate(Vector3 position){
		virtualposition.set(position);
		return primaryTarget;
	
	}

	@Override
	public void setFlightPath(ArrayList<Vector3> pathlist,Vector3 target) {
	
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

		pathlist.add(target);
		pathlist.add(primaryTarget);
	}

	public void getShipsInRange(Vector3 target, float radius) {
		ships_hit.clear();
		for (ObjectController oc : parent.controllers) {
			if(oc instanceof ShipController)
				if (!ships_hit.contains(oc)) {
					if (target.dst(oc.pollPosition()) < radius){
						ships_hit.add((ShipController) oc);
					}
			}
		}
		
		
	}

	

	@Override
	public void setRadarAnimationData(ArrayList<Vector3> path,
			ArrayList<Vector3> hits, ObjectController parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dealDamage(Set<ShipController> shipshit, Vector3 point,float radius,ProjectileRenderer renderer) {
		if(shipshit.size()>0){
			renderer.animateExplosion(point);
		for (ShipController ship : shipshit) {
			((ShipObject) ship.getObject()).dealDamage(new DamageCalculator(
					point, radius, (ShipObject) ship.getObject()).calculate());
		}
		}
		else
			renderer.animateSplash(point);
		
	}

}
