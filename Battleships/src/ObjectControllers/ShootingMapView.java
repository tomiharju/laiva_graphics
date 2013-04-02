package ObjectControllers;

import java.util.ArrayList;

import Commands.FireCommand;
import Commands.ShowMapCommand;
import Commands.ShowShipCommand;
import GameLogic.GameLogicHandler;
import GameLogic.Turn;
import ObjectModels.GuiObject;
import ObjectModels.ProjectileObject;
import ObjectModels.ShipObject;

import ObjectModels.ModelObject;
import ObjectModels.WeaponObject;
import ObjectModels.ShipObject.ShipType;
import ObjectModels.WeaponObject.Weapon;

import ObjectRenderers.GuiRenderer;
import ObjectRenderers.ProjectileRenderer;
import ObjectRenderers.ShipRenderer;

import ObjectRenderers.WeaponRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ShootingMapView extends ObjectController {
	public static ArrayList<WeaponController> weaponControllers;
	public ArrayList<GuiController> guiControllers;
	private ShipPlacementView _placementController;
	private WeaponController selected_weapon;

	// Gui objects
	private GuiObject button_fire;
	private GuiObject arrow_left;
	private GuiObject crosshair;

	public ShootingMapView(float x, float y, float w, float h) {
		super(x, y, w, h);
		weaponControllers = new ArrayList<WeaponController>();
		guiControllers = new ArrayList<GuiController>();
		crosshair = null;

	}

	public void fire() {
		if (selected_weapon != null) {
			Vector3 target = crosshair.getController().pollPosition();
			int weapon = ((WeaponObject) selected_weapon.getObject()).getWeapon().ordinal();
			GameLogicHandler.sendShoot(new Turn(Turn.TURN_SHOOT,target.x,target.y,weapon));
			
		} else
			System.out.println("Please select a weapon");
	}

	public void createWeapons() {
		float[][] p = { { 1, 14 }, { 3, 14 }, { 5, 14 }, { 7, 14 }, { 9, 14 } };
		for (Weapon weapon : Weapon.values()) {
			new WeaponObject(weapon, new WeaponController(
					p[weapon.ordinal()][0], p[weapon.ordinal()][1], 2, 2f),
					new WeaponRenderer());
			new ProjectileObject(new ProjectileController(5,15,1,1.5f),new ProjectileRenderer(),weapon.ordinal());
		}
		selected_weapon = weaponControllers.get(0);
		selected_weapon.select();
	}

	public void createGuiObjects() {
		button_fire = new GuiObject(new GuiController(5, 1.5f, 1.5f, 1.5f,
				new FireCommand(this)), new GuiRenderer(), "button_fire.png");
		crosshair = new GuiObject(new GuiController(4f, 5f, 1f, 1f, null),
				new GuiRenderer(), "crosshair.png");
		arrow_left = new GuiObject(new GuiController(2, 1.5f, 1.5f, 1.5f,
				new ShowShipCommand(this)), new GuiRenderer(), "arrow_left.png");
	
		
		guiControllers.add((GuiController) button_fire.getController());
		guiControllers.add((GuiController) arrow_left.getController());
		guiControllers.add((GuiController) crosshair.getController());
	}

	public void linkSeaController(ObjectController cont) {
		_placementController = (ShipPlacementView) cont;
	}

	@Override
	public void handleInputDown(Vector3 pos) {
		for (WeaponController wc : weaponControllers) {
			if (wc.pollBounds().contains(pos.x, pos.y)) {
				selected_weapon.deSelect();
				selected_weapon = (WeaponController) wc;
				selected_weapon.select();
				crosshair.getController().setBounds(
						((WeaponObject) selected_weapon.getObject())
								.getWeapon().getRadius());
				
			}

		}
		for (GuiController c : guiControllers) {
			if (c.pollBounds().contains(pos.x, pos.y)) {
				c.executeCommand();
				break;
			}
		}
	}

	@Override
	public void handleInputUp(Vector3 pos) {

	}

	@Override
	public void handleInputDrag(Vector3 pos) {
		crosshair.getController().handleInputDrag(pos);
	}

	public void hide() {
		object.setHidden();
		for (WeaponController sc : weaponControllers)
			sc.hide();
		for (GuiController gc : guiControllers)
			gc.hide();
	}

	public void show() {
		object.setVisible();
		for (WeaponController sc : weaponControllers)
			sc.show();
		for (GuiController gc : guiControllers)
			gc.show();

	}
}
