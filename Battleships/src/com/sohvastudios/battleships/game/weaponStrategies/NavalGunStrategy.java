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

public class NavalGunStrategy implements WeaponStrategy{

	private Vector3 currentTarget;

	public NavalGunStrategy() {
		currentTarget = new Vector3();
	}

	@Override
	public boolean animate(Sprite sprite,Vector3 position) {
		sprite.setRotation((float) Math.toDegrees(Math.atan2(currentTarget.y
				- position.y, currentTarget.x - position.x)
				- Math.PI / 2));
		position.x += ((currentTarget.x - position.x) * 0.4f)
				* Gdx.graphics.getDeltaTime() * 2;
		position.y += ((currentTarget.y - position.y) * 0.6f)
				* Gdx.graphics.getDeltaTime() * 2;
		if (position.dst(currentTarget) < 0.2f) {
			return true;
		}

		return false;

	}

	@Override
	public void setFlightPath(ArrayList<Vector3> pathlist,Vector3 target) {
		currentTarget.set(target);
		
	}

	@Override
	public void getShipsInRange(Vector3 target, float radius) {
		// TODO Auto-generated method stub

	}

	

	@Override
	public Vector3 simulate(Vector3 position) {
		return currentTarget;
		
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
