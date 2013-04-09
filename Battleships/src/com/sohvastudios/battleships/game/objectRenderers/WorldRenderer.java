package com.sohvastudios.battleships.game.objectRenderers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sohvastudios.battleships.game.objectModels.ModelObject;

public class WorldRenderer extends ObjectRenderer {

	public static ArrayList<ObjectRenderer> renderers;
	public static OrthographicCamera cam;
	public static OrthographicCamera seaCam;
	public static OrthographicCamera radarCam;
	public SpriteBatch batch;
	
	static final float VIEWPORT_WIDTH = 10; // *100meters
	static final float VIEWPORT_HEIGHT = 15;// *100meters

	

	public WorldRenderer() {
		renderers = new ArrayList<ObjectRenderer>();
		batch = new SpriteBatch();
		setupCamera();
	}

	public void setupCamera() {
		cam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT); 
		cam.position.set(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 2, 0);
		
		seaCam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		seaCam.position.set(5f,2.5f,0);
		
		
		radarCam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		radarCam.position.set(0,7.5f,0);
		
	}

	public void render() {
		cam.update();
		seaCam.update();
		radarCam.update();
		batch.begin();
		
		
		batch.setProjectionMatrix(seaCam.combined);
		for (int i = 0; i < SeaRenderer.objectsAtSea.size(); i++)
			SeaRenderer.objectsAtSea.get(i).draw(batch);
		
		batch.setProjectionMatrix(radarCam.combined);
		
		for (int i = 0; i < RadarRenderer.objectsAtRadar.size(); i++)
			RadarRenderer.objectsAtRadar.get(i).draw(batch);
		
		batch.setProjectionMatrix(cam.combined);
		
		for (int i = 0; i < renderers.size(); i++)
			renderers.get(i).draw(batch);
		
		batch.end();

	
	}

	public void draw(SpriteBatch batch) {

	}

	@Override
	public void setObject(ModelObject object) {
		this.object = object;

	}

	@Override
	public ModelObject getObject() {
		return object;
	}

	@Override
	public void addGraphics(Sprite s) {
		// TODO Auto-generated method stub

	}

}
