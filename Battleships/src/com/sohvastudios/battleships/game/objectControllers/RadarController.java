package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.commands.DisposeCommand;
import com.sohvastudios.battleships.game.commands.FireCommand;
import com.sohvastudios.battleships.game.commands.WeaponSelectCommand;
import com.sohvastudios.battleships.game.gamelogic.PlayScreen;
import com.sohvastudios.battleships.game.objectModels.UserInterfaceObject;
import com.sohvastudios.battleships.game.objectRenderers.UserInterfaceRenderer;

public class RadarController extends ObjectController {

	
	public static ArrayList<HitMarkerController> markerControllers;

	// Gui objects
	private UserInterfaceObject crosshair;
	private UserInterfaceObject weaponDescription;
	public boolean radarLocked;
	public Vector3 targetPosition;
	public int selectedWeapon;
	
	public RadarController(float x, float y, float w, float h) {
		super(x, y, w, h);
		radarLocked=true;
		targetPosition = new Vector3(0,14,0);
		selectedWeapon=0;
	}
	public void initialize(){
		markerControllers = new ArrayList<HitMarkerController>();
		crosshair = new UserInterfaceObject(new UserInterfaceController(0f,0f,.5f,.5f,null),new UserInterfaceRenderer(),"crosshair.png");
		crosshair.addToRadar(this);
		new UserInterfaceObject(new UserInterfaceController(0,4.5f,1f,1f, new FireCommand(this)), new UserInterfaceRenderer(),"button_ready.png").addToRadar(this);
		new UserInterfaceObject(new UserInterfaceController(-3.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,0)), new UserInterfaceRenderer(),"button_ready.png").addToRadar(this);
		new UserInterfaceObject(new UserInterfaceController(-2.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,1)), new UserInterfaceRenderer(),"button_ready.png").addToRadar(this);
		new UserInterfaceObject(new UserInterfaceController(-1.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,2)), new UserInterfaceRenderer(),"button_ready.png").addToRadar(this);
		new UserInterfaceObject(new UserInterfaceController(-0.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,3)), new UserInterfaceRenderer(),"button_ready.png").addToRadar(this);
		new UserInterfaceObject(new UserInterfaceController(0.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,4)), new UserInterfaceRenderer(),"button_ready.png").addToRadar(this);
		new UserInterfaceObject(new UserInterfaceController(1.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,3)), new UserInterfaceRenderer(),"button_ready.png").addToRadar(this);
		
		object.setVisible();
	
		for(UserInterfaceController uc : guiControllers){
			uc.show();
		}
		for (HitMarkerController gc : markerControllers)
			gc.show();
	
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
				PlayScreen.logicHandler.sendShoot(target.x,target.y,selectedWeapon);
				
			//}
			
		//}
	}
	public void selectWeapon(int weapon){
		selectedWeapon=weapon;
		showWeaponIcon();
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
		crosshair.getController().handleInputDrag(pos);
		
		
	}
	public void handleInputDown(Vector3 pos){
		for (int i = 0; i < guiControllers.size() ; i++)
			guiControllers.get(i).handleInputDown(pos);
		
		cleanTrash();
	}
	public void handleInputUp(Vector3 pos){
		
	}
	
	
	public void hide() {
		lockRadar();
		targetPosition.set(0,14,0);
	}

	public void show() {
		unlockRadar();
		targetPosition.set(0,0,0);
	}
	@Override
	public void removeObject(ObjectController obj) {
		object.dispose();
		
	}
	public void showWeaponIcon(){
		if(weaponDescription!=null)
			((UserInterfaceController) weaponDescription.getController()).executeCommand();
		weaponDescription = new UserInterfaceObject(new UserInterfaceController(2.5f,-6.5f,2.5f,2.5f,new DisposeCommand(this)),new UserInterfaceRenderer(),"wicon"+selectedWeapon+".png");
		weaponDescription.addToRadar(this);
	}
	
	@Override
	public void cleanTrash() {
		for(ObjectController uc : removeList)
			uc.getObject().dispose();
		guiControllers.removeAll(removeList);
		removeList.clear();
		
	}
}
