package ObjectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

import ObjectModels.ModelObject;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.WorldRenderer;

public class WorldController extends ObjectController{
	


		
		public static ArrayList<ObjectController> controllers;
		public static ArrayList<ShipController> shipControllers;
		
		private ObjectController activeController;
		
		public WorldController(){
			controllers = new ArrayList<ObjectController>();
			shipControllers= new ArrayList<ShipController>();
			
		}
		
		public void touchDown(float x,float y){
			
			
			for(ObjectController c: controllers){
				c.deSelect();
				if(c.getObject().getBounds().contains(x, y)){ 
					activeController=c;
					activeController.handleInputDown(x, y);
					activeController.select();
					
				}
				
				
			}
			
		
		}
		public void touchUp(float x,float y){
			if(activeController!=null)
				activeController.handleInputUp(x, y);
			
				
		}

		

		@Override
		public void handleInputDown(float x, float y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void handleInputUp(float x, float y) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		
		
		
		
		

		
	
	
}
