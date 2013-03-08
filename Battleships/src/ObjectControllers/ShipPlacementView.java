package ObjectControllers;

import java.util.ArrayList;

import Commands.ReadyCommand;
import Commands.HideCommand;
import ObjectModels.GuiObject;
import ObjectModels.ShipObject;

import ObjectModels.ShipObject.ShipType;

import ObjectRenderers.GuiRenderer;
import ObjectRenderers.ShipRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ShipPlacementView extends ObjectController {

	private ShootingMapView _shootingController;
	public static ArrayList<ShipController> shipControllers;
	public	ArrayList<GuiController> guiControllers;
	private ShipController activeController;

	// Gui objects
	private GuiObject button_ready;
	private GuiObject arrow_right;
	private GuiObject top_texture;
	private GuiObject bot_texture;

	public ShipPlacementView(float x,float y, float w, float h) {
		super(x,y,w,h);
		shipControllers 	= new ArrayList<ShipController>();
		guiControllers 		= new ArrayList<GuiController>();
		activeController 	= null;
		
	

		button_ready 		= new GuiObject(new GuiController(5,1.5f,1.5f,1.5f,new ReadyCommand(this)), new GuiRenderer(),"button_ready.png");
		arrow_right 		= new GuiObject(new GuiController(8,1.5f,1.5f,1.5f,new HideCommand(this)), new GuiRenderer(),"arrow_right.png");
		guiControllers.add((GuiController) button_ready.getController());
		guiControllers.add((GuiController) arrow_right.getController());
	}

	public void createShips() {
		for(ShipType ship : ShipType.values()){
			new ShipObject(ship,new ShipController(1,8,ship.getLenght(),ship.getWidth()),new ShipRenderer());
		}
		
		
	}

	public void linkMapController(ObjectController cont) {
		_shootingController = (ShootingMapView) cont;
		_shootingController.linkSeaController(this);
	}

	public void hide() {
		position.set(hidePosition);
		for (ShipController sc : shipControllers)
			sc.hide();
		for (GuiController gc : guiControllers)
			gc.hide();
	}

	public void show() {
		
		position.set(visiblePosition);
		for (ShipController sc :shipControllers)
			sc.show();
		for (GuiController gc : guiControllers)
			gc.show();
	}

	@Override
	public void handleInputDown(Vector3 pos) {
		
			for (ShipController c : shipControllers)
				if (c.getObject().getBounds().contains(pos.x,pos.y)) {
					if (c.isSelected())
						c.changeOrientation();

				activeController = c;
				c.select();
			}
		
		
			for (GuiController c : guiControllers)
				if (c.getObject().getBounds().contains(pos.x, pos.y)) {
					c.executeCommand();
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
		if (activeController != null){
			activeController.handleInputDrag(pos);
		}

	}

}
