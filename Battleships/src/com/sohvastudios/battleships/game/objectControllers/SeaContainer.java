package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.commands.ReadyCommand;
import com.sohvastudios.battleships.game.commands.RotateCommand;
import com.sohvastudios.battleships.game.gamelogic.PlayScreen;
import com.sohvastudios.battleships.game.objectModels.ProjectileObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject.ShipType;
import com.sohvastudios.battleships.game.objectModels.UserInterfaceObject;
import com.sohvastudios.battleships.game.objectModels.WeaponObject.Weapon;
import com.sohvastudios.battleships.game.objectRenderers.ProjectileRenderer;
import com.sohvastudios.battleships.game.objectRenderers.ShipRenderer;
import com.sohvastudios.battleships.game.objectRenderers.UserInterfaceRenderer;

public class SeaContainer extends ObjectController {
	
	private UserInterfaceObject weaponDescription;
	private ShipController activeController;
	private boolean shipsLocked;
	public ArrayList<Vector3> hitspot;
	public ArrayList<Vector3> flightpath;
	public HashMap<ArrayList<Vector3>,ArrayList<Vector3>> attackResult;

	public SeaContainer(float x, float y, float w, float h) {
		super(x, y, w, h);
		
	}
	public void initialize(ObjectController parent){
		controllers	= new ArrayList<ObjectController>();
		addlist		= new ArrayList<ObjectController>();
		removelist	= new ArrayList<ObjectController>();
		hitspot 	= new ArrayList<Vector3>();
		flightpath	= new ArrayList<Vector3>();
		attackResult = new HashMap<ArrayList<Vector3>,ArrayList<Vector3>>();
		this.parent=parent;
		createShips();
		activeController = null;
		weaponDescription =null;
		new UserInterfaceObject(new UserInterfaceController(0, -6f, 3f, 1.5f,
				new ReadyCommand(this)), new UserInterfaceRenderer(),this, "button_ready.png").addToSea();
	
		new UserInterfaceObject(new UserInterfaceController(-2.5f, -6f, 1.5f, 1.5f,
				new RotateCommand(this)), new UserInterfaceRenderer(),this, "button_ready.png").addToSea();
		
		object.setVisible();
		for(ObjectController oc : controllers){
			oc.show();
		}
		
		
	}

	public void createShips() {
	
		for (ShipType ship : ShipType.values()) {
			new ShipObject(ship, new ShipController(0, 6 ,ship.getLenght(), ship.getWidth()), new ShipRenderer(),this);
		}

	}
	public void lockShips(){
		shipsLocked=true;
	}

	public void calculateDamageTaken(Vector3 point, int weapon_type) {
		
		float radius = Weapon.values()[weapon_type].getRadius();

		switch (weapon_type) {

		case 0: {
			// Grenade
			for(int i = 0 ; i<1 ; i++){
			new ProjectileObject(new ProjectileController(5f, 15f, 1f, 1.5f),
					new ProjectileRenderer(),this, weapon_type).calculateDamage(point, radius);
			ArrayList<Vector3> temphit = new ArrayList<Vector3>();
			ArrayList<Vector3> temppath = new ArrayList<Vector3>();
			temphit.addAll(hitspot);
			temppath.addAll(flightpath);
			System.out.println("Added hits to list size is "+temphit.size());
			attackResult.put(temphit, temppath);
			hitspot.clear();
			flightpath.clear();
		
			}
			break;
		}

		case 1: {
			// Heatseeker
			for(int i = 0 ; i<1 ; i++){
				hitspot.clear();
				flightpath.clear();
				new ProjectileObject(new ProjectileController(5f, 15f, 1f, 1.5f),
						new ProjectileRenderer(),this, weapon_type).calculateDamage(point, radius);
				ArrayList<Vector3> temphit = new ArrayList<Vector3>();
				ArrayList<Vector3> temppath = new ArrayList<Vector3>();
				temphit.addAll(hitspot);
				temppath.addAll(flightpath);
				attackResult.put(temphit, temppath);
				
			
				}
		
			
			break;
		}
		case 2: {
			// Mortar
			for(int i = 0 ; i<5 ; i++){
				hitspot.clear();
				flightpath.clear();
				Vector3 displacement = new Vector3();
				displacement.set(point);
				displacement.add((float)(-2+Math.random()*4),(float)(-2+Math.random()*4), 0);
				new ProjectileObject(new ProjectileController(5f, 15f, 0.75f, 0.75f),
						new ProjectileRenderer(),this, weapon_type).calculateDamage(displacement, radius);
				
				ArrayList<Vector3> temphit = new ArrayList<Vector3>();
				ArrayList<Vector3> temppath = new ArrayList<Vector3>();
				temphit.addAll(hitspot);
				temppath.addAll(flightpath);
				attackResult.put(temppath, temphit);
			
			
				}
			System.out.println("All mortars away. "+attackResult.size());
		
			break;
		}
		case 3: {
			// NavalGun
			for(int i = 0 ; i<1 ; i++){
				new ProjectileObject(new ProjectileController(5f, 15f, 1f, 1.5f),
						new ProjectileRenderer(),this, weapon_type).calculateDamage(point, radius);
				ArrayList<Vector3> temphit = new ArrayList<Vector3>();
				ArrayList<Vector3> temppath = new ArrayList<Vector3>();
				temphit.addAll(hitspot);
				temppath.addAll(flightpath);
				attackResult.put(temphit, temppath);
				hitspot.clear();
				flightpath.clear();
			
				}
		
			break;
		}
		case 4: {
			// Phalanx CIWS
			for(int i = 0 ; i<1 ; i++){
				new ProjectileObject(new ProjectileController(5f, 15f, 1f, 1.5f),
						new ProjectileRenderer(),this, weapon_type).calculateDamage(point, radius);
				ArrayList<Vector3> temphit = new ArrayList<Vector3>();
				ArrayList<Vector3> temppath = new ArrayList<Vector3>();
				temphit.addAll(hitspot);
				temppath.addAll(flightpath);
				attackResult.put(temphit, temppath);
				hitspot.clear();
				flightpath.clear();
			
				}
		
			break;
		}
		}
		
		PlayScreen.logicHandler.sendResult(attackResult);
		
		attackResult.clear();
	}

	
	public void hide() {
		object.setHidden();
		for(ObjectController oc : controllers){
			oc.hide();
		}
	
	}

	public void show() {
		for(ObjectController oc : controllers){
			oc.show();
		}
	}

	@Override
	public void handleInputDown(Vector3 pos) {
		//Handle button touches
		for (ObjectController oc : controllers)
				oc.handleInputDown(pos);
		
		
		//Handle ship touches, return if ships locked
		if(shipsLocked)
			return;
		//Remove weapon description icon if its visible
		if(weaponDescription!=null){
			((UserInterfaceController) weaponDescription.getController()).executeCommand();
			}
		//Check which ship was pressed, set pressed ship as active ship.
			for (ObjectController oc : controllers){
				if(oc instanceof ShipController){
				oc.deSelect();
				if (oc.pollBounds().contains(pos.x,pos.y)){
					oc.select();
					activeController=(ShipController) oc;
				}
				}
			
		}
		

	}

	public void handleInputDrag(Vector3 pos) {
		if(shipsLocked)
			return;
		if (activeController != null) {
			activeController.handleInputDrag(pos);
		}
	}
	public void rotate(){
		if(shipsLocked)
			return;
		if (activeController != null) {
			activeController.rotate90();
		}
		
	}
	
	

}
