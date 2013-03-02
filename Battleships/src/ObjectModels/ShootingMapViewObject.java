package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ObjectControllers.ShootingMapView;
import ObjectControllers.ObjectController;
import ObjectControllers.ShipController;
import ObjectRenderers.MapRenderer;
import ObjectRenderers.ObjectRenderer;

public class ShootingMapViewObject extends ModelObject{

	
	
	public ShootingMapViewObject(ShootingMapView controller, MapRenderer renderer){
		setController(controller);
		setRenderer(renderer);
	bounds = new Rectangle(0,(float) (Gdx.graphics.getHeight()*0.2),Gdx.graphics.getWidth(),(float) (Gdx.graphics.getHeight()*0.6));
		
		position = new Vector2(controller.pollPosition());
		
		sprite 	=  new Sprite(new Texture(Gdx.files.internal("data/radarTextureRippled.png")));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(0,bounds.getY());
		
		WorldObject.objects.add(this);
	}
	@Override
	public void update() {
		position.lerp(controller.pollPosition(),0.5f);
		bounds.x=position.x;
		bounds.y=position.y;
		
		sprite.setPosition(position.x, position.y);
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
