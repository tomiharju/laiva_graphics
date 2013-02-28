package Screens;

import Core.NativeConnector;
import ObjectControllers.WorldController;
import ObjectModels.WorldObject;

public class GameLogicHandler extends Thread{
	
	private int game_state;
	private final int OPPONENT_TURN=1;
	private final int MY_TURN=2;
	private final int PAUSED=3;
	
	private WorldObject world;
	private WorldController controller;
	private NativeConnector nativeConnector;
	public GameLogicHandler(WorldController c,WorldObject w,NativeConnector con){
		this.nativeConnector=con;
		this.world=w;
		this.controller=c;
		setGameState(MY_TURN);
	}
	
	
	public void run(){
		//nativeConnector.helloworld(); //Call to android function
		
		
	}
	
	public void setGameState(int state){
		
		switch(state){
		
		case MY_TURN:{
		
			controller.changeController(MY_TURN);
			
			//Change view to map
			//Allow firing logic
			break;
		}
		case OPPONENT_TURN:{
			controller.changeController(OPPONENT_TURN);
			//Change view to sea
			//
			break;
		}
		case PAUSED:{
			
			break;
		}
		
		
		}
	}
}
