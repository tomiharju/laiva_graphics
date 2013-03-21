package ObjectControllers;

import Commands.Command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GuiController extends ObjectController {

	private Command command;
	Rectangle map_bounds;
	Rectangle clear_bounds;
	
	public GuiController(float x, float y,float w, float h,Command command){
		super(x,y,w,h);
		this.command=command;
		map_bounds=new Rectangle(0,2.5f,10,10);		
		clear_bounds=new Rectangle();
	}
	
	public void executeCommand(){
		if(command!=null)
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
		boolean legalmove=true;
		clear_bounds.set(pos.x-bounds.width/2,pos.y-bounds.height/2,bounds.width,bounds.height);
		if(!map_bounds.contains(clear_bounds))
			legalmove=false;
		if(legalmove){
			setPosition(pos);
		}
	
		
	}

}
