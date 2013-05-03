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
import com.sohvastudios.battleships.game.utilities.HitSpotCalculator;

public class MortarStrategy implements WeaponStrategy {


	//Weapon properties
		final float 	RADIUS 			= 0.25f;
		final float 	DMG_DENSITY     = 1.0f;
		final float		EXP_PROXIMITY	= 0.2f;
		final float		TRIGGER_RADIUS	= 0.8f;
		Vector3 projectilePosition;
		Vector3 projectileDestination;
		private Vector3 speedVector;
		final float SPEED 	= 10;
		private Vector3 firstWayPoint;
		private Vector3 secondWayPoint;
	
	//General variables
		private Set<ShipController> hits;
		private ArrayList<Vector3> hitspots;
		private ObjectController parent;
	
	

	public MortarStrategy(ObjectController parent) {
		this.parent=parent;
		hits					= new HashSet<ShipController>();
		projectilePosition 		= new Vector3();
		projectileDestination 	= new Vector3();
		hitspots = new ArrayList<Vector3>();
		speedVector = new Vector3();
	}

	@Override
	public boolean animate(Sprite sprite,Vector3 position) {
		sprite.setRotation((float) Math.toDegrees(Math.atan2(projectileDestination.y
				- position.y, projectileDestination.x - position.x)- Math.PI / 2));
		speedVector.set(projectileDestination);
		speedVector.sub(position);
		speedVector.nor().mul(Gdx.graphics.getDeltaTime()*SPEED);
		position.add(speedVector);
		
		//Check if projectile is close to final target
		if (position.dst(secondWayPoint) < TRIGGER_RADIUS) {
			speedVector.nor().mul(position.dst(secondWayPoint)-EXP_PROXIMITY);
			position.add(speedVector);
			return true;
		//Check if projectile is close to first target (Clustering point)
		} else if (position.dst(firstWayPoint) < TRIGGER_RADIUS) {
			projectileDestination.set(secondWayPoint);
		}
		return false;

	
	}

	@Override
	public void calculatePathAndHits(Vector3 target) {
		
		//Point where the clustering happens
		Vector3 origin = new Vector3(5,20,0);
		firstWayPoint = new Vector3();
		firstWayPoint.set(target);
		firstWayPoint.sub(origin);
		firstWayPoint.nor().mul(origin.dst(target));
		firstWayPoint.div(2);
		firstWayPoint.add(origin);
		//Displacement at clustering
		Vector3 displacement = new Vector3(0,0,0);
		displacement.add((float)(-2+Math.random()*4),(float)(-2+Math.random()*4), 0);
		//Target after clustering
		secondWayPoint = new Vector3().set(target);
		secondWayPoint.add(displacement);
		//Set first waypoint to clustering point
		projectileDestination.set(firstWayPoint);
		//Add both points to result set
		((SeaContainer)parent).flightpath.add(firstWayPoint);
		((SeaContainer)parent).flightpath.add(secondWayPoint);
		findBlastAffectedShips();
		
	}

	@Override
	public void getShipsInRange(Vector3 target) {
		//Not used with mortar
	}

	

	@Override
	public Vector3 simulate(Vector3 position) {
		//Not used with mortar
		return projectileDestination;
		
	}

	@Override
	public void setRadarAnimationData(ArrayList<Vector3> path,
			ArrayList<Vector3> hits, ObjectController parent) {
		//NOt used with mortar
		
	}

	@Override
	public void dealDamage(Vector3 point, ProjectileRenderer renderer) {
		if(hits.size()>0){
			renderer.animateExplosion(point);
		for (ShipController ship : hits) {
			((ShipObject) ship.getObject()).dealDamage();
		}
		}
		else
			renderer.animateSplash(point);
	}

	@Override
	public void findBlastAffectedShips() {
		projectilePosition.set(secondWayPoint);
		int radius_factor = (int) (RADIUS*100);
		Vector3 spot = new Vector3();
		for (ObjectController oc : parent.controllers) {
			if (oc instanceof ShipController) {
				for (int i = (int) (RADIUS*100) ; i > 0 ; i -= 5) {
					for (int a = 0; a <= 360; a += 2) {
						float x = (float) (projectilePosition.x + Math.cos(Math.toRadians(a))* radius_factor/100);
						float y = (float) (projectilePosition.y + Math.sin(Math.toRadians(a))* radius_factor/100);
						spot.set(x, y, 0);
						if (oc.pollBounds().contains(spot.x, spot.y)) {
							hits.add(((ShipController)oc));
							hitspots.add(new Vector3(spot));
						}

					}
					radius_factor -= 5;
				}
			}
			if(hitspots.size()>0){
				((SeaContainer) parent).hitspot.add(new HitSpotCalculator().getWeightedHit(hitspots));
				((ShipObject) oc.getObject()).saveDamageTaken(hitspots.size());
				hitspots.clear();
			}
			
		}
		
	}
}
