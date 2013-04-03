package GameLogic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import Core.NativeFunctions;
import ObjectControllers.HitMarkerController;
import ObjectControllers.WorldController;
import ObjectModels.HitMarkerObject;
import ObjectModels.WorldObject;
import ObjectRenderers.HitMarkerRenderer;
import Utilities.Turn;

public class GameLogicHandler extends Thread {

	


	private static WorldObject world;
	private static WorldController controller;
	private static NativeFunctions nativeConnector;

	public static int state;
	public static boolean shipViewLocked;
	public static boolean mapViewLocked;
	public static boolean fireButtonLocked;
	
	
	public GameLogicHandler(WorldController c, WorldObject w,
			NativeFunctions con) {
		this.nativeConnector = con;
		this.world = w;
		this.controller = c;
		nativeConnector.setLogicHandler(this);
		nativeConnector.connect();
		state = Turn.TURN_BEGINNING;
		runStateMachine();

	}

	public static void runStateMachine() {
	
		switch (state) {
		// Ship placement phase
		case Turn.TURN_BEGINNING: { 	//Lock to ship view while placing ships.
			lockShipView();
			mapViewLocked=true;
			System.out.println("Current state: Beginning");
			break;
		}
		
		case Turn.TURN_START: {			//Lock players view to map and allow firing.
			lockMapView();
			shipViewLocked=true;
			fireButtonLocked=false;
			System.out.println("Current state: Start");
			break;
		}
		case Turn.TURN_WAIT: {			//If opponent starts the game, allow player to freely change views but lock fire button.
			shipViewLocked=false;
			mapViewLocked=false;
			fireButtonLocked=true;
			System.out.println("Current state: Wait");
			break;
		}
		case Turn.TURN_SHOOT:{			//After shooting, lock fire button to avoid spamming
			fireButtonLocked=true;
			System.out.println("Current state: Shoot");
			break;
		}
		case Turn.TURN_READY:{			//Once you have finished placing ships, lock view and wait for other player
			shipViewLocked=true;
			mapViewLocked=true;
			System.out.println("Current state: Ready");
			break;
		}
		case Turn.TURN_RESULT:{			//Send damage result to opponent and start a new turn.
			state=Turn.TURN_WAIT;
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
			state=Turn.TURN_START;
			runStateMachine();
			break;
		}
		case Turn.TURN_WAIT: {
			System.out.println("Receiving turn: Wait");
			state=Turn.TURN_WAIT;
			runStateMachine();
			break;
		}
		case Turn.TURN_SHOOT:{
			System.out.println("Receiving turn: Shoot");
			lockShipView();
			float x = turn.x;
			float y = turn.y;
			int weapon = turn.weapon;
			controller.calculateDamageTaken(new Vector3(x,y,0),weapon);
			state=turn.TURN_START;
			
			break;
		}
		case Turn.TURN_RESULT: {
			System.out.println("Receiving turn: Result -- Total hits "+turn.hits.size());
			
			for(Vector2 v : turn.hits)
				new HitMarkerObject(new HitMarkerController(v.x,v.y,0.5f,0.5f),new HitMarkerRenderer());
			state=Turn.TURN_WAIT;
			runStateMachine();
			
			break;
		}
		default:{
			System.out.println("Uknown command");
		}

		}
	}

	public static void sendReady(Turn t) {
		nativeConnector.sendReady(t);
		state = t.type;
		runStateMachine();
	}
	public static void sendShoot(Turn t){
		nativeConnector.sendShoot(t);
		state = t.type;
		runStateMachine();
	}
	public static void sendResult(Turn t){
		nativeConnector.sendResult(t);
		state = t.type;
		runStateMachine();
	}

	


	public static void lockShipView() {
		controller.lockToShipView();
		}

	public static void lockMapView() {
		controller.lockToMapView();
	}
	public static void disconnect(){
		nativeConnector.disconnect();
	}



}
