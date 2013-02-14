package ObjectControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

import ObjectModels.ModelObject;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.WorldRenderer;

public class WorldController extends ObjectController{
	


	//Input handler variables
		final Plane xzPlane = new Plane(new Vector3(0,1,0),0);
		final Vector3 xzintersection = new Vector3();
		final Plane xyPlane = new Plane(new Vector3(0,0,-1),0);
		final Vector3 xyintersection = new Vector3();
		
		
		
		
		
		
		

		
	
	
}
