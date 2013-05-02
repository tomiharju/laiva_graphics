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
import com.sohvastudios.battleships.game.objectModels.WorldObject;
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
		new UserInterfaceObject(new UserInterfaceController(0, -10f, 3f, 1.5f,
				new ReadyCommand(this)), new UserInterfaceRenderer(),this, "button_ready.png").addToSea();
	
		new UserInterfaceObject(new UserInterfaceController(-2.5f, -10f, 1.5f, 1.5f,
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
		for(ObjectController oc : controllers)
			if(oc instanceof UserInterfaceController)
				WorldObject.removelist.add(oc.getObject());
		
		shipsLocked=true;
	}

	public void calculateDamageTaken(Vector3 point, int weapon_type) {

		switch (weapon_type) {

		case 0: {
			// Grenade
			new ProjectileObject(new ProjectileController(5f, 20f, 0.75f, 0.75f),
					new ProjectileRenderer(),this, weapon_type).animateDamage(point);
			ArrayList<Vector3> temphit = new ArrayList<Vector3>();
			ArrayList<Vector3> temppath = new ArrayList<Vector3>();
			temphit.addAll(hitspot);
			temppath.addAll(flightpath);
			System.out.println("New result Hitspots " + hitspot.size() +" Paths "+flightpath.size());
			attackResult.put(temppath,temphit);
			hitspot.clear();
			flightpath.clear();
		
			
			break;
		}

		case 1: {
			// Heatseeker
			for(int i = 0 ; i<1 ; i++){
				
				new ProjectileObject(new ProjectileController(5f, 20f, 0.75f, 0.75f),
						new ProjectileRenderer(),this, weapon_type).animateDamage(point);
				ArrayList<Vector3> temphit = new ArrayList<Vector3>();
				ArrayList<Vector3> temppath = new ArrayList<Vector3>();
				temphit.addAll(hitspot);
				temppath.addAll(flightpath);
				attackResult.put(temppath,temphit);
				hitspot.clear();
				flightpath.clear();
			
				}
		
			
			break;
		}
		case 2: {
			// Mortar
			for(int i = 0 ; i<5 ; i++){
				
				Vector3 displacement = new Vector3();
				displacement.set(point);
				displacement.add((float)(-2+Math.random()*4),(float)(-2+Math.random()*4), 0);
				new ProjectileObject(new ProjectileController(5f, 20f, 0.75f, 0.75f),
						new ProjectileRenderer(),this, weapon_type).animateDamage(displacement);
				
				ArrayList<Vector3> temphit = new ArrayList<Vector3>();
				ArrayList<Vector3> temppath = new ArrayList<Vector3>();
				temphit.addAll(hitspot);
				temppath.addAll(flightpath);
				attackResult.put(temppath, temphit);
				hitspot.clear();
				flightpath.clear();
			
				}
			
		
			break;
		}
		case 3: {
			// NavalGun
			for(int i = 0 ; i<1 ; i++){
				new ProjectileObject(new ProjectileController(5f, 20f, 0.75f, 0.75f),
						new ProjectileRenderer(),this, weapon_type).animateDamage(point);
				ArrayList<Vector3> temphit = new ArrayList<Vector3>();
				ArrayList<Vector3> temppath = new ArrayList<Vector3>();
				temphit.addAll(hitspot);
				temppath.addAll(flightpath);
				attackResult.put(temppath,temphit);
				hitspot.clear();
				flightpath.clear();
			
				}
		
			break;
		}
		case 4: {
			// Phalanx CIWS
			for(int i = 0 ; i<1 ; i++){
				new ProjectileObject(new ProjectileController(5f, 20f, 0.75f, 0.75f),
						new ProjectileRenderer(),this, weapon_type).animateDamage(point);
				ArrayList<Vector3> temphit = new ArrayList<Vector3>();
				ArrayList<Vector3> temppath = new ArrayList<Vector3>();
				temphit.addAll(hitspot);
				temppath.addAll(flightpath);
				attackResult.put(temppath,temphit);
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
	public boolean handleInputDown(Vector3 pos) {
		//Handle button touches
		for (ObjectController oc : controllers)
				if(oc.handleInputDown(pos))
					return true;
		
		
		//Handle ship touches, return if ships locked
		if(shipsLocked)
			return false;
		//Remove weapon description icon if its visible
		if(weaponDescription!=null){
			((UserInterfaceController) weaponDescription.getController()).executeCommand();
			}
		//Check which ship was pressed, set pressed ship as active ship.
			for (ObjectController oc : controllers){
				if(oc instanceof ShipController){
				oc.deSelect();
				System.out.println("Clicked at "+pos.toString()+ " Ship at "+oc.pollBounds().toString());
				if (oc.pollBounds().contains(pos.x,pos.y)){
					oc.select();
					activeController=(ShipController) oc;
					return true;
				}
				}
			
		}
			return false;
		

	}

	public boolean handleInputDrag(Vector3 pos) {
		if(shipsLocked)
			return false;
		if (activeController != null) {
			activeController.handleInputDrag(pos);
			return true;
		}else
			return false;
	}
	public void rotate(){
		if(shipsLocked)
			return;
		if (activeController != null) {
			activeController.rotate90();
		}
		
	}
	
	

}
