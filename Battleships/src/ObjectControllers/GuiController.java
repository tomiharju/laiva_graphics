package ObjectControllers;

import Commands.Command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GuiController extends ObjectController {

	private Command command;
	public GuiController(float x, float y,float w, float h,Command command){
		super(x,y,w,h);
		this.command=command;
				
		
	}
	
	public void executeCommand(){
		command.execute();
	}
	
	
	@Override
	public void hide() {
		object.setHidden();
	}

	@Override
	public void show() {
		object.setVisible();
	}

	@Override
	public void handleInputDown(Vector3 pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInputUp(Vector3 pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInputDrag(Vector3 pos) {
		// TODO Auto-generated method stub
		
	}

}
