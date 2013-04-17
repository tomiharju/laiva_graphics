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

	
	public UserInterfaceObject(ObjectController controller, ObjectRenderer renderer,ObjectController parent,String file) {
		setController(controller);
		setRenderer(renderer);

		position = controller.pollPosition();
		bounds = controller.pollBounds();
		
		sprite = new Sprite(AssetStorage.manager.get("data/guiobjects/" + file,
				Texture.class));

		sprite.setSize(bounds.getWidth(), bounds.getHeight());
		sprite.setPosition(position.x - bounds.width / 2, position.y
				- bounds.height / 2);
	
		
		
		controller.initialize(parent);
		WorldObject.addlist.add(this);
		this.renderer.addGraphics(sprite);
		setVisible();
	}
	
	public void addToSea(){
		SeaRenderer.objectsAtSea.add(renderer);
	}
	public void addToRadar(){
		RadarRenderer.objectsAtRadar.add(renderer);
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
