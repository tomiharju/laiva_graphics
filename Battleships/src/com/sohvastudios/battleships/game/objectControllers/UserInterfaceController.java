package com.sohvastudios.battleships.game.objectControllers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.commands.Command;

public class UserInterfaceController extends ObjectController {
	
	
	
	private Command command;
	Rectangle radar_bounds;
	Rectangle clear_bounds;
	private Vector3 movement;
	
	public UserInterfaceController(float x, float y, float w, float h, Command command) {
		super(x, y, w, h);
		this.command = command;
	
		
	}
	public void initialize(ObjectController parent){
		this.parent=parent;
		parent.addlist.add(this);
		clear_bounds = new Rectangle();
		movement = new Vector3();
	}

	
	
	


	public void executeCommand() {
		if (command != null)
			command.execute(this);
	}

	@Override
	public void hide() {
		object.setHidden();
	}

	@Override
	public void show() {
		object.setVisible();
	}

	@Override
	public void handleInputDown(Vector3 pos) {
		if (bounds.contains(pos.x, pos.y)) {
				executeCommand();
			}
	}

	@Override
	public void handleInputUp(Vector3 pos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleInputDrag(Vector3 pos) {
		boolean legalmove = true;
		movement.set(0,0,0);
		if(pos.x>-2.5 || pos.x<2.5)
			movement.set(pos.x,0,0);
		
		if(pos.y<2.5f || pos.y>-2.5f)
			movement.set(movement.x,pos.y,0);
		
		setPosition(movement);
		
		
	

	}
	

}
