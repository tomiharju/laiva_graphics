package com.sohvastudios.battleships.game.gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.interfaces.ConnectionHandler;
import com.sohvastudios.battleships.game.interfaces.LogicHandler;
import com.sohvastudios.battleships.game.interfaces.NativeActions;
import com.sohvastudios.battleships.game.objectControllers.WorldController;
import com.sohvastudios.battleships.game.objectModels.WorldObject;
import com.sohvastudios.battleships.game.objectRenderers.WorldRenderer;

public class PlayScreen implements Screen, GestureListener{
	
		OrthographicCamera 	guiCam;
		Vector3 			touchPoint;
		boolean paused;
		WorldObject 		world;
		WorldController		controller;
		WorldRenderer		renderer;
		
		public static LogicHandler logicHandler;
		
	
	
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

		Gdx.input.setInputProcessor(new GestureDetector(2.0f,1f,.2f,0.5f,this));
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
	public boolean tap(float x, float y, int count, int button) {
		guiCam.unproject(touchPoint.set(x, y, 0));
		controller.doubleTap(touchPoint);
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		guiCam.unproject(touchPoint.set(x, y, 0));
		System.out.println("LongPress!");
		controller.touchLong(touchPoint);
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		guiCam.unproject(touchPoint.set(x, y, 0));
		controller.touchDragged(touchPoint);
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		guiCam.unproject(touchPoint.set(x,y, 0));
		controller.touchDown(touchPoint);
	
		return false;
	}



}
