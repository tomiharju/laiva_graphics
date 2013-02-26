package ObjectControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ShipController extends ObjectController {
	
	

	
	public ShipController(float x, float y){
		WorldController.controllers.add(this);
		SeaController.shipControllers.add(this);
		
	
		position = new Vector2(x,y);
		hidePosition = new Vector2();
		visiblePosition = new Vector2();
	}

	@Override
	public void handleInputUp(float x,float y) {
		
		
	}

	@Override
	public void handleInputDrag(float x, float y) {
		boolean legalmove=true;
		for(ShipController sc:SeaController.shipControllers){
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
		hidePosition.set(position.x+Gdx.graphics.getWidth(), position.y);
		position.set(hidePosition);
	}
	public void show(){
		visiblePosition.set(hidePosition.x-Gdx.graphics.getWidth(), hidePosition.y);
		position.set(visiblePosition);
		System.out.println("Showing "+position.x + " "+position.y); 
	}

	@Override
	public void handleInputDown(float x, float y) {
		// TODO Auto-generated method stub
		
	}
	
	

}
