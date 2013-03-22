package ObjectControllers;

import GameLogic.GameLogicHandler;

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
		map_bounds=new Rectangle(0,2.5f,10,10);
		clear_bounds = new Rectangle();
		
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
		clear_bounds.set(pos.x-bounds.width/2,pos.y-bounds.height/2,bounds.height,bounds.width);
		if(!map_bounds.contains(clear_bounds))
			legalmove=false;
		
		if(legalmove)
			setPosition(pos);
	
		
		
	}

}
