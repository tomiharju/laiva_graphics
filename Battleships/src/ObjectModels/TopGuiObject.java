package ObjectModels;

import ObjectControllers.ObjectController;
import ObjectControllers.TopGuiController;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.TopGuiRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TopGuiObject extends ModelObject{

	
	public TopGuiObject(TopGuiController controller,TopGuiRenderer renderer){
		setController(controller);
		setRenderer(renderer);

		sprite 	=  new Sprite(new Texture(Gdx.files.internal("data/mapTile.jpg")));
		sprite.setSize(Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.2));
		sprite.setPosition(0, (float) (Gdx.graphics.getHeight()*0.8));
		WorldObject.objects.add(this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
