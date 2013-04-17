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

public class TorpedoStrategy implements WeaponStrategy{


	//Weapon properties
		final float 	RADIUS 			= 0.25f;
		final float 	DMG_DENSITY     = 1.0f;
		final float		EXP_PROXIMITY	= 0.1f;
		Vector3 projectilePosition;
		Vector3 projectileDestination;
		Vector3 primaryDestination;
	//General variables
	private ObjectController parent;
	private Set<ShipController> hits;
	
	public TorpedoStrategy(ObjectController parent){
		this.parent=parent;
		projectilePosition 		= new Vector3(5,10,0);	//TODO: Implement torpedo launch position
		projectileDestination 	= new Vector3();
		primaryDestination		= new Vector3();
		hits = new HashSet<ShipController>();
		
	}
	@Override
	public void calculatePathAndHits(Vector3 target) {
		projectileDestination.set(target);
		primaryDestination.set(simulate(projectileDestination));
		((SeaContainer)parent).flightpath.add(primaryDestination);
		projectilePosition.set(primaryDestination);
		findBlastAffectedShips();
	}

	@Override
	public void setRadarAnimationData(ArrayList<Vector3> path,
			ArrayList<Vector3> hits, ObjectController parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean animate(Sprite sprite, Vector3 position) {
		sprite.setRotation((float) Math.toDegrees(Math.atan2(projectileDestination.y
				- position.y,projectileDestination.x - position.x)
				- Math.PI / 2));
		position.lerp(projectileDestination,(float) (1*Gdx.graphics.getDeltaTime()));
		
		if (position.dst(projectileDestination) < EXP_PROXIMITY) {
			return true;
		}else
			return false;
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
	public Vector3 simulate(Vector3 target) {
		while(projectilePosition.dst(target)>0.1){
			projectilePosition.lerp(target, 0.1f);
			for(ObjectController sc : parent.controllers)
				if(sc instanceof ShipController){
					if(sc.pollBounds().contains(projectilePosition.x,projectilePosition.y)){
						projectileDestination.set(projectilePosition);
						return projectilePosition;
					}
				}
	
		}
		return target;
	}

	@Override
	public void getShipsInRange(Vector3 target) {
		//Not used in torpedo
		
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
