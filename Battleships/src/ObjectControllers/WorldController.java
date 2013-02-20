package ObjectControllers;

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
	


		public static ObjectController activeController;
		
		
		public void touchDown(float x,float y){
			
		
			if(y>Gdx.graphics.getHeight()*0.8){
				TopGuiController.touchDown(x, y);
				
			}
			else if(y>Gdx.graphics.getHeight()*0.2 && y<Gdx.graphics.getHeight()*0.8){
				if(activeController!=null)
					activeController.setPosition(new Vector2(x,y));
				SeaController.touchDown(x, y);
			}
			else
				BotGuiController.touchDown(x, y);
		
		}
		
		
		
		
		
		
		
		

		
	
	
}
