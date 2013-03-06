package ObjectControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ShipController extends ObjectController {
	
	

	
	public ShipController(float x, float y){
		ShipPlacementView.shipControllers.add(this);
		
		position 		= new Vector2(x,y);
		hidePosition 	= new Vector2(Gdx.graphics.getWidth(),position.y); 
		visiblePosition = new Vector2(position);
	}

	public Vector2 getRelativePosition(){
		Vector2 relPos 	= new Vector2();
		relPos.set(position.x, (float) (position.y-Gdx.graphics.getHeight()*0.2));
		return relPos;
		
	}
	@Override
	public void handleInputUp(float x,float y) {
		boolean legalmove=true;
		for(ShipController sc:ShipPlacementView.shipControllers){
			if(!sc.equals(this)){
				Rectangle clear_bounds = new Rectangle(x-object.getBounds().getWidth()/2,y-object.getBounds().getHeight()/2,object.getBounds().getWidth(),object.getBounds().getHeight());
				Rectangle sea_bounds =  new Rectangle(0,(float) (Gdx.graphics.getHeight()*0.2),Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.6));
				if(sc.getObject().getBounds().overlaps(clear_bounds) || !sea_bounds.contains(clear_bounds))
					legalmove=false;
			}
				
		}
		
		if(legalmove){
			setPosition(new Vector2(x,y));
		}
		
	}

	@Override
	public void handleInputDrag(float x, float y) {
		boolean legalmove=true;
		for(ShipController sc:ShipPlacementView.shipControllers){
			if(!sc.equals(this)){
				Rectangle clear_bounds = new Rectangle(x-object.getBounds().getWidth()/2,y-object.getBounds().getHeight()/2,object.getBounds().getWidth(),object.getBounds().getHeight());
				Rectangle sea_bounds =  new Rectangle(0,(float) (Gdx.graphics.getHeight()*0.2),Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.6));
				if(sc.getObject().getBounds().overlaps(clear_bounds) || !sea_bounds.contains(clear_bounds))
					legalmove=false;
			}
				
		}
		
		if(legalmove){
			setPosition(new Vector2(x,y));
		}
		
		
	}
	
	
	public void hide(){
		visiblePosition.set(position);
		position.add(hidePosition);
	}
	public void show(){
		position.set(visiblePosition);
	}

	@Override
	public void handleInputDown(float x, float y) {
		// TODO Auto-generated method stub
		
	}
	
	

}
