package ObjectControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ShipController extends ObjectController {
	
	
	public ShipController(float x, float y){
		WorldController.controllers.add(this);
		WorldController.shipControllers.add(this);
		
		
		position = new Vector2(x,y);
		rotation = 0;
	}

	@Override
	public void handleInputUp(float x,float y) {
		boolean legalmove=true;
		for(ShipController sc:WorldController.shipControllers){
			if(!sc.equals(this)){
				if(sc.getObject().getBounds().contains(x, y))
					legalmove=false;
			}
				
		}
		
		if(legalmove){
			setPosition(new Vector2(x,y));
		}
		
	}

	@Override
	public void handleInputDown(float x, float y) {
		// TODO Auto-generated method stub
		
	}
	
	

}
