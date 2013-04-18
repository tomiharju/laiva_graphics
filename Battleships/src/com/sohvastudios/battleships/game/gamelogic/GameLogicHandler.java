package com.sohvastudios.battleships.game.gamelogic;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.interfaces.ConfirmListener;
import com.sohvastudios.battleships.game.interfaces.ConnectionHandler;
import com.sohvastudios.battleships.game.interfaces.LogicHandler;
import com.sohvastudios.battleships.game.interfaces.NativeActions;
import com.sohvastudios.battleships.game.objectControllers.WorldController;
import com.sohvastudios.battleships.game.objectModels.WorldObject;

public class GameLogicHandler implements LogicHandler {

	private WorldController controller;
	private ConnectionHandler nativeConnector;
	
	public NativeActions nativeActions;

	public boolean receivedResult;
	public boolean receivedShoot;
	public Vector3 shootPosition;
	public int shootWeapon;
	public HashMap<ArrayList<Vector3>,ArrayList<Vector3>> result;
	
	public static boolean shipsLocked;
	public static boolean ableToFire;

	public GameLogicHandler(WorldController c, WorldObject w,
			ConnectionHandler con, NativeActions nativeActions) {
		this.nativeConnector = con;
		this.controller = c;
		this.nativeActions = nativeActions;
		nativeConnector.setLogicHandler(this);
	


	}
	
	public void handleServerInput(){
		
		if(receivedResult){
			System.out.println("Receiving result size "+result.size());
			controller.drawResult(result);
			receivedResult=false;
		}
		
		else if(receivedShoot){
			System.out.println("Receiving shoot");
			controller.calculateDamageTaken(shootPosition, shootWeapon);
			receivedShoot=false;
		}
		
		
		
		
		
		
	}

	public void opponentLeft() {
		nativeActions.createConfirmDialog(
				"Opponent has left the game", 
				"Leave game or wait for reconnect?", 
				"Leave game",
				"Wait",
				new ConfirmListener() {			
					@Override
					public void yes() {
						nativeActions.dismissProgressDialog();
						nativeConnector.leave();
						Gdx.app.exit();
					}

					@Override
					public void no() {
						// TODO Auto-generated method stub
					}
				});
	}

	@Override
	public void receiveStart() {
		System.out.println("Receiving turn: Start");
		controller.unlockRadar();
}
	public void receiveWait(){
		System.out.println("Receiving turn: Wait");
		controller.lockRadar();
	}

	@Override
	public void receiveResult(HashMap<ArrayList<Vector3>,ArrayList<Vector3>> result) {
	
		this.result=result;
		receivedResult=true;
		//controller.lockRadar();
	}

	@Override
	public void receiveShoot(float x, float y, int weapon) {
		shootWeapon=weapon;
		shootPosition = new Vector3(x,y,0);
		controller.unlockRadar();
		receivedShoot=true;
	}

	@Override
	public void sendReady() {
		System.out.println("Sending turn ready");
		nativeConnector.sendReady();
		controller.lockRadar();
	}

	@Override
	public void sendShoot(float x, float y, int weapon) {
		System.out.println("Sending turn shoot");
		nativeConnector.sendShoot(x, y, weapon);
		//receiveShoot(x,y,weapon);
		//controller.lockRadar();
	}

	@Override
	public void sendResult(HashMap<ArrayList<Vector3>,ArrayList<Vector3>> result) {
		System.out.println("Sending result size "+result.size());
		nativeConnector.sendResult(result);
		//receiveResult(result);
		
	}
	public void createToast(String message){
		nativeActions.createToast(message, 1);
	}

	@Override
	public void runStateMachine() {
		// TODO Auto-generated method stub
		
	}

}
