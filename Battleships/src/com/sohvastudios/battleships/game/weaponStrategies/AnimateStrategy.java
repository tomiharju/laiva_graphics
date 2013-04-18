package com.sohvastudios.battleships.game.weaponStrategies;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.HitMarkerController;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.RadarContainer;
import com.sohvastudios.battleships.game.objectModels.HitMarkerObject;
import com.sohvastudios.battleships.game.objectRenderers.HitMarkerRenderer;
import com.sohvastudios.battleships.game.objectRenderers.ProjectileRenderer;

public class AnimateStrategy implements WeaponStrategy {
	//Simulation variables
	private ObjectController parent;
	final float RADIUS_GRENADE	 	= 0.25f;
	final float RADIUS_MISSILE	 	= 0.5f;
	final float RADIUS_MORTAR	 	= 0.25f;
	final float RADIUS_NAVALGUN		= 0.75f;
	final float RADIUS_PHALANX		= 0.25f;
	final float RADIUS_TORPEDO		= 0.5f;
	
	final float PROX_GRENADE	 	= 0.1f;
	final float PROX_MISSILE	 	= 0.1f;
	final float PROX_MORTAR	 		= 0.1f;
	final float PROX_NAVALGUN		= 0.1f;
	final float PROX_PHALANX		= 0.1f;
	final float PROX_TORPEDO		= 0.1f;
	float blastSimulationRadius;
	float blastTriggerRange;
	private ArrayList<Vector3> path;
	private ArrayList<Vector3> hits;
	private Vector3 currentTarget;
	private int targetNumber;
	
	public AnimateStrategy(ObjectController parent){
		this.parent=parent;
		int weapon = ((RadarContainer)parent).selectedWeapon;
		switch(weapon){	
		case 0:blastSimulationRadius=RADIUS_GRENADE;blastTriggerRange=PROX_GRENADE;break;
		case 1:blastSimulationRadius=RADIUS_MISSILE;blastTriggerRange=PROX_MISSILE;break;
		case 2:blastSimulationRadius=RADIUS_MORTAR;blastTriggerRange=PROX_MORTAR;break;
		case 3:blastSimulationRadius=RADIUS_NAVALGUN;blastTriggerRange=PROX_NAVALGUN;break;
		case 4:blastSimulationRadius=RADIUS_TORPEDO;blastTriggerRange=PROX_TORPEDO;break;
		case 5:blastSimulationRadius=RADIUS_TORPEDO;blastTriggerRange=PROX_TORPEDO;break;
		}
		
		
	}
	

	@Override
	public boolean animate(Sprite sprite,Vector3 position) {
		position.lerp(currentTarget,(float) (1*Gdx.graphics.getDeltaTime()));
	
		if(position.dst(currentTarget)<blastTriggerRange){
			targetNumber++;
			if(path.size()>targetNumber){
				currentTarget.set(path.get(targetNumber));
			}else{
				for(Vector3 hit : hits){
					new HitMarkerObject(new HitMarkerController(hit.x,
						hit.y, 0.25f, 0.25f), new HitMarkerRenderer(),parent,true);
				}
				new HitMarkerObject(new HitMarkerController(position.x,
						position.y, blastSimulationRadius*2, blastSimulationRadius*2), new HitMarkerRenderer(),parent,false);
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
