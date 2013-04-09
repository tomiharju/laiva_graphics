package com.sohvastudios.battleships.game.objectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.sohvastudios.battleships.game.objectControllers.HitMarkerController;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.RadarController;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;
import com.sohvastudios.battleships.game.objectRenderers.RadarRenderer;
import com.sohvastudios.battleships.game.utilities.AssetStorage;

public class RadarObject extends ModelObject {

	private final int SWEEP_INTERVAL = 2;
	private final double SWEEP_STEP_INTERVAL = 0.033f;
	private float sweeptime;
	private float sweepStepTime;

	private Rectangle scanBar;
	private Sprite sweepEffect;
	
	public RadarObject(RadarController controller,
			RadarRenderer renderer) {
		setController(controller);
		setRenderer(renderer);
		position = controller.pollPosition();
		bounds = controller.pollBounds();
		sprite = new Sprite(AssetStorage.manager.get("data/radarTexture.png",
				Texture.class));
		sprite.setSize(bounds.getWidth(), bounds.getHeight());
		sprite.setPosition(position.x - bounds.width / 2, position.y
				- bounds.height / 2);

		
		WorldObject.objects.add(this);
		renderer.addGraphics(sprite);
		
		sweeptime = 0;
		sweepStepTime = 0;
		scanBar = new Rectangle(bounds.x-1, bounds.y,1, 5);
		sweepEffect = new Sprite(AssetStorage.manager.get("data/effects/sweep.png",Texture.class));
		sweepEffect.setSize(1,5);
		sweepEffect.setPosition(scanBar.x , scanBar.y);
		renderer.addSweepGraphics(sweepEffect);
	}

	@Override
	public void update() {
		sweeptime += Gdx.graphics.getDeltaTime();
		if (sweeptime > SWEEP_INTERVAL) {
			runSweep();

		}
	}

	public void runSweep() {
		if ((sweepStepTime += Gdx.graphics.getDeltaTime()) > SWEEP_STEP_INTERVAL) {
			for (HitMarkerController hc : RadarController.markerControllers) {
				if (scanBar.contains(hc.pollPosition().x, hc.pollPosition().y)) {
					((HitMarkerObject) hc.getObject()).resetAlpha();
				}
			}
			scanBar.set(scanBar.x + 0.1f, bounds.y, 1, 5);
			sweepEffect.setPosition(scanBar.x , scanBar.y);
			sweepStepTime = 0;
			if (scanBar.x > 5) {
				scanBar.set(bounds.x-1,  bounds.y,1,5);
				sweeptime = 0;
			}
		}
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller = (RadarController) controller;
		this.controller.setObject(this);

	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer = (RadarRenderer) renderer;
		this.renderer.setObject(this);

	}

}
