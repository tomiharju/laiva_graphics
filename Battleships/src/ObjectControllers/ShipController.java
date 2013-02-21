package ObjectControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ShipController extends ObjectController {
	
	
	public ShipController(float x, float y){
		position = new Vector2(x,y);
		
		rotation = 0;
		
		WorldController.controllers.add(this);
		WorldController.shipControllers.add(this);
	}

	@Override
	public void handleInput(float x,float y) {
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
	
	

}
