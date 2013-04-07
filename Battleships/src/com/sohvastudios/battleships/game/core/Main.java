package com.sohvastudios.battleships.game.core;




import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.sohvastudios.battleships.game.gamelogic.LoadingScreen;
import com.sohvastudios.battleships.game.gamelogic.PlayScreen;
import com.sohvastudios.battleships.game.utilities.AssetStorage;


public class Main extends Game{
	
	public NativeActions nativeConnector;
	
	
	public Main(NativeActions nativeActions){
		this.nativeConnector=nativeActions;
		new AssetStorage();
		//c.connectGame(this);
		
	}
	@Override
	public void create() {		
		setScreen(new LoadingScreen(this));
		
	}
	public void changeScreen(Screen s){
		setScreen(s);
	}
	public void startGame(){
		setScreen(new PlayScreen(nativeConnector));
	}
}


	
	
