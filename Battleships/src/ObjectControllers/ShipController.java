package ObjectControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ShipController extends ObjectController {
	
	Rectangle clear_bounds;
	Rectangle sea_bounds;

	
	public ShipController(float x, float y,float w, float h){
		super(x,y,w,h);
		ShipPlacementView.shipControllers.add(this);
		clear_bounds=new Rectangle();
		sea_bounds=new Rectangle(0,2.5f,10,10);
		
	
	}

	public Vector2 getRelativePosition(){
		Vector2 relPos 	= new Vector2();
		relPos.set(position.x, (float) (position.y-Gdx.graphics.getHeight()*0.2));
		return relPos;
		
	}
	@Override
	public void handleInputUp(Vector3 pos) {
		boolean legalmove=true;
		for(ShipController sc:ShipPlacementView.shipControllers){
			if(!sc.equals(this)){
				clear_bounds = new Rectangle(pos.x-object.getBounds().getWidth()/2,pos.y-object.getBounds().getHeight()/2,object.getBounds().getWidth(),object.getBounds().getHeight());
				sea_bounds =  new Rectangle(0,(float) (Gdx.graphics.getHeight()*0.2),Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.6));
				if(sc.getObject().getBounds().overlaps(clear_bounds) || !sea_bounds.contains(clear_bounds))
					legalmove=false;
			}
				
		}
		
		if(legalmove){
			setPosition(new Vector3(pos));
		}
		
	}

	@Override
	public void handleInputDrag(Vector3 pos) {
		boolean legalmove=true;
		for(ShipController sc:ShipPlacementView.shipControllers){
			if(!sc.equals(this)){
				clear_bounds.set(pos.x-object.getBounds().getWidth()/2,pos.y-object.getBounds().getHeight()/2,object.getBounds().getWidth(),object.getBounds().getHeight());
				if(sc.getObject().getBounds().overlaps(clear_bounds) || !sea_bounds.contains(clear_bounds))
					legalmove=false;
				
			}
				
		}
		
		if(legalmove){
			System.out.println("legal move, moving");
			setPosition(new Vector3(pos));
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
	public void handleInputDown(Vector3 pos) {
		// TODO Auto-generated method stub
		
	}
	
	

}
