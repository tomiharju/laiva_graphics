package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ObjectControllers.ObjectController;
import ObjectRenderers.ObjectRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
public abstract class ModelObject {
	
	protected ObjectController controller;
	protected ObjectRenderer renderer;
	protected Sprite sprite;
	protected Rectangle bounds;
	protected Vector2 position;
	
	
	public abstract void update();
	
	
	
	public Sprite getSprite(){
		return sprite;
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
	public Rectangle getBounds(){
		return bounds;
	}
	public Vector2 getPosition(){
		return position;
	}

	
	
	
	
	
	
}
