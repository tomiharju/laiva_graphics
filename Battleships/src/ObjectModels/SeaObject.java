package ObjectModels;

import ObjectControllers.ObjectController;
import ObjectControllers.SeaController;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.SeaRenderer;
import ObjectRenderers.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SeaObject extends ModelObject {

	

	
	public SeaObject(SeaController controller, SeaRenderer renderer){
		setController(controller);
		setRenderer(renderer);

		sprite 	=  new Sprite(new Texture(Gdx.files.internal("data/seaTile.jpg")));
		sprite.setSize(Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.6));
		sprite.setPosition(0, (float) (Gdx.graphics.getHeight()*0.2));
		WorldObject.objects.add(this);
		
	}
	
	
	@Override
	public void update() {
		((SeaController)controller).ImaSeaController();
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
