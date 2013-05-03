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
	
	final float PROX_GRENADE	 	= 0.2f;
	final float PROX_MISSILE	 	= 0.1f;
	final float PROX_MORTAR	 		= 0.5f;
	final float PROX_NAVALGUN		= 0.6f;
	final float PROX_PHALANX		= 0.6f;
	final float PROX_TORPEDO		= 0.6f;
	
	final float TRIGGER_RADIUS		= 0.8f;
	float blastSimulationRadius;
	float blastTriggerRange;
	private ArrayList<Vector3> path;
	private ArrayList<Vector3> hits;
	private Vector3 currentTarget;
	private int targetNumber;
	
	private Vector3 speedVector;
	private float SPEED;
	
	public AnimateStrategy(ObjectController parent){
		this.parent=parent;
		speedVector = new Vector3();
		int weapon = ((RadarContainer)parent).selectedWeapon;
		switch(weapon){	
		case 0:blastSimulationRadius=RADIUS_GRENADE;blastTriggerRange=PROX_GRENADE;SPEED=14;break;
		case 1:blastSimulationRadius=RADIUS_MISSILE;blastTriggerRange=PROX_MISSILE;SPEED=20;break;
		case 2:blastSimulationRadius=RADIUS_MORTAR;blastTriggerRange=PROX_MORTAR;SPEED=10;break;
		case 3:blastSimulationRadius=RADIUS_NAVALGUN;blastTriggerRange=PROX_NAVALGUN;SPEED=15;break;
		case 4:blastSimulationRadius=RADIUS_TORPEDO;blastTriggerRange=PROX_TORPEDO;SPEED=12;break;
		case 5:blastSimulationRadius=RADIUS_TORPEDO;blastTriggerRange=PROX_TORPEDO;SPEED=15;break;
		}
		
		
	}
	

	@Override
	public boolean animate(Sprite sprite,Vector3 position) {
		
		speedVector.set(currentTarget);
		speedVector.sub(position);
		speedVector.nor().mul(Gdx.graphics.getDeltaTime()*SPEED/5f);
		position.add(speedVector);
		if(position.dst(currentTarget)<TRIGGER_RADIUS){
			targetNumber++;
			if(path.size()>targetNumber){
				currentTarget.set(path.get(targetNumber));
			}else{
				speedVector.nor().mul(position.dst(currentTarget)-blastTriggerRange);
				position.add(speedVector);
				for(Vector3 hit : hits){
					float hitPercentage = hit.z/(((blastSimulationRadius*100)/5)*180);
					hit.set(hit.x,hit.y,hitPercentage);
					new HitMarkerObject(new HitMarkerController(hit.x,
						hit.y,hit.z,hit.z), new HitMarkerRenderer(),parent,true);
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
		for(Vector3 v : path)
			v.div(4f);
		for(Vector3 v : hits)
			v.div(4f);
		
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
