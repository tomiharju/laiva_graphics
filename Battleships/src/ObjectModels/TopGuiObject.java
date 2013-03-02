package ObjectModels;

import ObjectControllers.ObjectController;
import ObjectControllers.ShipController;
import ObjectControllers.TopGuiController;
import ObjectModels.ShipObject.ShipType;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.ShipRenderer;
import ObjectRenderers.TopGuiRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TopGuiObject extends ModelObject{
	
	
	

	
	public TopGuiObject(TopGuiController controller,TopGuiRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		position = new Vector2(controller.pollPosition());
		bounds = new Rectangle(position.x,(float) (Gdx.graphics.getHeight()*0.8),Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.2));
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
		this.controller=(TopGuiController)controller;
		this.controller.setObject(this);
		
	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=(TopGuiRenderer)renderer;
		this.renderer.setObject(this);
		
	}
	
	
}
