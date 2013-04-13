package com.sohvastudios.battleships.game.gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.interfaces.ConnectionHandler;
import com.sohvastudios.battleships.game.interfaces.LogicHandler;
import com.sohvastudios.battleships.game.interfaces.NativeActions;
import com.sohvastudios.battleships.game.objectControllers.WorldController;
import com.sohvastudios.battleships.game.objectModels.WorldObject;
import com.sohvastudios.battleships.game.objectRenderers.WorldRenderer;

public class PlayScreen extends InputAdapter implements Screen{
	
		OrthographicCamera 	guiCam;
		Vector3 			touchPoint;
		boolean paused;
		WorldObject 		world;
		WorldController		controller;
		WorldRenderer		renderer;
		
		public static LogicHandler logicHandler;
		
		Vector3 prevpoint;
	
	public PlayScreen(ConnectionHandler connector, NativeActions actions){
		
		guiCam 			=  	new OrthographicCamera(10,15);
		//guiCam.position.set(5,7.5f,0f);
		touchPoint		=	new Vector3();
		controller		= 	new WorldController(0,0,0,0);
		renderer		=	new WorldRenderer();
		world 			= 	new WorldObject(controller,renderer);
		controller.initialize();//Required to set other controllers in worldcontroller
			
		logicHandler 	= new GameLogicHandler((WorldController)world.getController(),world,connector, actions);
		
		
		paused			= false;
		prevpoint		= new Vector3();
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		guiCam.update();
		if (!paused) {
			Gdx.gl.glClearColor(0f, .0f, .0f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
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
		paused = true;

	}

	@Override
	public void resume() {
		paused = false;

	}

	@Override
	public void dispose() {

	}

	

	
	

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		guiCam.unproject(touchPoint.set(screenX,screenY, 0));
		controller.touchDown(touchPoint);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		guiCam.unproject(touchPoint.set(screenX,screenY, 0));
		controller.handleInputUp(touchPoint);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		guiCam.unproject(touchPoint.set(screenX,screenY, 0));
		controller.touchDragged(touchPoint);
		return false;
	}
	

	
	

	





}
