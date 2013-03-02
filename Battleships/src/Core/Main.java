package Core;


import Screens.PlayScreen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


public class Main extends Game{
	
	public NativeFunctions nativeConnector;
	
	
	public Main(NativeFunctions c){
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


	
	
