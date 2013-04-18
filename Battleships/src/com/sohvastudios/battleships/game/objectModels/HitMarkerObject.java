package com.sohvastudios.battleships.game.objectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sohvastudios.battleships.game.objectControllers.HitMarkerController;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectRenderers.HitMarkerRenderer;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;
import com.sohvastudios.battleships.game.utilities.AssetStorage;

public class HitMarkerObject extends ModelObject {

	public float alpha;
	public boolean isHitMarker;
	public HitMarkerObject(ObjectController controller, ObjectRenderer renderer,ObjectController parent,boolean isHitMarker) {
		setController(controller);
		setRenderer(renderer);
		position = controller.pollPosition();
		bounds = controller.pollBounds();

		sprite = new Sprite(AssetStorage.manager.get("data/hitmarker.png",
				Texture.class));
		sprite.setSize(bounds.getWidth(), bounds.getHeight());
		sprite.setPosition(position.x - bounds.width / 2, position.y
				- bounds.height / 2);
	
		if(isHitMarker){
			alpha = 1f; // Value used to render with increasing transparency
		}else{
			alpha=0.2f;
			sprite.setColor(1f,0f,0f,1f);
		}
		
		this.isHitMarker=isHitMarker;
		visible = true;
		controller.initialize(parent);
		WorldObject.addlist.add(this);
		this.renderer.addGraphics(sprite);
		setVisible();
	}

	@Override
	public void update() {
		if(isHitMarker){
			alpha -= Gdx.graphics.getDeltaTime()/10;
			if (alpha < 0)
				alpha = 0;
		}
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller = (HitMarkerController) controller;
		this.controller.setObject(this);

	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer = (HitMarkerRenderer) renderer;
		this.renderer.setObject(this);

	}

	public void resetAlpha() {
		if(isHitMarker)
			alpha = 1f;
	}

}
