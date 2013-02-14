package Core;


import Screens.PlayScreen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


public class Main extends Game{
	
	
	@Override
	
	public void create() {		
		setScreen(new PlayScreen());
		
	}
	public void changeScreen(Screen s){
		setScreen(s);
	}
}


	
	
