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

	public ShipPlacementView() {
		shipControllers = new ArrayList<ShipController>();
		guiControllers = new ArrayList<GuiController>();
		activeController = null;
		isHidden = false;
		position = new Vector2(0, (float) (Gdx.graphics.getHeight() * 0.2));
		hidePosition = new Vector2(Gdx.graphics.getWidth(), position.y);
		visiblePosition = new Vector2(position);

		button_ready = new GuiObject(new GuiController(Gdx.graphics.getWidth()/2,50,new ReadyCommand(this)), new GuiRenderer(),"button_ready.png",50, 50);
		arrow_right = new GuiObject(new GuiController(Gdx.graphics.getWidth()/2+100,50,new HideCommand(this)), new GuiRenderer(),
				"arrow_right.png",50, 50);
		guiControllers.add((GuiController) button_ready.getController());
		guiControllers.add((GuiController) arrow_right.getController());
	}

	public void createShips() {
		new ShipObject(ShipType.ROWBOAT, new ShipController(100,
				(float) (Gdx.graphics.getHeight() * 0.7)), new ShipRenderer());
		new ShipObject(ShipType.MOTORBOAT, new ShipController(250,
				(float) (Gdx.graphics.getHeight() * 0.7)), new ShipRenderer());
		
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
		_shootingController.hide();
		position.set(visiblePosition);
		for (ShipController sc :shipControllers)
			sc.show();
		for (GuiController gc : guiControllers)
			gc.show();
	}

	@Override
	public void handleInputDown(float x, float y) {
		if(object.getBounds().contains(x, y)){
			for (ShipController c : shipControllers)
				if (c.getObject().getBounds().contains(x, y)) {
					if (c.isSelected())
						c.changeOrientation();

				activeController = c;
				c.select();
			}
		}
		else{
			System.out.println("finding gui button");
			for (GuiController c : guiControllers){
				
				if (c.getObject().getBounds().contains(x, y)) {
					c.executeCommand();
				
					}
			}
		}

	}

	@Override
	public void handleInputUp(float x, float y) {
		for (ShipController c : shipControllers) {
			if (!c.equals(activeController))
				c.deSelect();
		}
	}

	@Override
	public void handleInputDrag(float x, float y) {
		if (activeController != null)
			activeController.handleInputDrag(x, y);

	}

}
