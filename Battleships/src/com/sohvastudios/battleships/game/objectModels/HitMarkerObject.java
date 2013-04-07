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

	public HitMarkerObject(ObjectController controller, ObjectRenderer renderer) {
		setController(controller);
		setRenderer(renderer);
		position = controller.pollPosition();
		bounds = controller.pollBounds();

		sprite = new Sprite(AssetStorage.manager.get("data/hitmarker.png",
				Texture.class));
		sprite.setSize(bounds.getWidth(), bounds.getHeight());
		sprite.setPosition(position.x - bounds.width / 2, position.y
				- bounds.height / 2);
		WorldObject.objects.add(this);
		this.renderer.addGraphics(sprite);

		alpha = 1f; // Value used to render with increasing transparency
		visible = true;
	}

	@Override
	public void update() {
		alpha -= Gdx.graphics.getDeltaTime() / 10;
		
		if (alpha < 0)
			alpha = 0;
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
		System.out.println("Resetting alpha");
		alpha = 1f;
	}

}
