package GameLogic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import Core.NativeFunctions;
import ObjectControllers.WorldController;
import ObjectModels.WorldObject;

public class GameLogicHandler extends Thread{
	
	private static final int WAITING				=	0;
	private static final int MY_TURN				=	1;
	private static final int SHIP_PLACEMENT			=	2;
	private static final int WAITING_FIRE_RESULT 	= 	3;
	
	
	
	private static WorldObject world;
	private static WorldController controller;
	private NativeFunctions nativeConnector;
	
	
	public static int state;
	public static boolean locked;
	
	
	public GameLogicHandler(WorldController c,WorldObject w,NativeFunctions con){
		this.nativeConnector=con;
		this.world=w;
		this.controller=c;
		
	}
	
		
	public static void runStateMachine(){
		
		switch(state){
		
		case SHIP_PLACEMENT:{ //Ship placement
			lockShipView();
			//disable button input 
			break;
		}
		
		case MY_TURN:{
			//shooting phase
			
			//Enable input
			break;
		}
		case WAITING:{
			
			//disable button input
			//waiting while enemy calculates damage taken
			//lock input
			
			//Show message "calculating fire effect"
		}
			
		
		
		}
		
		
		
		
	}
		
		
		
	
	
	
	public static void receiveTurn(Turn turn){
		switch(turn.type){
		case Turn.TURN_START:{
			//new turn started
			//Unlockinput
			break;
		}
		case Turn.TURN_WAIT:{
			//LockInput
			break;
		}
		case Turn.RECEIVE_RESULT:{
			// Draw hits on opponent to radar
			break;
		}
		case Turn.RECEIVE_FIRE:{
			// Draw explosions on own ships
			// Send result to opponent
			//change state to MY_TURN
			break;
		}
		
		
		}
	}
	public static void sendTurn(Turn turn){
		//nativeConnector.sendTurn(turn);
		state = turn.type;
		runStateMachine();
	}
	
	
	
	public static void sendAttackCoordinates(Vector3 point,int weapontype){
		controller.calculateDamageTaken(point, weapontype);
		//nativeConnector.sendAttackAction(pos.x,pos.y, weapontype);
	}
	
	
	public static  void lockInput(){
		locked=true;
	}
	public static void unlockInput(){
		locked=false;
	}
	
	public static void lockShipView(){
		controller.lockToShipView();
	}
	public static void lockMapView(){
		controller.lockToMapView();
	}
	
	public static void receiveFirecoordinates(){
		controller.lockToShipView();
	}
	
	
	
	
	
	
	
}
