package com.sohvastudios.battleships.game.weaponStrategies;

import java.util.ArrayList;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.ShipController;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectRenderers.ProjectileRenderer;
import com.sohvastudios.battleships.game.utilities.DamageCalculator;

public class TorpedoStrategy implements WeaponStrategy{

	private Vector3 currentTarget;
	private Vector3 torpedoPosition;
	private ObjectController parent;
	
	public TorpedoStrategy(ObjectController parentController){
		this.parent=parentController;
		currentTarget = new Vector3();
		torpedoPosition = new Vector3(10,15,0);
	}
	@Override
	public void setFlightPath(ArrayList<Vector3> pathlist, Vector3 target) {
		pathlist.add(simulate(target));
	}

	@Override
	public void setRadarAnimationData(ArrayList<Vector3> path,
			ArrayList<Vector3> hits, ObjectController parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean animate(Sprite sprite, Vector3 position) {
		sprite.setRotation((float) Math.toDegrees(Math.atan2(currentTarget.y
				- position.y, currentTarget.x - position.x)
				- Math.PI / 2));
		position.lerp(currentTarget,(float) (1*Gdx.graphics.getDeltaTime()));
		if(position.dst(currentTarget)<0.1f){
			return true;
		}
		
		return false;
	}

	@Override
	public void dealDamage(Set<ShipController> shipshit, Vector3 point,
			float radius, ProjectileRenderer renderer) {
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

	@Override
	public Vector3 simulate(Vector3 target) {
		while(torpedoPosition.dst(target)>0.1){
			torpedoPosition.lerp(target, 0.1f);
			for(ObjectController sc : parent.controllers)
				if(sc instanceof ShipController){
					if(sc.pollBounds().contains(torpedoPosition.x,torpedoPosition.y)){
						currentTarget.set(torpedoPosition);
						return torpedoPosition;
					}
				}
	
		}
		currentTarget.set(target);
		return target;
	}

	@Override
	public void getShipsInRange(Vector3 target, float radius) {
		// TODO Auto-generated method stub
		
	}

	

}
