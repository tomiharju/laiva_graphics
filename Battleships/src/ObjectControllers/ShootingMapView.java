package ObjectControllers;

import java.util.ArrayList;


import Commands.FireCommand;
import Commands.HideCommand;
import ObjectModels.GuiObject;
import ObjectModels.ShipObject;

import ObjectModels.ModelObject;
import ObjectModels.WeaponObject;
import ObjectModels.ShipObject.ShipType;
import ObjectModels.WeaponObject.WeaponType;

import ObjectRenderers.GuiRenderer;
import ObjectRenderers.ShipRenderer;

import ObjectRenderers.WeaponRenderer;
import Screens.GameLogicHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ShootingMapView extends ObjectController {
	public static ArrayList<WeaponController> weaponControllers;
	public	ArrayList<GuiController> guiControllers;
	private ShipPlacementView _placementController;
	

	private WeaponController activeController;
	private WeaponController selected_weapon;
	
	//Gui objects
	private GuiObject button_fire;
	private GuiObject arrow_left;
	
	
	public ShootingMapView(float x, float y, float w, float h){
		super(x,y,w,h);
		weaponControllers 	= new ArrayList<WeaponController>();
		guiControllers 		= new ArrayList<GuiController>();
		activeController	= null;
	
		
		button_fire 		= new GuiObject(new GuiController(5,1.5f,1.5f,1.5f,new FireCommand(this)),new GuiRenderer(),"button_fire.png");
		arrow_left 			= new GuiObject(new GuiController(2,1.5f,1.5f,1.5f,new HideCommand(this)),new GuiRenderer(),"arrow_left.png");
		guiControllers.add((GuiController) button_fire.getController());
		guiControllers.add((GuiController) arrow_left.getController());
	}
	
	public void fire(){
		if(selected_weapon!=null)
			GameLogicHandler.sendAttackCoordinates( activeController.position,((WeaponObject) selected_weapon.getObject()).getWeapon());
	}
	
	
	public void createWeapons(){
		float[][] p={{2,13},{4,13},{4,7}};
		for(WeaponType weapon : WeaponType.values()){
			new WeaponObject(weapon,new WeaponController(p[weapon.ordinal()][0],p[weapon.ordinal()][1],1,1),new WeaponRenderer());
		}
		
		//crosshair 			= new WeaponObject(WeaponType.CROSSHAIR,new WeaponController(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2),new WeaponRenderer());
	}
	public void linkSeaController(ObjectController cont){
		_placementController=(ShipPlacementView) cont;
	}
	@Override
	public void handleInputDown(Vector3 pos) {
	
			for(WeaponController wc:weaponControllers){
				if(wc.getObject().getBounds().contains(pos.x,pos.y)){
					if(((WeaponObject) wc.getObject()).getWeapon()==2)//Check if its crosshair type.
						activeController = wc;
					
					selected_weapon=wc;
					break;
				}
				
		}
			
	
			for (GuiController c : guiControllers)
				if (c.getObject().getBounds().contains(pos.x, pos.y)) {
					c.executeCommand();
					break;
					}
			
		
		
	}
	@Override
	public void handleInputUp(Vector3 pos) {
	
		
	}
	@Override
	public void handleInputDrag(Vector3 pos) {
		if(activeController!=null)
			activeController.handleInputDrag(pos);
		
	}
	public void hide(){
		
		position.set(hidePosition);
		for(WeaponController sc:weaponControllers)
			sc.hide();
		for(GuiController gc:guiControllers)
			gc.hide();
	}
	public void show(){
		
		position.set(visiblePosition);
		for(WeaponController sc:weaponControllers)
			sc.show();
		for(GuiController gc:guiControllers)
			gc.show();
		
		
	
	}

	

}
