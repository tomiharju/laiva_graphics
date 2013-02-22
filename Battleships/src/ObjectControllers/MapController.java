package ObjectControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class MapController extends ObjectController {

	private Vector2 firstTouchPosition;
	private SeaController _seaController;
	
	public MapController(){
		WorldController.controllers.add(this);
		
		isHidden=true;
		firstTouchPosition= new Vector2();
		position=new Vector2(3*Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
	}
	
	public void linkSeaController(ObjectController cont){
		_seaController=(SeaController) cont;
	}
	@Override
	public void handleInputDown(float x, float y) {
		firstTouchPosition.set(x, y);
		
	}

	@Override
	public void handleInputUp(float x, float y) {
		if((firstTouchPosition.dst(x, y))>object.getBounds().getWidth()/2){
			hide();
			_seaController.show();
			
		}
		
	}
	public void hide(){
		position.set(3*Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
	}
	public void show(){
		position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
	}

}
