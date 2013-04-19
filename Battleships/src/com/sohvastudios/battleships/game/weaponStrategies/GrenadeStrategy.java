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
import com.sohvastudios.battleships.game.utilities.HitSpotCalculator;

public class GrenadeStrategy implements WeaponStrategy {

	// Weapon properties
	final float RADIUS = 0.25f;
	final float DMG_DENSITY = 1.0f;
	final float EXP_PROXIMITY = 0.1f;
	Vector3 projectilePosition;
	Vector3 projectileDestination;

	// General variables
	private Set<ShipController> hits;
	private ArrayList<Vector3> hitspots;
	private ObjectController parent;

	public GrenadeStrategy(ObjectController parent) {
		this.parent = parent;
		projectilePosition = new Vector3();
		projectileDestination = new Vector3();
		hits = new HashSet<ShipController>();
		hitspots = new ArrayList<Vector3>();
	}

	@Override
	public boolean animate(Sprite sprite, Vector3 position) {
		sprite.setRotation((float) Math.toDegrees(Math.atan2(
				projectileDestination.y - position.y, projectileDestination.x	- position.x)	- Math.PI / 2));
		position.lerp(projectileDestination,(float) (Gdx.graphics.getDeltaTime()));

		if (position.dst(projectileDestination) < EXP_PROXIMITY) {
			return true;
		} else
			return false;
	}

	@Override
	public void calculatePathAndHits(Vector3 target) {
		projectileDestination.set(target);
		projectilePosition.set(projectileDestination);
		((SeaContainer) parent).flightpath.add(projectileDestination);
		findBlastAffectedShips();

	}

	@Override
	public void getShipsInRange(Vector3 target) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector3 simulate(Vector3 position) {
		return projectilePosition;

	}

	@Override
	public void setRadarAnimationData(ArrayList<Vector3> path,
			ArrayList<Vector3> hits, ObjectController parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dealDamage(Vector3 point, ProjectileRenderer renderer) {
		if(hits.size()>0){
			renderer.animateExplosion(point);
		for (ShipController ship : hits) {
			((ShipObject) ship.getObject()).dealDamage(new DamageCalculator(
					point, RADIUS,ship).calculate());
		}
		}
		else
			renderer.animateSplash(point);
	}

	@Override
	public void findBlastAffectedShips() {
		float radius_factor = RADIUS;
		Vector3 spot = new Vector3();
		for (ObjectController oc : parent.controllers) {
			if (oc instanceof ShipController) {
				for (float i = RADIUS; i >= 0; i -= 0.1) { // 10 circles in 1
					for (int a = 0; a <= 360; a += 2) {
						float x = (float) (projectilePosition.x + Math.cos(Math.toRadians(a))* radius_factor);
						float y = (float) (projectilePosition.y + Math.sin(Math.toRadians(a))* radius_factor);
						spot.set(x, y, 0);
						if (oc.pollBounds().contains(spot.x, spot.y)) {
							hits.add(((ShipController)oc));
							hitspots.add(new Vector3(spot));

						}

					}
					radius_factor -= 0.1;
				}
			}
			if(hitspots.size()>0){
				((SeaContainer) parent).hitspot.add(new HitSpotCalculator().getWeightedHit(hitspots));
				hitspots.clear();
			}
			
		}
		
	}

	
}