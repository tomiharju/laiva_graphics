package ObjectModels;

import com.badlogic.gdx.graphics.g2d.Sprite;

import ObjectControllers.ObjectController;
import ObjectRenderers.ObjectRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
	public void rotate90(){
		float temp;
		temp = bounds.width;
		bounds.width=bounds.height;
		bounds.height=temp;
		sprite.rotate90(true);
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		
	}

	public abstract void setRenderer(ObjectRenderer renderer);

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	public Rectangle getBounds(){
		return bounds;
	}
	public Vector3 getPosition(){
		return position;
	}

	
	
	
	
	
	
}
