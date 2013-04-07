package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.commands.ReadyCommand;
import com.sohvastudios.battleships.game.commands.ShowMapCommand;
import com.sohvastudios.battleships.game.objectModels.GuiObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject.ShipType;
import com.sohvastudios.battleships.game.objectRenderers.GuiRenderer;
import com.sohvastudios.battleships.game.objectRenderers.ShipRenderer;

public class ShipPlacementView extends ObjectController {

	public static ArrayList<ShipController> shipControllers;
	public ArrayList<GuiController> guiControllers;

	private ShipController activeController;

	// Gui objects
	private GuiObject button_ready;
	private GuiObject arrow_right;

	public ShipPlacementView(float x, float y, float w, float h) {
		super(x, y, w, h);
		shipControllers = new ArrayList<ShipController>();
		guiControllers = new ArrayList<GuiController>();

		activeController = null;

		button_ready = new GuiObject(new GuiController(5, 1.5f, 1.5f, 1.5f,
				new ReadyCommand(this)), new GuiRenderer(), "button_ready.png");
		arrow_right = new GuiObject(new GuiController(8, 1.5f, 1.5f, 1.5f,
				new ShowMapCommand(this)), new GuiRenderer(), "arrow_right.png");

		guiControllers.add((GuiController) button_ready.getController());
		guiControllers.add((GuiController) arrow_right.getController());
	}

	public void createShips() {
		float values[] = { 12, 10, 8, 6, 4 };
		for (ShipType ship : ShipType.values()) {
			new ShipObject(ship, new ShipController(3, values[ship.ordinal()],
					ship.getLenght(), ship.getWidth()), new ShipRenderer());
		}

	}

	public void removeGuiObject(GuiController obj) {
		guiControllers.remove(obj);
		obj.getObject().dispose();
	}

	public void hide() {
		object.setHidden();
		for (ShipController sc : shipControllers)
			sc.hide();
		for (GuiController gc : guiControllers)
			gc.hide();
	}

	public void show() {
		object.setVisible();
		for (ShipController sc : shipControllers)
			sc.show();
		for (GuiController gc : guiControllers)
			gc.show();
	}

	@Override
	public void handleInputDown(Vector3 pos) {

		for (ShipController c : shipControllers)
			if (c.pollBounds().contains(pos.x, pos.y)) {
				if (c.isSelected())
					c.rotate90();

				activeController = c;
				c.select();
				break;
			}

		for (GuiController c : guiControllers)
			if (c.pollBounds().contains(pos.x, pos.y)) {
				c.executeCommand();
				break;
			}

	}

	@Override
	public void handleInputUp(Vector3 pos) {
		for (ShipController c : shipControllers) {
			if (!c.equals(activeController))
				c.deSelect();
		}
	}

	@Override
	public void handleInputDrag(Vector3 pos) {
		if (activeController != null) {
			activeController.handleInputDrag(pos);
		}

	}

}
