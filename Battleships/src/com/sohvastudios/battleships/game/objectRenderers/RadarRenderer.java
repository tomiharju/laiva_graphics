package com.sohvastudios.battleships.game.objectRenderers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RadarRenderer extends ObjectRenderer {

	public static ArrayList<ObjectRenderer> objectsAtRadar;
	private Sprite sweepGraphics;
	
	public RadarRenderer() {
		objectsAtRadar = new ArrayList<ObjectRenderer>();
		objectsAtRadar.add(this);
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		if(object.isVisible()){
		graphics.draw(batch);
		sweepGraphics.draw(batch);
		}
	
	}

	@Override
	public void addGraphics(Sprite s) {
		graphics = s;
	}
	public void addSweepGraphics(Sprite s){
		sweepGraphics=s;
	}
}
