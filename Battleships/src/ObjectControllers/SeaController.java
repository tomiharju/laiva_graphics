package ObjectControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class SeaController extends ObjectController{

	
	private Vector2 firstTouchPosition;
	private MapController _mapController;
	
	public SeaController(){
		WorldController.controllers.add(this);
		
		isHidden=false;
		firstTouchPosition= new Vector2();
		position = new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
	}
	
	public void linkMapController(ObjectController cont){
		_mapController=(MapController) cont;
		_mapController.linkSeaController(this);
	}
	@Override
	public void handleInputDown(float x, float y) {
			firstTouchPosition.set(x, y);
		
		
	
		
	
	}
	@Override
	public void handleInputUp(float x, float y) {
		if((firstTouchPosition.dst(x, y))>object.getBounds().getWidth()/2){
			hide();
			_mapController.show();
			
		}
			
		
		
	}
	
	public void hide(){
		position.set(3*Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
	}
	public void show(){
		position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
	}

	
	
	
}