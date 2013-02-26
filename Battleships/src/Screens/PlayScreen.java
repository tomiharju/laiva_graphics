package Screens;

import ObjectControllers.WorldController;
import ObjectModels.WorldObject;
import ObjectRenderers.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class PlayScreen implements Screen, InputProcessor{

		
	
		private WorldRenderer	 	renderer;
		private WorldController 	controller;
		private WorldObject 		world;
	
	public PlayScreen(){
		renderer 	= 	new WorldRenderer();
		controller 	= 	new WorldController();
		world 		= 	new WorldObject();
		renderer.setObject(world);
		controller.setObject(world);
		world.setController(controller);
		world.setRenderer(renderer);
		Gdx.input.setInputProcessor(this);
		
		
	}
	
	
	
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0f,.0f,.0f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.update();		//Update all game objects
		renderer.draw();	//Render all game objects
	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void dispose() {
		
		
	}

	
	
	
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
			boolean onefinger = Gdx.input.isTouched(0);
			boolean secondfinger = Gdx.input.isTouched(1);
			boolean multitouch = onefinger && secondfinger;
			System.out.println(multitouch);
			controller.touchDown(screenX,Gdx.graphics.getHeight()- screenY,multitouch);
		return false;
		
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		controller.touchUp(screenX,Gdx.graphics.getHeight()- screenY);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		controller.touchDragged(screenX,Gdx.graphics.getHeight()- screenY);
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
