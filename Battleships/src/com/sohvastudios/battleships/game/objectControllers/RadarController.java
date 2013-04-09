package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.objectModels.GuiObject;
import com.sohvastudios.battleships.game.objectModels.ShipObject;
import com.sohvastudios.battleships.game.objectRenderers.GuiRenderer;
import com.sohvastudios.battleships.game.utilities.Turn;

public class RadarController extends ObjectController {

	public static ArrayList<HitMarkerController> markerControllers;

	// Gui objects
	private GuiObject crosshair;

	public RadarController(float x, float y, float w, float h) {
		super(x, y, w, h);
		markerControllers = new ArrayList<HitMarkerController>();
		crosshair = new GuiObject(new GuiController(7.5f,2.5f,.5f,.5f,null),new GuiRenderer(),"crosshair.png");

	}

	public void fire() {
		for(ShipController sc : SeaController.shipControllers){
			if(sc.isSelected()){
				System.out.println("Fire in the hole!");
				Vector3 target = new Vector3();
				target.set(crosshair.getController().pollPosition());
				target.set(target.x-5,target.y,0);
				target.mul(2);
				int weapon = ((ShipObject)sc.getObject()).shipWeapon.ordinal();
				System.out.println("Shooting at "+target.toString()+ " with "+weapon);
				GameLogicHandler.sendShoot(new Turn(Turn.TURN_SHOOT,target.x,target.y,weapon));
				break;
			}
			
		}
	}

	@Override
	public void handleInputDrag(Vector3 pos) {
		crosshair.getController().handleInputDrag(pos);
	}
	public void handleDoubleTap(Vector3 pos){
		
	}
	public void handleLongPress(Vector3 pos){
		if(GameLogicHandler.ableToFire)
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
