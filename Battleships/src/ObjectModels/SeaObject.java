package ObjectModels;

import ObjectControllers.ObjectController;
import ObjectControllers.SeaController;
import ObjectControllers.ShipController;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.SeaRenderer;
import ObjectRenderers.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SeaObject extends ModelObject {

	

	
	public SeaObject(SeaController controller, SeaRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		bounds = new Rectangle(0,(float) (Gdx.graphics.getHeight()*0.2),Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.6));
		
		position = new Vector2(bounds.x+bounds.getWidth()/2,bounds.y+bounds.getHeight()/2);
		
		sprite 	=  new Sprite(new Texture(Gdx.files.internal("data/waterTexture.jpg")));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(0,bounds.getY());
		
		WorldObject.objects.add(this);
		
	}
	
	
	@Override
	public void update() {
		position.lerp(controller.pollPosition(),0.1f);
		bounds.x=position.x-bounds.width/2;
		bounds.y=position.y-bounds.height/2;
		
		sprite.setPosition(position.x-sprite.getWidth()/2, position.y-sprite.getHeight()/2);
		
	}


	@Override
	public void setController(ObjectController controller) {
		this.controller=(SeaController)controller;
		this.controller.setObject(this);
		
		
	}


	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=(SeaRenderer)renderer;
		this.renderer.setObject(this);
		
	}
	


	
	
	


	
}
