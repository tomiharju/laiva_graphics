package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import ObjectControllers.ObjectController;
import ObjectControllers.ProjectileController;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.ProjectileRenderer;
import ObjectRenderers.WeaponRenderer;

public class ProjectileObject extends ModelObject{

	private Vector3 target;
	private float speed;
	
	public ProjectileObject(ObjectController controller, ObjectRenderer renderer,int weaponType){
		setController(controller);
		setRenderer(renderer);
		
		position 		= controller.pollPosition();
		bounds 			= controller.pollBounds();
		
		sprite 			= new Sprite(new Texture(Gdx.files.internal("data/weapons/w2.png")));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x-bounds.width/2, position.y-bounds.height/2);
		
		WorldObject.objects.add(this);
		
		((ProjectileRenderer) renderer).createAnimation(0);
		setVisible();
		this.renderer.addGraphics(sprite);
		
	}
	
	public void setTarget(Vector3 target){
		this.target=target;
		sprite.setRotation((float) Math.atan((position.x-target.x)/(position.y-target.y)));
		speed=0.05f;
	}
	
	@Override
	public void update() {
		if(visible){
			speed+=Gdx.graphics.getDeltaTime();
			position.lerp(target,0.01f);
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
