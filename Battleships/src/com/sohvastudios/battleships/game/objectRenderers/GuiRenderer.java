package com.sohvastudios.battleships.game.objectRenderers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiRenderer extends ObjectRenderer{

	
	public GuiRenderer(){
		WorldRenderer.renderers.add(this);
		
	}
	@Override
	public void draw(SpriteBatch batch) {
		batch.setProjectionMatrix(WorldRenderer.cam.combined);
		if(object.isVisible())
			graphics.draw(batch);
		else
			return;
		
	}
	@Override
	public void addGraphics(Sprite s) {
		graphics=s;
		
	}
	

}
