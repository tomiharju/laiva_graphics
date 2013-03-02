package ObjectControllers;

import java.util.ArrayList;

import ObjectModels.BotGuiObject;
import ObjectModels.TopGuiObject;
import ObjectModels.WeaponObject;
import ObjectModels.WeaponObject.WeaponType;
import ObjectRenderers.BotGuiRenderer;
import ObjectRenderers.TopGuiRenderer;
import ObjectRenderers.WeaponRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ShootingMapView extends ObjectController {
	public static ArrayList<WeaponController> controllers;
	private ShipPlacementView _placementController;
	
	private WeaponObject crosshair;
	private WeaponController activeController;
	private WeaponController selected_weapon;
	//Gui objects
	private TopGuiObject weaponSelector;
	private BotGuiObject fireControl;
	
	public ShootingMapView(){
		controllers = new ArrayList<WeaponController>();
		activeController=null;
		
		position = new Vector2(0,(float) (Gdx.graphics.getHeight()*0.2));
		hidePosition = new Vector2(Gdx.graphics.getWidth(),position.y);
		visiblePosition = new Vector2(position);
	
		
		
		weaponSelector = new TopGuiObject(new TopGuiController(position),new TopGuiRenderer());
		fireControl = new BotGuiObject(new BotGuiController(position),new BotGuiRenderer());
		
	}
	
	public void createWeapons(){
		new WeaponObject(WeaponType.CANNON,new WeaponController(50,100),new WeaponRenderer());
		crosshair = new WeaponObject(WeaponType.CROSSHAIR,new WeaponController(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2),new WeaponRenderer());
	}
	
	public void linkSeaController(ObjectController cont){
		_placementController=(ShipPlacementView) cont;
	}
	@Override
	public void handleInputDown(float x, float y) {
		for(WeaponController wc:controllers){
			if(wc.getObject().getBounds().contains(x,y))
				wc.select();
		}
		if(crosshair.getBounds().contains(x,y))
			activeController = (WeaponController) crosshair.getController();
		else
			activeController=null;
		
	}

	@Override
	public void handleInputUp(float x, float y) {
	
		
	}
	
	@Override
	public void handleInputDrag(float x, float y) {
		if(activeController!=null)
			activeController.handleInputDrag(x, y);
		
	}
	public void hide(){
		position.set(hidePosition);
		for(WeaponController sc:controllers)
			sc.hide();
	}
	public void show(){
		_placementController.hide();
		position.set(visiblePosition);
		for(WeaponController sc:controllers)
			sc.show();
		
	
	}

	

}
