package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ObjectControllers.HitMarkerController;
import ObjectControllers.ObjectController;
import ObjectRenderers.HitMarkerRenderer;
import ObjectRenderers.ObjectRenderer;
import Utilities.AssetStorage;

public class HitMarkerObject extends ModelObject{

	public float alpha;
	
	public HitMarkerObject(ObjectController controller, ObjectRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		position 		= controller.pollPosition();
		bounds 			= controller.pollBounds();
		
		sprite 			= new Sprite(AssetStorage.manager.get("data/hitmarker.png",Texture.class));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x-bounds.width/2, position.y-bounds.height/2);
		WorldObject.objects.add(this);
		this.renderer.addGraphics(sprite);
		
		alpha = 1f; //Value used to render with increasing transparency
		visible=true;
	}
	@Override
	public void update() {
		alpha-=Gdx.graphics.getDeltaTime()/100;
		if(alpha<0)
			alpha=1;
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller=(HitMarkerController)controller;
		this.controller.setObject(this);
		
	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=(HitMarkerRenderer)renderer;
		this.renderer.setObject(this);
		
	}

}
