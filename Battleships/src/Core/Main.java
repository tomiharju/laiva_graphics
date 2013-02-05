package Core;

import Screens.GameScreen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


public class Main extends Game{
	
	
	@Override
	
	public void create() {		
		setScreen(new GameScreen());
	}
	public void changeScreen(Screen s){
		setScreen(s);
	}
}


	
	
