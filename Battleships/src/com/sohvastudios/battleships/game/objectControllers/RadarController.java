package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.gamelogic.PlayScreen;
import com.sohvastudios.battleships.game.objectModels.UserInterfaceObject;
import com.sohvastudios.battleships.game.objectRenderers.UserInterfaceRenderer;

public class RadarController extends ObjectController {

	public static ArrayList<HitMarkerController> markerControllers;

	// Gui objects
	private UserInterfaceObject crosshair;
	
	private boolean radarLocked;
	
	public RadarController(float x, float y, float w, float h) {
		super(x, y, w, h);
		radarLocked=true;

	}
	public void initialize(){
		markerControllers = new ArrayList<HitMarkerController>();
		crosshair = new UserInterfaceObject(new UserInterfaceController(0f,0f,.5f,.5f,null),new UserInterfaceRenderer(),"crosshair.png");
		crosshair.addToRadar(this);
	
	}

	public void fire() {
		//for(ShipController sc : SeaController.shipControllers){
		//	if(sc.isSelected()){
				System.out.println("Fire in the hole!");
				Vector3 target = new Vector3();
				target.set(crosshair.getController().pollPosition());
				target.set(target.x,target.y,0);
				target.mul(1.25f);
				//BUGFIXGING int weapon = ((ShipObject)sc.getObject()).shipWeapon.ordinal();
				int weapon = 1;
				System.out.println("Shooting at "+target.toString()+ " with "+weapon);
				PlayScreen.logicHandler.sendShoot(target.x,target.y,weapon);
				
			//}
			
		//}
	}

	public void unlockRadar(){
		radarLocked=false;
	}
	public void lockRadar(){
		radarLocked=true;
	}
	public boolean isLocked(){
		return radarLocked;
	}
	@Override
	public void handleInputDrag(Vector3 pos) {
		for(UserInterfaceController uc : guiControllers){
			uc.handleInputDrag(pos);
		}
		
	}
	public void handleInputDown(Vector3 pos){
		for(UserInterfaceController uc : guiControllers){
			uc.handleInputDown(pos);
		}
	}
	public void handleDoubleTap(Vector3 pos){
		
	}
	public void handleLongPress(Vector3 pos){
		if(!radarLocked)
			fire();
	}

	public void hide() {
		object.setHidden();
		crosshair.setHidden();
		for (HitMarkerController gc : markerControllers)
			gc.hide();
	}

	public void show() {
		object.setVisible();
		crosshair.setVisible();
		for (HitMarkerController gc : markerControllers)
			gc.show();

	}
}
