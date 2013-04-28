package com.sohvastudios.battleships.game.utilities;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;

public class HitSpotCalculator {
	
	public HitSpotCalculator(){
		
	}
	
	public Vector3 getWeightedHit(ArrayList<Vector3> hits){
		float avgX=0;
		float sumX=0;
		float avgY=0;
		float sumY=0;
		Vector3 weightedHit = new Vector3();
		//Calculate center point 
		for(Vector3 v : hits){
			sumX+=v.x;
			sumY+=v.y;
		}
		avgX=sumX/hits.size();
		avgY=sumY/hits.size();
		System.out.println("Average x "+avgX +" Average y "+avgY);
		System.out.println("Total hits at spot  calculator"+hits.size());
		weightedHit.set(avgX,avgY,hits.size());
		
		return weightedHit;
	}

}
