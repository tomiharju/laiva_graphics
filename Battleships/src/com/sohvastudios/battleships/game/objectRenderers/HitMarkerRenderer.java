package com.sohvastudios.battleships.game.objectRenderers;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sohvastudios.battleships.game.objectModels.HitMarkerObject;

public class HitMarkerRenderer extends ObjectRenderer{

	
	public HitMarkerRenderer(){
		RadarRenderer.objectsAtRadar.add(this);
	}
	@Override
	public void draw(SpriteBatch batch) {
		if(object.isVisible())
			graphics.draw(batch,((HitMarkerObject)object).alpha);
		
	}

	@Override
	public void addGraphics(Sprite s) {
		graphics=s;
		
	}

}
