package ObjectControllers;

import Screens.GameLogicHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



public class WeaponController extends ObjectController{

	
	public WeaponController(float x, float y){
		ShootingMapView.weaponControllers.add(this);
		
		position = new Vector2(x,y);
		hidePosition = new Vector2(position.x+Gdx.graphics.getWidth(),position.y); 
		visiblePosition = new Vector2(position);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Vector2 getRelativePosition(){
		Vector2 relPos = new Vector2();
		relPos.set(position.x, (float) (position.y-Gdx.graphics.getHeight()*0.2));
		return relPos;
		
	}
	
	@Override
	public void hide() {
		hidePosition.set(position.x+Gdx.graphics.getWidth(), position.y);
		visiblePosition.set(position);
		position.set(hidePosition);
		System.out.println("Im a weapon my location is "+position.toString());
	}

	@Override
	public void show() {
		position.set(visiblePosition);
		System.out.println("Im a weapon my location is "+position.toString());
		
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
		boolean legalmove=true;
			Rectangle map_bounds =  new Rectangle(0,(float) (Gdx.graphics.getHeight()*0.2),Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.6));
			Rectangle clear_bounds = new Rectangle(x-object.getBounds().getWidth()/2,y-object.getBounds().getHeight()/2,object.getBounds().getWidth(),object.getBounds().getHeight());	
			if(!map_bounds.contains(clear_bounds))
					legalmove=false;
			
				
		
		
		if(legalmove){
			setPosition(new Vector2(x,y));
			
		}
		
		
	}

}
