package com.sohvastudios.battleships.game.interfaces;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;


public interface ConnectionHandler {
	public void setLogicHandler(GameLogicHandler h);
	public void sendReady();
	public void sendShoot(float x,float y, int weapon);
	public void sendResult(ArrayList<Vector2> result);
	public void connect();
	public void disconnect();
	public void leave();
	public void matchMake();
	public void join(CharSequence room);
}
