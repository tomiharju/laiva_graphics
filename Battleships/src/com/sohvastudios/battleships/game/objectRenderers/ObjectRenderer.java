package com.sohvastudios.battleships.game.objectRenderers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sohvastudios.battleships.game.objectModels.ModelObject;


public abstract class ObjectRenderer {
	
	protected ModelObject object;
	protected Sprite graphics;
	

	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}



	public abstract void draw(SpriteBatch batch);
	public abstract void addGraphics(Sprite s);
	

}
