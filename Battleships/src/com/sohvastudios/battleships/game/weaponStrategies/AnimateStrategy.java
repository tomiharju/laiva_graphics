package com.sohvastudios.battleships.game.weaponStrategies;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.HitMarkerController;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectModels.HitMarkerObject;
import com.sohvastudios.battleships.game.objectRenderers.HitMarkerRenderer;
import com.sohvastudios.battleships.game.objectRenderers.ProjectileRenderer;

public class AnimateStrategy implements WeaponStrategy {
	//Simulation variables
	private ObjectController parent;
	
	private ArrayList<Vector3> path;
	private ArrayList<Vector3> hits;
	private Vector3 currentTarget;
	private int targetNumber;
	
	public AnimateStrategy(ObjectController parent){
		this.parent=parent;
	}
	

	@Override
	public boolean animate(Sprite sprite,Vector3 position) {
		position.lerp(currentTarget,(float) (1*Gdx.graphics.getDeltaTime()));
		if(position.dst(currentTarget)<0.2f){
			targetNumber++;
			if(path.size()>targetNumber){
				currentTarget.set(path.get(targetNumber));
			}else{
				for(Vector3 hit : hits){
					new HitMarkerObject(new HitMarkerController(hit.x,
						hit.y, 0.25f, 0.25f), new HitMarkerRenderer(),parent);
				}
				return true;
			}
		}
		
		return false;
	
	}

	@Override
	public Vector3 simulate(Vector3 position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getShipsInRange(Vector3 target) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void setRadarAnimationData(ArrayList<Vector3> path,ArrayList<Vector3> hits, ObjectController parent) {
		this.parent=parent;
		this.path=path;
		this.hits=hits;
		currentTarget = path.get(0);
		targetNumber = 0;
	}
	@Override
	public void dealDamage(Vector3 point,ProjectileRenderer renderer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void findBlastAffectedShips() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void calculatePathAndHits(Vector3 target) {
		// TODO Auto-generated method stub
		
	}
	

}
