package Screens;

import Core.NativeFunctions;
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

		private boolean paused;
		private WorldObject 		world;
		private WorldController		controller;
		private WorldRenderer		renderer;
		private GameLogicHandler 	logicHandler;
		
		
	
	public PlayScreen(NativeFunctions connector){
		
		controller	= 	new WorldController();
		renderer	=	new WorldRenderer();
		world 		= 	new WorldObject(controller,renderer);
		Gdx.input.setInputProcessor(this);
			
		logicHandler = new GameLogicHandler((WorldController)world.getController(),world,connector);
		logicHandler.run();
	}
	
	
	
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0f,.0f,.0f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		if(!paused){	
			world.update();				//Update all game objects
			renderer.draw();		//Render all game objects
		}
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
		paused=true;
		
	}

	@Override
	public void resume() {
		paused=false;
		
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
		controller.touchDown(screenX,Gdx.graphics.getHeight()- screenY);
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
