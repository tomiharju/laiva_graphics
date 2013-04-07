package com.sohvastudios.battleships.game.objectRenderers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sohvastudios.battleships.game.objectModels.ModelObject;

public class WorldRenderer extends ObjectRenderer {

	public static ArrayList<ObjectRenderer> renderers;
	public static OrthographicCamera cam;
	public SpriteBatch batch;
	public static float ppux, ppuy;
	static final float VIEWPORT_WIDTH = 10; // *100meters
	static final float VIEWPORT_HEIGHT = 15;// *100meters

	

	public WorldRenderer() {
		renderers = new ArrayList<ObjectRenderer>();
		batch = new SpriteBatch();

		setupCamera();
		

	}

	public void setupCamera() {

		cam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT); // Dimension
																		// of
																		// the
																		// world,
																		// 10x15(km)
		// cam.setToOrtho(true,Gdx.graphics.getWidth(),
		// Gdx.graphics.getHeight());
		cam.position.set(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 2, 0);

		ppux = Gdx.graphics.getWidth() / 100;
		ppuy = Gdx.graphics.getHeight() / 100;

	}

	public void render() {
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
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
