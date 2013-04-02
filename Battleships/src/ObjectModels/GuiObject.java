package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import Core.AssetStorage;
import ObjectControllers.GuiController;
import ObjectControllers.ObjectController;
import ObjectRenderers.GuiRenderer;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.ShipRenderer;

public class GuiObject extends ModelObject {

	
	public GuiObject(ObjectController controller,ObjectRenderer renderer,String file){
		setController(controller);
		setRenderer(renderer);
		
		position 		= controller.pollPosition();
		bounds 			= controller.pollBounds();
		sprite 			= new Sprite(AssetStorage.manager.get("data/guiobjects/"+file,Texture.class));
		
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x-bounds.width/2, position.y-bounds.height/2);
	
		WorldObject.objects.add(this);
		this.renderer.addGraphics(sprite);
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


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
