package ObjectControllers;

import ObjectModels.ModelObject;
import ObjectRenderers.ObjectRenderer;

public abstract class ObjectController {


	protected ModelObject object;

	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}
	
	
	
	

	
	
}
