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
		
		//Calculate standard deviation
		float tempSum=0;
		for(Vector3 v :hits){
			tempSum+=((v.x-avgX)*(v.x-avgX));
		}
		float std = (float) Math.sqrt(tempSum/hits.size());
		weightedHit.set(avgX,avgY,std);
		
		return weightedHit;
	}

}
