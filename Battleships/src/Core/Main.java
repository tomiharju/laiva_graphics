package Core;


import GameLogic.LoadingScreen;
import GameLogic.PlayScreen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


public class Main extends Game{
	
	public NativeFunctions nativeConnector;
	
	
	public Main(NativeFunctions c){
		this.nativeConnector=c;
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


	
	
