package com.sohvastudios.battleships.game.core;




import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.sohvastudios.battleships.game.gamelogic.LoadingScreen;
import com.sohvastudios.battleships.game.gamelogic.PlayScreen;
import com.sohvastudios.battleships.game.utilities.AssetStorage;


public class Main extends Game{
	
	public NativeActions nativeActions;
	public ConnectionHandler connectionHandler;
	
	
	public Main(NativeActions nativeActions){
		this.nativeActions = nativeActions;

		new AssetStorage();
		//c.connectGame(this);
		
	}
	@Override
	public void create() {		
		setScreen(new LoadingScreen(this));
	}
	
	public void loadGame(ConnectionHandler connectionHandler) {
		this.connectionHandler = connectionHandler;
	}
	
	public void changeScreen(Screen s){
		setScreen(s);
	}
	public void startGame(){
		setScreen(new PlayScreen(connectionHandler, nativeActions));
	}
}


	
	
