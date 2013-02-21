package ObjectControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ShipController extends ObjectController {
	
	
	public ShipController(float x, float y){
		position = new Vector2(x,y);
		rotation = 0;
		WorldController.controllers.add(this);
	}

	@Override
	public void handleInput(float x,float y) {
		setPosition(new Vector2(x,y));
		
	}
	
	

}
