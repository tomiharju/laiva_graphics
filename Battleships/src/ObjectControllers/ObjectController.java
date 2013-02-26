package ObjectControllers;

import com.badlogic.gdx.math.Vector2;

import ObjectModels.ModelObject;
import ObjectRenderers.ObjectRenderer;

public abstract class ObjectController {

	
	protected ModelObject object;
	protected Vector2 position;
	protected Vector2 hidePosition;
	protected Vector2 visiblePosition;
	protected boolean orientation_changed;
	protected boolean selected;
	protected boolean isHidden;
	
	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}
	public Vector2 pollPosition(){
		return position;
	}
	public boolean pollOrientation(){
		return orientation_changed;
	}
	public void setPosition(Vector2 p){
		position=p;
	}
	public boolean pollSelection(){
		return selected;
	}
	public void select(){
		selected=true;
	}
	public void deSelect(){
		selected=false;
	}
	public boolean isHidden(){
		return isHidden;
	}
	public void hide(){
		isHidden=true;
	}
	public void show(){
		isHidden=false;
	}
	
	public abstract void handleInputDown(float x, float y);
	public abstract void handleInputUp(float x, float y);
	public abstract void handleInputDrag(float x,float y);
	public void changeOrientation(){
		System.out.println("Changing orientation");
		orientation_changed=true;
	}
	public void orientationConfirmed(){
		orientation_changed=false;
	}
	
	
	

	
	
}
