package com.sohvastudios.battleships.game.objectModels;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;
import com.sohvastudios.battleships.game.objectRenderers.RadarRenderer;
import com.sohvastudios.battleships.game.objectRenderers.SeaRenderer;
import com.sohvastudios.battleships.game.objectRenderers.WorldRenderer;

public abstract class ModelObject {

	protected ObjectController controller;
	protected ObjectRenderer renderer;
	public Sprite sprite;
	public Sprite baseSprite;
	protected Rectangle bounds;
	protected Vector3 position;
	protected boolean visible;

	public abstract void update();

	public Sprite getSprite() {
		return sprite;
	}
	

	public void dispose() {
		SeaRenderer.objectsAtSea.remove(this.renderer);
		RadarRenderer.objectsAtRadar.remove(this.renderer);
		WorldRenderer.renderers.remove(this.renderer);
		WorldObject.objects.remove(this);
	}
	
	

	public boolean isVisible() {
		return visible;
	}

	public void setVisible() {
		visible = true;
	}

	public void setHidden() {
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


	public Vector3 getPosition() {
		return position;
	}

}
