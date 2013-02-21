package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

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
		bounds = new Rectangle(0,0,Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.2));
		sprite 	=  new Sprite(new Texture(Gdx.files.internal("data/BarTexture.jpg")));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(bounds.getX(),bounds.getY());
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
