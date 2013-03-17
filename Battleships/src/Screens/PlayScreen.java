package Screens;

import Core.NativeFunctions;
import ObjectControllers.WorldController;
import ObjectModels.WorldObject;
import ObjectRenderers.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class PlayScreen implements Screen, InputProcessor{
	
		OrthographicCamera 	guiCam;
		Vector3 			touchPoint;
		boolean paused;
		WorldObject 		world;
		WorldController		controller;
		WorldRenderer		renderer;
		GameLogicHandler 	logicHandler;
		
	
	
	public PlayScreen(NativeFunctions connector){
		
		guiCam 			=  	new OrthographicCamera(10,15);
		guiCam.position.set(5,7.5f,0f);
		guiCam.update();
		touchPoint		=	new Vector3();
		controller		= 	new WorldController(0,0,0,0);
		renderer		=	new WorldRenderer();
		world 			= 	new WorldObject(controller,renderer);
		controller.initialize();//Required to set other controllers in worldcontroller
			
		logicHandler 	= new GameLogicHandler((WorldController)world.getController(),world,connector);
		logicHandler.run();
		
		paused			=false;
		Gdx.input.setInputProcessor(this);
	}
	
	
	
	
	@Override
	public void render(float delta) {
		
		
			if(!paused){
				
				Gdx.gl.glClearColor(0f,.0f,.0f,1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
				world.update();	
				renderer.render();	
			
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
		guiCam.unproject(touchPoint.set(screenX,screenY,0));
		controller.touchDown(touchPoint);
		return false;
		
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		guiCam.unproject(touchPoint.set(screenX,screenY,0));
		controller.touchUp(touchPoint);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		guiCam.unproject(touchPoint.set(screenX,screenY,0));
		controller.touchDragged(touchPoint);
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
