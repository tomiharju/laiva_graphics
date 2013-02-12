package Screens;

import Controllers.WorldController;
import Models.World;
import Views.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class GameScreen implements Screen, InputProcessor{

	private WorldRenderer worldRenderer;
	WorldController worldController;
	private World world;
	
	
	//Input handler variables
	final Plane xzPlane = new Plane(new Vector3(0,1,0),0);
	final Vector3 xzintersection = new Vector3();
	final Plane xyPlane = new Plane(new Vector3(0,0,-1),0);
	final Vector3 xyintersection = new Vector3();
	
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.223f,.345f,.474f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		worldRenderer.render();
		
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void show() {
		world = new World();
		worldRenderer = new WorldRenderer(world);
		worldController = new WorldController(world);
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	//Methods to handle input

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		Ray pickRay = worldRenderer.getCamera().getPickRay(Gdx.input.getX(), Gdx.input.getY());
		if(Intersector.intersectRayPlane(pickRay, xzPlane, xzintersection)){
			if(xzintersection.x>0 && xzintersection.x<100 && xzintersection.z>0 && xzintersection.z<100)
				worldController.handleTouchAtBoard(0, 0, xzintersection.x, xzintersection.z);
		}
		pickRay = worldRenderer.getCamera().getPickRay(Gdx.input.getX(), Gdx.input.getY());
		if(Intersector.intersectRayPlane(pickRay, xyPlane, xyintersection)){
			if(xyintersection.x>0 && xyintersection.x<100 && xyintersection.y>0 && xyintersection.y<100)
				worldController.handleTouchAtBoard(xyintersection.x, 100-xyintersection.y,0, 0);
			
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
	
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
