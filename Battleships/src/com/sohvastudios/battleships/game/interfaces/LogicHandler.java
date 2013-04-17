package com.sohvastudios.battleships.game.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Vector3;

public interface LogicHandler {


	public void runStateMachine();
	public void receiveShoot(float x,float y, int weapon);
	public void receiveStart();
	public void receiveWait();
	public void receiveResult(HashMap<ArrayList<Vector3>,ArrayList<Vector3>> result);
	public void sendReady();
	public void sendShoot(float x,float y, int weapon);
	public void sendResult(HashMap<ArrayList<Vector3>,ArrayList<Vector3>> result);
	public void opponentLeft();

	
		

}