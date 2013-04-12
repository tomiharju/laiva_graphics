package com.sohvastudios.battleships.game.objectControllers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.commands.Command;

public class UserInterfaceController extends ObjectController {
	
	
	
	private Command command;
	Rectangle radar_bounds;
	Rectangle clear_bounds;

	public UserInterfaceController(float x, float y, float w, float h, Command command) {
		super(x, y, w, h);
		this.command = command;
	
		
	}
	public void initialize(){
		clear_bounds = new Rectangle();
	}
	public UserInterfaceController(){
		System.out.println("Creating elements");
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
				System.out.println("Executing");
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
		clear_bounds.set(pos.x - bounds.width / 2, pos.y - bounds.height / 2,
				bounds.width, bounds.height);
		if (!area_bounds_radar.contains(clear_bounds))
			legalmove = false;
		if (legalmove) {
			setPosition(pos);
		}

	}

}
