package com.sohvastudios.battleships.game.objectRenderers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WeaponRenderer extends ObjectRenderer {

	public WeaponRenderer() {
		WorldRenderer.renderers.add(this);
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (object.isVisible()){
			graphics.draw(batch);
			
		}
		else
			return;
	}

	@Override
	public void addGraphics(Sprite s) {
		graphics = s;
	}

}
