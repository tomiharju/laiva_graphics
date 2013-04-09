package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.commands.Command;
import com.sohvastudios.battleships.game.commands.ReadyCommand;
import com.sohvastudios.battleships.game.objectModels.GuiObject;
import com.sohvastudios.battleships.game.objectRenderers.GuiRenderer;

public class GuiController extends ObjectController {
	public static ArrayList<GuiController> guiControllers = new ArrayList<GuiController>();;
	private Command command;
	Rectangle radar_bounds;
	Rectangle clear_bounds;

	public GuiController(float x, float y, float w, float h, Command command) {
		super(x, y, w, h);
		this.command = command;
		clear_bounds = new Rectangle();
		guiControllers.add(this);
		
	}
	public GuiController(){
		System.out.println("Creating elements");
		createGuiElements();
	}
	
	public void createGuiElements(){
		new GuiObject(new GuiController(2, 1.5f, 1.5f, 1.5f,
				new ReadyCommand()), new GuiRenderer(), "button_ready.png");
		
	}
	public static void removeGuiObject(GuiController obj) {
		System.out.println("Removing object, objects in list "+guiControllers.size());
		guiControllers.remove(obj);
		obj.getObject().dispose();
		System.out.println("Finished removing, objects in list "+guiControllers.size());
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
		for (GuiController c : guiControllers)
			if (c.pollBounds().contains(pos.x, pos.y)) {
				System.out.println("Executing readycommand");
				c.executeCommand();
				break;
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
