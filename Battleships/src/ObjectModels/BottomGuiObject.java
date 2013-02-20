package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ObjectControllers.BotGuiController;
import ObjectControllers.ObjectController;
import ObjectControllers.SeaController;
import ObjectRenderers.BottomGuiRenderer;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.SeaRenderer;

public class BottomGuiObject extends ModelObject{

	
	
	public BottomGuiObject(BotGuiController controller, BottomGuiRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		
		sprite 	=  new Sprite(new Texture(Gdx.files.internal("data/BarTexture.jpg")));
		sprite.setSize(Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.2));
		sprite.setPosition(0, (float) (0));
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
