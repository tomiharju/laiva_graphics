package com.sohvastudios.battleships.game.objectModels;

import com.badlogic.gdx.graphics.g2d.Sprite;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectRenderers.HitMarkerRenderer;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;
import com.sohvastudios.battleships.game.objectRenderers.WorldRenderer;
public abstract class ModelObject {
	
	protected ObjectController controller;
	protected ObjectRenderer renderer;
	protected Sprite sprite;
	protected Rectangle bounds;
	protected Vector3 position;
	protected boolean visible;
	

	public abstract void update();
	
	
	
	public Sprite getSprite(){
		return sprite;
	}
	public void dispose(){
		WorldRenderer.renderers.remove(this.getRenderer());
		WorldObject.objects.remove(this);
	}
	
	public boolean isVisible(){
		return visible;
	}
	public void setVisible(){
		visible = true;
	}
	public void setHidden(){
		visible = false;
	}
	public ObjectController getController() {
		return controller;
	}

	public abstract void setController(ObjectController controller);

	public ObjectRenderer getRenderer() {
		return renderer;
	}
	

	public abstract void setRenderer(ObjectRenderer renderer);

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public Vector3 getPosition(){
		return position;
	}

	
	
	
	
	
	
}
