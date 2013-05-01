package com.sohvastudios.battleships.game.objectRenderers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectModels.ModelObject;

public class WorldRenderer extends ObjectRenderer {

	public static ArrayList<ObjectRenderer> renderers;
	public static OrthographicCamera cam;
	public static PerspectiveCamera seaCam;
	public static OrthographicCamera radarCam;
	public SpriteBatch batch;
	
	static final float VIEWPORT_WIDTH = 10; // *100meters
	static final float VIEWPORT_HEIGHT = 15;// *100meters
	public static boolean isInPerspective=false;
	
	
	
	
	public WorldRenderer() {
		renderers = new ArrayList<ObjectRenderer>();
		batch = new SpriteBatch();
	
		setupCamera();
	}

	public void setupCamera() {
		cam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT); 
		seaCam = new PerspectiveCamera(45,VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		seaCam.position.set(0,0,17);
	
	
		radarCam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
	
	}
	
	public void render() {
		
		cam.update();
		seaCam.update();
		radarCam.update();
		
		
		
		batch.begin();
		
		if(isInPerspective)
			batch.setProjectionMatrix(seaCam.combined);
		else
			batch.setProjectionMatrix(cam.combined);
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
	public void setToPerspective(){
		seaCam.lookAt(0, 20, 5);
		seaCam.position.set(0,-30,17);
		isInPerspective=true;
	}
	public static void move(Vector3 point){
		if(isInPerspective){
			seaCam.position.set(0,-30,17);
			Vector3 v = new Vector3(seaCam.position);
			seaCam.position.set(v.x+point.x,v.y+point.y,v.z);
		}
	}

}
