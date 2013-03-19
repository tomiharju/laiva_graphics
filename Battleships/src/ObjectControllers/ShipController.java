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
		
	}

	@Override
	public void handleInputDrag(Vector3 pos) {
		boolean legalmove=true;
		for(ShipController sc:ShipPlacementView.shipControllers){
			if(!sc.equals(this)){
				clear_bounds.set(pos.x-bounds.width/2,pos.y-bounds.height/2,bounds.width,bounds.height);
				if(sc.pollBounds().overlaps(clear_bounds) || !sea_bounds.contains(clear_bounds))
					legalmove=false;
				
			}
				
		}
		
		if(legalmove){
			setPosition(pos);
		}
		
		
	}
	
	
	public void hide(){
		object.setHidden();
	}
	public void show(){
		object.setVisible();
	}

	@Override
	public void handleInputDown(Vector3 pos) {
		// TODO Auto-generated method stub
		
	}
	
	

}
