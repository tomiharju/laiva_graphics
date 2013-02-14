package ObjectRenderers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ObjectModels.ModelObject;

public abstract class ObjectRenderer {
	
	protected ModelObject object;
	
	

	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}



	public abstract void draw();
		
	

}
