package com.sohvastudios.battleships.game.weaponStrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.SeaContainer;
import com.sohvastudios.battleships.game.objectControllers.ShipController;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectRenderers.ProjectileRenderer;
import com.sohvastudios.battleships.game.utilities.DamageCalculator;

public class MissileStrategy implements WeaponStrategy {


	//Weapon properties
		final float 	RADIUS 			= 0.25f;
		final float 	SEEKING_RADIUS 	= 1.5f;
		final float 	DMG_DENSITY     = 1.0f;
		final float		EXP_PROXIMITY	= 0.1f;
		
		Vector3 projectilePosition;
		Vector3 projectileDestination;
		Vector3 primaryDestination;
		
		Set<ShipController>		targetsInSweep;
		
	//General variables
		
		private Set<ShipController> hits;
		private ObjectController parent;
	
	
	
	
	

	public MissileStrategy(ObjectController parent) {
		this.parent=parent;
		hits 				= new HashSet<ShipController>();
		targetsInSweep 		= new HashSet<ShipController>();
		projectilePosition 	= new Vector3();
		projectileDestination = new Vector3();
		primaryDestination  = new Vector3();
		
	}

	@Override
	public boolean animate(Sprite sprite, Vector3 position) {
		sprite.setRotation((float) Math.toDegrees(Math.atan2(projectileDestination.y
				- position.y, projectileDestination.x - position.x)
				- Math.PI / 2));
		position.lerp(projectileDestination,(float) (Gdx.graphics.getDeltaTime()));
		if (position.dst(primaryDestination) < EXP_PROXIMITY) {
			return true;
		} else if (position.dst(projectileDestination) < EXP_PROXIMITY) {
			projectileDestination.set(primaryDestination);
		}
		
		return false;

	}
	public Vector3 simulate(Vector3 position){
		//Not used with missiles
		return projectileDestination;
	
	}

	@Override
	public void calculatePathAndHits(Vector3 target) {
	
		projectileDestination.set(target);
		primaryDestination.set(target);
		getShipsInRange(target);
		boolean closerTargetFound = false;
		Vector3 distance_vector = new Vector3(10, 10, 0);
		Vector3 gap_vector 		= new Vector3(0, 0, 0);
		Vector3 temp_pos 		= new Vector3(0, 0, 0);

		for (ShipController ship : targetsInSweep) {
			temp_pos.set(ship.pollPosition());
			gap_vector.set(temp_pos.sub(target));
			if (gap_vector.len() < distance_vector.len()) {
				distance_vector.set(gap_vector);
				closerTargetFound = true;
			}
		}
		if (closerTargetFound)
			primaryDestination.add(distance_vector);

		((SeaContainer)parent).flightpath.add(projectileDestination);
		((SeaContainer)parent).flightpath.add(primaryDestination);
		projectilePosition.set(primaryDestination);
		findBlastAffectedShips();
	}

	public void getShipsInRange(Vector3 target) {
		targetsInSweep.clear();
		for (ObjectController oc : parent.controllers) {
			if(oc instanceof ShipController)
				if (!targetsInSweep.contains(oc)) {
					if (target.dst(oc.pollPosition()) < SEEKING_RADIUS){
						targetsInSweep.add((ShipController) oc);
					}
			}
		}
		
		
	}

	

	@Override
	public void setRadarAnimationData(ArrayList<Vector3> path,
			ArrayList<Vector3> hits, ObjectController parent) {
		//Not used with missiles
		
	}

	@Override
	public void dealDamage(Vector3 point,ProjectileRenderer renderer) {
		if(hits.size()>0){
			renderer.animateExplosion(point);
		for (ShipController ship : hits) {
			((ShipObject) ship.getObject()).dealDamage(new DamageCalculator(
					point, RADIUS, (ShipObject) ship.getObject()).calculate());
		}
		}
		else
			renderer.animateSplash(point);
		
	}

	@Override
	public void findBlastAffectedShips() {
		Vector3 hitIndicator = new Vector3();
		for (ObjectController oc : parent.controllers) {
			if(oc instanceof ShipController){
			if (!hits.contains(oc)) {
				hitIndicator.set(projectilePosition.x, projectilePosition.y, 0);
				if (oc.pollBounds().contains(hitIndicator.x, hitIndicator.y)) {
					((SeaContainer)parent).hitspot.add(new Vector3(hitIndicator.x, hitIndicator.y,0));
					hits.add((ShipController) oc);
					continue;
				}
				for (int a = 0; a <= 360; a++) {
					float x = (float) (projectilePosition.x + Math.cos(Math.toRadians(a))* RADIUS);
					float y = (float) (projectilePosition.y + Math.sin(Math.toRadians(a))* RADIUS);
					hitIndicator.set(x, y, 0);
					if (oc.pollBounds().contains(hitIndicator.x, hitIndicator.y)&& !hits.contains(oc)) {
						hits.add((ShipController) oc);
						((SeaContainer)parent).hitspot.add(new Vector3(hitIndicator.x, hitIndicator.y,0));

					}
				}
			}
		}
		}
	
		
	}

}
