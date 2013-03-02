package Screens;

import Core.NativeFunctions;
import ObjectControllers.WorldController;
import ObjectModels.WorldObject;

public class GameLogicHandler extends Thread{
	
	private int game_state;
	private static final int OPPONENT_TURN=1;
	private static final int MY_TURN=2;
	private static final int PAUSED=3;
	
	private static WorldObject world;
	private static WorldController controller;
	private NativeFunctions nativeConnector;
	public GameLogicHandler(WorldController c,WorldObject w,NativeFunctions con){
		this.nativeConnector=con;
		this.world=w;
		this.controller=c;
		setGameState(OPPONENT_TURN);
	}
	
	
	public void run(){
		//nativeConnector.helloworld(); //Call to android function
		
		
	}
	
	public static void setGameState(int state){
	
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
