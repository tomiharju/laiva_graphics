package ObjectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class SeaController extends ObjectController{

	
	private Vector2 firstTouchPosition;
	private MapController _mapController;
	public static ArrayList<ShipController> shipControllers;
	
	public SeaController(){
		WorldController.controllers.add(this);
		
		shipControllers= new ArrayList<ShipController>();
		isHidden=false;
		firstTouchPosition= new Vector2();
		position = new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		hidePosition = new Vector2(3*Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		visiblePosition = new Vector2(position);
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
		position.set(hidePosition);
		for(ShipController sc:shipControllers)
			sc.hide();
	}
	public void show(){
		position.set(visiblePosition);
		for(ShipController sc:shipControllers)
			sc.show();
	}

	@Override
	public void handleInputDrag(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
