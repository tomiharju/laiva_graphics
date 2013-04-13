package com.sohvastudios.battleships.game.objectModels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.UserInterfaceController;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;
import com.sohvastudios.battleships.game.objectRenderers.RadarRenderer;
import com.sohvastudios.battleships.game.objectRenderers.SeaRenderer;
import com.sohvastudios.battleships.game.objectRenderers.UserInterfaceRenderer;
import com.sohvastudios.battleships.game.utilities.AssetStorage;

public class UserInterfaceObject extends ModelObject {

	
	public UserInterfaceObject(ObjectController controller, ObjectRenderer renderer,
			String file) {
		setController(controller);
		setRenderer(renderer);

		position = controller.pollPosition();
		bounds = controller.pollBounds();
		
		sprite = new Sprite(AssetStorage.manager.get("data/guiobjects/" + file,
				Texture.class));

		sprite.setSize(bounds.getWidth(), bounds.getHeight());
		sprite.setPosition(position.x - bounds.width / 2, position.y
				- bounds.height / 2);
	
		setVisible();
		
		controller.initialize();
		WorldObject.objects.add(this);
		this.renderer.addGraphics(sprite);
	}
	
	public void addToSea(ObjectController controller){
		controller.guiControllers.add((UserInterfaceController) this.getController());
		SeaRenderer.objectsAtSea.add(renderer);
	}
	public void addToRadar(ObjectController controller){
		controller.guiControllers.add((UserInterfaceController) this.getController());
		RadarRenderer.objectsAtRadar.add(renderer);
	}
	
	public UserInterfaceObject(ObjectController controller){
		System.out.println("Another constructor");
		setController(controller);
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller = (UserInterfaceController) controller;
		this.controller.setObject(this);

	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer = (UserInterfaceRenderer) renderer;
		this.renderer.setObject(this);

	}

	@Override
	public void update() {
		
		// TODO Auto-generated method stub

	}
	

}
