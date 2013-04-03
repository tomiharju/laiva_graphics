package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import ObjectControllers.ObjectController;
import ObjectControllers.ProjectileController;
import ObjectControllers.ShipPlacementView;
import ObjectControllers.ShootingMapView;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.ProjectileRenderer;
import ObjectRenderers.WeaponRenderer;
import Utilities.AssetStorage;

public class ProjectileObject extends ModelObject{

	private Vector3 target;
	private float speed;
	
	public ProjectileObject(ObjectController controller, ObjectRenderer renderer,int weaponType){
		setController(controller);
		setRenderer(renderer);
		position 		= controller.pollPosition();
		bounds 			= controller.pollBounds();
		
		sprite 			= new Sprite(AssetStorage.manager.get("data/weapons/w"+weaponType+".png",Texture.class));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x-bounds.width/2, position.y-bounds.height/2);
		
		WorldObject.objects.add(this);
		this.renderer.addGraphics(sprite);
		
		((ProjectileRenderer) this.renderer).createAnimation(0);
		visible=false;
	}
	
	public void setTarget(Vector3 target){
		setVisible();
		this.target=target;
		sprite.setOrigin(0+sprite.getWidth()/2, 0+sprite.getHeight()/2);
		sprite.setRotation((float)Math.toDegrees(Math.atan((position.y-target.y)/(position.x-target.x)))+90);
		speed=0.01f;
	}
	
	@Override
	public void update() {
		if(visible){
			
			speed+=Gdx.graphics.getDeltaTime()/10;
			position.lerp(target,speed);
			if(position.dst(target)<1){
				((ProjectileRenderer) renderer).animateExplosion(position);
				setHidden();
			}
			controller.setPosition(position);
		}
		
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller=(ProjectileController)controller;
		this.controller.setObject(this);
		
	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=(ProjectileRenderer)renderer;
		this.renderer.setObject(this);
		
	}

}
