package ObjectModels;

import ObjectControllers.ObjectController;
import ObjectControllers.ShipPlacementView;
import ObjectControllers.ShipController;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.SeaRenderer;
import ObjectRenderers.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ShipPlacementViewObject extends ModelObject {

	

	
	public ShipPlacementViewObject(ShipPlacementView controller, SeaRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		bounds = new Rectangle(0,(float) (Gdx.graphics.getHeight()*0.2),Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.6));
		
		position = new Vector2(controller.pollPosition());
		
		sprite 	=  new Sprite(new Texture(Gdx.files.internal("data/waterTexture.jpg")));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(0,bounds.getY());
		
		WorldObject.objects.add(this);
		
	}
	
	
	@Override
	public void update() {
		position.lerp(controller.pollPosition(),0.5f);
		bounds.x=position.x;
		bounds.y=position.y;
		
		sprite.setPosition(position.x, position.y);
		
	}


	@Override
	public void setController(ObjectController controller) {
		this.controller=(ShipPlacementView)controller;
		this.controller.setObject(this);
		
		
	}


	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=(SeaRenderer)renderer;
		this.renderer.setObject(this);
		
	}
	


	
	
	


	
}
