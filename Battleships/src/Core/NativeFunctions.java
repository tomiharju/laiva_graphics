package Core;

import GameLogic.GameLogicHandler;
import GameLogic.Turn;

public interface NativeFunctions {
	public void setLogicHandler(GameLogicHandler h);
	public void sendReady(Turn t);
	public void sendShoot(Turn t);
	public void sendResult(Turn t);
	public void connect();
	public void disconnect();
	

}
