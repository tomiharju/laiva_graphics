package com.sohvastudios.battleships.game.objectRenderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SeaRenderer extends ObjectRenderer {

	Animation seaAnimation;
	Texture seaSheet;
	TextureRegion[] seaFrames;
	TextureRegion currentFrame;

	public SeaRenderer() {
		WorldRenderer.renderers.add(this);
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (object.isVisible()) {
			graphics.draw(batch);
		} else
			return;
	}

	@Override
	public void addGraphics(Sprite s) {
		graphics = s;

	}

}
