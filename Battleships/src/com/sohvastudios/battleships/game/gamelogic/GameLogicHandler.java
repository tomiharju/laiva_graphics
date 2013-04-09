package com.sohvastudios.battleships.game.gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.nativeinterface.CancelListener;
import com.sohvastudios.battleships.game.nativeinterface.ConfirmListener;
import com.sohvastudios.battleships.game.nativeinterface.ConnectionHandler;
import com.sohvastudios.battleships.game.nativeinterface.NativeActions;
import com.sohvastudios.battleships.game.objectControllers.HitMarkerController;
import com.sohvastudios.battleships.game.objectControllers.WorldController;
import com.sohvastudios.battleships.game.objectModels.HitMarkerObject;
import com.sohvastudios.battleships.game.objectModels.WorldObject;
import com.sohvastudios.battleships.game.objectRenderers.HitMarkerRenderer;
import com.sohvastudios.battleships.game.utilities.Turn;

public class GameLogicHandler extends Thread {

	private static WorldController controller;
	private static ConnectionHandler nativeConnector;
	private static NativeActions nativeActions;

	public static int state;
	private static boolean dialogOpen;
	public static boolean shipsLocked;
	public static boolean ableToFire;

	public GameLogicHandler(WorldController c, WorldObject w,
			ConnectionHandler con, NativeActions nativeActions) {
		this.nativeConnector = con;
		this.controller = c;
		this.nativeActions = nativeActions;
		nativeConnector.setLogicHandler(this);

		state = Turn.TURN_BEGINNING;
		runStateMachine();

	}

	public static void runStateMachine() {
		System.out.println("State is now " + state);
		switch (state) {
		// Ship placement phase
		case Turn.TURN_BEGINNING: { //
			nativeActions.createToast("Place your ships", 1);
			ableToFire	=	false;
			shipsLocked	=	false;
			System.out.println("Current state: Beginning");
			break;
		}

		case Turn.TURN_START: { // 
			nativeActions.createToast("Your turn", 5000);
			ableToFire 	= 	true;
			shipsLocked =	true;
			System.out.println("Current state: Start");
			break;
		}
		case Turn.TURN_WAIT: { // 
			nativeActions.createProgressDialog("Waiting", "Waiting for opponent to shoot", false, new CancelListener() {
				@Override
				public void cancel() {}
			});
			dialogOpen=true;
			ableToFire = false;
			shipsLocked =	true;
			System.out.println("Current state: Wait");
			break;
		}
		case Turn.TURN_SHOOT: { // 
								// 
			ableToFire = false;
			System.out.println("Current state: Shoot");
			break;
		}
		case Turn.TURN_READY: { // 
								// 
			ableToFire = false;
			System.out.println("Current state: Ready");
			break;
		}
		case Turn.TURN_RESULT: { 	
									
			state = Turn.TURN_WAIT;
			System.out.println("Current state: Result");
			runStateMachine();
			break;
		}
		}

	}

	public static void receiveTurn(Turn turn) {

		switch (turn.type) {
		case Turn.TURN_START: {
			System.out.println("Receiving turn: Start");
			state = Turn.TURN_START;
			runStateMachine();
			break;
		}
		case Turn.TURN_WAIT: {
			System.out.println("Receiving turn: Wait");
			state = Turn.TURN_WAIT;
			runStateMachine();
			break;
		}
		case Turn.TURN_SHOOT: {
			System.out.println("Receiving turn: Shoot");
			nativeActions.dismissProgressDialog();
			dialogOpen=false;
			float x = turn.x;
			float y = turn.y;
			int weapon = turn.weapon;
			controller.calculateDamageTaken(new Vector3(x, y, 0), weapon);
			state = Turn.TURN_START;

			break;
		}
		case Turn.TURN_RESULT: {
			System.out.println("Receiving turn: Result -- Total hits "
					+ turn.hits.size());

			for (Vector2 v : turn.hits){
				Vector2 scaledHit = v;
				scaledHit.div(2);
				new HitMarkerObject(new HitMarkerController(scaledHit.x, scaledHit.y, 0.25f,
						0.25f), new HitMarkerRenderer());
			}
			state = Turn.TURN_WAIT;
			runStateMachine();

			break;
		}
		default: {
			System.out.println("Uknown command");
		}

		}
	}

	public static void sendReady(Turn t) {
		System.out.println("Sending turn ready");
		nativeConnector.sendReady(t);
		state = t.type;
		runStateMachine();
	}

	public static void sendShoot(Turn t) {
		System.out.println("Sending turn shoot");
		nativeConnector.sendShoot(t);
		state = t.type;
		runStateMachine();
	}

	public static void sendResult(Turn t) {

		nativeConnector.sendResult(t);

		runStateMachine();
	}

	public static void lockShipView() {
	
	}

	public static void lockMapView() {
		
	}
	
	public static void opponentLeft() {
		nativeActions.createConfirmDialog(
				"Opponent has left the game", 
				"Leave game or wait for reconnect?", 
				"Leave game",
				"Wait",
				new ConfirmListener() {			
					@Override
					public void yes() {
						if(dialogOpen)
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

}
