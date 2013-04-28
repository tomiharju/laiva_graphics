package com.sohvastudios.battleships.game.objectRenderers;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sohvastudios.battleships.game.objectModels.HitMarkerObject;

public class HitMarkerRenderer extends ObjectRenderer{

	
	public HitMarkerRenderer(){
		
	}
	@Override
	public void draw(SpriteBatch batch) {
		if(object.isVisible())
			graphics.draw(batch,((HitMarkerObject)object).alpha);
		
	}

	@Override
	public void addGraphics(Sprite s) {
		graphics=s;
		if(((HitMarkerObject)object).isHitMarker)
			RadarRenderer.objectsAtRadar.add(RadarRenderer.objectsAtRadar.size()-2,this);
		else{
			RadarRenderer.objectsAtRadar.add(1,this);
		}
		
	}

}
