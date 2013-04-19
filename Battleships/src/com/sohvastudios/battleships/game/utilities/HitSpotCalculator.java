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
		//Calculate standard deviation
		float tempSumX=0;
		float tempSumY=0;
		for(Vector3 v :hits){
			tempSumX+=((v.x-avgX)*(v.x-avgX));
			tempSumY+=((v.y-avgX)*(v.y-avgX));
		}
		float stdx = (float) Math.sqrt(tempSumX/hits.size());
		float stdy = (float) Math.sqrt(tempSumY)/hits.size();
		float avgStd = (stdx+stdy)/2;
		System.out.println("Std "+avgStd);
		weightedHit.set(avgX,avgY,avgStd);
		
		return weightedHit;
	}

}
