package com.sohvastudios.battleships.game.interfaces;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public interface LogicHandler {


	public void runStateMachine();
	public void receiveShoot(float x,float y, int weapon);
	public void receiveStart();
	public void receiveWait();
	public void receiveResult(ArrayList<Vector2> result);
	public void sendReady();
	public void sendShoot(float x,float y, int weapon);
	public void sendResult(ArrayList<Vector2> result);
	public void opponentLeft(); 
		

}