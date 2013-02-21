package ObjectModels;

import ObjectControllers.ObjectController;
import ObjectControllers.SeaController;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.SeaRenderer;
import ObjectRenderers.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SeaObject extends ModelObject {

	

	
	public SeaObject(SeaController controller, SeaRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		bounds = new Rectangle(0,(float) (Gdx.graphics.getHeight()*0.2),Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.6));
		sprite 	=  new Sprite(new Texture(Gdx.files.internal("data/waterTexture.jpg")));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(0,bounds.getY());
		WorldObject.objects.add(this);
		
	}
	
	
	@Override
	public void update() {
		
	}


	@Override
	public void setController(ObjectController controller) {
		this.controller=controller;
		this.controller.setObject(this);
		
		
	}


	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=renderer;
		this.renderer.setObject(this);
		
	}


	
	
	


	
}
