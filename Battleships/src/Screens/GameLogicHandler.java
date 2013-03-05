package Screens;

import com.badlogic.gdx.math.Vector2;

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
	
	
	public static void sendAttackCoordinates(Vector2 pos,int weapontype){
		System.out.println("Firing at "+pos.toString()+ " with "+weapontype);
		//nativeConnector.sendAttackAction(pos.x,pos.y, weapontype);
	}
	
	
	
	
	
	public static void setGameState(int state){
	
		switch(state){
		
		case MY_TURN:{
		
			controller.changeController();
			
		
			break;
		}
		case OPPONENT_TURN:{
			controller.changeController();
			
			break;
		}
		case PAUSED:{
			
			break;
		}
		
		
		}
	}
}
