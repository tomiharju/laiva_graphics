package com.sohvastudios.battleships.game.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;


public interface ConnectionHandler {
	public void setLogicHandler(GameLogicHandler h);
	public void sendReady();
	public void sendShoot(float x,float y, int weapon);
	public void sendResult(HashMap<ArrayList<Vector3>,ArrayList<Vector3>> result);
	public void connect();
	public void disconnect();
	public void leave();
	public void matchMake();
	public void join(CharSequence room);
}
