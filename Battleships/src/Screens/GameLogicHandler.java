package Screens;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

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
		
	}
	
	
	public void run(){
		
		
		
	}
	
	
	public static void sendAttackCoordinates(Rectangle bounds,int weapontype){
		controller.calculateDamageTaken(bounds, weapontype);
	
	
		//nativeConnector.sendAttackAction(pos.x,pos.y, weapontype);
	}
	
	public static void lockShipView(){
		controller.lockToShipView();
	}
	public static void lockMapView(){
		controller.lockToMapView();
	}
	
	public static void sendImReady(){
		//nativeConnector.sendReadyNotification();
	}
	
	public static void receiveFirecoordinates(){
		controller.lockToShipView();
	}
	
	
	
	
	
	
	
}
