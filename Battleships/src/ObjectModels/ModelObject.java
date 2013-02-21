package ObjectModels;

import com.badlogic.gdx.graphics.g2d.Sprite;

import ObjectControllers.ObjectController;
import ObjectRenderers.ObjectRenderer;
import com.badlogic.gdx.math.Rectangle;
public abstract class ModelObject {
	
	protected ObjectController controller;
	protected ObjectRenderer renderer;
	protected Sprite sprite;
	protected Rectangle bounds;
	
	
	
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
	
	
	
	
	
	
}
