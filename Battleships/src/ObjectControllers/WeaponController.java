package ObjectControllers;

import Screens.GameLogicHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;



public class WeaponController extends ObjectController{
	
	Rectangle clear_bounds;
	Rectangle map_bounds;
	
	public WeaponController(float x, float y,float w, float h){
		super(x,y,w,h);
		ShootingMapView.weaponControllers.add(this);
		clear_bounds=new Rectangle();
		map_bounds=new Rectangle(0,2.5f,10,10);
	
		
	}
	
	
	public Vector2 getRelativePosition(){
		Vector2 relPos = new Vector2();
		relPos.set(position.x, (float) (position.y-Gdx.graphics.getHeight()*0.2));
		return relPos;
		
	}
	
	@Override
	public void hide() {
		visiblePosition.set(position);
		position.add(hidePosition);
		
	}

	@Override
	public void show() {
		position.set(visiblePosition);
	
		
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
			
			clear_bounds.set(pos.x-object.getBounds().getWidth()/2,pos.y-object.getBounds().getHeight()/2,object.getBounds().getWidth(),object.getBounds().getHeight());	
			if(!map_bounds.contains(clear_bounds))
					legalmove=false;
			
				
		
		
		if(legalmove){
			setPosition(new Vector3(pos));
			
		}
		
		
	}

}
