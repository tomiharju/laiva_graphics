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
		
		private boolean objectClicked;
		private ObjectController activeController;
		
		public WorldController(){
			controllers = new ArrayList<ObjectController>();
			objectClicked=false;
		}
		
		public void touchDown(float x,float y){
			
			Vector2 touchPoint = new Vector2(x,y);
			for(ObjectController c: controllers){
				if(touchPoint.dst(c.pollPosition())<15){
					activeController=c;
					c.select();
					objectClicked=true;
					break;
				}
				objectClicked=false;
				
			}
			if(objectClicked)
			activeController.handleInput(x, y);
		
		}
		public void touchDragged(float x,float y){
			if(objectClicked){
				activeController.handleInput(x, y);
			}
		}

		@Override
		public void handleInput(float x, float y) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		
		
		
		
		

		
	
	
}
