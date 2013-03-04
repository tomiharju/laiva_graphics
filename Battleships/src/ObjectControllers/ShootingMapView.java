package ObjectControllers;

import java.util.ArrayList;


import Commands.FireCommand;
import Commands.HideCommand;
import ObjectModels.GuiObject;

import ObjectModels.WeaponObject;
import ObjectModels.WeaponObject.WeaponType;

import ObjectRenderers.GuiRenderer;

import ObjectRenderers.WeaponRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ShootingMapView extends ObjectController {
	public static ArrayList<WeaponController> weaponControllers;
	public	ArrayList<GuiController> guiControllers;
	private ShipPlacementView _placementController;
	
	private WeaponObject crosshair;
	private WeaponController activeController;
	private WeaponController selected_weapon;
	
	//Gui objects
	private GuiObject button_fire;
	private GuiObject arrow_left;
	
	
	public ShootingMapView(){
		weaponControllers = new ArrayList<WeaponController>();
		guiControllers = new ArrayList<GuiController>();
		activeController=null;
		
		position = new Vector2(0,(float) (Gdx.graphics.getHeight()*0.2));
		hidePosition = new Vector2(Gdx.graphics.getWidth(),position.y);
		visiblePosition = new Vector2(position);
		button_fire = new GuiObject(new GuiController(Gdx.graphics.getWidth()/2,50,new FireCommand(this)),new GuiRenderer(),"button_fire.png",50,50);
		arrow_left = new GuiObject(new GuiController(Gdx.graphics.getWidth()/2+100,50,new HideCommand(this)),new GuiRenderer(),"arrow_left.png",50,50);
		guiControllers.add((GuiController) button_fire.getController());
		guiControllers.add((GuiController) arrow_left.getController());
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
		if(object.getBounds().contains(x,y)){
		for(WeaponController wc:weaponControllers){
			if(wc.getObject().getBounds().contains(x,y))
				selected_weapon=wc;
		}
		if(crosshair.getBounds().contains(x,y))
			activeController = (WeaponController) crosshair.getController();
		else
			activeController=null;
		}
		else{
			for (GuiController c : guiControllers)
				if (c.getObject().getBounds().contains(x, y)) {
					c.executeCommand();
					}
			
		}
		
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
		for(WeaponController sc:weaponControllers)
			sc.hide();
		for(GuiController gc:guiControllers)
			gc.hide();
	}
	public void show(){
		_placementController.hide();
		position.set(visiblePosition);
		for(WeaponController sc:weaponControllers)
			sc.show();
		for(GuiController gc:guiControllers)
			gc.show();
		
		
	
	}

	

}
