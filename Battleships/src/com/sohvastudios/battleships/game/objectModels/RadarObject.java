package com.sohvastudios.battleships.game.objectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.HitMarkerController;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.RadarController;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;
import com.sohvastudios.battleships.game.objectRenderers.RadarRenderer;
import com.sohvastudios.battleships.game.objectRenderers.WorldRenderer;
import com.sohvastudios.battleships.game.utilities.AssetStorage;

public class RadarObject extends ModelObject {


	private final double SWEEP_STEP_INTERVAL = 0.033f;
	private float sweepStepTime;

	private Rectangle scanBar;
	private Sprite sweepEffect;
	private Vector3 camPosition;
	float radarpos;
	float dt;
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

		
		
	
		sweepStepTime = 0;
		scanBar = new Rectangle(bounds.x-1, bounds.y,1, bounds.height);
		sweepEffect = new Sprite(AssetStorage.manager.get("data/effects/sweep.png",Texture.class));
		sweepEffect.setSize(1,bounds.height);
		sweepEffect.setPosition(scanBar.x , scanBar.y);
		renderer.addSweepGraphics(sweepEffect);
		
		camPosition		= new Vector3(0,14,0);
		WorldRenderer.radarCam.translate(0,14);	
		radarpos=14;
		controller.initialize();
		WorldObject.objects.add(this);
		this.renderer.addGraphics(sprite);
	}

	@Override
	public void update() {
		
		
		
		if(isVisible())
			runSweep();
		radarpos+=dt;
		double realvalue = radarpos*1000;
		realvalue = Math.round(realvalue);
		realvalue = realvalue/1000;
	
		if((realvalue)==((RadarController)controller).targetPosition.y){
			dt=0;
		}
		else if((realvalue)<(((RadarController)controller).targetPosition.y)){
			dt=0.1f;
		}
		else if((realvalue)>(((RadarController)controller).targetPosition.y)){
			dt=-0.1f;
		}
		WorldRenderer.radarCam.translate(0,dt);
		
		
	}

	public void runSweep() {
		if ((sweepStepTime += Gdx.graphics.getDeltaTime()) > SWEEP_STEP_INTERVAL) {
			for (HitMarkerController hc : RadarController.markerControllers) {
				if (scanBar.contains(hc.pollPosition().x, hc.pollPosition().y)) {
					((HitMarkerObject) hc.getObject()).resetAlpha();
				}
			}
			scanBar.set(scanBar.x + 0.05f, bounds.y, 1,bounds.height);
			sweepEffect.setPosition(scanBar.x , scanBar.y);
			sweepStepTime = 0;
			if (scanBar.x+scanBar.width > bounds.width/2) {
				scanBar.set(bounds.x,  bounds.y,1,bounds.height);
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
