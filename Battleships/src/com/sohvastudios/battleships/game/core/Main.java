package com.sohvastudios.battleships.game.core;




import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.sohvastudios.battleships.game.gamelogic.LoadingScreen;
import com.sohvastudios.battleships.game.gamelogic.PlayScreen;
import com.sohvastudios.battleships.game.utilities.AssetStorage;


public class Main extends Game{
	
<<<<<<< HEAD
	public NativeActions nativeConnector;
	
	
	public Main(NativeActions nativeActions){
		this.nativeConnector=nativeActions;
=======
	public ConnectionHandler nativeConnector;
	public NativeActions nativeActions;
	
	
	public Main(NativeActions a){
		this.nativeActions = a;
>>>>>>> Changes reflecting core project.
		new AssetStorage();
		//c.connectGame(this);
		
	}
	@Override
	public void create() {		
		setScreen(new LoadingScreen(this));
	}
	
	public void loadGame(ConnectionHandler connectionHandler) {
		nativeConnector = connectionHandler;
	}
	
	public void changeScreen(Screen s){
		setScreen(s);
	}
	public void startGame(){
		setScreen(new PlayScreen(nativeConnector, nativeActions));
	}
}


	
	
