package GameLogic;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import Core.NativeFunctions;
import ObjectControllers.WorldController;
import ObjectModels.WorldObject;

public class GameLogicHandler extends Thread {

	


	private static WorldObject world;
	private static WorldController controller;
	private NativeFunctions nativeConnector;

	public static int state;
	public static boolean shipViewLocked;
	public static boolean mapViewLocked;
	public static boolean fireButtonLocked;
	
	
	public GameLogicHandler(WorldController c, WorldObject w,
			NativeFunctions con) {
		this.nativeConnector = con;
		this.world = w;
		this.controller = c;
		
		state = Turn.TURN_BEGINNING;
		runStateMachine();

	}

	public static void runStateMachine() {
		System.out.println(state);
		switch (state) {
		// Ship placement phase
		case Turn.TURN_BEGINNING: { 	//Lock to ship view while placing ships.
			lockShipView();
			break;
		}
		
		case Turn.TURN_START: {			//Lock players view to map and allow firing.
			lockMapView();
			fireButtonLocked=false;
			break;
		}
		case Turn.TURN_WAIT: {			//If opponent starts the game, allow player to freely change views but lock fire button.
			shipViewLocked=false;
			mapViewLocked=false;
			fireButtonLocked=true;
			break;
		}
		case Turn.TURN_SHOOT:{			//After shooting, lock fire button to avoid spamming
			fireButtonLocked=true;
			break;
		}
		case Turn.TURN_READY:{			//Once you have finished placing ships, lock view and wait for other player
			shipViewLocked=true;
			mapViewLocked=true;
		}
		case Turn.TURN_RESULT:{			//Send damage result to opponent and start a new turn.
			state=Turn.TURN_START;
			runStateMachine();
		}
		}

	}

	public static void receiveTurn(Turn turn) {

		switch (turn.type) {
		case Turn.TURN_START: {
			state=Turn.TURN_START;
			runStateMachine();
			break;
		}
		case Turn.TURN_WAIT: {
			state=Turn.TURN_WAIT;
			runStateMachine();
			break;
		}
		case Turn.TURN_SHOOT:{
			lockShipView();
			float x = turn.x;
			float y = turn.y;
			int weapon = turn.weapon;
			//Controller.drawExplosion()
			//Controller.calculateDamagetaken();
			break;
		}
		case Turn.TURN_RESULT: {
			state=Turn.TURN_WAIT;
			runStateMachine();
			//World.getRenderer.addHitMarker();
			// Draw hits on opponent to radar
			break;
		}
		default:{
			System.out.println("Uknown command");
		}

		}
	}

	public static void sendTurn(Turn turn) {
		// nativeConnector.sendTurn(turn);
		state = turn.type;
		runStateMachine();
	}

	public static void sendAttackCoordinates(Vector3 point, int weapontype) {
		controller.calculateDamageTaken(point, weapontype);
		// nativeConnector.sendAttackAction(pos.x,pos.y, weapontype);
	}



	public static void lockShipView() {
		controller.lockToShipView();
		mapViewLocked=true;
	}

	public static void lockMapView() {
		controller.lockToMapView();
		shipViewLocked=true;
	}



}
