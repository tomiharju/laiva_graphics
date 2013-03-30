package Core;

import GameLogic.GameLogicHandler;
import GameLogic.Turn;

public interface NativeFunctions {
	public void setLogicHandler(GameLogicHandler h);
	public void sendTurn(Turn t);
		
	

}
