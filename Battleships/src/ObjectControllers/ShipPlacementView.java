package ObjectControllers;

import java.util.ArrayList;

import ObjectModels.BotGuiObject;
import ObjectModels.ShipObject;
import ObjectModels.TopGuiObject;
import ObjectModels.ShipObject.ShipType;
import ObjectRenderers.BotGuiRenderer;
import ObjectRenderers.ShipRenderer;
import ObjectRenderers.TopGuiRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ShipPlacementView extends ObjectController{

	

	private ShootingMapView _shootingController;
	public static ArrayList<ShipController> controllers;
	private ShipController activeController;
	
	//Gui objects
	private TopGuiObject topGui;
	private BotGuiObject botGui;
	
	public ShipPlacementView(){
		controllers= new ArrayList<ShipController>();
		activeController=null;
		isHidden=false;
		position = new Vector2(0,(float) (Gdx.graphics.getHeight()*0.2));
		hidePosition = new Vector2(Gdx.graphics.getWidth(),position.y);
		visiblePosition = new Vector2(position);
		
		topGui = new TopGuiObject(new TopGuiController(position),new TopGuiRenderer());
		botGui = new BotGuiObject(new BotGuiController(position),new BotGuiRenderer());
		
	}
	
	
	public void createShips(){
		new ShipObject(ShipType.ROWBOAT,new ShipController(100,(float) (Gdx.graphics.getHeight()*0.7)),new ShipRenderer());
		new ShipObject(ShipType.MOTORBOAT,new ShipController(250,(float) (Gdx.graphics.getHeight()*0.7)),new ShipRenderer());
	//	new ShipObject(ShipType.BATTLESHIP,new ShipController(),new ShipRenderer());
	}
	
	public void linkMapController(ObjectController cont){
		_shootingController=(ShootingMapView) cont;
		_shootingController.linkSeaController(this);
	}
	
	public void hide(){
		position.set(hidePosition);
		for(ShipController sc:controllers)
			sc.hide();
	}
	public void show(){
		_shootingController.hide();
		position.set(visiblePosition);
		for(ShipController sc:controllers)
			sc.show();
	}
	
	
	
	@Override
	public void handleInputDown(float x, float y) {
		for(ShipController c:controllers)
			if(c.getObject().getBounds().contains(x, y)){
				if(c.isSelected())
					c.changeOrientation();
				
				activeController = c;
				c.select();
			}

	}
	@Override
	public void handleInputUp(float x, float y) {
		for(ShipController c : controllers){
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
