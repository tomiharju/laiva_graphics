package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import Core.AssetStorage;
import ObjectControllers.ShootingMapView;
import ObjectControllers.ObjectController;
import ObjectControllers.ShipController;
import ObjectRenderers.MapRenderer;
import ObjectRenderers.ObjectRenderer;

public class ShootingMapViewObject extends ModelObject{

	
	
	public ShootingMapViewObject(ShootingMapView controller, MapRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		position 		= controller.pollPosition();
		bounds 			= controller.pollBounds();
		
		
		sprite 			= new Sprite(AssetStorage.manager.get("data/radarTexture.png",Texture.class));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x-bounds.width/2, position.y-bounds.height/2);
	
		WorldObject.objects.add(this);
		renderer.addGraphics(sprite);
	}
	@Override
	public void update() {
		
	
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller=(ShootingMapView)controller;
		this.controller.setObject(this);
		
	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=(MapRenderer)renderer;
		this.renderer.setObject(this);
		
	}

}
