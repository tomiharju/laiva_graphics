package com.sohvastudios.battleships.game.objectModels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.ShipController;
import com.sohvastudios.battleships.game.objectControllers.ShipPlacementView;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;
import com.sohvastudios.battleships.game.objectRenderers.SeaRenderer;
import com.sohvastudios.battleships.game.objectRenderers.WorldRenderer;
import com.sohvastudios.battleships.game.utilities.AssetStorage;

public class ShipPlacementViewObject extends ModelObject {

	

	
	public ShipPlacementViewObject(ShipPlacementView controller, SeaRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		position 		= controller.pollPosition();
		bounds 			= controller.pollBounds();
		
		sprite 			= new Sprite(AssetStorage.manager.get("data/waterTexture.png",Texture.class));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x-bounds.width/2, position.y-bounds.height/2);
	
		WorldObject.objects.add(this);
		renderer.addGraphics(sprite);
	
	}
	
	
	@Override
	public void update() {
		
	}


	@Override
	public void setController(ObjectController controller) {
		this.controller=(ShipPlacementView)controller;
		this.controller.setObject(this);
		
		
	}


	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=(SeaRenderer)renderer;
		this.renderer.setObject(this);
		
	}
	


	
	
	


	
}
