package com.sohvastudios.battleships.game.objectRenderers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectModels.ModelObject;

public class WorldRenderer extends ObjectRenderer {

	public static ArrayList<ObjectRenderer> renderers;
	public static OrthographicCamera cam;
	public static PerspectiveCamera seaCam;
	public static OrthographicCamera radarCam;
	public SpriteBatch batch;
	
	static final float VIEWPORT_WIDTH = 12; // *100meters
	static final float VIEWPORT_HEIGHT = 20;// *100meters
	public static boolean isInPerspective=false;
	
	
	//Testing variables
	Matrix4 projection = new Matrix4();
	Matrix4 view = new Matrix4();
	Matrix4 model = new Matrix4();
	Matrix4 combined = new Matrix4();
	
	public WorldRenderer() {
		renderers = new ArrayList<ObjectRenderer>();
		batch = new SpriteBatch();
	
		setupCamera();
	}

	public void setupCamera() {
		cam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT); 
		seaCam = new PerspectiveCamera(67,VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		seaCam.position.set(0,0,20);
		radarCam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
	
	}
	
	public void render() {
		
		cam.update();
		seaCam.update();
		radarCam.update();
		
		batch.begin();
		batch.setProjectionMatrix(seaCam.combined);
		for (ObjectRenderer or : SeaRenderer.objectsAtSea)
			or.draw(batch);
		
		batch.setProjectionMatrix(radarCam.combined);

		for (ObjectRenderer or : RadarRenderer.objectsAtRadar)
			or.draw(batch);
		
		batch.setProjectionMatrix(cam.combined);
		for (ObjectRenderer or : renderers)
			or.draw(batch);
		
		
	
		
		batch.end();

	
	}

	public void draw(SpriteBatch batch) {

	}
	public void cleanup(){
		batch.dispose();
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
		seaCam.position.set(0,-10,12);
		isInPerspective=true;
		
	}
	public static void move(Vector3 point){
		if(isInPerspective){
			seaCam.position.set(0,-10,12);
			seaCam.position.set(seaCam.position.x+point.x,seaCam.position.y+point.y,seaCam.position.z);
		}
	}

}
