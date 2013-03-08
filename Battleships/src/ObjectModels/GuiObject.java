package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ObjectControllers.GuiController;
import ObjectControllers.ObjectController;
import ObjectRenderers.GuiRenderer;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.ShipRenderer;

public class GuiObject extends ModelObject {

	
	public GuiObject(ObjectController controller,ObjectRenderer renderer,String file){
		setController(controller);
		setRenderer(renderer);
		
		position 		= new Vector3(controller.pollPosition());
		bounds 			= new Rectangle(controller.pollBounds());
		sprite 			= new Sprite(new Texture(Gdx.files.internal("data/"+file)));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x,position.y);
		
		WorldObject.objects.add(this);
	}
	@Override
	public void update() {
		position.lerp(controller.pollPosition(),0.5f);
		bounds.x=position.x-bounds.width/2;
		bounds.y=position.y-bounds.height/2;
		sprite.setPosition(position.x-sprite.getWidth()/2, position.y-sprite.getHeight()/2);
		
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller=(GuiController)controller;
		this.controller.setObject(this);
		
	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=(GuiRenderer)renderer;
		this.renderer.setObject(this);
		
	}

}
