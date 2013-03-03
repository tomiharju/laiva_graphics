package ObjectControllers;

import Commands.Command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GuiController extends ObjectController {

	private Command command;
	public GuiController(float x, float y,Command command){
		this.command=command;
		position = new Vector2(x,y);
		hidePosition = new Vector2(position.x+Gdx.graphics.getWidth(),position.y); 
		visiblePosition = new Vector2(position);
			
		
	}
	
	public void executeCommand(){
		command.execute();
	}
	
	
	@Override
	public void hide() {
		hidePosition.set(position.x+Gdx.graphics.getWidth(), position.y);
		visiblePosition.set(position);
		position.set(hidePosition);
		
	}

	@Override
	public void show() {
		position.set(visiblePosition);
		
	}

	@Override
	public void handleInputDown(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInputUp(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInputDrag(float x, float y) {
		// TODO Auto-generated method stub
		
	}

}
