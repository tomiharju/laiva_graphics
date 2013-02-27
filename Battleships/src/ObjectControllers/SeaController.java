package ObjectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class SeaController extends ObjectController{

	

	private MapController _mapController;
	public static ArrayList<ShipController> shipControllers;
	private ShipController activeController;
	
	public SeaController(){
		shipControllers= new ArrayList<ShipController>();
		isHidden=false;
		position = new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		hidePosition = new Vector2(3*Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		visiblePosition = new Vector2(position);
	}
	
	public void linkMapController(ObjectController cont){
		_mapController=(MapController) cont;
		_mapController.linkSeaController(this);
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
	public void handleInputDown(float x, float y) {
		for(ShipController c:shipControllers)
			if(c.getObject().getBounds().contains(x, y)){
				if(c.isSelected() && c instanceof ShipController)
					c.changeOrientation();
				
				activeController = c;
				c.select();
			}

	}
	@Override
	public void handleInputUp(float x, float y) {
		for(ShipController c : shipControllers){
			if(!c.equals(activeController))
				c.deSelect();
		}
	}
	@Override
	public void handleInputDrag(float x, float y) {
		if(activeController!=null)
			activeController.handleInputDrag(x, y);
		
		
	}
		
	
	
	
}
