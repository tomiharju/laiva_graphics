package Core;


import Screens.PlayScreen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


public class Main extends Game{
	
	public NativeConnector nativeConnector;
	
	
	public Main(NativeConnector c){
		this.nativeConnector=c;
	}
	@Override
	public void create() {		
		setScreen(new PlayScreen(nativeConnector));
		
	}
	public void changeScreen(Screen s){
		setScreen(s);
	}
}


	
	
