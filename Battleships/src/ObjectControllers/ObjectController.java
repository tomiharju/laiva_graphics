package ObjectControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ObjectModels.ModelObject;
import ObjectRenderers.ObjectRenderer;

public abstract class ObjectController {

	
	protected ModelObject object;
	protected Vector3 position;
	protected Rectangle bounds;
	protected boolean orientation_changed;
	protected boolean selected;
	protected boolean isHidden;
	
	public ObjectController(float x, float y,float width, float height){
		position = new Vector3(x,y,0);
		bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
	}
	
	
	
	
	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}
	public Vector3 pollPosition() {
		return position;
	}
	public Rectangle pollBounds() {
		return bounds;
	}
	public boolean pollOrientation() {
		return orientation_changed;
	}
	public void setPosition(Vector3 vector3) {
		position=vector3;
	}
	public boolean pollSelection() {
		return selected;
	}
	public void select(){
		System.out.println("Selected "+object.toString());
		selected=true;
	}
	public void deSelect(){
		selected=false;
	}
	public boolean isSelected(){
		return selected;
	}
	
	
	public void hide(){};
	public void show(){};
	public void handleInputDown(Vector3 touchPoint){};
	public void handleInputUp(Vector3 pos){};
	public void handleInputDrag(Vector3 pos){};
	public void changeOrientation(){
		orientation_changed=true;
	}
	public void orientationConfirmed(){
		orientation_changed=false;
	}
	
	
	

	
	
}
