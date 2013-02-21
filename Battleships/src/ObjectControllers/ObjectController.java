package ObjectControllers;

import com.badlogic.gdx.math.Vector2;

import ObjectModels.ModelObject;
import ObjectRenderers.ObjectRenderer;

public abstract class ObjectController {


	protected ModelObject object;
	protected Vector2 position;
	protected float rotation;
	protected boolean selected;
	
	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}
	public Vector2 pollPosition(){
		return position;
	}
	public float pollRotation(){
		return rotation;
	}
	public void setPosition(Vector2 p){
		position=p;
	}
	public boolean pollSelection(){
		return selected;
	}
	public void select(){
		selected=!selected;
	}
	public abstract void handleInput(float x, float y);
		
	
	
	
	
	

	
	
}
