package Core;

import Utilities.Turn;
import GameLogic.GameLogicHandler;

public interface NativeFunctions {
	public void setLogicHandler(GameLogicHandler h);
	public void sendReady(Turn t);
	public void sendShoot(Turn t);
	public void sendResult(Turn t);
	public void connect();
	public void disconnect();
	

}
