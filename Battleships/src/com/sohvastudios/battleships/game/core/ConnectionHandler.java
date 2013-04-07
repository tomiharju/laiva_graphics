package com.sohvastudios.battleships.game.core;

import com.sohvastudios.battleships.game.gamelogic.GameLogicHandler;
import com.sohvastudios.battleships.game.utilities.Turn;


public interface ConnectionHandler {
	public void setLogicHandler(GameLogicHandler h);
	public void sendReady(Turn t);
	public void sendShoot(Turn t);
	public void sendResult(Turn t);
	public void connect();
	public void disconnect();
	public void leave();
	public void matchMake();
	public void join(CharSequence room);

	
	
	

}
