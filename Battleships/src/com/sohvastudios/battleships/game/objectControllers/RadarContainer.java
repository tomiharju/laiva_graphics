package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.commands.DisposeCommand;
import com.sohvastudios.battleships.game.commands.FireCommand;
import com.sohvastudios.battleships.game.commands.WeaponSelectCommand;
import com.sohvastudios.battleships.game.gamelogic.PlayScreen;
import com.sohvastudios.battleships.game.objectModels.ProjectileObject;
import com.sohvastudios.battleships.game.objectModels.UserInterfaceObject;
import com.sohvastudios.battleships.game.objectRenderers.ProjectileRenderer;
import com.sohvastudios.battleships.game.objectRenderers.UserInterfaceRenderer;

public class RadarContainer extends ObjectController {

	
	

	// Gui objects
	private UserInterfaceObject crosshair;
	private UserInterfaceObject weaponDescription;
	public boolean radarLocked;
	public Vector3 targetPosition;
	public int selectedWeapon;
	
	public RadarContainer(float x, float y, float w, float h) {
		super(x, y, w, h);
		radarLocked=true;
		targetPosition = new Vector3(10,0,0);
		selectedWeapon=0;
	}
	public void initialize(ObjectController parent){
		controllers	= new ArrayList<ObjectController>();
		addlist		= new ArrayList<ObjectController>();
		removelist	= new ArrayList<ObjectController>();
		
		this.parent=parent;
		crosshair = new UserInterfaceObject(new UserInterfaceController(0f,0f,.5f,.5f,null),new UserInterfaceRenderer(),this,"crosshair.png");
		crosshair.addToRadar();
		new UserInterfaceObject(new UserInterfaceController(0,4.5f,1f,1f, new FireCommand(this)), new UserInterfaceRenderer(),this,"button_ready.png").addToRadar();
		new UserInterfaceObject(new UserInterfaceController(-3.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,0)), new UserInterfaceRenderer(),this,"button_ready.png").addToRadar();
		new UserInterfaceObject(new UserInterfaceController(-2.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,1)), new UserInterfaceRenderer(),this,"button_ready.png").addToRadar();
		new UserInterfaceObject(new UserInterfaceController(-1.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,2)), new UserInterfaceRenderer(),this,"button_ready.png").addToRadar();
		new UserInterfaceObject(new UserInterfaceController(-0.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,3)), new UserInterfaceRenderer(),this,"button_ready.png").addToRadar();
		new UserInterfaceObject(new UserInterfaceController(0.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,4)), new UserInterfaceRenderer(),this,"button_ready.png").addToRadar();
		new UserInterfaceObject(new UserInterfaceController(1.5f,-4.5f,1f,1f,new WeaponSelectCommand(this,3)), new UserInterfaceRenderer(),this,"button_ready.png").addToRadar();
		
		object.setVisible();
		hide();
		for(ObjectController uc : controllers){
			uc.show();
		}
		
	
	}

	public void fire() {
	if(!isLocked()){
		Vector3 target = new Vector3();
		target.set(crosshair.getController().pollPosition());
		PlayScreen.logicHandler.sendShoot(target.x,target.y,selectedWeapon);
	}	
	}
	
	public void drawResult(HashMap<ArrayList<Vector3>,ArrayList<Vector3>> results){
		for(Map.Entry<ArrayList<Vector3>, ArrayList<Vector3>> result : results.entrySet() ){
			ArrayList<Vector3> flightpath = result.getKey();
			ArrayList<Vector3> hitspots = result.getValue();
			System.out.println("HitSpot size "+hitspots.size()+ " flightpath size "+flightpath.size());
			new ProjectileObject(new ProjectileController(5f, 10f, 0.5f, 0.5f),
					new ProjectileRenderer(),this, -1).simulateDamage(flightpath, hitspots, this);
		}
		
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
		for (ObjectController uc : controllers)
			uc.handleInputDown(pos);
		
		
	}
	public void handleInputUp(Vector3 pos){
		
	}
	
	
	public void hide() {
		lockRadar();
		targetPosition.set(10,0,0);
	}

	public void show() {
		unlockRadar();
		targetPosition.set(0,0,0);
	}
	
	public void showWeaponIcon(){
		if(weaponDescription!=null)
			((UserInterfaceController) weaponDescription.getController()).executeCommand();
		weaponDescription = new UserInterfaceObject(new UserInterfaceController(2.5f,-6.5f,2.5f,2.5f,new DisposeCommand(this)),new UserInterfaceRenderer(),this,"wicon"+selectedWeapon+".png");
		weaponDescription.addToRadar();
	}
	
	
	
}
